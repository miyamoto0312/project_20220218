package com.example.practice.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import com.example.practice.bean.CircleDataBean


class CirqueStatisticalView(context: Context?, @Nullable attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {

    private var total = 0
    private var items: List<CircleDataBean>? = null
    private val textColor = 0xFF000000

    //-------------ビュー関連-------------
    //ビューの幅と高
    private var mHeight = 0
    private var mWidth = 0
    //円の直径
    private val mRadius = 600f

    //円の太さ
    private val mStrokeWidth = 80f

    //文字サイズ
    private val textSize = 40
    //-------------絵筆に関連-------------
    //円環の絵筆
    private var cyclePaint: Paint? = null

    //文字の絵筆
    private var textPaint: Paint? = null

    //パステルカラー
    private var labelPaint: Paint? = null

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context?) : this(context, null) {}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate(mWidth / 2 - mRadius / 2, mHeight / 2 - mRadius / 2)
        //絵筆初期化
        initPaint()
        //円環を描く
        drawCycle(canvas)
        //文字とパステルカラーを描く
        drawTextAndLabel(canvas)

    }
    private fun initPaint() {
        //ボーダーパステルカラー
        cyclePaint = Paint()
        cyclePaint!!.isAntiAlias = true
        cyclePaint!!.style = Paint.Style.STROKE
        cyclePaint!!.strokeWidth = mStrokeWidth
        //文字の絵筆
        textPaint = Paint()
        textPaint!!.isAntiAlias = true
        textPaint!!.color = textColor.toInt()
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.strokeWidth = 1f
        textPaint!!.textSize = textSize.toFloat()
        //パステルカラー
        labelPaint = Paint()
        labelPaint!!.isAntiAlias = true
        labelPaint!!.style = Paint.Style.FILL
        labelPaint!!.strokeWidth = 2F
    }
    /**
     * 円環を描く
     */
    private fun drawCycle(canvas: Canvas) {
        var startPercent = 270f //円弧の始点
        var sweepPercent = 0f
        for (item in items!!) {
            cyclePaint!!.color = item.color
            startPercent += sweepPercent
            //円を占める角度をスケールで計算する
            sweepPercent = (item.value * 360 / 100).toFloat()
            canvas.drawArc(
                RectF(0F, 0F, mRadius, mRadius), startPercent, sweepPercent, false,
                cyclePaint!!
            )
        }
        //データが100未満の場合は、残りの部分を描きます
        if(total < 100){
            cyclePaint!!.color = 0xFF888888.toInt()
            startPercent += sweepPercent
            sweepPercent = ((101-total) * 360 / 100).toFloat()
            canvas.drawArc(
                RectF(0F, 0F, mRadius, mRadius), startPercent, sweepPercent, false,
                cyclePaint!!
            )
        }
    }
    /**
     * 円環の底の文字&パステルカラーを描く
     */
    private fun drawTextAndLabel(canvas: Canvas) {
        var start = 0
        var totalWidth = getTotalWidth( items!!)//文字の全長
        var textWidth = 0f//毎回描く文字の幅
        var rectWidth = 0f//毎回描く四角の幅
        var width = 0f //四角いの幅+文字の幅
        val point = mRadius/2-totalWidth/2-40 //文字描画の開始点
        var leftP = 0f //四角の左点
        var rightP = 0f //四角の右点
        for (item in items!!)  {
            start++
            //四角パステルカラーを描く
            labelPaint!!.color = item.color
            leftP = point+width+30*start
            rightP = leftP+20
            val rect = RectF(leftP, (mRadius+mStrokeWidth), rightP,  (mRadius+mStrokeWidth+20f))
            canvas.drawRect(rect, labelPaint!!)
            //文字を描く
            item.contentText
                ?.let { canvas.drawText(it, ( rightP+10),  mRadius+mStrokeWidth+20f, textPaint!!) }
            //四角の幅と文字の幅を計算
            rectWidth += 20
            textWidth += textPaint!!.measureText(item.contentText)
            width = rectWidth + textWidth + 10
        }
        if(total < 100){
            start++
            leftP = point+width+30*start
            rightP = leftP+20
            canvas.drawText("その他", ( rightP+10f),  mRadius+mStrokeWidth+20f, textPaint!!)
            labelPaint!!.color = 0xFF888888.toInt()
            val rect = RectF(leftP, (mRadius+mStrokeWidth), rightP,  (mRadius+mStrokeWidth+20f))
            canvas.drawRect(rect, labelPaint!!)
        }
    }
    /**
     * 文字の長さの計算
     */
    private fun getTotalWidth(items: List<CircleDataBean>): Float{
        var totalWidth = 0f
        for (item in items) {
            totalWidth += textPaint!!.measureText(item.contentText)+40
        }
        totalWidth += textPaint!!.measureText("その他")+40
        return totalWidth
    }
    /**
     * 円を描くデーターを設定
     *
     */
    fun setItems(items: List<CircleDataBean>) {
        this.items = items
        total = 0
        for (item in items) {
            total += item.value
        }
        invalidate()
    }

}
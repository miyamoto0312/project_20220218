package com.example.practice.utils

import android.graphics.Canvas

import android.graphics.Bitmap
import android.graphics.Color

import com.google.zxing.WriterException

import com.google.zxing.BarcodeFormat

import com.google.zxing.qrcode.QRCodeWriter

import com.google.zxing.common.BitMatrix

import com.google.zxing.EncodeHintType

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.io.FileOutputStream

import java.util.HashMap

class QRCodeUtil {
    fun createQRImage(
        content: String?, widthPix: Int, heightPix: Int,
        logoBm: Bitmap?,filePath:String
    ): Boolean {
        try {
            if (content == null || "" == content) {
                return false
            }

            //パラメーターを配置する
            val hints: MutableMap<EncodeHintType, Any?> = HashMap()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            //QRCodeフォールトトレランスレベル
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            //空白の余白の幅を設定
            hints[EncodeHintType.MARGIN] = 1 //default is 4

            //画像データ変換、マトリクス変換を使用
            var bitMatrix: BitMatrix? = null
            try {
                bitMatrix = QRCodeWriter().encode(
                    content, BarcodeFormat.QR_CODE, widthPix,
                    heightPix, hints
                )
            } catch (e: WriterException) {
                e.printStackTrace()
            }
            val pixels = IntArray(widthPix * heightPix)
            // ここではQRコードのアルゴリズムに従って、QRコードの画像を1つずつ生成します
            // 2つのforループはピクチャ横列スキャンの結果である
            for (y in 0 until heightPix) {
                for (x in 0 until widthPix) {
                    if (bitMatrix!![x, y]) {
                        pixels[y * widthPix + x] = Color.BLACK
                    } else {
                        pixels[y * widthPix + x] = Color.WHITE
                    }
                }
            }

            // QRコード画像のフォーマットを生成し、ARGB_を使用する8888
            var bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888)
            bitmap!!.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix)
            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm)
            }

            //compressメソッドを使用してbitmapをファイルに保存してから読み込む必要があります。直接返されるbitmapには圧縮はありません。
            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100,  FileOutputStream(filePath))

        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return false
    }

    /**
     * QRコードにロゴパターンを追加
     */
    private fun addLogo(src: Bitmap?, logo: Bitmap?): Bitmap? {
        if (src == null) {
            return null
        }
        if (logo == null) {
            return src
        }

        //画像の幅を取得
        val srcWidth = src.width
        val srcHeight = src.height
        val logoWidth = logo.width
        val logoHeight = logo.height
        if (srcWidth == 0 || srcHeight == 0) {
            return null
        }
        if (logoWidth == 0 || logoHeight == 0) {
            return src
        }

        //ロゴサイズはQRコード全体のサイズの1/5です
        val scaleFactor = srcWidth * 1.0f / 5 / logoWidth
        var bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888)
        try {
            val canvas = Canvas(bitmap!!)
            canvas.drawBitmap(src, 0f, 0f, null)
            canvas.scale(
                scaleFactor,
                scaleFactor,
                (srcWidth / 2).toFloat(),
                (srcHeight / 2).toFloat()
            )
            canvas.drawBitmap(
                logo,
                ((srcWidth - logoWidth) / 2).toFloat(),
                ((srcHeight - logoHeight) / 2).toFloat(),
                null
            )
            canvas.save()
            canvas.restore()
        } catch (e: Exception) {
            bitmap = null
            e.stackTrace
        }
        return bitmap
    }

}
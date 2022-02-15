package com.example.practice.module

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.R
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class ScanQRActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(R.layout.activity_scan_qr)

        //スキャンボタンのクリックイベントを設定
            val qrScan = IntentIntegrator(this)
            qrScan.setOrientationLocked(false)
            qrScan.setPrompt("QRコードを認識してください。")
            qrScan.initiateScan()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                finish()
            } else {

                val intent=Intent()
                intent.putExtra("qrdata",result.contents)
                setResult(Activity.RESULT_OK,intent)
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                finish()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
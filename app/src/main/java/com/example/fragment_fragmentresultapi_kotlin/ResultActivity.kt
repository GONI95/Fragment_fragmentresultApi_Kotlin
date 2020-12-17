package com.example.fragment_fragmentresultapi_kotlin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.os.bundleOf

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // 버튼 클릭 시 intent에 키값이 data인 데이터를 담음
        findViewById<Button>(R.id.result_button).setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "나는 서브액티비티다")
            intent.putExtra("data2", "나는 서브액티비티다2")
            intent.putExtra("data3", "나는 서브액티비티다3")
            intent.putExtra("data4", "나는 서브액티비티다4")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }



        findViewById<Button>(R.id.camera_button).setOnClickListener {
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}
package com.example.audiocapture

import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var etServerUrl: EditText
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var tvStatus: TextView

    private var mediaProjectionManager: MediaProjectionManager? = null
    private val REQUEST_CODE_SCREEN_CAPTURE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etServerUrl = findViewById(R.id.etServerUrl)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)
        tvStatus = findViewById(R.id.tvStatus)

        mediaProjectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager

        btnStart.setOnClickListener {
            val url = etServerUrl.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "请输入 WebSocket 地址", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // 请求屏幕录制权限（MediaProjection）
            val intent = mediaProjectionManager?.createScreenCaptureIntent()
            startActivityForResult(intent, REQUEST_CODE_SCREEN_CAPTURE)
        }

        btnStop.setOnClickListener {
            stopCapture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SCREEN_CAPTURE && resultCode == RESULT_OK && data != null) {
            val serverUrl = etServerUrl.text.toString().trim()
            startCapture(resultCode, data, serverUrl)
        } else {
            Toast.makeText(this, "用户未授权", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCapture(resultCode: Int, data: Intent, serverUrl: String) {
        val serviceIntent = Intent(this, RecordingService::class.java).apply {
            putExtra("resultCode", resultCode)
            putExtra("data", data)
            putExtra("serverUrl", serverUrl)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
        updateUI(true)
        tvStatus.text = "状态：正在捕获音频，发送至 $serverUrl"
    }

    private fun stopCapture() {
        val serviceIntent = Intent(this, RecordingService::class.java)
        stopService(serviceIntent)
        updateUI(false)
        tvStatus.text = "状态：已停止"
    }

    private fun updateUI(isCapturing: Boolean) {
        btnStart.isEnabled = !isCapturing
        btnStop.isEnabled = isCapturing
        etServerUrl.isEnabled = !isCapturing
    }

    override fun onDestroy() {
        super.onDestroy()
        stopCapture()
    }
}

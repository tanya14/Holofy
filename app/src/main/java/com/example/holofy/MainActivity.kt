package com.example.holofy

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var videoView: VideoView? = null
    private var arrayList: ArrayList<String> = ArrayList()
    private var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videoView = findViewById(R.id.videoView)
        intent?.getStringExtra("URL")?.let { arrayList.add(it) }

        val mediaController = MediaController(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        mediaController.setAnchorView(videoView)
        videoView?.setMediaController(mediaController)
        videoView?.setVideoURI(Uri.parse(arrayList[index]))
        videoView?.requestFocus()
        videoView?.start()

        videoView?.setOnCompletionListener { mp ->
            Toast.makeText(applicationContext, "Video over", Toast.LENGTH_SHORT).show()
            mp.release()
        }
        videoView?.setOnErrorListener { _, what, extra ->
            Log.d("API123", "What $what extra $extra")
            false
        }
    }
}

interface ClickedVideo {
    fun clickedVideoItem(videoPath: String)
}

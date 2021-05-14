package com.example.holofy

import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_video_list.*

class VideoListActivity : AppCompatActivity() {
    private lateinit var videoListAdapter: VideoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        setUpRecycleView()
        setAdapter()
    }

    private fun setUpRecycleView() {
        videoListRV?.setHasFixedSize(true)
        videoListRV?.layoutManager = LinearLayoutManager(this)
        videoListRV?.itemAnimator = DefaultItemAnimator()
        videoListAdapter = VideoListAdapter()
    }

    private fun setAdapter() {
        val mediaController = MediaController(this)
        val video1 = Video()
        video1.sources?.add("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
        val video2 = Video()
        video2.sources?.add("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4")
        val video3 = Video()
        video3.sources?.add("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
        val video4 = Video()
        video4.sources?.add("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4")
        val video5 = Video()
        video5.sources?.add("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerFun.mp4")
        val video6 = Video()
        video6.sources?.add("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4")

        val videoList: ArrayList<Video> = ArrayList()
        videoList.add(video1)
        videoList.add(video2)
        videoList.add(video3)
        videoList.add(video4)
        videoList.add(video5)
        videoList.add(video6)

        videoList.let { videoListAdapter.addData(it, this, mediaController) }
        videoListRV?.adapter = videoListAdapter
    }


}
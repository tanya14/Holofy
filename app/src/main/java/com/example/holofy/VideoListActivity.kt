package com.example.holofy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_video_list.*

class VideoListActivity : AppCompatActivity() {
    private lateinit var videoListAdapter: VideoListAdapter
    private var previousView: View? = null
    private var currentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)
        setUpRecycleView()
        setAdapter()
        setOnScrollListener()
    }

    private fun setUpRecycleView() {
        videoListRV?.setHasFixedSize(true)
        videoListRV?.layoutManager = LinearLayoutManager(this)
        videoListRV?.itemAnimator = DefaultItemAnimator()
        videoListAdapter = VideoListAdapter(object: ClickedVideo {
            override fun clickedVideoItem(videoPath: String) {
                val intent = Intent(this@VideoListActivity, MainActivity::class.java)
                intent.putExtra("URL", videoPath)
                startActivity(intent)
            }

        })
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

    private fun setOnScrollListener() {
        videoListRV?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                //super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    val positionView =
                        ((videoListRV?.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() +
                                (videoListRV?.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()) / 2
                    Log.i("ChangeT VISIBLE: ", positionView.toString())
                    if (positionView >= 0) {
                        if (previousView != null) {
                            //Stop the previous video playback after new scroll
                            val videoView =
                                (previousView as android.widget.VideoView).findViewById<android.widget.VideoView>(R.id.videoViewItem)
                            videoView.stopPlayback()
                        }

                        currentView = (videoListRV?.layoutManager as LinearLayoutManager).findViewByPosition(positionView)
                        val videoView = (currentView as android.widget.VideoView).findViewById<android.widget.VideoView>(R.id.videoViewItem)
                        videoView?.start()
                        previousView = currentView
                    }
                }
            }
        })
    }

}
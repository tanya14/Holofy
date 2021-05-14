package com.example.holofy

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.example.holofy.VideoListAdapter.DataViewHolder
import kotlinx.android.synthetic.main.video_list_item.view.*


class VideoListAdapter : RecyclerView.Adapter<DataViewHolder>() {

    private var mainVideoList: MutableList<Video>? = mutableListOf()
    private var context: Context? = null
    private var mediaController: MediaController? = null
    private var selectedMedicinesList: MutableList<Video>? = mutableListOf()
    private var count = 0

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(positionItem: Video?) {
            positionItem?.sources?.get(0)?.let { itemView.videoViewItem.setVideoPath(it) }
            (context as VideoListActivity).requestedOrientation =
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            mediaController?.setAnchorView(itemView.videoViewItem)
            //itemView.videoViewItem?.setMediaController(mediaController)
            //itemView.videoViewItem?.requestFocus()
            itemView.videoViewItem?.isSoundEffectsEnabled = false

            if (count++ == 0)
            itemView.videoViewItem?.start()
            if (itemView.videoViewItem?.isFocused == true) {
                itemView.videoViewItem?.start()
            } else {
                itemView.videoViewItem?.stopPlayback()
            }

            itemView.videoViewItem?.setOnCompletionListener { mp ->
                mp.release()
            }
            itemView.videoViewItem?.setOnErrorListener { _, what, extra ->
                Log.d("API123", "What $what extra $extra")
                false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.video_list_item, parent,
                false
            )
        )

    override fun getItemCount(): Int = mainVideoList?.size ?: 0

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(mainVideoList?.get(position))

    fun addData(list: MutableList<Video>, context: Context, mediaController: MediaController) {
        mainVideoList?.addAll(list)
        this.context = context
        this.mediaController = mediaController
    }

    fun getSelectedList(): MutableList<Video>? {
        return selectedMedicinesList
    }
}




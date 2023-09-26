package com.example.testapp

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class VideoDialog(context: Context, private val videoUrl: String) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.dialog_video)

        val videoView = findViewById<VideoView>(R.id.videoView)

        val mediaController = MediaController(
            context
        )
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        val videoUri = Uri.parse(videoUrl)
        videoView.setVideoURI(videoUri)

        videoView.start()
    }
}

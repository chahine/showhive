/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.chahinem.showhive

import android.os.Bundle
import android.support.v17.leanback.app.VideoSupportFragment
import android.support.v17.leanback.app.VideoSupportFragmentGlueHost
import android.support.v17.leanback.media.MediaPlayerGlue
import android.support.v17.leanback.media.PlaybackGlue

/** Handles video playback with media controls. */
class PlaybackVideoFragment : VideoSupportFragment() {

  private lateinit var mTransportControlGlue: MediaPlayerGlue

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val (_, title, description, _, _, videoUrl) =
        activity?.intent?.getSerializableExtra(DetailsActivity.MOVIE) as Movie

    val glueHost = VideoSupportFragmentGlueHost(this@PlaybackVideoFragment)
    mTransportControlGlue = MediaPlayerGlue(activity)
    mTransportControlGlue.setMode(MediaPlayerGlue.NO_REPEAT)
    mTransportControlGlue.host = glueHost
    mTransportControlGlue.addPlayerCallback(object : PlaybackGlue.PlayerCallback() {
      override fun onPreparedStateChanged(glue: PlaybackGlue?) {
        glue?.let {
          if (it.isPrepared) {
            it.play()
          }
        }
      }
    })
    mTransportControlGlue.setTitle(title)
    mTransportControlGlue.setArtist(description)
    mTransportControlGlue.setVideoUrl(videoUrl)
  }

  override fun onPause() {
    super.onPause()
    mTransportControlGlue.pause()
  }
}
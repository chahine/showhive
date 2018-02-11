package com.chahinem.showhive.auth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_trakt_oauth.content

class TraktOAuthActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_trakt_oauth)

    content.text = intent.data.toString()
  }
}

package com.example.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_loading_screen.*

class LoadingScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)

        iv_note.alpha = 0f
        iv_note.animate().setDuration(4000).alpha(1f).withEndAction{
            val acc = Intent(this, MainActivity::class.java)
            startActivity(acc)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}
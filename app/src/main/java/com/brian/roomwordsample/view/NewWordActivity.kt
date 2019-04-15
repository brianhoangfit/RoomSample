package com.brian.roomwordsample.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.RestrictTo
import com.brian.roomwordsample.R
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_REPLY : String = "com.brian.roomwordssample.extra_reply"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        button_save.setOnClickListener {

            val replyIntent = Intent()
            if (TextUtils.isEmpty(edit_word.text.toString())) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = edit_word.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }
}

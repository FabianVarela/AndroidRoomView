package com.developer.fabian.phonebookandroid.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.developer.fabian.phonebookandroid.R

class NewContactActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_REPLY: String = "com.developer.fabian.phonebookandroid.REPLY"
    }

    private lateinit var mEditWordView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_new_contact)
        mEditWordView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)

        button.setOnClickListener {
            val replyIntent = Intent()

            if (TextUtils.isEmpty(mEditWordView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = mEditWordView.text.toString()

                replyIntent.putExtra(EXTRA_REPLY, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }

            finish()
        }
    }

}

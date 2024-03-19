package com.example.savingstate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFS_NAME = "AppPrefs"
        const val IMAGE_KEY = "image"
        const val TEXT_KEY = "text"
    }

    private val images = listOf(R.drawable.castle, R.drawable.law, R.drawable.marsh, R.drawable.cds)
    private val texts = listOf(R.string.castle, R.string.law, R.string.marsh, R.string.cds)

    private lateinit var editText: EditText
    private lateinit var imageView: ImageView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.button)

        val sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedImage = sharedPrefs.getInt(IMAGE_KEY, R.mipmap.ic_launcher)
        val savedText = sharedPrefs.getInt(TEXT_KEY, R.string.def) ?: R.string.def

        imageView.setImageResource(savedImage)
        editText.setText(savedText)

        button.setOnClickListener {
            val randomImage = images.random()
            val randomText = texts.random()

            imageView.setImageResource(randomImage)
            editText.setText(randomText)

            sharedPrefs.edit().apply {
                putInt(IMAGE_KEY, randomImage)
                putInt(TEXT_KEY, randomText)
                apply()
            }
        }
    }
}
package com.example.lab6

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submitBtn(view: View) {
        val inputTextVar = findViewById<EditText>(R.id.inputText)
        val input = inputTextVar.text
        val outputTextVar = findViewById<TextView>(R.id.outputText)
        val image = findViewById<ImageView>(R.id.image)

        hideKeyboard()



        when (input.toString().uppercase()) {
            "CHRISTMAS" -> {
                outputTextVar.setText("Wow! " + input + " is my favorite holiday too!")
                image.setImageResource(R.drawable.santa)

            }
            "HALLOWEEN" -> {
                outputTextVar.setText("Spooky! " + input + " is the scariest holiday of the year!")
                image.setImageResource(R.drawable.ghost)
            }
            "EASTER" -> {
                outputTextVar.setText("The best part of " + input + " is a good old egg hunt!")
                image.setImageResource(R.drawable.bunny)
            }
            "THANKSGIVING" -> {
                outputTextVar.setText("You must like the feast on " + input + "! I sure do.")
                image.setImageResource(R.drawable.turkey)
            }
            else -> {
                outputTextVar.setText("I have never heard of that holiday")
                image.setImageResource(0)
            }


        }
    }
}

//https://dev.to/rohitjakhar/hide-keyboard-in-android-using-kotlin-in-20-second-18gp

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
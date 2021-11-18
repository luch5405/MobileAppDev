package com.example.gametest_movement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var playerAv: ImageView
    lateinit var coords: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        playerAv = findViewById(R.id.playerAv)
        coords = findViewById(R.id.playCoord)
    }

    private var x: Float = 0.0F
    private var y: Float = 0.0F

    fun leftFun(view: android.view.View) {
        x -= 100
        coords.text = "X:$x Y:$y"
        playerAv.translationX = x
        playerAv.rotationY = 180F
    }
    fun downFun(view: android.view.View) {
        y += 100
        coords.text = "X:$x Y:$y"
        playerAv.scaleX += 0.05F
        playerAv.scaleY += 0.05F

        playerAv.translationY = y
        playerAv.rotationY = 0F


    }
    fun upFun(view: android.view.View) {
        y -= 100
        coords.text = "X:$x Y:$y"
        playerAv.scaleX -= 0.05F
        playerAv.scaleY -= 0.05F

        playerAv.translationY = y
        playerAv.rotationY = 0F


    }
    fun rightFun(view: android.view.View) {
        x += 100
        coords.text = "X:$x Y:$y"
        playerAv.translationX = x
        playerAv.rotationY = 0F
    }
}
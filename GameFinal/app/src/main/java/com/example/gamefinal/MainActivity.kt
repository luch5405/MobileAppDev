package com.example.gamefinal

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.TranslateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var up: ImageButton
    private lateinit var down: ImageButton
    private lateinit var left: ImageButton
    private lateinit var right: ImageButton
    private lateinit var player: ImageView
    private var x = 0.0F
    private var y = 0.0F
    private lateinit var coords: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        up = findViewById(R.id.upBtn)
        down = findViewById(R.id.down)
        left = findViewById(R.id.left)
        right = findViewById(R.id.right)
        player = findViewById(R.id.player)
        coords = findViewById(R.id.coords)

        up.setOnClickListener {
            Log.i("up", "up")
            player.rotationY = 0F
            y -= 20
            for(i in 1..10){
                ObjectAnimator.ofFloat(player,"translationY", y).apply{
                    duration = 1000
                    start()
                }
            }
            coords.text = "X: $x Y: $y"
        }
        down.setOnClickListener {
            Log.i("down", "down")
            player.rotationY = 0F
            y += 20
            for(i in 1..10){
                ObjectAnimator.ofFloat(player,"translationY", y).apply{
                    duration = 1000
                    start()
                }
            }
            coords.text = "X: $x Y: $y"
        }
        left.setOnClickListener {
            Log.i("left", "left")
            x -= 20
            player.rotationY = 180F
            for(i in 1..10){
                ObjectAnimator.ofFloat(player,"translationX", x).apply{
                    duration = 1000
                    start()
                }
            }
            coords.text = "X: $x Y: $y"
        }
        right.setOnClickListener {
            Log.i("right", "right")
            player.rotationY = 0F
            x += 20
            for(i in 1..10){
                ObjectAnimator.ofFloat(player,"translationX", x).apply{
                    duration = 1000
                    start()
                }
            }
            coords.text = "X: $x Y: $y"
        }

    }

    fun checkPillar(){
        if(x.equals(0.0) && y.equals(-20.0)){
            //pillar 1
        }

        if(x.equals(20.0) && y.equals(-320.0)){
            //pillar 2
        }
    }
}
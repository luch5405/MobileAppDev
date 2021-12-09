package com.example.gamefinal

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import kotlinx.coroutines.delay


class MainActivity : AppCompatActivity() {
    private lateinit var up: ImageButton
    private lateinit var down: ImageButton
    private lateinit var left: ImageButton
    private lateinit var right: ImageButton
    private lateinit var center: ImageView
    private lateinit var player: ImageView
    private var x = 0.0F
    private var y = 0.0F

    //https://codersguidebook.com/how-to-create-an-android-app/play-sounds-music-android-app
    lateinit var music: MediaPlayer
    lateinit var pilSound: MediaPlayer
    lateinit var winSound: MediaPlayer
    lateinit var pilOff: MediaPlayer

    private lateinit var coords: TextView
    private lateinit var outputText: TextView
    private lateinit var touch: Button
    private var btnCheck = false

    private lateinit var pillar1: ImageView
    private lateinit var pillar2: ImageView
    private lateinit var pillar3: ImageView
    private lateinit var pillar4: ImageView
    private var pil1 = false
    private var pil2 = false
    private var pil3 = false
    private var pil4 = false
    private lateinit var main1: ImageView
    private lateinit var main2: ImageView
    private lateinit var main3: ImageView
    private lateinit var main4: ImageView
    private var pilNum = 0

    private lateinit var endScreen: ImageView
    private lateinit var pilwins: ImageView
    private lateinit var white: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        up = findViewById(R.id.upBtn)
        down = findViewById(R.id.downBtn)
        left = findViewById(R.id.leftBtn)
        right = findViewById(R.id.rightBtn)
        center = findViewById(R.id.center)
        player = findViewById(R.id.player)
        coords = findViewById(R.id.coords)
        outputText = findViewById(R.id.outputText)
        touch = findViewById(R.id.touch)

        music = MediaPlayer.create(this,R.raw.music)
        pilSound = MediaPlayer.create(this,R.raw.sound)
        pilOff = MediaPlayer.create(this, R.raw.piloff)
        winSound = MediaPlayer.create(this, R.raw.win)


        pillar1 = findViewById(R.id.pillar1)
        pillar2 = findViewById(R.id.pillar2)
        pillar3 = findViewById(R.id.pillar3)
        pillar4 = findViewById(R.id.pillar4)

        main1 = findViewById(R.id.main1)
        main2 = findViewById(R.id.main2)
        main3 = findViewById(R.id.main3)
        main4 = findViewById(R.id.main4)

        endScreen = findViewById(R.id.winScreen)
        pilwins = findViewById(R.id.pilwins)
        white = findViewById(R.id.white)


        textOut("Where am I? How did I get here? I should check out this island ans see if I can find anything...", btnCheck)
        music.isLooping = true
        music.start()


        up.setOnClickListener {
            Log.i("up", "up")
            player.rotationY = 0F
            y -= 20
            for(i in 1..10){
                //https://developer.android.com/reference/android/animation/ObjectAnimator
                ObjectAnimator.ofFloat(player,"translationY", y).apply{
                    duration = 1000
                    start()
                }
            }
            coords.text = "X: $x Y: $y"
            checkPillar()
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
            checkPillar()
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
            checkPillar()
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
            checkPillar()
        }

    }

    private fun checkPillar(){
        if((x.equals(0.0F) && y.equals(-40.0F)) || (x.equals(-20.0F) && y.equals(-40.0F))){
            //pillar 1
                Log.i("pillar 1", "pillar 1")

            btnCheck = true
            textOut("What is this strange pillar? I wonder what happens when I touch it.", btnCheck)

            touch.setOnClickListener {
                pil1 = true
                pillar()
            }
        }

        else if((x.equals(20.0F) && y.equals(-320.0F)) || (x.equals(60.0F) && y.equals(-320.0F))){
            //pillar 4
            Log.i("pillar 4", "pillar 4")

            btnCheck = true
            textOut("Another pillar, I think I see a pattern", btnCheck)

            touch.setOnClickListener {
                pil4 = true
                pillar()
            }
        }

        else if((x.equals(440.0F) && y.equals(-240.0F)) || (x.equals(480.0F) && y.equals(-240.0F))){
            //pillar 3
            Log.i("pillar 3", "pillar 3")

            btnCheck = true
            textOut("There seems to be 4 of them on the island", btnCheck)

            touch.setOnClickListener {
                pil3 = true
                pillar()
            }
        }

        else if((x.equals(320.0F) && y.equals(140.0F)) || (x.equals(280.0F) && y.equals(140.0F))){
            //pillar 2
            Log.i("pillar 2", "pillar 2")

            btnCheck = true
            textOut("Maybe these will help escape this place", btnCheck)

            touch.setOnClickListener {
                pil2 = true
                pillar()
            }
        }
        else{
            btnCheck = false
            ObjectAnimator.ofFloat(outputText,"alpha",0F).apply{
                duration = 1000
                start()
            }
            ObjectAnimator.ofFloat(touch,"alpha",0F).apply{
                duration = 1000
                start()
            }
        }
    }

    private fun textOut(input: String, check: Boolean){
        outputText.text = input

        val fadeIn = ObjectAnimator.ofFloat(outputText, "alpha", 1.0F)
        fadeIn.duration = 1000

        val fadeOut = ObjectAnimator.ofFloat(outputText, "alpha", 0.0F)
        fadeOut.duration = 1000
        fadeOut.startDelay = 3000

        val btnFadeIn = ObjectAnimator.ofFloat(touch, "alpha", 1.0F)
        btnFadeIn.duration = 1000



        AnimatorSet().apply {
            if(!check){
                play(fadeIn).before(fadeOut)
            }else{
                play(btnFadeIn).with(fadeIn)
            }
            start()
        }
    }

    private fun pillar(){
        if(pilNum == 0){
            if (pil1){
                pilOn("pillar1")
                pilNum++
                return
            }else{
                pilReset()
                return
            }
        }
        if(pilNum == 1){
            if (pil4){
                pilOn("pillar4")
                pilNum++
                return
            }else{
                pilReset()
                return
            }
        }
        if(pilNum == 2){
            if (pil2){
                pilOn("pillar2")
                pilNum++
                return
            }else{
                pilReset()
                return
            }
        }
        if(pilNum == 3){
            if (pil3){
                pilOn("pillar3")
                pilNum++


                //endgame
                endGame()
            }else{
                pilReset()
                return
            }
        }

    }

    private fun pilOn(pil: String){
        if (pil ==  "pillar1"){
            val pil1An = ObjectAnimator.ofFloat(pillar1, "alpha", 1F)
            pil1An.duration = 1000
            pil1An.start()

            val main1An = ObjectAnimator.ofFloat(main1, "alpha", 1F)
            main1An.duration = 1000
            main1An.start()

            pilSound.start()
        }
        if(pil == "pillar4"){
            val pil4An = ObjectAnimator.ofFloat(pillar4, "alpha", 1F)
            pil4An.duration = 1000
            pil4An.start()

            val main3An = ObjectAnimator.ofFloat(main3, "alpha", 10F)
            main3An.duration = 1000
            main3An.start()

            pilSound.start()
        }

        if(pil == "pillar2"){
            val pil2An = ObjectAnimator.ofFloat(pillar2, "alpha", 1F)
            pil2An.duration = 1000
            pil2An.start()

            val main2An = ObjectAnimator.ofFloat(main2, "alpha", 1F)
            main2An.duration = 1000
            main2An.start()

            pilSound.start()
        }

        if(pil == "pillar3"){
            val pil3An = ObjectAnimator.ofFloat(pillar3, "alpha", 1F)
            pil3An.duration = 1000
            pil3An.start()

            val main4An = ObjectAnimator.ofFloat(main4, "alpha", 1F)
            main4An.duration = 1000
            main4An.start()

            pilSound.start()
        }

    }

    private fun pilReset(){
        val pil1An = ObjectAnimator.ofFloat(pillar1, "alpha", 0F)
        pil1An.duration = 1000
        pil1An.start()

        val pil2An = ObjectAnimator.ofFloat(pillar2, "alpha", 0F)
        pil2An.duration = 1000
        pil2An.start()

        val pil3An = ObjectAnimator.ofFloat(pillar3, "alpha", 0F)
        pil3An.duration = 1000
        pil3An.start()

        val pil4An = ObjectAnimator.ofFloat(pillar4, "alpha", 0F)
        pil4An.duration = 1000
        pil4An.start()

        val main1An = ObjectAnimator.ofFloat(main1, "alpha", 0F)
        main1An.duration = 1000
        main1An.start()

        val main2An = ObjectAnimator.ofFloat(main2, "alpha", 0F)
        main2An.duration = 1000
        main2An.start()

        val main3An = ObjectAnimator.ofFloat(main3, "alpha", 0F)
        main3An.duration = 1000
        main3An.start()

        val main4An = ObjectAnimator.ofFloat(main4, "alpha", 0F)
        main4An.duration = 1000
        main4An.start()


        pil1 = false
        pil2 = false
        pil3 = false
        pil4 = false
        pilNum = 0
        pilOff.start()
    }

        fun endGame(){
        music.pause()
        winSound.start()

        //https://www.tutorialkart.com/kotlin-android/draw-shape-to-canvas-example/
//        shapeDrawable = ShapeDrawable(RectShape())
//        shapeDrawable.setBounds( left, top, right, bottom)
//        shapeDrawable.getPaint().setColor(Color.parseColor("#009944"))
//        shapeDrawable.draw(canvas)
        val fin = ObjectAnimator.ofFloat(white, "alpha", 1F)

        endScreen.translationZ = 100F
        player.translationZ = 200F
        pilwins.translationZ = 300F
        white.translationZ = 400F

        right.translationZ = 350F
        left.translationZ = 350F
        center.translationZ = 350F

        fin.startDelay = 3000
        fin.duration = 4000
        val fout = ObjectAnimator.ofFloat(white, "alpha", 0F)
        fout.startDelay = 3000
        fout.duration = 1000

        val pil = ObjectAnimator.ofFloat(pilwins, "alpha", 1F)
        pil.startDelay = 7000
        pil.duration = 0
        val back = ObjectAnimator.ofFloat(endScreen, "alpha", 1F)
        back.startDelay = 7000
        back.duration = 0

        AnimatorSet().apply{
            play(fin).with(pil).with(back)
            play(fout).after(fin)
            start()
        }
//        delay(7000)
        player.translationX = 220F
        player.translationY = -230F
        x = 220F
        y = -230f

        textOut("Another strange world. Looks like I'll be here a while",false)


    }
}
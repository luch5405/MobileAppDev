package com.example.lab7

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {

    lateinit var layoutRoot: ConstraintLayout
    lateinit var radGroup: RadioGroup
    lateinit var check1: CheckBox
    lateinit var check2: CheckBox
    lateinit var check3: CheckBox
    lateinit var check4: CheckBox
    lateinit var size: Spinner
    lateinit var genderSwitch: SwitchMaterial
    lateinit var outputText: TextView
    private var nameID = -1

    lateinit var sitterOutputText: TextView
    lateinit var sitterBtn: Button
    private var myPuppyCare = PuppyCare()
    private var selectedPosition = 0
    private val REQUEST_CODE = 1




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutRoot = findViewById<ConstraintLayout>(R.id.layout)
        radGroup = findViewById(R.id.radioGroup)
        check1 = findViewById(R.id.checkBox)
        check2 = findViewById(R.id.checkBox2)
        check3 = findViewById(R.id.checkBox3)
        check4 = findViewById(R.id.checkBox4)
        size = findViewById(R.id.spinner)
        genderSwitch = findViewById(R.id.genderSwitch)
        outputText = findViewById(R.id.outputText)
        sitterOutputText= findViewById(R.id.sitterOuputText)
        sitterBtn = findViewById(R.id.findSitter)

        genderSwitch.setOnClickListener {
            if(genderSwitch.isChecked){
                genderSwitch.text = "Female"

            }else{
                genderSwitch.text = "Male"
            }
        }

        sitterBtn.setOnClickListener {
            nameID = radGroup.checkedRadioButtonId
            if(nameID == -1){
                //snackbar
                val snack = Snackbar.make(layoutRoot,"Please select the name of your puppy",Snackbar.LENGTH_LONG)
                snack.show()
            }else{
                selectedPosition = size.selectedItemPosition
                myPuppyCare.suggestPuppyCare(selectedPosition)
                Log.i("day care", myPuppyCare.name)
                Log.i("URL", myPuppyCare.url)
                Log.i("LatLng", myPuppyCare.LngLat)

                val intent = Intent(this, DaycareActivity::class.java)
                intent.putExtra("daycareName", myPuppyCare.name)
                intent.putExtra("daycareURL", myPuppyCare.url )
                intent.putExtra("daycareLngLat", myPuppyCare.LngLat )

                startActivityForResult(intent, REQUEST_CODE)

            }
        }

    }

    fun checkIn(view: android.view.View) {
        var fix = false
        var name: CharSequence = ""
        var restrictions = ""
        var gender = ""
        nameID = radGroup.checkedRadioButtonId


        if(nameID == -1){
            //snackbar
            val snack = Snackbar.make(layoutRoot,"Please select the name of your puppy",Snackbar.LENGTH_LONG)
            snack.show()
        }else{
            name = findViewById<RadioButton>(nameID).text

            if(check1.isChecked){
                restrictions += ", " + check1.text
            }
            if(check2.isChecked){
                restrictions += ", " + check2.text
            }
            if(check3.isChecked){
                fix = true
            }
            if(check4.isChecked){
                restrictions += ", " + check4.text
            }

            //spinner
            val sizeText = size.selectedItem

            //switch
            gender = if(genderSwitch.isChecked){
                "Female"
            }else{
                "Male"
            }

            if(!fix){
                outputText.text = "I'm sorry, we do not take dogs that are not fixed. $name will need to go somewhere else."
            }else{
                outputText.text = "Your $sizeText puppy, $name ($gender), is all checked in! We will be cognisant of his restrictions including $restrictions."
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == REQUEST_CODE) && (resultCode == Activity.RESULT_OK)){
            sitterOutputText.setText(data?.let{data.getStringExtra("daycareName")})
        }
    }


}
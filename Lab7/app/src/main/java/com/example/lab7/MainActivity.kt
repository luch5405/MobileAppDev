package com.example.lab7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun checkIn(view: android.view.View) {
        val layoutRoot = findViewById<ConstraintLayout>(R.id.layout)
        var fix = false

        //Radio
        val radGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val nameID = radGroup.checkedRadioButtonId
        var name: CharSequence = ""

        //Check
        val check1 = findViewById<CheckBox>(R.id.checkBox)
        val check2 = findViewById<CheckBox>(R.id.checkBox2)
        val check3 = findViewById<CheckBox>(R.id.checkBox3)
        val check4 = findViewById<CheckBox>(R.id.checkBox4)
        var restrictions = ""

        //Spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        val gender = spinner.selectedItem

        //CheckBox
        val friendSwitch = findViewById<SwitchMaterial>(R.id.friendSwitch)

        //Switch
        val friendly: String
        if(friendSwitch.isChecked){
            friendly = "We will also allow them to play with other dogs since they are friendly"
        }else{
            friendly = "We will also limit their exposure with other dogs since they are not friendly"
        }


        if(nameID == -1){
            val snack = Snackbar.make(layoutRoot,"Please select the name of your puppy",Snackbar.LENGTH_LONG)
            snack.show()
        }else{
            name = findViewById<RadioButton>(nameID).text
            val outputText = findViewById<TextView>(R.id.outputText)

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

            if(fix == false){
                outputText.text = "I'm sorry, we do not take dogs that are not fixed. $name will need to go somewhere else."
            }else{
                outputText.text = "Your $gender puppy, $name, is all checked in! We will be cognisant of his restrictions including $restrictions.$friendly"
            }
        }
    }
}
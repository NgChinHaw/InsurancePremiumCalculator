package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun display(){
            //if(myData.premiumAmount != 0.0)
            prem.text = "RM " + myData.premiumAmount.toString()
        }

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)
        display()


        fun getPremium(): Double{

            return when(ageSpin.selectedItemPosition){
                0 -> 60.00
                1 -> 70.00 +
                        (if(maleButt.isChecked) 50.00 else 0.0) + (if(smokeCheck.isChecked) 100.00 else 0.0)
                2 -> 90.00 +
                        (if(maleButt.isChecked) 100.00 else 0.0) + (if(smokeCheck.isChecked) 150.00 else 0.0)
                3 -> 120.00 +
                        (if(maleButt.isChecked) 150.00 else 0.0) + (if(smokeCheck.isChecked) 200.00 else 0.0)
                4 -> 150.00 +
                        (if(maleButt.isChecked) 200.00 else 0.0) + (if(smokeCheck.isChecked) 250.00 else 0.0)
                else -> 150.00 +
                        (if(maleButt.isChecked) 200.00 else 0.0) + (if(smokeCheck.isChecked) 300.00 else 0.0)
            }
        }

        val btn = findViewById<View>(R.id.calculate)

        btn.setOnClickListener() {

            myData.premiumAmount= getPremium()
            //prem.text = "RM %.2f".format(cal)
            display()

        }

        val butt2 = findViewById<View>(R.id.reset)
        butt2.setOnClickListener(){
            ageSpin.setSelection(0)

            buttGroup.clearCheck()

            smokeCheck.setChecked(false)

            myData.premiumAmount = 0.0

            display()

        }


    }
}

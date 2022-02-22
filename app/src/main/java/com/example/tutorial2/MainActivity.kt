package com.example.tutorial2

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var names = arrayOf("Affenpinscher", "Afghan hound", "African hunting")
    var bool1 = false
    var bool2 = false
    var bool3 = false
    var count = 0
    var answered = true

    var correctAns = 0
    var total = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // retrieve the image view
        val imgView1 = findViewById<ImageView>(R.id.imageView3)
        val imgView2 = findViewById<ImageView>(R.id.imageView4)
        val imgView3 = findViewById<ImageView>(R.id.imageView5)
        val text = findViewById<TextView>(R.id.textView)
        val textAns = findViewById<TextView>(R.id.textView2)

        val nextBut = findViewById<Button>(R.id.button)
        val finishBut = findViewById<Button>(R.id.button2)

        generateDogs(imgView1, imgView2, imgView3, text, textAns)

        nextBut.setOnClickListener{
            generateDogs(imgView1, imgView2, imgView3, text, textAns)
        }
        finishBut.setOnClickListener{
            val scoreIntent = Intent(this, ScoreActivity::class.java)
            scoreIntent.putExtra("correct", correctAns.toString())
            scoreIntent.putExtra("total", total.toString())
            startActivity(scoreIntent)
        }
        //setting onclick event to images
        imgView1.setOnClickListener{
            imageOnClick(0, textAns)
            imgView1.setBackgroundResource(R.drawable.image_boarder)
        }
        imgView2.setOnClickListener{
            imageOnClick(1, textAns)
        }
        imgView3.setOnClickListener{
            imageOnClick(2, textAns)
        }

    }
    private fun generateDogs(imgView1: ImageView, imgView2: ImageView, imgView3: ImageView, text: TextView, answerTxt: TextView) {
        if (answered){
            var arr = ArrayList<Int>()
            bool1 = false
            bool2 = false
            bool3 = false
            answerTxt.text = ""
            count = 0
            total++

            //adding 3 random values for number of dog breeds
            var new_number = randomVal(0,2)
            while (arr.size < 3) {
                if (new_number !in arr){
                    arr.add(new_number)
                }else
                    new_number = randomVal(0,2)
            }

            //get a random value from the array to which the correct answer will be assigned
            val correctDog = arr.random()
            text.text = "Which dog is " + names[correctDog]

            if (arr[0] == correctDog){
                bool1 = true
            }else if (arr[1] == correctDog){
                bool2 = true
            }else if (arr[2] == correctDog){
                bool3 = true
            }

            //for every index checking if it equals with values and assigning random dog image number
            //specific to that dog breed into the same array index
            for ((index, value) in arr.withIndex()) {
                if (value == 0){
                    arr[index] = randomVal(1,10)
                } else if (value == 1){
                    arr[index] = randomVal(11,20)
                }else if (value == 2){
                    arr[index] = randomVal(21,30)
                }
            }
            //assigning dog images to imageview
            randImage(imgView1, imgView2, imgView3, arr[0], arr[1], arr[2])
            answered = false
        }else{
            Toast.makeText(this, "Please choose an answer", Toast.LENGTH_LONG).show()
        }
    }

    private fun randImage(imgView1: ImageView, imgView2: ImageView, imgView3: ImageView, img1No: Int, img2No: Int, img3No: Int)
    {
        val image1 = "dog$img1No"                 //https://stackoverflow.com/questions/7941304/android-reference-things-in-r-drawable-using-variables
        val resID1 = resources.getIdentifier(image1, "drawable", "com.example.tutorial2")
        imgView1.setImageResource(resID1)

        val image2 = "dog$img2No"
        val resID2 = resources.getIdentifier(image2, "drawable", "com.example.tutorial2")
        imgView2.setImageResource(resID2)

        val image3 = "dog$img3No"
        val resID3 = resources.getIdentifier(image3, "drawable", "com.example.tutorial2")
        imgView3.setImageResource(resID3)
    }

    private fun imageOnClick(int: Int, answerTxt: TextView){
        answered = true
        if (count == 0) {
            if (int == 0 && bool1){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))

            }else if (int == 1 && bool2){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))
            }else if (int == 2 && bool3){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))
            }else{
                answerTxt.text = "WRONG"
                answerTxt.setTextColor(Color.parseColor("#FF0000"))
            }
            count = 1
        }
    }

    private fun randomVal(startVal: Int, endVal: Int): Int {
        //var gen: java.util.Random = java.util.Random()
        return (startVal..endVal).random()
    }
}
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
    //names of dogs
    var names = arrayOf("Affenpinscher", "Afghan hound", "African hunting")
    //storing values of used int values .. to generate unique image indexes
    var doneValues = ArrayList<Int>()
    //getting the count of how many times the specific dog breed was used
    var breedUsedCount = IntArray(names.size)
    var bool1 = false
    var bool2 = false
    var bool3 = false
    var count = 0
    var answered = true

    var correctAns = 0
    var total = 0;
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
            finishView()
        }
        //setting onclick event to images
        imgView1.setOnClickListener{
            imgView1.setBackgroundResource(R.drawable.image_boarder)
            imageOnClick(0, textAns, imgView1, imgView2, imgView3)
        }
        imgView2.setOnClickListener{
            imgView2.setBackgroundResource(R.drawable.image_boarder)
            imageOnClick(1, textAns, imgView1, imgView2, imgView3)
        }
        imgView3.setOnClickListener{
            imgView3.setBackgroundResource(R.drawable.image_boarder)
            imageOnClick(2, textAns, imgView1, imgView2, imgView3)
        }

    }
    private fun generateDogs(imgView1: ImageView, imgView2: ImageView, imgView3: ImageView, text: TextView, answerTxt: TextView) {
        //check if all the breads are run .. and if so finishing the program
        var finishedBreads = 0
        for (value in breedUsedCount)
        {
            if (value == 10)
                finishedBreads++
        }
        if (finishedBreads != names.size)
        {
            //only letting go next if the user has answered
            if (answered){
                //resetting all--------------------------
                imgView1.setBackgroundResource(R.drawable.image_boarder_empty)
                imgView2.setBackgroundResource(R.drawable.image_boarder_empty)
                imgView3.setBackgroundResource(R.drawable.image_boarder_empty)
                bool1 = false
                bool2 = false
                bool3 = false
                answerTxt.text = ""
                count = 0
                //----------------------------------------
                var arr = ArrayList<Int>()
                total++

                //adding 3 random values for number of dog breeds
                var new_number = randomVal(0,names.size-1)
                while (arr.size < 3) {
                    //check if new number is in the array and if the specific number dog breed has
                    // finished running 10 times ..selecting another bread
                    if (new_number !in arr && breedUsedCount[new_number] != 10){
                        arr.add(new_number)
                    }else
                        new_number = randomVal(0,names.size-1)
                }

                //get a random value from the array to which the correct answer will be assigned
                val correctDog = arr.random()
                text.text = "Which dog is " + names[correctDog]

                //if the random correct answer is equal to the index assigned number..setting true
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
                        arr[index] = valueGen(1, 10)
                        breedUsedCount[0] = breedUsedCount[0] + 1
                    } else if (value == 1){
                        arr[index] = valueGen(11, 20)
                        breedUsedCount[1] = breedUsedCount[1] + 1
                    }else if (value == 2){
                        arr[index] = valueGen(21, 30)
                        breedUsedCount[2] = breedUsedCount[2] + 1
                    }
                }
                //assigning dog images to imageview
                randImage(imgView1, imgView2, imgView3, arr[0], arr[1], arr[2])
                //resetting answered bool
                answered = false
            }else{
                Toast.makeText(this, "Please choose an answer", Toast.LENGTH_LONG).show()
            }
        }else {
            finishView()
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

    private fun imageOnClick(int: Int, answerTxt: TextView, imgView1: ImageView, imgView2: ImageView, imgView3: ImageView){
        //setting answered to true telling that user has answered
        answered = true
        //not letting user choose another answer
        if (count == 0) {
            //int values used for 3 images
            //bool checking if the selected image is the correct or not
            if (int == 0 && bool1){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))
                //setting frame to green
                imgView1.setBackgroundResource(R.drawable.image_boarder_correct)
            }else if (int == 1 && bool2){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))
                imgView2.setBackgroundResource(R.drawable.image_boarder_correct)
            }else if (int == 2 && bool3){
                correctAns++
                answerTxt.text = "CORRECT"
                answerTxt.setTextColor(Color.parseColor("#00FF00"))
                imgView3.setBackgroundResource(R.drawable.image_boarder_correct)
            }else{
                answerTxt.text = "WRONG"
                answerTxt.setTextColor(Color.parseColor("#FF0000"))
            }
            //setting count to 1 so cannot change answer
            count = 1
        }
    }
    private fun valueGen(min: Int, max: Int): Int{
        //check if the same dog image index is called or not
        var isTrue = false
        var value = randomVal(min, max)
        while (!isTrue) {
            if (value !in doneValues) {
                //add the image index to array
                doneValues.add(value)
                isTrue = true
            } else {
                value = randomVal(min, max)
            }
        }
        //return the unique value
        return value
    }

    private fun finishView(){
        val scoreIntent = Intent(this, ScoreActivity::class.java)
        scoreIntent.putExtra("correct", correctAns.toString())
        scoreIntent.putExtra("total", total.toString())
        startActivity(scoreIntent)
    }

    private fun randomVal(startVal: Int, endVal: Int): Int {
        //var gen: java.util.Random = java.util.Random()
        return (startVal..endVal).random()
    }
}
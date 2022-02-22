package com.example.tutorial2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val resultTxt = findViewById<TextView>(R.id.textView3)

        val correct =intent.getStringExtra("correct")
        var total = intent.getStringExtra("total")

        resultTxt.text = " You Got $correct Correct of $total"
    }
}
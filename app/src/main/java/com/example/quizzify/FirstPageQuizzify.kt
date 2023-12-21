package com.example.quizzify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FirstPageQuizzify : AppCompatActivity() {
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page_quizzify)
        button=findViewById(R.id.button)

        button.setOnClickListener{

            val intent= Intent(this@FirstPageQuizzify,SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
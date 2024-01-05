package com.example.quizzify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.quizzify.activities.FetchingActivity
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.database.FirebaseDatabase


class QuizGameActivity : AppCompatActivity() {

    private lateinit var etenterQuestion: TextView
    private lateinit var etOption1: TextView
    private lateinit var etOption2: TextView
    private lateinit var etOption3: TextView
    private lateinit var etOption4: TextView
    private lateinit var btnUpdate: Button
    private lateinit var etquestionId: TextView
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        initView()
        setValuesToViews()


    }


    private fun initView() {
        etenterQuestion = findViewById(R.id.EnterQuestion)
        etOption1 = findViewById(R.id.Option1)
        etOption2 = findViewById(R.id.Option2)
        etOption3 = findViewById(R.id.Option3)
        etOption4 = findViewById(R.id.Option4)


    }


    private fun setValuesToViews() {
        etenterQuestion.text = intent.getStringExtra("enterQuestion")
        etOption1.text = intent.getStringExtra("Option1")
        etOption2.text = intent.getStringExtra("Option2")
        etOption3.text = intent.getStringExtra("Option3")
        etOption4.text = intent.getStringExtra("Option4")
    }

}








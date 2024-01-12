package com.example.quizzify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.quizzify.databinding.ActivityPlayQuizBinding
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlayQuiz : AppCompatActivity() {

    companion object {
        var questionModelList: List<QuestionBankModel> = listOf()
    }

    lateinit var binding: ActivityPlayQuizBinding

    var currentQuestionIndex=0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadQuestions()

    }

    private fun loadQuestions(){
        binding.apply {


        }

    }




}
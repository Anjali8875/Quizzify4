package com.example.quizzify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.R
import com.example.quizzify.adaptor.ImageQuesAdapter
import com.example.quizzify.models.ImageQuestionModel
import com.example.quizzify.models.ScoreModel
import com.google.firebase.database.FirebaseDatabase

class ImageQuesPage : AppCompatActivity() ,ImageQuesAdapter.QuizItemClickListener{
    private lateinit var submitImg:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var quizAdapter: ImageQuesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_ques_page)
       // val quizQuestions=getQuizQuestions()
      //  quizAdapter= ImageQuesAdapter(quizQuestions,this)

        recyclerView=findViewById(R.id.imageQuesList)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=quizAdapter

        submitImg=findViewById(R.id.submitimg)




    }

    override fun onOptionClick(questionIndex: Int, optionIndex: Int) {
        val selectOption=optionIndex+1
        val correctOption=quizAdapter.ImageQuesList[questionIndex].correctimgans

        if (selectOption==correctOption){
            Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show()
        }
    }

  //  private fun getQuizQuestions():ArrayList<ImageQuestionModel>{

  //  }






}
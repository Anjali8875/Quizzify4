package com.example.quizzify.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzify.R
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class InsertionActivity : AppCompatActivity() {

    private lateinit var etenterQuestion:EditText
    private lateinit var etOption1:EditText
    private lateinit var etOption2:EditText
    private lateinit var etOption3:EditText
    private lateinit var etOption4:EditText
    private lateinit var btnsaveData: Button
    private lateinit var etquizTitle:EditText
    private lateinit var etquizNo:EditText
    private lateinit var etcorrectans:EditText
    private lateinit var ettime:EditText

    private lateinit var dbRef:DatabaseReference
    private lateinit var etquesNo:EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etenterQuestion = findViewById(R.id.enterQuestion)
        etOption1 = findViewById(R.id.editTextText5)
        etOption2 = findViewById(R.id.editTextText8)
        etOption3 = findViewById(R.id.editTextText7)
        etOption4 = findViewById(R.id.editTextText6)
        btnsaveData = findViewById(R.id.button4)
        etquizTitle = findViewById(R.id.quizTitle)
        etcorrectans=findViewById(R.id.correctans)
        ettime=findViewById(R.id.time)
        val timeLeftInMillis:Long=ettime.text.toString().toLongOrNull()?:0



        dbRef = FirebaseDatabase.getInstance().getReference("quiz")


        btnsaveData.setOnClickListener {
            saveQuestionsData()
        }


    }


    private fun saveQuestionsData(){
            val enterQuestion = etenterQuestion.text.toString()
            val Option1 = etOption1.text.toString()
            val Option2 = etOption2.text.toString()
            val Option3 = etOption3.text.toString()
            val Option4 = etOption4.text.toString()
            val savedata = btnsaveData.text.toString()
            val title = etquizTitle.text.toString()
            val correctans=etcorrectans.text.toString()
            val time=ettime.text.toString()

           // val quizNoReference=dbRef.child(quizNo)
           // val titleReference=quizNoReference.child("title").setValue(title)


        if (enterQuestion.isEmpty()){
            etenterQuestion.error="Please enter the question"

        }
        if (title.isEmpty()){
            etquizTitle.error="Please enter the title"
        }

        if (Option1.isEmpty()){
            etOption1.error="Please enter 1st option"
        }
        if (Option2.isEmpty()){
            etOption2.error="Please enter 2nd option"
        }
        if (Option3.isEmpty()){
            etOption3.error="Please enter 3rd option"
        }
        if (Option4.isEmpty()){
            etOption4.error="Please enter 4th option"
        }

        if (correctans.isEmpty()){
            etcorrectans.error="Please enter the correct answer"
        }

        val quesId=dbRef.push().key!!

        val questions=QuestionBankModel(quesId,title, enterQuestion, Option1, Option2, Option3, Option4,correctans,time)
        dbRef.child(quesId).setValue(questions)
            .addOnCompleteListener {
                Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_LONG).show()

                etenterQuestion.text.clear()
                etquizTitle.text.clear()
                etOption1.text.clear()
                etOption2.text.clear()
                etOption3.text.clear()
                etOption4.text.clear()
                ettime.text.clear()
                etcorrectans.text.clear()

            }.addOnFailureListener {err->
                  Toast.makeText(this,"Error${err.message}",Toast.LENGTH_LONG).show()

            }











    }
}
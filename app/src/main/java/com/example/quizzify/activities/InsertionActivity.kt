package com.example.quizzify.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizzify.R
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {

    private lateinit var etenterQuestion:EditText
    private lateinit var etOption1:EditText
    private lateinit var etOption2:EditText
    private lateinit var etOption3:EditText
    private lateinit var etOption4:EditText
    private lateinit var btnsaveData: Button

    private lateinit var dbRef:DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etenterQuestion=findViewById(R.id.enterQuestion)
        etOption1=findViewById(R.id.editTextText5)
        etOption2=findViewById(R.id.editTextText8)
        etOption3=findViewById(R.id.editTextText7)
        etOption4=findViewById(R.id.editTextText6)
        btnsaveData=findViewById(R.id.button4)

        dbRef=FirebaseDatabase.getInstance().getReference("Questions")

        btnsaveData.setOnClickListener{
            saveQuestions()

        }

    }

    private fun saveQuestions(){

        val enterQuestion=etenterQuestion.text.toString()
        val Option1=etOption1.text.toString()
        val Option2=etOption2.text.toString()
        val Option3=etOption3.text.toString()
        val Option4=etOption4.text.toString()
        val savedata=btnsaveData.text.toString()

        if(enterQuestion.isEmpty()){
            etenterQuestion.error="Please enter the question"
        }

        if (Option1.isEmpty())  {
            etOption1.error="Please enter the first option"

        }
        if (Option2.isEmpty()){
            etOption2.error="Please enter the second option"
        }

        if (Option3.isEmpty()){
            etOption3.error="Please enter the third option"
        }

        if (Option4.isEmpty()){
            etOption4.error="Please enter the fourth option"
        }

        val questionId=dbRef.push().key!!

        val questionBank= QuestionBankModel(questionId,enterQuestion,Option1,Option2,Option3,Option4)

        dbRef.child(questionId).setValue(questionBank)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted successfully", Toast.LENGTH_LONG).show()

                etenterQuestion.text.clear()
                etOption1.text.clear()
                etOption2.text.clear()
                etOption3.text.clear()
                etOption4.text.clear()

            }.addOnFailureListener{err->
                Toast.makeText(this,"Error${err.message}",Toast.LENGTH_LONG).show()

            }

    }
}
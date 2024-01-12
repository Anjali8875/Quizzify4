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

    private lateinit var dbRef:DatabaseReference

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
        etquizNo=findViewById(R.id.quizNo)



        dbRef = FirebaseDatabase.getInstance().reference
       // val questionId=dbRef.push().key

        btnsaveData.setOnClickListener {


            val enterQuestion = etenterQuestion.text.toString()
            val Option1 = etOption1.text.toString()
            val Option2 = etOption2.text.toString()
            val Option3 = etOption3.text.toString()
            val Option4 = etOption4.text.toString()
            val savedata = btnsaveData.text.toString()
            val title = etquizTitle.text.toString()
            val quesNo=etquizNo.text.toString()
            val quesNoReference=dbRef.child(quesNo)
            val titleReference=quesNoReference.child("title").setValue(title)
            val quesListReference=quesNoReference.child("QuestionList")



            quesListReference.child("enterQuestion").setValue(enterQuestion)
            quesListReference.child("Option1").setValue(Option1)
            quesListReference.child("Option2").setValue(Option2)
            quesListReference.child("Option3").setValue(Option3)
            quesListReference.child("Option4").setValue(Option4)


            Toast.makeText(this,"Data inserted successfully",Toast.LENGTH_SHORT).show()

            etquizTitle.text.clear()
            etOption1.text.clear()
            etOption2.text.clear()
            etOption2.text.clear()
            etOption3.text.clear()
            etOption4.text.clear()
            etenterQuestion.text.clear()
            etquizNo.text.clear()



        }


    }
}
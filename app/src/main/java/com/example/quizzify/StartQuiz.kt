package com.example.quizzify

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.activities.InsertionActivity
import com.example.quizzify.adaptor.QuestionAdaptor
import com.example.quizzify.adaptor.QuizListAdapter
import com.example.quizzify.databinding.ActivityStartQuizBinding
import com.example.quizzify.models.QuesModel
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class StartQuiz : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var quizAdaptor: QuizListAdapter
    private lateinit var databaseReference: DatabaseReference
    private lateinit var etquizNo: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.activity_start_quiz)
        recyclerView = findViewById(R.id.recycler_view)



        val intent=Intent(this@StartQuiz,InsertionActivity::class.java)
        intent.putExtra("etquizNo",etquizNo)
        etquizNo=findViewById(R.id.quizNo)
        val quesNo = etquizNo.text.toString()
        val quesNoReference = databaseReference.child(quesNo)

        databaseReference = FirebaseDatabase.getInstance().reference.child(quesNo)

        fetchQuizTitles()

        quizAdaptor = QuizListAdapter(ArrayList())
        recyclerView.adapter = quizAdaptor


    }

    private fun fetchQuizTitles() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val quizzes = mutableListOf<QuesModel>()
                for (snapshot in snapshot.children) {
                    etquizNo = findViewById(R.id.quizNo)
                    val quesNo = etquizNo.text.toString()
                    val quesNoReference = databaseReference.child(quesNo)
                    val title = snapshot.child(quesNo).getValue(String::class.java)
                    if (title != null) {
                        quizzes.add(QuesModel(title))
                    }
                }
                quizAdaptor.QuizModelList = quizzes
                quizAdaptor.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }

}

private fun Intent.putExtra(s: String, etquizNo: EditText) {

}






























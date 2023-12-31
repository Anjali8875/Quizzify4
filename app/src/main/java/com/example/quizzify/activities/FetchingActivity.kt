package com.example.quizzify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.QuizGameActivity
import com.example.quizzify.R
import com.example.quizzify.adaptor.QuestionAdaptor
import com.example.quizzify.models.QuestionBankModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FetchingActivity : AppCompatActivity() {

    private lateinit var empRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var empList: ArrayList<QuestionBankModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        empRecyclerView = findViewById(R.id.rvEmp)
        empRecyclerView.layoutManager = LinearLayoutManager(this)
         empRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        empList = arrayListOf<QuestionBankModel>()

        getQuestionsData()


    }
    private fun getQuestionsData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Questions")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(QuestionBankModel::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter = QuestionAdaptor(empList)
                    empRecyclerView.adapter = mAdapter



                    mAdapter.setOnClickListener(object :QuestionAdaptor.onItemClickListener{




                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@FetchingActivity, QuizGameActivity::class.java)

                            intent.putExtra("questionId",empList[position].questionId)
                            intent.putExtra("Option1",empList[position].Option1)
                            intent.putExtra("Option2",empList[position].Option2)
                            intent.putExtra("Option3",empList[position].Option3)
                            intent.putExtra("Option4",empList[position].Option4)
                            intent.putExtra("enterQuestion",empList[position].enterQuestion)
                            startActivity(intent)
                        }


                    })





                    empRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}







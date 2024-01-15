package com.example.quizzify.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.R
import com.example.quizzify.adaptor.QuizListAdapter3
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
    private lateinit var etquizNo: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

       empRecyclerView=findViewById(R.id.rvEmp)
       empRecyclerView.layoutManager=LinearLayoutManager(this)
       empRecyclerView.setHasFixedSize(true)
       tvLoadingData=findViewById(R.id.tvLoadingData)






        empList = arrayListOf<QuestionBankModel>()

        getQuestionsData()


    }
    private fun getQuestionsData() {

        empRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("quiz")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                empList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val empData = empSnap.getValue(QuestionBankModel::class.java)
                        empList.add(empData!!)
                    }
                    val mAdapter = QuizListAdapter3(empList)
                    empRecyclerView.adapter = mAdapter



                    mAdapter.setOnClickListener(object :QuizListAdapter3.onItemClickListener{




                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@FetchingActivity, QuizGameActivity2::class.java)

                            intent.putExtra("questionId",empList[position].questionId)
                            intent.putExtra("Option1",empList[position].Option1)
                            intent.putExtra("Option2",empList[position].Option2)
                            intent.putExtra("Option3",empList[position].Option3)
                            intent.putExtra("Option4",empList[position].Option4)
                            intent.putExtra("enterQuestion",empList[position].enterQuestion)
                            intent.putExtra("time",empList[position].time)
                            intent.putExtra("correct", empList[position].correctans)
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







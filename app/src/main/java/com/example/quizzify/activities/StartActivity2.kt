package com.example.quizzify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizzify.R
import com.example.quizzify.adaptor.QuizListAdapter
import com.example.quizzify.adaptor.QuizListAdapter2
import com.example.quizzify.databinding.ActivityStart2Binding
import com.example.quizzify.databinding.ActivityStartQuizBinding
import com.example.quizzify.models.QuesModel
import com.google.firebase.database.FirebaseDatabase

class StartActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityStart2Binding
    lateinit var QuizModelList:MutableList<QuesModel>
    lateinit var adapter:QuizListAdapter2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)
        binding=ActivityStart2Binding.inflate(layoutInflater)


        QuizModelList= mutableListOf()
        getDataFromFirebase()

    }
    private fun setUpRecyclerView(){
        adapter= QuizListAdapter2(QuizModelList)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        binding.recyclerView.adapter=adapter

    }


    private fun getDataFromFirebase(){
        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener {dataSnapshot->
                if (dataSnapshot.exists()){
                    for (snapshot in dataSnapshot.children){
                        val quesModel=snapshot.getValue(QuesModel::class.java)
                        if (quesModel != null) {
                            QuizModelList.add(quesModel)
                        }
                    }
                }
                setUpRecyclerView()
            }



    }
}
package com.example.quizzify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.adaptor.ImageQuesAdapter
import com.example.quizzify.models.ImageQuestionModel
import com.example.quizzify.models.ProfileModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ImageQuesList : AppCompatActivity() {



    private lateinit var db: DatabaseReference
    private lateinit var imageQuesRecyclerView: RecyclerView
    private lateinit var imageQuesArrayList: ArrayList<ImageQuestionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_ques_list)
        imageQuesRecyclerView=findViewById(R.id.imageQuesList)
        imageQuesRecyclerView.layoutManager= LinearLayoutManager(this)
        imageQuesRecyclerView.hasFixedSize()
        imageQuesArrayList= arrayListOf<ImageQuestionModel>()
        getImageQuesData()
    }

    private fun getImageQuesData() {
        db= FirebaseDatabase.getInstance().getReference("ImageQuestions")
        db.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(profilesnapshot in snapshot.children){
                        val imagequestions=profilesnapshot.getValue(ImageQuestionModel::class.java)
                        imageQuesArrayList.add(imagequestions!!)
                    }
                    imageQuesRecyclerView.adapter= ImageQuesAdapter(imageQuesArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
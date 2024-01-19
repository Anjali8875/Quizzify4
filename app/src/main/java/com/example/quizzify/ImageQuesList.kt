package com.example.quizzify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.adaptor.ImageQuesAdapter
import com.example.quizzify.models.ImageQuestionModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ImageQuesList : AppCompatActivity(),ImageQuesAdapter.QuizItemClickListener{



    private lateinit var db: DatabaseReference
    private lateinit var imageQuesRecyclerView: RecyclerView
    private lateinit var imageQuesArrayList: ArrayList<ImageQuestionModel>
  //  private lateinit var  listener:ImageQuesAdapter.QuizItemClickListener
    private lateinit var quizAdapter: ImageQuesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_ques_list)
        imageQuesRecyclerView=findViewById(R.id.imageQuesList)
        imageQuesRecyclerView.layoutManager= LinearLayoutManager(this)
        imageQuesRecyclerView.hasFixedSize()
        imageQuesArrayList= arrayListOf<ImageQuestionModel>()
        getImageQuesData()
        val quizItemClickListener=object :ImageQuesAdapter.QuizItemClickListener{
            override fun onOptionClick(questionIndex: Int, optionIndex: Int) {
                handleOptionClick(questionIndex,optionIndex)


            }
        }
        quizAdapter=ImageQuesAdapter(imageQuesArrayList,quizItemClickListener)
        imageQuesRecyclerView.adapter=quizAdapter



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

                   //  imageQuesRecyclerView.adapter= ImageQuesAdapter(imageQuesArrayList)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onOptionClick(questionIndex: Int, optionIndex: Int) {

    }

    private fun handleOptionClick(questionIndex: Int,optionIndex: Int){
        val selectOption=optionIndex+1
        val correctOption=quizAdapter.ImageQuesList[questionIndex].correctimgans

        if (selectOption==correctOption){
            Toast.makeText(this,"Correct", Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this,"Wrong", Toast.LENGTH_SHORT).show()
        }

    }
}
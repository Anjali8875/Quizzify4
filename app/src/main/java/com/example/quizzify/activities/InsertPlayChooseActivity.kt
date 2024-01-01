package com.example.quizzify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizzify.R
import com.example.quizzify.UploadingImageActivity

class InsertPlayChooseActivity : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var btnFetchData:Button
    private lateinit var btnImage:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_play_choose)

        btnInsertData=findViewById(R.id.btnInsertData)
        btnFetchData=findViewById(R.id.btnFetchData)
        btnImage=findViewById(R.id.image)

        btnInsertData.setOnClickListener{
            val intent= Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener{
            val intent= Intent(this, FetchingActivity::class.java)
            startActivity(intent)
        }

        btnImage.setOnClickListener{
            val intent=Intent(this,UploadingImageActivity::class.java)
            startActivity(intent)
        }


    }
}
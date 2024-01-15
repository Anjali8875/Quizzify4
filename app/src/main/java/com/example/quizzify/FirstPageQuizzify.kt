package com.example.quizzify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizzify.activities.InsertPlayChooseActivity
import com.google.firebase.auth.FirebaseAuth

class FirstPageQuizzify : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page_quizzify)
        button=findViewById(R.id.button)
        auth = FirebaseAuth.getInstance()
        if(auth.currentUser !=null){
            val intent= Intent(this@FirstPageQuizzify,InsertPlayChooseActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener{

            val intent= Intent(this@FirstPageQuizzify,SignInActivity::class.java)
            startActivity(intent)
        }
    }

}
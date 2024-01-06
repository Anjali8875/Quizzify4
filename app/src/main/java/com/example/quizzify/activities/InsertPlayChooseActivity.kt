package com.example.quizzify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.quizzify.ImageQuesList
import com.example.quizzify.ProfileListActivity
import com.example.quizzify.R
import com.example.quizzify.SignInActivity
import com.example.quizzify.UploadImageActivity2
import com.example.quizzify.UploadingImageActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class InsertPlayChooseActivity : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var btnFetchData:Button
    private lateinit var btnImage:Button
    private lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_play_choose)

        auth= FirebaseAuth.getInstance()

        val email=intent.getStringExtra("email")
        val displayName=intent.getStringExtra("name")

        findViewById<TextView>(R.id.textView).text=email+"/n"+displayName

        findViewById<Button>(R.id.signOutBtn).setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,SignInActivity::class.java))
        }



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
            val intent=Intent(this, UploadImageActivity2::class.java)
            startActivity(intent)
        }


    }

    fun showList(view: View) {
        var i:Intent
        i= Intent(this,ProfileListActivity::class.java)
        startActivity(i)
    }

    fun showList2(view: View) {
        var j:Intent
        j= Intent(this,ImageQuesList::class.java)
        startActivity(j)
    }


}
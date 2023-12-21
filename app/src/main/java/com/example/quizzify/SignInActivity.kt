package com.example.quizzify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzify.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivitySignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()


        binding.textView6.setOnClickListener{
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener{
            val email=binding.editTextText.text.toString()
            val pass=binding.textView2.text.toString()


            if(email.isNotEmpty() && pass.isNotEmpty()){

                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }

            }else{
                Toast.makeText(this,"Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

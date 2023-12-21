package com.example.quizzify

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizzify.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()

        binding.textView6.setOnClickListener{
            val intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)

        }

        binding.button.setOnClickListener{
            val email=binding.editTextText.text.toString()
            val pass=binding.editTextText2.text.toString()
            val confirmPass=binding.editTextText3.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()){
                if (pass==confirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent= Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty Fields are not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


package com.example.quizzify

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.example.quizzify.activities.InsertPlayChooseActivity
import com.example.quizzify.databinding.ActivityProfileBinding
import com.example.quizzify.databinding.ActivitySignInBinding
import com.example.quizzify.models.ProfileModel
import com.example.quizzify.uTils.Config
import com.example.quizzify.uTils.Config.hideDialog
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var db:DatabaseReference
    var sImage:String?="null"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun insert_data(view: View) {
        val firstName=binding.firstName.text.toString()
        val lastName=binding.lastName.text.toString()
        val gender=binding.Gender.text.toString()
        val age=binding.Age.text.toString()
        db=FirebaseDatabase.getInstance().getReference("profile")
        val profile=ProfileModel(firstName,lastName,age,gender,sImage)
        val databaseReference=FirebaseDatabase.getInstance().reference
        val id=databaseReference.push().key
        db.child(id.toString()).setValue(profile).addOnSuccessListener{
            binding.firstName.text.clear()
            binding.lastName.text.clear()
            binding.Age.text.clear()
            binding.Gender.text.clear()
            sImage=""
            Toast.makeText(this,"data inserted",Toast.LENGTH_SHORT).show()
            val intent:Intent=Intent(this,InsertPlayChooseActivity::class.java)
            startActivity(intent)

        }.addOnFailureListener{
            Toast.makeText(this,"data not inserted",Toast.LENGTH_SHORT).show()
        }

    }
    fun insert_Img(view: View) {
        var myfileintent=Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)



    }
    private val ActivityResultLauncher=registerForActivityResult<Intent,ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result:ActivityResult->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap=BitmapFactory.decodeStream(inputStream)
                val  stream=ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.JPEG,0,stream)
                val bytes=stream.toByteArray()
                sImage=Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.userImage.setImageBitmap(myBitmap)
                inputStream!!.close()


            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(),Toast.LENGTH_LONG).show()
            }
        }

    }
}

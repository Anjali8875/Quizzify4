package com.example.quizzify

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.quizzify.activities.InsertPlayChooseActivity
import com.example.quizzify.databinding.ActivityProfileBinding
import com.example.quizzify.databinding.ActivityUploadImage2Binding
import com.example.quizzify.models.ImageQuestionModel
import com.example.quizzify.models.ProfileModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class UploadImageActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityUploadImage2Binding
    private lateinit var db: DatabaseReference
    var sImage1:String?="null"
    var sImage2:String?="null"
    var sImage3:String?="null"
    var sImage4:String?="null"


    private lateinit var etimgquestion: EditText
    private lateinit var etsimage1: ImageView
    private lateinit var etsimage2: ImageView
    private lateinit var etsimage3: ImageView
    private lateinit var etsimage4: ImageView
    private lateinit var btnmoreques: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadImage2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        etimgquestion=findViewById(R.id.enterQuestion)
        etsimage1=findViewById(R.id.imageView)
        etsimage2=findViewById(R.id.imageView2)
        etsimage3=findViewById(R.id.imageView3)
        etsimage4=findViewById(R.id.imageView4)
        btnmoreques=findViewById(R.id.createmorequestions)


        setContentView(R.layout.activity_upload_image2)

        btnmoreques.setOnClickListener{
            etimgquestion.text.clear()
            etsimage1.setImageDrawable(null)
            etsimage2.setImageDrawable(null)
            etsimage3.setImageDrawable(null)
            etsimage4.setImageDrawable(null)

        }




       }








    fun insert_data(view: View) {
        val enterQuestion=binding.enterQuestion.text.toString()
        db= FirebaseDatabase.getInstance().getReference("ImageQuestions")

        val imagequestions= ImageQuestionModel(enterQuestion,sImage1,sImage2,sImage3,sImage4)
        val databaseReference= FirebaseDatabase.getInstance().reference
        val id=databaseReference.push().key
        db.child(id.toString()).setValue(imagequestions).addOnSuccessListener{
            binding.enterQuestion.text.clear()

            sImage1=""
            sImage2=""
            sImage3=""
            sImage4=""
            Toast.makeText(this,"data inserted", Toast.LENGTH_SHORT).show()






        }.addOnFailureListener{
            Toast.makeText(this,"data not inserted", Toast.LENGTH_SHORT).show()
        }

    }
    fun insert_Img(view: View) {
        var myfileintent= Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher1.launch(myfileintent)
        ActivityResultLauncher2.launch(myfileintent)
        ActivityResultLauncher3.launch(myfileintent)
        ActivityResultLauncher4.launch(myfileintent)




    }
    private val ActivityResultLauncher1=registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result: ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap= BitmapFactory.decodeStream(inputStream)
                val  stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.JPEG,0,stream)
                val bytes=stream.toByteArray()
                sImage1= Base64.encodeToString(bytes, Base64.DEFAULT)
                binding.imageView.setImageBitmap(myBitmap)
                inputStream!!.close()


            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }
    private val ActivityResultLauncher2=registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result: ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap= BitmapFactory.decodeStream(inputStream)
                val  stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.JPEG,0,stream)
                val bytes=stream.toByteArray()
                sImage2= Base64.encodeToString(bytes, Base64.DEFAULT)
                binding.imageView2.setImageBitmap(myBitmap)
                inputStream!!.close()


            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }
    private val ActivityResultLauncher3=registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result: ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap= BitmapFactory.decodeStream(inputStream)
                val  stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.JPEG,0,stream)
                val bytes=stream.toByteArray()
                sImage3= Base64.encodeToString(bytes, Base64.DEFAULT)
                binding.imageView3.setImageBitmap(myBitmap)
                inputStream!!.close()


            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }
    private val ActivityResultLauncher4=registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result: ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri=result.data!!.data
            try{
                val inputStream=contentResolver.openInputStream(uri!!)
                val myBitmap= BitmapFactory.decodeStream(inputStream)
                val  stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.JPEG,0,stream)
                val bytes=stream.toByteArray()
                sImage4= Base64.encodeToString(bytes, Base64.DEFAULT)
                binding.imageView4.setImageBitmap(myBitmap)
                inputStream!!.close()


            }catch (ex:Exception){
                Toast.makeText(this,ex.message.toString(), Toast.LENGTH_LONG).show()
            }
        }

    }


}




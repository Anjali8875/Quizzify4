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
    private lateinit var etcorrectans:EditText
    private lateinit var etOption1:Button
    private lateinit var etOption2:Button
    private lateinit var etOption3:Button
    private lateinit var etOption4:Button
    var sImage1:String?="null"
    var sImage2:String?="null"
    var sImage3:String?="null"
    var sImage4:String?="null"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUploadImage2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        etcorrectans=findViewById(R.id.correctimgans)
        etOption1=findViewById<Button>(R.id.browse1)
        etOption2=findViewById<Button>(R.id.browse2)
        etOption3=findViewById<Button>(R.id.browse3)
        etOption4=findViewById<Button>(R.id.browse4)












       }








    fun insert_data(view: View) {
        val enterQuestion=binding.enterQuestion.text.toString()
        val correctimgans=binding.correctimgans.text.toString().toIntOrNull()
        val Option1=etOption1.text.toString()
        val Option2=etOption2.text.toString()
        val Option3=etOption3.text.toString()
        val Option4=etOption4.text.toString()

        val options= listOf(Option1,Option2,Option3,Option4)

        db= FirebaseDatabase.getInstance().getReference("ImageQuestions")


        val imagequestions=
            correctimgans?.let {
                ImageQuestionModel(enterQuestion,sImage1,sImage2,sImage3,sImage4,
                    it,options
                )
            }


//        val databaseReference= FirebaseDatabase.getInstance().reference
        val id=db.push().key
        db.child(id.toString()).setValue(imagequestions).addOnSuccessListener{
            binding.enterQuestion.text.clear()
            binding.correctimgans.text.clear()

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
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
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
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
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
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
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
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
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




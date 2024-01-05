package com.example.quizzify

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage


class UploadingImageActivity : AppCompatActivity() {
    private lateinit var image1:ImageView
    private lateinit var image2:ImageView
    private lateinit var image3:ImageView
    private lateinit var image4:ImageView
    private lateinit var btnBrowse1:Button
    private lateinit var btnBrowse2:Button
    private lateinit var btnBrowse3:Button
    private lateinit var btnBrowse4:Button
    private lateinit var btnUpload1:Button
    private lateinit var btnUpload2:Button
    private lateinit var btnUpload3:Button
    private lateinit var btnUpload4:Button
    private lateinit var uri: Uri
    private lateinit var EnterQuestion:EditText
    private lateinit var dbRef: DatabaseReference

    private var storageRef= Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploading_image)

        dbRef=FirebaseDatabase.getInstance().getReference("user images")

        storageRef= FirebaseStorage.getInstance()
        image1=findViewById(R.id.imageView)
        image2=findViewById(R.id.imageView2)
        image3=findViewById(R.id.imageView3)
        image4=findViewById(R.id.imageView4)
        btnBrowse1=findViewById(R.id.browse1)
        btnBrowse2=findViewById(R.id.browse2)
        btnBrowse3=findViewById(R.id.browse3)
        btnBrowse4=findViewById(R.id.browse4)



        btnUpload1=findViewById(R.id.upload1)
        btnUpload2=findViewById(R.id.upload2)
        btnUpload3=findViewById(R.id.upload3)
        btnUpload4=findViewById(R.id.upload4)
        val galleryImage1=registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                image1.setImageURI(it)
                if (it != null) {
                    uri=it
                }

            }
        )

        btnBrowse1.setOnClickListener{
            galleryImage1.launch("image/*")
        }

        val galleryImage2=registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                image2.setImageURI(it)
                if (it != null) {
                    uri=it
                }

            }
        )

        btnBrowse2.setOnClickListener{
            galleryImage2.launch("image/*")
        }

        val galleryImage3=registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                image3.setImageURI(it)
                if (it != null) {
                    uri=it
                }

            }
        )

        btnBrowse3.setOnClickListener{
            galleryImage3.launch("image/*")
        }

        val galleryImage4=registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {

                image4.setImageURI(it)
                if (it != null) {
                    uri=it
                }

            }
        )

        btnBrowse4.setOnClickListener{
            galleryImage4.launch("image/*")
        }


        btnUpload1.setOnClickListener{
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            val userId= FirebaseAuth.getInstance().currentUser!!.uid

                            val mapImage= mapOf(
                                "url" to it.toString()
                            )

                            val databaseReference=FirebaseDatabase.getInstance().getReference("user Images")
                            databaseReference.child(userId).setValue(mapImage)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()


                                }

                                .addOnFailureListener{error->
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()


                                }

                        }

                }

        }

        btnUpload2.setOnClickListener{
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            val userId= FirebaseAuth.getInstance().currentUser!!.uid

                            val mapImage= mapOf(
                                "url" to it.toString()
                            )

                            val databaseReference=FirebaseDatabase.getInstance().getReference("user Images")
                            databaseReference.child(userId).setValue(mapImage)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()


                                }

                                .addOnFailureListener{error->
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()


                                }

                        }

                }

        }

        btnUpload3.setOnClickListener{
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            val userId= FirebaseAuth.getInstance().currentUser!!.uid

                            val mapImage= mapOf(
                                "url" to it.toString()
                            )

                            val databaseReference=FirebaseDatabase.getInstance().getReference("user Images")
                            databaseReference.child(userId).setValue(mapImage)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()


                                }

                                .addOnFailureListener{error->
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()


                                }

                        }

                }

        }
        btnUpload4.setOnClickListener{
            storageRef.getReference("images").child(System.currentTimeMillis().toString())
                .putFile(uri)
                .addOnSuccessListener { task->
                    task.metadata!!.reference!!.downloadUrl
                        .addOnSuccessListener {
                            val userId= FirebaseAuth.getInstance().currentUser!!.uid

                            val mapImage= mapOf(
                                "url" to it.toString()
                            )

                            val databaseReference=FirebaseDatabase.getInstance().getReference("user Images")
                            databaseReference.child(userId).setValue(mapImage)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Successful",Toast.LENGTH_SHORT).show()


                                }

                                .addOnFailureListener{error->
                                    Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()


                                }

                        }

                }

        }



        val userId=dbRef.push().key!!













    }
}
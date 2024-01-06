package com.example.quizzify.adaptor

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.ImageQuesList
import com.example.quizzify.ProfileAdapter
import com.example.quizzify.R
import com.example.quizzify.models.ImageQuestionModel
import com.example.quizzify.models.ProfileModel

class ImageQuesAdapter(private val ImageQuesList: ArrayList<ImageQuestionModel>): RecyclerView.Adapter<ImageQuesAdapter.imagequestionsHolder>() {
    class imagequestionsHolder(imagequestionsView: View) : RecyclerView.ViewHolder(imagequestionsView) {


        val ImageQues: EditText = imagequestionsView.findViewById(R.id.ImageQues)

        val Image1: ImageView = imagequestionsView.findViewById(R.id.Image1)
        val Image2: ImageView = imagequestionsView.findViewById(R.id.Image2)
        val Image3: ImageView = imagequestionsView.findViewById(R.id.Image3)
        val Image4: ImageView = imagequestionsView.findViewById(R.id.Image4)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagequestionsHolder {
        val imagequestionsView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_image_ques_page,
            parent, false
        )
        return imagequestionsHolder(imagequestionsView)
    }



    override fun onBindViewHolder(holder: imagequestionsHolder, position: Int) {
        val currentimagequestions= ImageQuesList[position]
        holder.ImageQues.setText(currentimagequestions.enterQuestion.toString())

        val bytes1 = Base64.decode(
            currentimagequestions.simage1,
            Base64.DEFAULT
        )
        val bitmap1 = BitmapFactory.decodeByteArray(bytes1, 0, bytes1.size)
        holder.Image1.setImageBitmap(bitmap1)

        val bytes2 = Base64.decode(
            currentimagequestions.simage2,
            Base64.DEFAULT
        )
        val bitmap2 = BitmapFactory.decodeByteArray(bytes2, 0, bytes2.size)
        holder.Image2.setImageBitmap(bitmap2)

        val bytes3 = Base64.decode(
            currentimagequestions.simage3,
            Base64.DEFAULT
        )
        val bitmap3 = BitmapFactory.decodeByteArray(bytes3, 0, bytes3.size)
        holder.Image3.setImageBitmap(bitmap3)

        val bytes4 = Base64.decode(
            currentimagequestions.simage1,
            Base64.DEFAULT
        )
        val bitmap4 = BitmapFactory.decodeByteArray(bytes4, 0, bytes4.size)
        holder.Image4.setImageBitmap(bitmap4)
    }

    override fun getItemCount(): Int {
        return ImageQuesList.size
    }

}




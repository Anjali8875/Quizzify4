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

class ImageQuesAdapter(private val ImageQuesList: ArrayList<ImageQuestionModel>): RecyclerView.Adapter<ProfileAdapter.profileHolder>() {
    class imagequestionsHolder(imagequestionsView: View) :
        RecyclerView.ViewHolder(imagequestionsView) {


        val ImageQues: EditText = imagequestionsView.findViewById(R.id.ImageQues)

        val Image1: ImageView = imagequestionsView.findViewById(R.id.Image1)
        val Image2: ImageView = imagequestionsView.findViewById(R.id.Image2)
        val Image3: ImageView = imagequestionsView.findViewById(R.id.Image3)
        val Image4: ImageView = imagequestionsView.findViewById(R.id.Image4)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagequestionsHolder {
        val profileView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_profile_page,
            parent, false
        )
        return imagequestionsHolder(profileView)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.profileHolder, position: Int) {
        val currentimageques= ImageQuesList[position]
        holder.ImageQues.setText(currentprofile.imageQuestion.toString())

        val bytes = Base64.decode(
            currentprofile.simage1,
            android.util.Base64.DEFAULT
        )
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        holder.simage1.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return ImageQuesList.size
    }

}




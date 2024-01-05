package com.example.quizzify

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.models.ProfileModel
class ProfileAdapter(private val profileList: ArrayList<ProfileModel>): RecyclerView.Adapter<ProfileAdapter.profileHolder>() {
    class profileHolder(profileView: View) : RecyclerView.ViewHolder(profileView) {



        val firstname: EditText = profileView.findViewById(R.id.firstname2)
        val lastname: EditText = profileView.findViewById(R.id.lastname2)
        val age: EditText = profileView.findViewById(R.id.age2)
        val gender: EditText = profileView.findViewById(R.id.Gender2)
        val image: ImageView = profileView.findViewById(R.id.imageView5)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): profileHolder {
        val profileView = LayoutInflater.from(parent.context).inflate(R.layout.activity_profile_page,
            parent,false)
             return profileHolder(profileView)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    override fun onBindViewHolder(holder: profileHolder, position: Int) {
        val currentprofile = profileList[position]
        holder.firstname.setText(currentprofile.firstName.toString())
        holder.lastname.setText(currentprofile.lastName.toString())
        holder.age.setText(currentprofile.age.toString())
        holder.gender.setText(currentprofile.gender.toString())
        val bytes = Base64.decode(
            currentprofile.image,
            android.util.Base64.DEFAULT
        )
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        holder.image.setImageBitmap(bitmap)


    }

}
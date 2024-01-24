package com.example.quizzify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.models.ProfileModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileListActivity : AppCompatActivity() {
    private lateinit var db:DatabaseReference
    private lateinit var profileRecyclerView: RecyclerView
    private lateinit var profileArrayList: ArrayList<ProfileModel>
    private lateinit var auth:FirebaseAuth
    private  var userID=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)
        profileRecyclerView=findViewById(R.id.profileList)
        profileRecyclerView.layoutManager=LinearLayoutManager(this)
        profileRecyclerView.hasFixedSize()
        profileArrayList= arrayListOf<ProfileModel>()
        getProfileData()


    }

    private fun getProfileData() {
        db=FirebaseDatabase.getInstance().getReference("items")
        db.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(profilesnapshot in snapshot.children){
                        val profile=profilesnapshot.getValue(ProfileModel::class.java)

                        profileArrayList.add(profile!!)
                    }
                    profileRecyclerView.adapter=ProfileAdapter(profileArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}
package com.example.quizzify.adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.PlayQuiz
import com.example.quizzify.R
import com.example.quizzify.models.QuesModel
import com.example.quizzify.databinding.QuizItemRecyclerRowBinding

class QuizListAdapter(var QuizModelList:List<QuesModel>):RecyclerView.Adapter<QuizListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView=itemView.findViewById(R.id.quiz_title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.quiz_item_recycler_row,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return QuizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentQuiz=QuizModelList[position]
        holder.titleTextView.text=currentQuiz.title
    }

}
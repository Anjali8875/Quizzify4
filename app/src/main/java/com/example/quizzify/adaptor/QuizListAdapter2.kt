package com.example.quizzify.adaptor

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.PlayQuiz
import com.example.quizzify.databinding.ActivityStart2Binding
import com.example.quizzify.databinding.QuizItemRecyclerRowBinding
import com.example.quizzify.models.QuesModel

class QuizListAdapter2(private val QuizModelList:List<QuesModel>):RecyclerView.Adapter<QuizListAdapter2.MyViewHolder> (){
    class MyViewHolder(private val binding: QuizItemRecyclerRowBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(model:QuesModel){
            binding.apply {
                quizTitleText.text=model.title
                root.setOnClickListener{
                    val intent=Intent(root.context,PlayQuiz::class.java)
                    root.context.startActivity(intent)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=QuizItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return QuizModelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(QuizModelList[position])
    }
}
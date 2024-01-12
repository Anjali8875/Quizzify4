package com.example.quizzify.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.R
import com.example.quizzify.models.QuestionBankModel
import android.view.View
import android.widget.TextView


class QuestionAdaptor(private val empList: ArrayList<QuestionBankModel>) : RecyclerView.Adapter<QuestionAdaptor.ViewHolder>(){



    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(clickListener: onItemClickListener){
        mListener=clickListener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.question_list_item, parent, false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: QuestionAdaptor.ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvEmpName.text = currentEmp.questionId
    }


    override fun getItemCount(): Int {
        return empList.size
    }


    class ViewHolder(itemView: View,clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvEmpName: TextView = itemView.findViewById(R.id.tvEmpName)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)

            }

        }
    }


}




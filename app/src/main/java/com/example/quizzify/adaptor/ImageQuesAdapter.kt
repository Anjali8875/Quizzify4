package com.example.quizzify.adaptor

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzify.R
import com.example.quizzify.models.ImageQuestionModel
import java.lang.IndexOutOfBoundsException

class ImageQuesAdapter(val ImageQuesList: ArrayList<ImageQuestionModel>,private val listener: QuizItemClickListener): RecyclerView.Adapter<ImageQuesAdapter.imagequestionsHolder>() {



    interface QuizItemClickListener {
        fun onOptionClick(questionIndex: Int, optionIndex: Int)

    }

    class imagequestionsHolder(imagequestionsView: View) :
        RecyclerView.ViewHolder(imagequestionsView) {


        val ImageQues: EditText = imagequestionsView.findViewById(R.id.ImageQues)
      //  val correctimgans: EditText = imagequestionsView.findViewById(R.id.correctimgans)

        val Image1: ImageView = imagequestionsView.findViewById(R.id.Image1)
        val Image2: ImageView = imagequestionsView.findViewById(R.id.Image2)
        val Image3: ImageView = imagequestionsView.findViewById(R.id.Image3)
        val Image4: ImageView = imagequestionsView.findViewById(R.id.Image4)
        val optionsButton: List<Button> = List(4) { index ->
            itemView.findViewById<Button>(getButtonId(index))
        }

        private fun getButtonId(index: Int): Int {
            return when (index) {
                0 -> R.id.option1
                1 -> R.id.option2
                2 -> R.id.option3
                3 -> R.id.option4
                else -> throw IndexOutOfBoundsException("Invalid Button Index")

            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): imagequestionsHolder {
        val imagequestionsView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_image_ques_page,
            parent, false
        )
        return imagequestionsHolder(imagequestionsView)
    }


    override fun onBindViewHolder(holder: imagequestionsHolder, position: Int) {
        val currentimagequestions = ImageQuesList[position]
        holder.ImageQues.setText(currentimagequestions.enterQuestion.toString())
       // holder.correctimgans.setText(currentimagequestions.correctimgans.toString())

        for (i in currentimagequestions.options!!.indices) {
            holder.optionsButton[i].text = currentimagequestions.options[i]
            holder.optionsButton[i].setOnClickListener {
                listener.onOptionClick(position, i)
            }

        }

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


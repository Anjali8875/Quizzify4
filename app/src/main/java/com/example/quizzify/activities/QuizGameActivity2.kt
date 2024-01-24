package com.example.quizzify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.example.quizzify.R
import com.example.quizzify.models.ScoreModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class QuizGameActivity2 : AppCompatActivity() {
    private lateinit var etenterQuestion: TextView
    private lateinit var etOption1: TextView
    private lateinit var etOption2: TextView
    private lateinit var etOption3: TextView
    private lateinit var etOption4: TextView
   // private lateinit var etquestionId: TextView

    private lateinit var ettime:TextView

    private var countdownTime = 0L

    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long=60000// 60 seconds
    private val countDownInterval: Long = 1000 // 1 second
    private lateinit var timerText:TextView



    private lateinit var databaseReference: DatabaseReference
  //  private lateinit var correctAns: String
    private lateinit var databaseReference2: DatabaseReference
    private var iniTime = 0L
    private lateinit var auth : FirebaseAuth
    private var userID = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game2)
        timerText = findViewById(R.id.time2)
        auth = FirebaseAuth.getInstance()
        userID = auth.currentUser?.uid.toString()
      //  var countdownTime=0L
        iniTime = System.currentTimeMillis()








        initCountDownTimer()

        startCountDownTimer()








        databaseReference = FirebaseDatabase.getInstance().getReference("quiz")




        initView()
        setValuesToViews()
        






        val submitButton: Button = findViewById(R.id.submitButton)



        submitButton.setOnClickListener {
            recordAnswerTime()
            checkAnswer()
        }





    }

    fun recordAnswerTime(){
        countdownTime=System.currentTimeMillis()



    }

    private fun calculateScore():Long{

        val timeElapsed =  countdownTime - iniTime
        val score = 100 - timeElapsed/1000
        return score


    }

    private fun initView() {
        etenterQuestion = findViewById(R.id.EnterQuestion2)
        etOption1 = findViewById(R.id.radioBtn1)
        etOption2 = findViewById(R.id.radioBtn2)
        etOption3 = findViewById(R.id.radioBtn3)
        etOption4 = findViewById(R.id.radioBtn4)
        ettime=findViewById(R.id.time2)


    }


    private fun setValuesToViews() {
        etenterQuestion.text = intent.getStringExtra("enterQuestion")
        etOption1.text = intent.getStringExtra("Option1")
        etOption2.text = intent.getStringExtra("Option2")
        etOption3.text = intent.getStringExtra("Option3")
        etOption4.text = intent.getStringExtra("Option4")
        ettime.text=intent.getStringExtra("time")

    }




    private fun checkAnswer() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)


                    val correctOption = intent.getStringExtra("correct")
                    var score= 0
                    if (correctOption != null && selectedRadioButton.text.toString() == correctOption.toString()) {
                        score = calculateScore().toInt()

                        Toast.makeText(this@QuizGameActivity2,"correct answer\nScore - $score",Toast.LENGTH_LONG).show()
                        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@QuizGameActivity2,"Incorrect answer",Toast.LENGTH_LONG).show()
                        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
                        startActivity(intent)
                        finish()


                    }

                    databaseReference2 = FirebaseDatabase.getInstance().getReference("score")

                   val quesId=databaseReference2.push().key!!

                   val questions=ScoreModel(quesId,score)
                   databaseReference2.child(userID).child(quesId).setValue(questions)



        } else {

            println("Please select an option.")
        }
    }



    private fun initCountDownTimer() {



       countDownTimer = object : CountDownTimer(timeLeftInMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
               timeLeftInMillis = millisUntilFinished
                updateCountDownText()
           }

           override fun onFinish() {

                 handleTimerFinish()
           }
        }
    }

    private fun startCountDownTimer() {
        countDownTimer.start()
    }

    private fun updateCountDownText() {
        val seconds = (timeLeftInMillis/1000).toInt()
        timerText.text = seconds.toString()
    }

    private fun handleTimerFinish() {
        checkAnswer()
        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
        startActivity(intent)
        finish()

    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}


















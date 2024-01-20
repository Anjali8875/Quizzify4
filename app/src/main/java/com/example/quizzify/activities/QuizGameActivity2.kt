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
    private lateinit var btnUpdate: Button
    private lateinit var etquestionId: TextView
    private lateinit var btnDelete: Button
    private lateinit var ettime:TextView

    private var countdownTime = 0L

    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long=60000// 60 seconds
    private val countDownInterval: Long = 1000 // 1 second
    private lateinit var timerText:TextView



    private lateinit var databaseReference: DatabaseReference
    private lateinit var correctAns: String
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
        var countdownTime=0L
        iniTime = System.currentTimeMillis()




//        recordAnswerTime()
        val userScore=calculateScore()

       // fun calculateElapsedTime():Long{
           // val currentTime=System.currentTimeMillis()
           // return currentTime-startTime
      //  }
       // var timeLeftInMillis:Long=ettime.text.toString().toLongOrNull()?:0



        // timeLeftInMillis=(ettime.text)*1000


        // Initialize the CountDownTimer
        initCountDownTimer()

        // Start the timer
        startCountDownTimer()
       // val durationInMinutes=intent.getStringExtra("time")
      //  startTimer(durationInMinutes)




        val database = FirebaseDatabase.getInstance().reference
        val timerRef = database.child("quiz")
        val databaseReference2=FirebaseDatabase.getInstance().getReference("score")



      //  if (questionId != null) {
           // timerRef.child(questionId.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
             //   override fun onDataChange(snapshot: DataSnapshot) {
              //      val durationInMinutes = snapshot.child("time").getValue(Long::class.java) ?: 0
              //      startTimer(durationInMinutes)
              //  }

               // override fun onCancelled(error: DatabaseError) {

               // }
           // })
       // }





        databaseReference = FirebaseDatabase.getInstance().getReference("quiz")




        initView()
        setValuesToViews()
        





        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val submitButton: Button = findViewById(R.id.submitButton)

        val quesId=databaseReference.push().key


        submitButton.setOnClickListener {
            recordAnswerTime()
            checkAnswer()
        }



        if (quesId != null) {
            databaseReference.child(quesId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val correctAns = snapshot.child("correctans").value.toString()


                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }




    }

    fun recordAnswerTime(){
        countdownTime=System.currentTimeMillis()



    }

    private fun calculateScore():Long{
//            val maxTime=10L
//            val minScore=0
//            val maxScore=100



//            val normalizedTime=countdownTime.coerceIn(0L,maxTime)
//            val score=((maxTime-normalizedTime)/maxTime.toDouble()*(maxScore-minScore)+minScore).toInt()
        val timeElapsed =  countDownInterval - iniTime
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

  //  private fun startTimer(durationInMinutes: String?) {
     //   val millisInFuture = durationInMinutes * 60 * 1000

       // var countdownTimer = object : CountDownTimer(millisInFuture, 1000) {
          //  override fun onTick(millisUntilFinished: Long) {
           //     updateTimerText(millisUntilFinished)
          //  }

          //  override fun onFinish() {
                //timerText.text = "Timer finished!"
          //  }
       // }


   // }

  //  private fun updateTimerText(millisUntilFinished: Long) {
       // val minutes = millisUntilFinished / 1000 / 60
      //  val seconds = (millisUntilFinished / 1000) % 60
       // timerText.text = String.format("%02d:%02d", minutes, seconds)
   // }


    private fun checkAnswer() {
        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)



//            val questionId=databaseReference.get()
//            databaseReference.child(questionId.toString()).addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
                //    val correctOption = snapshot.child("correctans").getValue(String::class.java)
                    val correctOption = intent.getStringExtra("correct")
                    var score= 0
                    if (correctOption != null && selectedRadioButton.text.toString() == correctOption.toString()) {
                        score = calculateScore().toInt()

                        Toast.makeText(this@QuizGameActivity2,"correct answer\nScore - $score",Toast.LENGTH_LONG).show()
                        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@QuizGameActivity2,"Incorrect answer",Toast.LENGTH_LONG).show()
                        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
                        startActivity(intent)


                    }

                    databaseReference2 = FirebaseDatabase.getInstance().getReference("score")

                   val quesId=databaseReference2.push().key!!

                   val questions=ScoreModel(quesId,score)
                   databaseReference2.child(userID).child(quesId).setValue(questions)


                    //insert
//                }

//                override fun onCancelled(error: DatabaseError) {
//
//                }
//            })
        } else {

            println("Please select an option.")
        }
    }


   // private var timeLeftInMillis: Long=ettime.text.toString().toLongOrNull()?:0// 60 seconds
    private fun initCountDownTimer() {
      // ettime=findViewById(R.id.time2)
       // var timeLeftInMillis: Long=ettime.text.toString().toLongOrNull()?:0// 60 seconds


       countDownTimer = object : CountDownTimer(timeLeftInMillis, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
               timeLeftInMillis = millisUntilFinished
                updateCountDownText()
           }

           override fun onFinish() {
                // Timer finished, handle accordingly
                 handleTimerFinish()
           }
        }
    }

    private fun startCountDownTimer() {
        countDownTimer.start()
    }

    private fun updateCountDownText() {
       // ettime=findViewById(R.id.time2)
       // var timeLeftInMillis: Long=ettime.text.toString().toLongOrNull()?:0// 60 seconds
        // Update your UI with the remaining time (e.g., update a TextView)
        val seconds = (timeLeftInMillis/1000).toInt()
        timerText.text = seconds.toString()
    }

    private fun handleTimerFinish() {
        // Timer finished, handle accordingly
        //timerText.text = "0"
        checkAnswer()
        val intent= Intent(this@QuizGameActivity2,FetchingActivity::class.java)
        startActivity(intent)
        finish()
        // Add your logic here for actions to be taken when the timer finishes
    }

    override fun onDestroy() {
        super.onDestroy()
        // Cancel the timer to avoid leaks when the activity is destroyed
        countDownTimer.cancel()
    }
}


















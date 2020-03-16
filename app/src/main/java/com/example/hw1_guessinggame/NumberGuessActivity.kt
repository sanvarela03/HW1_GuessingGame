package com.example.hw1_guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class NumberGuessActivity : AppCompatActivity() {
    private lateinit var hint: TextView
    private lateinit var inputText: EditText
    private lateinit var gameDescription: TextView
    private lateinit var buttonPlay: Button
    private lateinit var title: TextView
    private var attempts = 0
    private var numberOnComputerMind = (1..1000).random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)

        hint = findViewById(R.id.hint_text)
        inputText = findViewById(R.id.number_editText)
        gameDescription = findViewById(R.id.description_game_text)
        buttonPlay = findViewById(R.id.play_button)
        title = findViewById(R.id.title_text)

        inputText.setOnClickListener { changeButtonText() }
        buttonPlay.setOnClickListener { sendNumber() }
    }

    private fun changeButtonText() {
        buttonPlay.text = "PLAY"
        inputText.hint = "#"
        hint.text = ""

    }

    private fun sendNumber() {
        val inputNumber = inputText.text.toString().toIntOrNull()


        attempts++
        when {
            inputNumber == null -> {
                attempts--
                hint.text = "That is not a number dude :/"
            }
            inputNumber > numberOnComputerMind -> {
                Toast.makeText(this,"Hint: It's lower!",Toast.LENGTH_LONG).show()
                inputText.text.clear()
            }
            inputNumber < numberOnComputerMind -> {
                Toast.makeText(this,"Hint: It's higher!",Toast.LENGTH_LONG).show()
                inputText.text.clear()
            }
            inputNumber == numberOnComputerMind -> {
                hint.text = "YOU GOT IT !! \n Attemps: $attempts"
                gameDescription.text = numberOnComputerMind.toString()
                buttonPlay.text = "REPLAY"
                title.text = "YOU WIN !!"
                buttonPlay.setOnClickListener { replay() }
            }
        }
    }

    private fun replay() {
        numberOnComputerMind = (1..1000).random()
        buttonPlay.text = "PLAY"
        title.text ="Number Guess Game!"
        hint.text = "Let's Go !!"
        gameDescription.text = "Try to guess the number I'm thinking of, from 1-1000!"
        inputText.text.clear()
        attempts = 0
        buttonPlay.setOnClickListener { sendNumber() }
    }
}

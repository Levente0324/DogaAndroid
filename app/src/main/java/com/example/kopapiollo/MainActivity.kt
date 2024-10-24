package com.example.kopapirollo

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.kopapiollo.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var playerChoice: ImageView
    private lateinit var computerChoice: ImageView
    private lateinit var drawCount: TextView
    private lateinit var playerHeart1: ImageView
    private lateinit var playerHeart2: ImageView
    private lateinit var playerHeart3: ImageView
    private lateinit var computerHeart1: ImageView
    private lateinit var computerHeart2: ImageView
    private lateinit var computerHeart3: ImageView
    private var playerPoints = 0
    private var computerPoints = 0
    private var draws = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoice = findViewById(R.id.player_choice)
        computerChoice = findViewById(R.id.computer_choice)
        drawCount = findViewById(R.id.draw_count)
        playerHeart1 = findViewById(R.id.player_heart1)
        playerHeart2 = findViewById(R.id.player_heart2)
        playerHeart3 = findViewById(R.id.player_heart3)
        computerHeart1 = findViewById(R.id.computer_heart1)
        computerHeart2 = findViewById(R.id.computer_heart2)
        computerHeart3 = findViewById(R.id.computer_heart3)


        playerHeart1.setImageResource(R.drawable.heart2)
        playerHeart2.setImageResource(R.drawable.heart2)
        playerHeart3.setImageResource(R.drawable.heart2)
        computerHeart1.setImageResource(R.drawable.heart2)
        computerHeart2.setImageResource(R.drawable.heart2)
        computerHeart3.setImageResource(R.drawable.heart2)
    }


    fun playerMove(view: View) {
        val playerMove = view.tag.toString().toInt()
        setPlayerChoice(playerMove)
        val computerMove = Random.nextInt(3)
        setComputerChoice(computerMove)
        calculateResult(playerMove, computerMove)
    }

    private fun setPlayerChoice(choice: Int) {
        when (choice) {
            0 -> playerChoice.setImageResource(R.drawable.rock)
            1 -> playerChoice.setImageResource(R.drawable.paper)
            2 -> playerChoice.setImageResource(R.drawable.scissors)
        }
    }

    private fun setComputerChoice(choice: Int) {
        when (choice) {
            0 -> computerChoice.setImageResource(R.drawable.rock)
            1 -> computerChoice.setImageResource(R.drawable.paper)
            2 -> computerChoice.setImageResource(R.drawable.scissors)
        }
    }

    private fun calculateResult(playerMove: Int, computerMove: Int) {

        when {
            playerMove == computerMove -> {
                draws++
                drawCount.text = draws.toString()
                Toast.makeText(this, "Döntetlen!", Toast.LENGTH_SHORT).show()
            }
            (playerMove == 0 && computerMove == 2) ||
                    (playerMove == 1 && computerMove == 0) ||
                    (playerMove == 2 && computerMove == 1) -> {
                playerPoints++
                updateHearts(playerPoints, playerHeart1, playerHeart2, playerHeart3)
                Toast.makeText(this, "Nyertél!", Toast.LENGTH_SHORT).show()
            }
            else -> {
                computerPoints++
                updateHearts(computerPoints, computerHeart1, computerHeart2, computerHeart3)
                Toast.makeText(this, "Vesztettél!", Toast.LENGTH_SHORT).show()
            }
        }
        checkGameOver()
    }

    private fun updateHearts(points: Int, heart1: ImageView, heart2: ImageView, heart3: ImageView) {
        when (points) {
            1 -> heart2.setImageResource(R.drawable.heart1)
            2 -> heart2.setImageResource(R.drawable.heart1)
            3 -> heart2.setImageResource(R.drawable.heart1)
        }
    }

    private fun checkGameOver() {
        if (playerPoints == 3 || computerPoints == 3) {
            val message = if (playerPoints == 3) "Győzelem!" else "Vereség!"
            AlertDialog.Builder(this)
                .setTitle(message)
                .setMessage("Szeretnél új játékot?")
                .setPositiveButton("Igen") { _: DialogInterface, _: Int -> resetGame() }
                .setNegativeButton("Nem") { _: DialogInterface, _: Int -> finish() }
                .show()
        }
    }

    private fun resetGame() {
        playerPoints = 0
        computerPoints = 0
        draws = 0
        drawCount.text = "0"

        playerHeart1.setImageResource(R.drawable.heart2)
        playerHeart2.setImageResource(R.drawable.heart2)
        playerHeart3.setImageResource(R.drawable.heart2)
        computerHeart1.setImageResource(R.drawable.heart2)
        computerHeart2.setImageResource(R.drawable.heart2)
        computerHeart3.setImageResource(R.drawable.heart2)

        playerChoice.setImageResource(R.drawable.rock)
        computerChoice.setImageResource(R.drawable.rock)
    }

}

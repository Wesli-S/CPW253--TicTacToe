package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class playerTurn
    {
        X,
        O
    }
    private var firstTurn = playerTurn.X
    private var currentTurn = playerTurn.X

    private var boardList = mutableListOf<Button>()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initBoard(){
        boardList.add(binding.button1)
        boardList.add(binding.button2)
        boardList.add(binding.button3)
        boardList.add(binding.button4)
        boardList.add(binding.button5)
        boardList.add(binding.button6)
        boardList.add(binding.button7)
        boardList.add(binding.button8)
        boardList.add(binding.button9)
    }

    fun boardTapped(view: View)
    {
        if(view !is Button)
            return
        addToBoard(view)
    }

    private fun addToBoard(button: Button)
    {
        if (button.text != "")
            return

        if (currentTurn == playerTurn.O){//O's turn
            button.text = O
            currentTurn = playerTurn.X
        }
        else if (currentTurn == playerTurn.X){//X's turn
            button.text = X
            currentTurn = playerTurn.O
        }

    }
    private fun setTurnText(){
        var turnText = ""
        if(currentTurn == playerTurn.X)
            turnText = "Player $X's turn"
        else if(currentTurn == playerTurn.O)
            turnText = "Player $O's turn"

        binding.Score.text = turnText
    }

    companion object{
        const val O = "O"
        const val X = "X"
    }
}
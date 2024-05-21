package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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


    private var xScore = 0
    private var oScore = 0

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
            // Initialize the game board
            initBoard()
            //Makes the turn text visible
            setTurnText()

        binding.newGameButton.setOnClickListener {
            clearBoard()
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

    fun boardTapped(view: View) {
        if (view !is Button)
            return
        addToBoard(view)

        // Check for a winner for both O and X
        if (gameWinner(O)) {
            oScore++
            result("O wins!")
        } else if (gameWinner(X)) {
            xScore++
            result("X wins!")
        } else if (fullBoard()) {
            result("Draw")
        }
    }

    private fun gameWinner(s: String): Boolean {
        //Horizontal Victory
        if (match(binding.button1, s) && match(binding.button2, s) && match(binding.button3, s))
            return true
        else if (match(binding.button4, s) && match(binding.button5, s) && match(binding.button6, s))
            return true
        else if (match(binding.button7, s) && match(binding.button8, s) && match(binding.button9, s))
            return true

        //Vertical Victory
        else if (match(binding.button1, s) && match(binding.button4, s) && match(binding.button7, s))
            return true
        else if (match(binding.button2, s) && match(binding.button5, s) && match(binding.button8, s))
            return true
        else if (match(binding.button3, s) && match(binding.button6, s) && match(binding.button9, s))
            return true

        //Diagonal Victory
        else if (match(binding.button1, s) && match(binding.button5, s) && match(binding.button9, s))
            return true
        else if (match(binding.button3, s) && match(binding.button5, s) && match(binding.button7, s))
            return true

        else
            return false
    }
    private fun match(button: Button, symbol : String): Boolean = button.text == symbol
    private fun result(title: String) {
        binding.Score.text = "Score O: $oScore X: $xScore"

        val message = "\nO: $oScore\n\nX: $xScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("New Game")
            {_,_->
                clearBoard()
                // Set first turn to X on new game
                currentTurn = playerTurn.X
                setTurnText()
            }

            .show()
    }

    private fun clearBoard() {
        for (button in boardList) {
            button.text = ""
        }
        // Set first turn to X on new game
        if(firstTurn == playerTurn.X)
            firstTurn = playerTurn.O
        else if(firstTurn == playerTurn.O)
            firstTurn = playerTurn.X

        currentTurn = firstTurn
        setTurnText()
    }

    private fun fullBoard(): Boolean {
        for(button in boardList){
            if (button.text == "")
                return false
        }
        return true
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
        setTurnText()

    }
    private fun setTurnText(){
        var turnText = ""
        if(currentTurn == playerTurn.X)
            turnText = "Player $X's turn"
        else if(currentTurn == playerTurn.O)
            turnText = "Player $O's turn"

        binding.Turn.text = turnText
    }

    companion object{
        const val O = "O"
        const val X = "X"
    }
}
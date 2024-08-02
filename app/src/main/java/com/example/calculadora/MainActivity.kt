package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private lateinit var display: TextView
    private var input = StringBuilder()
    private var expression = StringBuilder()
    private var value1 = Double.NaN
    private var value2 = 0.0
    private var currentAction: Char = ' '

    companion object {
        const val ADDITION = '+'
        const val SUBTRACTION = '-'
        const val MULTIPLICATION = '*'
        const val DIVISION = '/'
        const val EQU = 0.toChar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.display)
    }

    fun onNumberClick(view: View) {
        val button = view as Button
        input.append(button.text.toString())
        expression.append(button.text.toString())
        display.text = expression.toString()
    }

    fun onOperatorClick(view: View) {
        val button = view as Button
        if (!value1.isNaN()) {
            if (input.isNotEmpty()) {
                value2 = input.toString().toDouble()
                input.clear()
                calculate()
                currentAction = button.text[0]
                expression.append(currentAction)
                display.text = expression.toString()
            }
        } else {
            if (input.isNotEmpty()) {
                value1 = input.toString().toDouble()
                input.clear()
                currentAction = button.text[0]
                expression.append(currentAction)
                display.text = expression.toString()
            }
        }
    }

    fun onEqualsClick(view: View) {
        if (!value1.isNaN()) {
            if (input.isNotEmpty()) {
                value2 = input.toString().toDouble()
                input.clear()
                calculate()
                currentAction = EQU
                expression.append("=$value1")
                display.text = expression.toString()
                expression.clear()
                expression.append(value1)
            }
        }
    }

    private fun calculate() {
        when (currentAction) {
            ADDITION -> value1 += value2
            SUBTRACTION -> value1 -= value2
            MULTIPLICATION -> value1 *= value2
            DIVISION -> value1 /= value2
        }
    }

    fun onClearClick(view: View) {
        input.clear()
        expression.clear()
        value1 = Double.NaN
        value2 = Double.NaN
        display.text = ""
    }

    fun onTrigFunctionClick(view: View) {
        val button = view as Button
        val function = button.text.toString()
        val inputValue = input.toString().toDouble()
        input.clear()
        expression.clear()
        val result = when (function) {
            "sin" -> sin(inputValue)
            "cos" -> cos(inputValue)
            "tan" -> tan(inputValue)
            else -> 0.0
        }
        display.text = result.toString()
    }
}

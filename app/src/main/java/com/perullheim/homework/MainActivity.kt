package com.perullheim.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatToggleButton

class MainActivity : AppCompatActivity() {

    private var lastInput: String = ""

    private lateinit var toggleButton: AppCompatToggleButton
    private lateinit var resultText: AppCompatTextView
    private lateinit var editText: AppCompatEditText
    private lateinit var submitButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton = findViewById(R.id.toggleButton)
        resultText = findViewById(R.id.resultText)
        editText = findViewById(R.id.editText)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            lastInput = editText.text.toString()

            numberToLanguage()

            editText.setText("")
        }

        toggleButton.setOnClickListener {
            numberToLanguage()

            editText.hint = if (toggleButton.isChecked) "Enter number here" else "შეიყვანეთ ტექსტი აქ"
            submitButton.text = if (toggleButton.isChecked) "Submit" else "შეყვანა"
        }
    }

    private fun numberToLanguage() {
        when (toggleButton.isChecked) {
            true -> {
                submitText(lastInput)?.let { parts ->
                    resultText.text = numberToEnglish(parts)
                }
            }
            false -> {
                submitText(lastInput)?.let { parts ->
                    resultText.text = numberToGeorgian(parts)
                }
            }

        }
    }


    private fun submitText(text: String): List<Int>? {
        var input = text
        while (input.startsWith("0") && input.length > 1) {
            input = input.substring(1)
        }

        val digitCount = input.count()
        val parts = mutableListOf<String>()

        val remainder = digitCount % 3
        if (remainder != 0) {
            parts.add(input.substring(0, remainder))
        }

        for (i in remainder..<digitCount step 3) {
            parts.add(input.substring(i, i + 3))
        }

        return try {
            parts.map { it.toInt() }
        } catch (error: NumberFormatException) {
            resultText.text = if (toggleButton.isChecked) "try again" else "სცადეთ ხელახლა"
            null
        }
    }

    private fun numberToGeorgian(parts: List<Int>): String {
        val partsCount = parts.count()

        if (partsCount * 3 > GeoDict.base1000.keys.last()) {
            return "ძალიან დიდი რიცხვი!"
        }

        if (partsCount == 1 && parts.first() == 0) {
            return "ნული"
        }

        var result = ""

        mainLoop@ for (index in parts.indices) {
            val base1000Index = (partsCount - index - 1) * 3
            val base1000Word = GeoDict.base1000[base1000Index]

            val numberWord = parts[index].toGeorgian()

            if (numberWord != "ნული") {
                if (!(index == 0 && numberWord == "ერთი" && partsCount != 1)) {
                    result += numberWord
                    if (index < partsCount - 1)
                        result += " "
                }

                base1000Word?.let { word ->
                    result += word.dropLast(n = 1)
                    result += " "
                }
            }

            if (parts[index] == 0) {
                for (i in index..<partsCount) {
                    if (parts[i] != 0)
                        break
                    else if (i == partsCount - 1) {
                        result = result.dropLast(n = 1) + 'ი'
                        break@mainLoop
                    }
                }
            }
        }

        return result
    }

    private fun numberToEnglish(parts: List<Int>): String {
        val partsCount = parts.count()

        if (partsCount * 3 > EngDict.base1000.keys.last()) {
            return "Very large number!"
        }

        if (partsCount == 1 && parts.first() == 0) {
            return "zero"
        }

        var result = ""

        for (index in parts.indices) {
            val base1000Index = (partsCount - index - 1) * 3
            val base1000Word = EngDict.base1000[base1000Index]

            val numberWord = parts[index].toEnglish()

            if (numberWord != "zero") {
                result += numberWord
                if (index < partsCount - 1)
                    result += " "


                base1000Word?.let { word ->
                    result += word
                    result += " "
                }
            }
        }

        return result
    }
}
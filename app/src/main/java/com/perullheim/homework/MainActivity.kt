package com.perullheim.homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.perullheim.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val enteredWords = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            save()
        }

        binding.btnOutput.setOnClickListener {
            output()
        }

        binding.btnClear.setOnClickListener {
            clear()
        }
    }

    private fun save() {
        val newWord = binding.etAnagram.text.toString()
        binding.etAnagram.text = null

        if (!newWord.validateForWord()) {
            val errorMsg = "Not a valid word, use only letters!"
            binding.tvAnagramsCount.text = errorMsg
            return
        }

        enteredWords.add(newWord)
    }

    private fun output() {
        val newWord = binding.etAnagram.text.toString()
        binding.etAnagram.text = null

        if (!newWord.validateForWord()) {
            val errorMsg = "Not a valid word, use only letters!"
            binding.tvAnagramsCount.text = errorMsg
            return
        }

        if (newWord.isNotEmpty())
            enteredWords.add(newWord)


        val anagramsDict = enteredWords.groupBy { it.toCharArray().sorted().joinToString() }

        val anagramsList = anagramsDict.values.filter { it.size > 1 }

        val anagramCount = "Anagram Count -> ${anagramsList.count()}"
        binding.tvAnagramsCount.text = anagramCount
    }

    private fun clear() {
        enteredWords.clear()
        binding.etAnagram.text = null

        val anagramCount = "Anagram Count -> 0"
        binding.tvAnagramsCount.text = anagramCount
    }
}







package com.perullheim.homework

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView

class MainActivity : AppCompatActivity() {

    private lateinit var textFieldArray: Array<AppCompatEditText>
    private lateinit var resultTextViewsArray: Array<AppCompatTextView>

    // ------ Layouts ------
    private lateinit var fieldsLayout: LinearLayout
    private lateinit var resultsLayout: LinearLayout

    // ------ Text Fields ------
    private lateinit var email: AppCompatEditText
    private lateinit var username: AppCompatEditText
    private lateinit var firstName: AppCompatEditText
    private lateinit var lastName: AppCompatEditText
    private lateinit var age: AppCompatEditText

    // ------ Results ------
    private lateinit var emailResult: AppCompatTextView
    private lateinit var usernameResult: AppCompatTextView
    private lateinit var fullNameResult: AppCompatTextView
    private lateinit var ageResult: AppCompatTextView

    // ------ Error Message ------
    private lateinit var errorMsg: AppCompatTextView

    // ------ Buttons ------
    private lateinit var save: AppCompatButton
    private lateinit var clear: AppCompatButton
    private lateinit var again: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupViews()

        setupOnClickListeners()
    }

    // <=========> Validation Functions <=========>

    private fun areFieldsValid(): Boolean {
        errorMsg.text = when {
            !checkForEmptyTextFields() -> "Please fill out all of the fields!"
            !isUsernameValid() -> "Username is not valid!"
            !isEmailValid() -> "Email is not valid!"
            !isAgeValid() -> "Age is not valid!"
            else -> { errorMsg.text = null; return true }
        }
        return false
    }

    private fun checkForEmptyTextFields(): Boolean = textFieldArray.none { it.text.toString() == "" }

    private fun isUsernameValid(): Boolean = username.text.toString().length >= 10

    private fun isEmailValid(): Boolean {
        val enteredEmail = email.text.toString()
        val emailParts = enteredEmail.split('@', '.')

        return (emailParts.count() == 3 && emailParts.none { it.isEmpty() } && enteredEmail[emailParts.first().length] == '@')
    }

    private fun isAgeValid(): Boolean {
        try {
            age.text.toString().toUInt()
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

    // <=========> Helper Functions <=========>

    private fun setupOnClickListeners() {
        save.setOnClickListener {
            if (!areFieldsValid())
                return@setOnClickListener

            val email = email.text.toString()
            val username = username.text.toString()
            val fullName = firstName.text.toString() + " " + lastName.text.toString()
            val age = age.text.toString()

            emailResult.text = email
            usernameResult.text = username
            fullNameResult.text = fullName
            ageResult.text = age

            showResults()
        }

        clear.setOnLongClickListener {
            clearFields()
            true
        }

        again.setOnClickListener {
            clearResults()
            clearFields()
            showFields()
        }
    }

    private fun clearFields() {
        textFieldArray.forEach { it.text = null }
        errorMsg.text = null
    }

    private fun clearResults() {
        resultTextViewsArray.forEach { it.text = null }
    }

    private fun showFields() {
        resultsLayout.visibility = View.INVISIBLE
        fieldsLayout.visibility = View.VISIBLE
    }

    private fun showResults() {
        resultsLayout.visibility = View.VISIBLE
        fieldsLayout.visibility = View.INVISIBLE
    }

    private fun setupViews() {
        // ------ Layouts ------
        fieldsLayout = findViewById(R.id.fields)
        resultsLayout = findViewById(R.id.results)

        // ------ Text Fields ------
        email = findViewById(R.id.email)
        username = findViewById(R.id.username)
        firstName = findViewById(R.id.first_name)
        lastName = findViewById(R.id.last_name)
        age = findViewById(R.id.age)

        // ------ Results ------
        emailResult = findViewById(R.id.email_result)
        usernameResult = findViewById(R.id.username_result)
        fullNameResult = findViewById(R.id.full_name_result)
        ageResult = findViewById(R.id.age_result)

        // ------ Error Message ------
        errorMsg = findViewById(R.id.errorMsg)

        // ------ Buttons ------
        save = findViewById(R.id.save)
        clear = findViewById(R.id.clear)
        again = findViewById(R.id.again)

        textFieldArray = arrayOf(email, username, firstName, lastName, age)
        resultTextViewsArray = arrayOf(emailResult, usernameResult, fullNameResult, ageResult)
    }
}
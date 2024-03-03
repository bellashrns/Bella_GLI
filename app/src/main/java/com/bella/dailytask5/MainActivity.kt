package com.bella.dailytask5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fullName = findViewById<TextInputEditText>(R.id.fullName)
        val email = findViewById<TextInputEditText>(R.id.email)
        val gender = findViewById<TextInputEditText>(R.id.gender)
        val dob = findViewById<TextInputEditText>(R.id.dob)
        val address = findViewById<TextInputEditText>(R.id.address)
        val degree = findViewById<TextInputEditText>(R.id.degree)

        fullName.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
        email.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
        gender.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
        dob.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
        address.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
        degree.onFocusChangeListener = View.OnFocusChangeListener { view, _ -> onFocusChange(view) }
    }

    fun onButtonLogin(view: View) {
        if (iterateHelperText()) Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
    }

    private fun iterateHelperText(): Boolean {
        val fullName = findViewById<TextInputLayout>(R.id.fullName).helperText
        val email = findViewById<TextInputLayout>(R.id.email).helperText
        val gender = findViewById<TextInputLayout>(R.id.gender).helperText
        val dob = findViewById<TextInputLayout>(R.id.dob).helperText
        val address = findViewById<TextInputLayout>(R.id.address).helperText
        val degree = findViewById<TextInputLayout>(R.id.degree).helperText

        val helperTextList = listOf(fullName, email, gender, dob, address, degree)

        for (helperText in helperTextList) {
            if (helperText != null) {
                return false
            }
        }

        return true
    }

    private fun onFocusChange(view: View) {
        when (view.id) {
            R.id.fullName -> fullNameValidation()
            R.id.email -> emailValidation()
            R.id.gender -> genderValidation()
            R.id.dob -> dobValidation()
            R.id.address -> addressValidation()
            R.id.degree -> degreeValidation()
        }
    }

    private fun fullNameValidation(): Boolean {
        val fullNameLayout = findViewById<TextInputLayout>(R.id.fullNameLayout)
        val fullName = findViewById<TextInputEditText>(R.id.fullName)
        val fullNameText = fullName.text.toString()

        validationMsg(fullNameText.isEmpty(), fullNameLayout, "Full Name is required")

        return helperTextIsEmpty(fullNameLayout)
    }

    private fun emailValidation(): Boolean {
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val email = findViewById<TextInputEditText>(R.id.email)
        val emailText = email.text.toString()

        validationMsg(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches(), emailLayout, "Invalid email")

        return helperTextIsEmpty(emailLayout)
    }

    private fun genderValidation(): Boolean {
        val genderLayout = findViewById<TextInputLayout>(R.id.genderLayout)
        val gender = findViewById<TextInputEditText>(R.id.gender)
        val genderText = gender.text.toString()

        validationMsg(genderText.isEmpty(), genderLayout, "Gender is required")

        return helperTextIsEmpty(genderLayout)
    }

    private fun dobValidation(): Boolean {
        val dobLayout = findViewById<TextInputLayout>(R.id.dobLayout)
        val dob = findViewById<TextInputEditText>(R.id.dob)
        val dobText = dob.text.toString()

        validationMsg(dobText.isEmpty(), dobLayout, "Date of Birth is required")
//        validationMsg(validDate(dobText), dobLayout, "Invalid Date of Birth")

        return helperTextIsEmpty(dobLayout)
    }

    private fun validDate(dobText: String): Boolean {
        val dobArray = dobText.split("/")

        val day = dobArray[0].toInt()
        val month = dobArray[1].toInt()
        val year = dobArray[2].toInt()

        val invalidDay = day < 1 || day > 31
        val invalidMonth = month < 1 || month > 12
        val invalidYear = year < 1900 || year > 2024

        return !(invalidDay || invalidMonth || invalidYear)
    }

    private fun addressValidation(): Boolean {
        val addressLayout = findViewById<TextInputLayout>(R.id.addressLayout)
        val address = findViewById<TextInputEditText>(R.id.address)
        val addressText = address.text.toString()

        validationMsg(addressText.isEmpty(), addressLayout, "Address is required")

        return helperTextIsEmpty(addressLayout)
    }

    private fun degreeValidation(): Boolean {
        val degreeLayout = findViewById<TextInputLayout>(R.id.degreeLayout)
        val degree = findViewById<TextInputEditText>(R.id.degree)
        val degreeText = degree.text.toString()

        validationMsg(!validDegree(degreeText), degreeLayout, "Invalid Degree")

        return helperTextIsEmpty(degreeLayout)
    }

    private fun validDegree(degreeText: String): Boolean {
        val degreeArray = arrayOf("SD", "SMP", "SMA", "SMK", "D3", "D4", "S1", "S2", "S3")

        return degreeArray.contains(degreeText)
    }

    private fun validationMsg(condition: Boolean, layout: TextInputLayout, msg: String) {
        layout.helperText = if (condition) msg else null
    }

    private fun helperTextIsEmpty(layout: TextInputLayout): Boolean {
        return layout.helperText == null
    }
}
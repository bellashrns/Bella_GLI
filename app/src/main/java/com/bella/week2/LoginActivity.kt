package com.bella.week2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bella.week2.model.User
import com.bella.week2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            login(username, password)
        }
    }

    private fun login(username: String, password: String) {
        val user = User.getUser(username, password)

//      username dan password valid => product activity
        if (user != null) {
            setErrorMsg(getString(R.string.login_success), getColor(R.color.green))
            setErrorDesc(getString(R.string.login_success_desc, username))

            val intent = Intent(this, ProductActivity::class.java)
            startActivity(intent)
        }

//      username dan password tidak valid
        setErrorMsg(getString(R.string.login_failed), getColor(R.color.red))

        val usernameOrPassIsEmpty = username.isEmpty() || password.isEmpty()

        if (usernameOrPassIsEmpty) {
            setErrorDesc(getString(R.string.username_and_password_are_required))
        }

        val usernameAndPasswordLength =
            ((password.length < 8) || (password.length > 20)) && ((username.length < 6) || (username.length > 15))

        if (usernameAndPasswordLength) {
            setErrorDesc(getString(R.string.username_and_password_length))
        }

        val usernameAndPasswordCharacters =
            (!username.containsLetterAndDigit() || password.onlyLetterAndDigit() || !password.containsLetterAndDigit())

        if (usernameAndPasswordCharacters) {
            setErrorDesc(getString(R.string.username_and_password_characters))
        }
    }

    private fun setErrorMsg(msg: String, color: Int) {
        binding.errorMsg.text = msg
        binding.errorMsg.setTextColor(color)
    }

    private fun setErrorDesc(msg: String) {
        binding.errorDesc.text = msg
    }

    private fun String.containsLetterAndDigit(): Boolean {
        return this.any { it.isLetter() } && this.any { it.isDigit() }
    }

    private fun String.onlyLetterAndDigit(): Boolean {
        return this.all { it.isLetterOrDigit() }
    }
}
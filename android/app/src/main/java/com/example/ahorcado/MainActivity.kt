package com.example.ahorcado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.ahorcado.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val CORRECT_USERNAME = "ForzaFerrari@enti.cat"
    private val CORRECT_PASSWORD = "161616"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.loginButton.setOnClickListener {
            val username = binding.UserInput.text.toString()
            val password = binding.PasswordInput.text.toString()

            if (username == CORRECT_USERNAME && password == CORRECT_PASSWORD) {

                binding.loginProgressBar.visibility = View.VISIBLE

                CoroutineScope(Dispatchers.Default).launch {
                    delay(3000)

                    val intent = Intent(this@MainActivity, MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }
            }  else {
                Toast.makeText(this, "Incorrect Username or Password",Toast.LENGTH_SHORT).show()
            }

        }
        binding.UserInput.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val username = binding.UserInput.text.toString()
                if (!Patterns.EMAIL_ADDRESS.matcher(username).matches())
                    binding.UserInput.error = "Invalid username"
                else
                    binding.UserInput.error = null
            }
        }
    }
}
package com.example.ciperapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = findViewById<EditText>(R.id.inputText)
        val rails = findViewById<EditText>(R.id.rails)
        
        val encryptButton = findViewById<Button>(R.id.encryptButton)
        val decryptButton = findViewById<Button>(R.id.decryptButton)
        val resultText = findViewById<TextView>(R.id.resultText)
//        val registerButton = findViewById<Button>(R.id.registerButton)

        encryptButton.setOnClickListener { v: View? ->
            val text = inputText.text.toString()
            val numRails = rails.text.toString().toIntOrNull()

            if (numRails == null || numRails <= 0) {
                resultText.text = "Please enter a valid number of rails"
            } else {
                val encryptedText = RailFenceCipher.encrypt(text, numRails)
                resultText.text = encryptedText
            }
        }

        decryptButton.setOnClickListener { v: View? ->
            val text = inputText.text.toString()
            val numRails = rails.text.toString().toIntOrNull()

            if (numRails == null || numRails <= 0) {
                resultText.text = "Please enter a valid number of rails"
            } else {
                try {
                    val decryptedText = RailFenceCipher.decrypt(text, numRails)
                    resultText.text = decryptedText
                } catch (e: Exception) {
                    resultText.text = "Decryption error: ${e.message}"
                }
            }
        }

//        registerButton.setOnClickListener { v: View? ->
//            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
//            startActivity(intent)
//        }
    }
}

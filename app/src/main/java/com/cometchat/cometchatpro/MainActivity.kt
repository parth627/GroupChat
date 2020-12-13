package com.cometchat.cometchatpro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var join: Button
    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)


        join = findViewById(R.id.join_chat)
        join.setOnClickListener {
            disableAuthField()
            login()
        }
        val create = findViewById<Button>(R.id.create)
        create.setOnClickListener{
            val intent = Intent(this, NewusernameActivity::class.java)
            startActivity(intent)
        }

    }

    private fun disableAuthField() {
        join.isEnabled = false
        username.isEnabled = false
        password.isEnabled = false
    }

    private fun login() {
        CometChat.login(username.text.toString(), getString(R.string.apiKey), object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User) {
                username.setText("")

                enableAuthField()

                val intent = Intent(this@MainActivity, MessagesActivity::class.java)
                startActivity(intent)
            }

            override fun onError(e: CometChatException) {
                Toast.makeText(this@MainActivity, "Error or username doesn't exist.", Toast.LENGTH_SHORT).show()
                enableAuthField()
            }
        })
    }

    private fun enableAuthField() {
        join.isEnabled = true
        username.isEnabled = true
        password.isEnabled = true
    }
}

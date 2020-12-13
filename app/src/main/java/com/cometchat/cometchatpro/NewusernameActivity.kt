package com.cometchat.cometchatpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User


class NewusernameActivity : AppCompatActivity() {
    private lateinit var join: Button


    private lateinit var username: EditText
    private lateinit var password: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newusername)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)


        join = findViewById(R.id.join_chat)
        join.setOnClickListener {


            disableAuthField()
            createUser()
            login()
        }


    }
    private fun login() {
        CometChat.login(username.text.toString(), getString(R.string.apiKey), object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User) {
                username.setText("")

                enableAuthField()

                val intent = Intent(this@NewusernameActivity, MessagesActivity::class.java)
                startActivity(intent)
            }

            override fun onError(e: CometChatException) {
                Toast.makeText(this@NewusernameActivity, "Error or username doesn't exist.", Toast.LENGTH_SHORT).show()
                enableAuthField()
            }
        })
    }

    private fun createUser() {
        val apiKey = "37b6061f828679c531dc0fa7dc8e229df657f0d2" // Replace with your API Key.
        val user = User()
        user.uid = username.text.toString() // Replace with your uid for the user to be created.
        user.name = username.text.toString() // Replace with the name of the user

        CometChat.createUser(user, apiKey, object : CometChat.CallbackListener<User>() {
            override fun onSuccess(user: User) {
                Log.d("createUser", user.toString())

            }

            override fun onError(e: CometChatException) {
                Log.e("createUser", e.message)
            }
        })

    }

    private fun disableAuthField() {
        join.isEnabled = false
        username.isEnabled = false
        password.isEnabled = false
    }

    private fun enableAuthField() {
        join.isEnabled = true
        username.isEnabled = true
        password.isEnabled = true
    }



















}
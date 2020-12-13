package com.cometchat.cometchatpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



import android.content.Intent


import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User



class VideoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
    }
}
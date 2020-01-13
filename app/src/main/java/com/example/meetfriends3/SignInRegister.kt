package com.example.meetfriends3

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.signinregister.*

class SignInRegister : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signinregister)

        button_signin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)

            //start the second activity. With no return value
            startActivity(intent)
            finish()
        }
        button_register.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)

            //start the second activity. With no return value
            startActivity(intent)
            finish()
        }
    }
}
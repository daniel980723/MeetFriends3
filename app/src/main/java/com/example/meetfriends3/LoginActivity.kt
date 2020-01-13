package com.example.meetfriends3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var token = getSharedPreferences("loginDetails", Context.MODE_PRIVATE)
        buttonlogin.setOnClickListener {

            val username = editTextUsername.text.toString()
            val password = editText2.text.toString()

            val loginURL = getString(R.string.url_server) + getString(R.string.url_user_read_one) + "?username=" + username + "&password=" + password

            //val loginURL = getString(R.string.url_server) + getString(R.string.url_user_read_one) + "?username=Chai&password=chai5337"
            //output = (TextView) findViewById(R.id.jsonData);

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, loginURL, null,
                Response.Listener { response ->
                    try {
                        if (response != null) {
                            val strResponse = response.toString()
                            val jsonResponse  = JSONObject(strResponse)

                            if(jsonResponse != null){
                                Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
                                //val intent = Intent(this, MainActivity::class.java)
                                //intent.putExtra("username", username)
                                //intent.putExtra("password", password)
                                //intent.putExtra("loginStatus", "true")

                                var loginDetail = token.edit()
                                loginDetail.putString("username",username)
                                loginDetail.putString("password",password)
                                loginDetail.putString("loginStatus", "true")
                                loginDetail.commit()
                                //startActivity(intent)
                                finish()
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { e ->
                    Log.e("Volley", "Error" + e.message.toString())
                    Toast.makeText(this, "Username or password wrong", Toast.LENGTH_SHORT).show()
                }
            )
            // Access the RequestQueue through your singleton class.
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

        }
        button2.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)

            //start the second activity. With no return value
            startActivity(intent)
        }
    }
}

package com.example.meetfriends3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        buttonRegisterNow.setOnClickListener {
            var genderInput = 'E'
            val gender: Int = rdg.checkedRadioButtonId
            if(gender == radioButtonMale.id){
                genderInput = 'M'
            }
            else if(gender == radioButtonFemale.id){
                genderInput = 'F'
            }
            createUser(User(editTextRegUsername.text.toString(),
                editTextRegPassword.text.toString(),
                (editTextRegContact.text.toString()).toInt(),
                genderInput.toString(),
                (editTextRegAge.text.toString()).toInt(),
                editTextRegEmail.text.toString(),
                editTextRegAddress.text.toString(),
                editTextRegDescription.text.toString()
                ))
        }
    }

    private fun createUser(user: User) {
        val url = getString(R.string.url_server) + getString(R.string.url_user_create) + "?username=" + user.username +
                "&password=" + user.password +"&contact="+user.contact+ "&gender=" + user.gender +"&age="+user.age+ "&email=" + user.email+"&address="+user.address+"&description="+user.description

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    if (response != null) {
                        val success: String = response.get("success").toString()

                        if (success.equals("1")) {
                            Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                            //Add record to user list
                            //userList.add(user)
                            //Explicit Intent
                            val intent = Intent(this, LoginActivity::class.java)
                            //start the second activity. With no return value
                            startActivity(intent)
                        } else {
                            Toast.makeText(applicationContext,"Record not saved", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Main", "Response: %s".format(e.message.toString()))
                }
            },
            Response.ErrorListener { response ->
                Log.d("Main", "Response: %s".format(response.message.toString()))
            }
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }
    companion object {
        const val TAG = "com.example.MeetFriends3"
    }
}
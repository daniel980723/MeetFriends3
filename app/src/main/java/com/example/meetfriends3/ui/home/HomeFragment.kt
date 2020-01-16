package com.example.meetfriends3.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.example.meetfriends3.MySingleton
import com.example.meetfriends3.R
import com.example.meetfriends3.RegisterActivity.Companion.TAG
import com.example.meetfriends3.User
import com.example.meetfriends3.UserAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var userList: ArrayList<User>
    lateinit var adapter: UserAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        //val textView: TextView = root.findViewById(R.id.textViewHome)
        //homeViewModel.text.observe(this, Observer {
        //   textView.text = it
        //})
        //Initialise variables and UI

        userList = ArrayList<User>()

        adapter = UserAdapter(activity!!.applicationContext)
        adapter.setUsers(userList)
        //read()

        val recycleView = root.findViewById<RecyclerView>(R.id.recycleview)

            //recycleview.adapter = adapter
        //recycleview.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        buttonDislike.setOnClickListener(){
            read()
        }
        return root





    }


    private fun read() {
        val url = getString(R.string.url_server) + getString(R.string.url_user_read)

        //Display progress bar

        //Delete all user records
        userList.clear()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Process the JSON
                try{
                    if(response != null){
                        val strResponse = response.toString()
                        val jsonResponse  = JSONObject(strResponse)
                        val jsonArray: JSONArray = jsonResponse.getJSONArray("records")
                        val n = 0
                        val size: Int = jsonArray.length()

                        for(i in n..n){
                            var jsonUser: JSONObject = jsonArray.getJSONObject(i)
                            var user: User = User(
                                jsonUser.getString("username"),
                                jsonUser.getString("password"),
                                jsonUser.getInt("contact"),
                                jsonUser.getString("gender"),
                                jsonUser.getInt("age"),
                                jsonUser.getString("email"),
                                jsonUser.getString("address"),
                                jsonUser.getString("description")


                            )
                            userList.add(user)
                            recycleview.adapter = adapter
                            recycleview.layoutManager = LinearLayoutManager(activity!!.applicationContext)

                        }

                    }
                }catch (e:Exception){
                    Log.d("Main", "Response: %s".format(e.message.toString()))


                }
            },
            Response.ErrorListener { error ->
                Log.d("Main", "Response: %s".format(error.message.toString()))

            }
        )

        //Volley request policy, only one time request
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0, //no retry
            1f
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(activity!!.applicationContext).addToRequestQueue(jsonObjectRequest)

    }

}



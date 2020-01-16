package com.example.meetfriends3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>() // Cached copy of user

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView = itemView.findViewById(R.id.textViewusername)
        val userContactTextView : TextView = itemView.findViewById(R.id.textViewcontact)
        val userGenderTextView : TextView = itemView.findViewById(R.id.textViewgender)
        val userAgeTextView : TextView = itemView.findViewById(R.id.textViewage)
        val userEmailTextView : TextView = itemView.findViewById(R.id.textViewemail)
        val userAddressTextView : TextView = itemView.findViewById(R.id.textViewaddress)
        val userDescriptionTextView : TextView = itemView.findViewById(R.id.textViewdecription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.user_information, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.userNameTextView.text = current.username
        holder.userContactTextView.text = current.contact.toString()
        holder.userAgeTextView.text=current.age.toString()
        holder.userGenderTextView.text=current.gender
        holder.userEmailTextView.text=current.email
        holder.userDescriptionTextView.text=current.description
        holder.userAddressTextView.text=current.address
    }

    internal fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size
}

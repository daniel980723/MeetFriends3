package com.example.meetfriends3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="user")
data class User(@PrimaryKey val username: String,
                val password: String,
                val contact: Int,
                val gender: Char,
                val age:Int,
                val email: String,
                val address:String,
                val description:String
               ){
}
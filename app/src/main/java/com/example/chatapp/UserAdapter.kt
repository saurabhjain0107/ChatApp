package com.example.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context : Context, val userList : ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txt_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view: View = LayoutInflater.from(context).inflate(R.layout.user_lay,parent,false)
        return UserViewHolder(view)

    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentUser = userList[position]

        holder.txtName.text = currentUser.firstName

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}


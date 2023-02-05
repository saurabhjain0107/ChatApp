package com.example.chatapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

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

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("firstName",currentUser.firstName)
            intent.putExtra("lastName",currentUser.lastName)
            intent.putExtra("uid",currentUser.id)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}


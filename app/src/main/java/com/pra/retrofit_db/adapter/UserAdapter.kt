package com.pra.retrofit_db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pra.retrofit_db.databinding.RowUserBinding
import com.pra.retrofit_db.model.user.Result
import com.squareup.picasso.Picasso

class UserAdapter (var userList: List<Result>, private var context: Context) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mBinding = RowUserBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)
        return UserViewHolder(mBinding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model = userList[position]
        holder.binding.tvName.text = model.name.first
        holder.binding.tvCity.text = model.email
        Picasso.get().load(model.picture.thumbnail).into(holder.binding.ivProfile)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


     inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public val binding = RowUserBinding.bind(view)
    }
}
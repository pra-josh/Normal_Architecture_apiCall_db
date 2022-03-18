package com.pra.retrofit_db.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pra.retrofit_db.OnItemClickListener
import com.pra.retrofit_db.databinding.RowUserBinding
import com.pra.retrofit_db.model.user.User
import com.squareup.picasso.Picasso

class UserAdapter(var userList: List<User>,
                  private var context: Context,
                  private var mOnitemClick:OnItemClickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mBinding = RowUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return UserViewHolder(mBinding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val model = userList[position]
        holder.binding.tvName.text = model.cell
        holder.binding.tvCity.text = model.email
        Picasso.get().load("https://randomuser.me/api/portraits/women/36.jpg")
            .into(holder.binding.ivProfile)

        holder.binding.constraintParent.setOnClickListener {
           mOnitemClick.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        public val binding = RowUserBinding.bind(view)
    }
}
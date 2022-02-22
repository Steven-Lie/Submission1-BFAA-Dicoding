package com.dicoding.githubuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.githubuser.databinding.ItemRowUserBinding

class ListUserAdapter(private val listUser: ArrayList<User>): RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val name = listUser[position].name
        val username = listUser[position].username
        val followers = listUser[position].followers
        val following = listUser[position].following
        val avatar = listUser[position].avatar

        Glide.with(holder.itemView.context)
                .load(avatar)
                .circleCrop()
                .into(holder.binding.imgAvatar)
        holder.binding.tvNamaUser.text = name
        holder.binding.tvIdUser.text = username
        holder.binding.tvFollower.text = "$followers followers"
        holder.binding.tvFollowing.text = "$following following"

        holder.itemView.setOnClickListener(){
            onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

    class ListViewHolder(var binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }
}
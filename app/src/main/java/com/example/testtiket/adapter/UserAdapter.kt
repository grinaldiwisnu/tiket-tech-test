package com.example.testtiket.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtiket.databinding.ItemUserBinding
import com.example.testtiket.model.User

class UserAdapter(
    private val listUser: ArrayList<User>,
) : RecyclerView.Adapter<UserAdapter.CardViewHolder>() {
    inner class CardViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listUser: User) {
            binding.data = listUser
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                binding.root.transitionName = listUser.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        holder.bind(listUser[position])

    override fun getItemCount(): Int {
        return listUser.size
    }

    fun setData(items: List<User>) {
        listUser.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}
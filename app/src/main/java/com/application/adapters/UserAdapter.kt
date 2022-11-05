package com.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.application.R
import com.application.databinding.UserItemBinding
import com.application.models.UserModel
import com.application.utils.DiffUtil
import com.application.utils.IUserAdapterListener
import com.application.extensions.setImage


class UserAdapter(private val listener: IUserAdapterListener) :
    ListAdapter<UserModel, UserAdapter.UserViewHolder>(DiffUtil),
    View.OnClickListener {

    class UserViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(v: View?) {
        v?.let {
            if (it.id == R.id.btn_delete) {
                listener.deleteItem(it.tag as UserModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.btnDelete.setOnClickListener(this)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding) {
            tvUsername.text = user.username
            tvCareer.text = user.career
            ivAccIcon.setImage(user.photo)

            root.tag = user
            btnDelete.tag = user
        }
    }
}
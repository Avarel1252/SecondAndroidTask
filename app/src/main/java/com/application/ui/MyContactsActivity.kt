package com.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.databinding.ActivityMyContactsBinding
import com.application.models.UserModel
import com.application.models.UserViewModel
import com.application.adapters.UserAdapter
import com.application.utils.IUserAdapterListener

class MyContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyContactsBinding
    private lateinit var userViewModel: UserViewModel
    private val adapter = UserAdapter(object : IUserAdapterListener {
        override fun deleteItem(user: UserModel) {
            userViewModel.deleteUser(user)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        setObserver()
    }

    private fun initialize() {
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        with(binding) {
            rwUsers.adapter = adapter
            tvAddContacts.setOnClickListener {
                AddContactDialogFragment().show(
                    supportFragmentManager,
                    AddContactDialogFragment.TAG
                )
            }

        }
    }

    private fun setObserver() {
        userViewModel.userLiveData.observe(this) {
            adapter.submitList(it)
        }
    }

    fun addUserToModel(user: UserModel) {
        userViewModel.addUser(user)
    }

    fun getLastIdInModel(): Int {
        return userViewModel.getLastId()
    }
}
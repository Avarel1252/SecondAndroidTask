package com.application.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.databinding.ActivityMyContactsBinding
import com.application.models.UserModel
import com.application.models.UserViewModel
import com.secondandroidtask.adapters.UserAdapter

class MyContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyContactsBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        val adapter = UserAdapter(object : UserAdapter.Listener {
            override fun deleteItem(user: UserModel) {
                userViewModel.deleteUser(user)
            }
        })


        userViewModel.userLiveData.observe(this) {
            adapter.submitList(it)
        }
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

    fun addUserToModel(user: UserModel) {
        userViewModel.addUser(user)
    }

    fun getLastIdInModel(): Int {
        return userViewModel.getLastId()
    }


}
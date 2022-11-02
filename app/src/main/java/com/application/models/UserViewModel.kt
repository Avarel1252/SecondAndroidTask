package com.application.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getHardUsersList

class UserViewModel : ViewModel() {
    private val _userLiveData = MutableLiveData<List<UserModel>>()
    var userLiveData: LiveData<List<UserModel>> = _userLiveData

    init {
        _userLiveData.value = getHardUsersList()
    }

    fun deleteUser(user: UserModel) {
        with(_userLiveData) {
            value = value?.minus(user)
        }
    }

    fun addUser(user: UserModel) {
        with(_userLiveData) {
            value = value?.plus(user)
        }
    }

    fun getLastId(): Int {
        return _userLiveData.value?.last()?.id ?: -1
    }
}
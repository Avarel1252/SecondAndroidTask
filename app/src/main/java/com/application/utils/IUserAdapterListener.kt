package com.application.utils

import com.application.models.UserModel

interface IUserAdapterListener {
    fun deleteItem(user: UserModel)
}
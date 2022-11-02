package com.application.models

data class UserModel(
    var id: Int,
    val photo: String,
    val username: String,
    val career: String,
    val email: String,
    val phone: String,
    val homeAddress: String,
    val dataBirth: String
) : java.io.Serializable
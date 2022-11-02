package com

import com.application.models.UserModel

fun getHardUsersList(): List<UserModel> {
    val accIconUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdoikUA-Hjh54hb_aaAY9sBwE9h4cUneajPg&usqp=CAU"
    var list = ArrayList<UserModel>()
    list.add(
        UserModel(
            1,
            accIconUrl,
            "max kharchenko",
            "teacher",
            "max.harchenko@gmail.com",
            "+3805123123",
            "home street",
            "14.03"
        )
    )

    return list

}
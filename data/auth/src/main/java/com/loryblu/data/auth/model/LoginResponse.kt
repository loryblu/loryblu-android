package com.loryblu.data.auth.model

data class LoginResponse(
    val message: String,
    val data: Data
)

data class Data(
    val accessToken: String,
    val refreshToken: String,
    val user: User,
)

data class User(
    val childrens: List<Children>,
    val parentName: String,
    val pid: String
)
data class Children(
    val birthdate: String,
    val fullname: String,
    val gender: Gender,
    val id: Int
)

enum class Gender {
    male, female
}

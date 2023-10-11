package com.loryblu.feature.auth.register.model

data class FinalUserApi(
    var email: String,
    var password: String,
    var policiesAccepted: Boolean,
    var parentName: String,
    var childrenName: String,
    var childrenBirthDate: String,
    var childrenGender: String,
)

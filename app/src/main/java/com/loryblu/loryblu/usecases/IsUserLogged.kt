package com.loryblu.loryblu.usecases

interface IsUserLogged {
    suspend operator fun invoke(): Boolean
}

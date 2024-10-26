package com.loryblu.loryblu.usecases

interface UserLoginChecker {
    /**
     * Checks whether the user has saved credentials and attempts to log them in.
     *
     * This function performs the following actions:
     * 1. Verifies if the user's credentials are saved.
     * 2. If credentials are found, it attempts to log the user in.
     * 3. Returns `true` if the login is successful; otherwise, returns `false`.
     *
     * Possible outcomes:
     * - If the user has no saved credentials or the login fails, the function will return `false`.
     * - If the login is successful, it will return `true`.
     *
     * @return A Boolean indicating whether the user was successfully logged in.
     */
    suspend operator fun invoke(): Boolean
}

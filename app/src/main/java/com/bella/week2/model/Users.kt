package com.bella.week2.model

data class User(val username: String, val password: String) {
    companion object {
        private val users = listOf(
            User("global1234", "gL0b@l123@"),
            User("loyalty5678", "l0y@lTyS67B"),
            User("indonesia1945", "inD0n35!a19AS"),
            User("alfagift2024", "alF@g1fTz0z4"),
            User("bella123", "bella123!")
        )

        fun getUser(username: String, password: String): User? {
            return users.find { it.username == username && it.password == password }
        }
    }
}
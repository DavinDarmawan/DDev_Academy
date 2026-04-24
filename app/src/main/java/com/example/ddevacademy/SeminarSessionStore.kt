package com.example.ddevacademy

data class SeminarResultData(
    val username: String,
    val name: String,
    val email: String,
    val phone: String,
    val gender: String,
    val seminar: String,
)

object SeminarSessionStore {
    var latestSeminarResult: SeminarResultData? = null

    fun clear() {
        latestSeminarResult = null
    }
}

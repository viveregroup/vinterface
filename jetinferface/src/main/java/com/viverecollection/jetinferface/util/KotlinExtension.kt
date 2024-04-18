package com.viverecollection.jetinferface.util

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt
import kotlinx.datetime.todayIn

/**
 * Created by Annas Surdyanto on 16/12/21.
 *
 */

fun String.isPasswordValid(): Boolean {
    val passwordPattern = "^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 8 characters
            "$"
    val passwordMatcher = Regex(passwordPattern)
    return passwordMatcher.find(this) != null
}

fun String.separateMonthAndYear(): List<String> {
    var month: String
    val year: String
    if (this.isNotEmpty()) {
        month = this.substringBefore("-")
        year = this.substringAfter("-")
    } else {
        val currentTime = Clock.System.todayIn(TimeZone.currentSystemDefault())
        month = currentTime.monthNumber.toString()
        if (month.length == 1) month = "0$month"
        year = currentTime.year.toString()
    }
    return listOf(month, year)
}

fun String.asNavigationParam(): String{
    return this.replace("/", "_")
}

fun String.extractNavigationParam(): String{
    return this.replace("_", "/")
}
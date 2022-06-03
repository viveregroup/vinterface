package com.viverecollection.jetinferface.data

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayAt

data class MonthAndYear(
    val id: String,
    val name: String,
    val year: String
)

data class Month(
    val id: String,
    val name: String
) {
    companion object {
        val list = listOf(
            Month("01", "January"),
            Month("02", "February"),
            Month("03", "March"),
            Month("04", "April"),
            Month("05", "May"),
            Month("06", "June"),
            Month("07", "July"),
            Month("08", "August"),
            Month("09", "September"),
            Month("10", "October"),
            Month("11", "November"),
            Month("12", "December")
        )

        val secondLine = listOf(
            Month("05", "May"),
            Month("06", "June"),
            Month("07", "July"),
            Month("08", "August"),
        )

        val firstLine = listOf(
            Month("01", "January"),
            Month("02", "February"),
            Month("03", "March"),
            Month("04", "April"),
        )

        val thirdLine = listOf(
            Month("09", "September"),
            Month("10", "October"),
            Month("11", "November"),
            Month("12", "December")
        )

        fun getMonth(id: Int): Month {
            return list.find { it.id.toInt() == id }!!
        }

        fun getMonthLine(
            lineOne: (List<Month>) -> Unit,
            lineTwo: (List<Month>) -> Unit,
            lineThree: (List<Month>) -> Unit
        ) {
            lineOne(firstLine)
            lineTwo(secondLine)
            lineThree(thirdLine)
        }

        fun lastSixMonths(): List<MonthAndYear> {
            val currentTime = Clock.System.todayAt(TimeZone.currentSystemDefault())
            val reversedMonth = list.asReversed()
            val monthInMonthList =
                reversedMonth.find { it.name.lowercase() == currentTime.month.name.lowercase() }!!
            val monthIndex = reversedMonth.indexOf(monthInMonthList)
            val list = ArrayList<MonthAndYear>()
            reversedMonth.forEachIndexed { index, month ->
                if (list.size < 6 && index >= monthIndex) {
                    list.add(MonthAndYear(month.id, month.name, currentTime.year.toString()))
                }
            }
            if (list.size < 6) {
                val previousYear = currentTime.year - 1
                reversedMonth.forEach { month ->
                    if (list.size < 6) list.add(
                        list.size,
                        MonthAndYear(month.id, month.name, previousYear.toString())
                    )
                }
            }
            return list
        }
    }
}

fun threeYearsAround(): List<String> {
    val currentTime = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val yearList = mutableListOf<String>()
    yearList.add((currentTime.year - 1).toString())
    yearList.add(currentTime.year.toString())
    yearList.add((currentTime.year + 1).toString())
    return yearList
}

fun fiveYearsAround(): List<String> {
    val currentTime = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val yearList = mutableListOf<String>()
    yearList.add((currentTime.year - 2).toString())
    yearList.add((currentTime.year - 1).toString())
    yearList.add(currentTime.year.toString())
    yearList.add((currentTime.year + 1).toString())
    yearList.add((currentTime.year + 2).toString())
    return yearList
}
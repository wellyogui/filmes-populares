package br.well.coreapp.ext

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

const val YEAR_MONTH_DAY_HOUR = "yyyy-MM-dd"

@SuppressLint("SimpleDateFormat")
fun String.toHumanDate(): String {
    val dateFormat = SimpleDateFormat(YEAR_MONTH_DAY_HOUR)
    val date = dateFormat.parse(this)
    val formattedDate = SimpleDateFormat("dd/MM/yyyy")
    return formattedDate.format(date!!)}
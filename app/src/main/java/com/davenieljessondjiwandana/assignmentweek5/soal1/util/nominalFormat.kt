package com.davenieljessondjiwandana.assignmentweek5.soal1.util

fun nominalFormat(amount: Int):String {
    return "Rp "+String.format("%,d", amount).replace(',', '.')
}
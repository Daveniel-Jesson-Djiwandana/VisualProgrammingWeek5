package com.davenieljessondjiwandana.assignmentweek5.soal2.model

data class Question (
    val question: String,
    val options: List<String>,
    val jawaban: String
)
val allQuestions = listOf(
    Question("Ibu Kota Di Indonesia?", listOf("Surabaya", "Bandung", "Jakarta", "Medan"), "Jakarta"),
    Question("Ada berapa hari dalam seminggu?", listOf("11", "7", "5", "6"), "7"),
    Question("Mata Kuliah VP pakai apa?", listOf("Word", "Android Studio", "Excel", "Photoshop"), "Android Studio"),
    Question("Ibu Kota Jawa Timur?", listOf("Malang", "Surabaya", "Sidoarjo", "Kediri"), "Surabaya"),
    Question("Apakah Mata Kuliah VP susah?", listOf("Sangat Sulit", "Sangat Mudah", "Biasa", "Tidak Tahu"), "Sangat Mudah"),
    Question("Siapa dosen yang mengajar VP?", listOf("Pak Rudi", "Bu Eliz", "Pak Christo", "Pak Mychael"), "Pak Mychael"),
    Question("Siapa tutor yang mengajar VP?", listOf("Ko Timothy", "Ko Probo", "Ko Galih", "Ko Rei"), "Ko Timothy")
)
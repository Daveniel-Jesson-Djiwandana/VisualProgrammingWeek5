package com.davenieljessondjiwandana.assignmentweek5.soal2.viewmodel

import androidx.lifecycle.ViewModel
import com.davenieljessondjiwandana.assignmentweek5.soal2.model.Question
import com.davenieljessondjiwandana.assignmentweek5.soal2.model.allQuestions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuestionsViewModel: ViewModel() {
    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()
    private val max = allQuestions.size
    private val _answers = MutableStateFlow<List<String>>(emptyList())
    val answers: StateFlow<List<String>> = _answers.asStateFlow()
    private val _dipilih = MutableStateFlow<String?>(null)
    val dipilih: StateFlow<String?> = _dipilih.asStateFlow()

    private val _jumlahSoal = MutableStateFlow("")
    val jumlahSoal: StateFlow<String> = _jumlahSoal.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    private var inputJumlah = 0

    fun updateJumlah(newJumlah: String) {
        _jumlahSoal.value = newJumlah.filter {it.isDigit()}
        _error.value = null
    }
    fun mulai() {
        val num = _jumlahSoal.value.toIntOrNull()
        if (num == null || num <= 0) {
            _error.value = "You need at least 1 question!"
            return
        }
        if (num > max) {
            _error.value = "Maximum is $max questions."
            return
        }
        inputJumlah = num
        _questions.value = allQuestions.shuffled().take(inputJumlah)
        _answers.value = emptyList()
        _dipilih.value = null
    }
    fun pilih(terpilih: String) {
        _dipilih.value = terpilih
    }
    fun nextSoal() {
        val pilihan = _dipilih.value
        if (pilihan == null) {
            return
        }
        _answers.update {it + pilihan}
        _dipilih.value = null
    }
    fun restart() {
        _questions.value = emptyList()
        _answers.value = emptyList()
        _dipilih.value = null
        _jumlahSoal.value = ""
    }
}
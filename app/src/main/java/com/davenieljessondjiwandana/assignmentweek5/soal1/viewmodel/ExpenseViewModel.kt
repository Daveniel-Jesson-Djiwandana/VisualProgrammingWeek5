package com.davenieljessondjiwandana.assignmentweek5.soal1.viewmodel


import androidx.lifecycle.ViewModel
import com.davenieljessondjiwandana.assignmentweek5.soal1.model.Expense
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExpenseViewModel: ViewModel() {
    private val _expenseList = MutableStateFlow<List<Expense>>(emptyList())
    val expenseList: StateFlow<List<Expense>> = _expenseList.asStateFlow()

    // total?? mestinya total juga di viewmodel ini?
    fun total(): Int {
        return _expenseList.value.sumOf { it.amount }
    }

    //inputan dari user
    private val _nama = MutableStateFlow("")
    val nama: StateFlow<String> = _nama.asStateFlow()
    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()
    private val _namaError = MutableStateFlow<String?>(null)
    val namaError: StateFlow<String?> = _namaError.asStateFlow()
    private val _amountError = MutableStateFlow<String?>(null)
    val amountError: StateFlow<String?> = _amountError.asStateFlow()
    private fun nextId(): Int {
        val idNow = if (_expenseList.value.isEmpty()) {0}
            else {_expenseList.value.last().id}
        return idNow + 1
    }

    fun namaChange(newNama: String) {
        _nama.value = newNama
        _namaError.value = null
    }
    fun amountChange(newAmount: String) {
        _amount.value = newAmount.filter {it.isDigit()}
        _amountError.value = null
    }
    fun addExpense() {
        val namaTemp = _nama.value.trim()
        val amountTemp = _amount.value.toIntOrNull()
        if (namaTemp.isEmpty()) {
            _namaError.value = "Your name have to not be empty!"
            return
        }
        if (amountTemp == null || amountTemp <= 0) {
            _amountError.value = "Amount must be greater than 0"
            return
        }
        _expenseList.update {it + Expense(nextId(), namaTemp, amountTemp)}
        _nama.value = ""
        _amount.value = ""
        _namaError.value = null
        _amountError.value = null
    }
    fun deleteExpense(id: Int) {
        _expenseList.update { list ->
            list.filter{it.id != id}
        }
    }
    fun updateExpense(id: Int, newAmount: String): Boolean {
        val amountTemp: Int? = newAmount.filter {it.isDigit()}.toIntOrNull()
        if (amountTemp == null || amountTemp <= 0) {
            return false
        }
        _expenseList.update { list ->
            list.map {
                if (it.id == id) {
                    it.copy(amount = amountTemp)
                } else {
                    it
                }
            }
        }
        return true


    }

}
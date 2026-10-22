package com.davenieljessondjiwandana.assignmentweek5.soal1.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davenieljessondjiwandana.assignmentweek5.soal1.model.Expense
import com.davenieljessondjiwandana.assignmentweek5.soal1.util.nominalFormat
import com.davenieljessondjiwandana.assignmentweek5.soal1.viewmodel.ExpenseViewModel

@Composable
fun Soal1ExpenseScreen(viewmodel: ExpenseViewModel= viewModel()) {
    val expenseList by viewmodel.expenseList.collectAsState()
    val nama by viewmodel.nama.collectAsState()
    val amount by viewmodel.amount.collectAsState()
    val namaError by viewmodel.namaError.collectAsState()
    val amountError by viewmodel.amountError.collectAsState()

    var lagiEdit by rememberSaveable {mutableStateOf<Expense?>(null)}
    var editanText by rememberSaveable {mutableStateOf("")}
    var editanError by rememberSaveable {mutableStateOf(false)}

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, start = 14.dp, end = 14.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Expenses", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Text("Total Expenses : ${nominalFormat(viewmodel.total())}", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = nama,
                onValueChange = viewmodel::namaChange,
                label = {Text("Expense Name")},
                modifier = Modifier.fillMaxWidth(),
                isError = namaError != null,
                supportingText = {
                    if (namaError != null) {
                        Text(namaError!!, fontSize = 10.sp)
                    }},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorCursorColor = Color.Red,
                    errorSupportingTextColor = Color.Red)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = amount,
                onValueChange = viewmodel::amountChange,
                label = {Text("Amount of Expense")},
                modifier = Modifier.weight(1f),
                isError = amountError != null,
                supportingText = {
                    if (amountError != null) {
                        Text(amountError!!, fontSize = 10.sp)
                    }},
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color.Blue,
                    unfocusedLabelColor = Color.Gray,
                    cursorColor = Color.Blue,
                    errorBorderColor = Color.Red,
                    errorLabelColor = Color.Red,
                    errorCursorColor = Color.Red,
                    errorSupportingTextColor = Color.Red)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = viewmodel::addExpense,
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Gray
                ),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Expense", tint = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        val edit = lagiEdit
        if (edit != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Edit ${edit.name}", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = editanText,
                        onValueChange = {
                            editanText = it.filter {c ->c.isDigit()}
                            editanError = false
                        },
                        label = {Text("Amount of Expense")},
                        singleLine = true,
                        isError = editanError,
                        supportingText = {
                            if (editanError) {
                                Text("Amount must be greater than 0", fontSize = 10.sp
                            )}
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Blue,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = Color.Blue,
                            unfocusedLabelColor = Color.Gray,
                            cursorColor = Color.Blue,
                            errorBorderColor = Color.Red,
                            errorLabelColor = Color.Red,
                            errorCursorColor = Color.Red,
                            errorSupportingTextColor = Color.Red)
                    )
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        TextButton(onClick = {
                            lagiEdit = null
                        }) {
                            Text("Cancel", color = Color.Red)
                        }
                        TextButton(onClick = {
                            if (viewmodel.updateExpense(edit.id, editanText)) {
                                lagiEdit = null
                            } else {
                                editanError = true
                            }
                        }) {
                            Text("Save", color = Color.Black)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(expenseList, key = {it.id}) { expense ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDCE2F9)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp, end = 10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier
                            .weight(3f)
                            .padding(vertical = 20.dp), verticalArrangement = Arrangement.Center) {
                            Text("Name : ${expense.name}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${nominalFormat(expense.amount)}", fontSize = 14.sp)
                        }
                        IconButton(
                            onClick = {
                                lagiEdit = expense
                                editanText = expense.amount.toString()
                                editanError = false
                            }
                        ) {
                            Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color(0xFFC99700))
                        }
                        IconButton(
                            onClick = {viewmodel.deleteExpense(expense.id)}
                        ) {
                            Icon(Icons.Filled.Delete, contentDescription = "Remove", tint = Color.Red)
                        }
                    }
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    Soal1ExpenseScreen()
}
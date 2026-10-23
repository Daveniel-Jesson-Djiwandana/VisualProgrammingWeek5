package com.davenieljessondjiwandana.assignmentweek5.soal2.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davenieljessondjiwandana.assignmentweek5.soal2.viewmodel.QuestionsViewModel

@Composable
fun QuizScreen(viewmodel: QuestionsViewModel = viewModel()) {
    val questions by viewmodel.questions.collectAsState()
    val answers by viewmodel.answers.collectAsState()
    val dipilih by viewmodel.dipilih.collectAsState()
    val indexSoal = answers.size
    val question = questions[indexSoal]

    Column(modifier = Modifier.fillMaxSize().padding(vertical = 26.dp, horizontal = 14.dp), verticalArrangement = Arrangement.Center) {
        Text("Question ${indexSoal+1}", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(4.dp))
        Text(question.question, fontSize = 14.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))
        question.options.forEach { pilihan ->
            Button(
                onClick = {viewmodel.pilih(pilihan)}, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (pilihan == dipilih) {Color(0xFF4D5D8A)} else {Color(0xFFDCE2F9)},
                    contentColor = if (pilihan == dipilih) {Color.White} else {Color.Black},
                )
            ) {
                Text(pilihan)
            }

        }
        Spacer(modifier = Modifier.height(18.dp))
        Button(
            onClick = {viewmodel.nextSoal()}, enabled = dipilih != null, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4D5D8A),
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray,
            )
        ) {
            Text("Next")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen()
}
package com.davenieljessondjiwandana.assignmentweek5.soal2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
fun ResultScreen(viewmodel: QuestionsViewModel = viewModel()) {
    val questions by viewmodel.questions.collectAsState()
    val answers by viewmodel.answers.collectAsState()
    val score = questions.indices.count {answers[it] == questions[it].jawaban}

    Column(modifier = Modifier.fillMaxSize().padding(vertical = 26.dp, horizontal = 14.dp), verticalArrangement = Arrangement.Center) {
        Text("Quiz finished", fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Your score: $score / ${questions.size}", fontSize = 14.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(12.dp))

        if (score == questions.size) {
            Text("Perfect! No Wrong answers.", fontSize = 14.sp, color = Color(0xFF727FA5), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        } else {
            questions.forEachIndexed { i, question ->
                if (answers[i] != question.jawaban) {
                    Text("Q${i+1}: ${question.question}", fontSize = 14.sp)
                    Text("Your Answer: ${answers[i]}", fontSize = 12.sp, color = Color.Red)
                    Text("Correct Answer: ${question.jawaban}", fontSize = 12.sp, color = Color(0xFF727FA5))
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color.Black))
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {viewmodel.restart()}, modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4D5D8A),
                contentColor = Color.White,
            )
        ) {
            Text("Restart Quiz")
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    ResultScreen()
}
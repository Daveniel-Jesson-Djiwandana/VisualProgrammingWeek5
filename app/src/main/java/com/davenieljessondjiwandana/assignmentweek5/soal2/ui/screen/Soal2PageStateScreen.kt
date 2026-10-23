package com.davenieljessondjiwandana.assignmentweek5.soal2.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davenieljessondjiwandana.assignmentweek5.soal2.viewmodel.QuestionsViewModel

@Composable
fun Soal2PageStateScreen(viewmodel: QuestionsViewModel = viewModel()) {
    val questions by viewmodel.questions.collectAsState()
    val answers by viewmodel.answers.collectAsState()
    if (questions.isEmpty()) {
      StartScreen(viewmodel = viewmodel)
    } else if (answers.size >= questions.size) {
        ResultScreen(viewmodel = viewmodel)
    } else {
        QuizScreen(viewmodel = viewmodel)
    }
}
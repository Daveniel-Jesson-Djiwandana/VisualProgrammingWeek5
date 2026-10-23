package com.davenieljessondjiwandana.assignmentweek5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.davenieljessondjiwandana.assignmentweek5.soal1.ui.screen.Soal1ExpenseScreen
import com.davenieljessondjiwandana.assignmentweek5.soal2.ui.screen.Soal2PageStateScreen
import com.davenieljessondjiwandana.assignmentweek5.ui.theme.AssignmentWeek5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AssignmentWeek5Theme {
                Soal1ExpenseScreen()
                //Soal2PageStateScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SoalPreview() {
    AssignmentWeek5Theme {
        Soal1ExpenseScreen()
        //Soal2PageStateScreen()
    }
}
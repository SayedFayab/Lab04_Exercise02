package com.example.group5sayed_and_selenecomp304sec005_lab04_ex02

import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Typography
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.group5sayed_and_selenecomp304sec005_lab04_ex02.ui.theme.Group5Sayed_and_SeleneCOMP304SEC005_Lab04_Ex02Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val programNames = resources.getStringArray(R.array.program_names)
        val programDescriptions = resources.getStringArray(R.array.program_descriptions)

        val programs = mutableListOf<Program>()

        for (i in programNames.indices){
            val program = Program(programNames[i],programDescriptions[i])
            programs.add(program)
        }

        setContent {
            Group5Sayed_and_SeleneCOMP304SEC005_Lab04_Ex02Theme{
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    )  {
                        Text(text = "List Of Available Programs",
                            style = typography.h5.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colors.primary)
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        ProgramList(programs = programs)
                    }
                }
            }
        }
    }
}

@Composable
fun ProgramList(programs: List<Program>){
    LazyColumn{
        items(programs){
                program -> Column {
            ProgramCard(program)
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
        }
        }
    }
}

@Composable
fun ProgramCard(program: Program) {
    Column {
        Text(text = program.name, style = typography.h6.copy(color = MaterialTheme.colors.secondary))
        Spacer(modifier = Modifier.width(4.dp))
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Surface(shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            color= surfaceColor,
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .clickable { isExpanded = !isExpanded }) {
            Text(
                text = program.description,
                modifier = Modifier
                    .padding(start = 10.dp, top = 15.dp, end = 10.dp, bottom = 16.dp)
                    .animateContentSize(),
                style = Typography.subtitle2,
                color = MaterialTheme.colors.onBackground,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun PreviewProgramCard() {
    val programs = listOf(
        Program("Sample Program 1", "This is the first sample program."),
        Program("Sample Program 2", "This is the second sample program.")
    )
    ProgramList(programs = programs)
}

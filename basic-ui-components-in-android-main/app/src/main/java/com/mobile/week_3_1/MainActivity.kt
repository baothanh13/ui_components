package com.mobile.week_3_1
import android.graphics.fonts.FontStyle
import androidx.compose.ui.text.font.FontWeight
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mobile.week_3_1.R
import com.mobile.week_3_1.ui.theme.Week_3_1Theme
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun WelcomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text("Jetpack Compose", fontSize = 24.sp)
        Text("Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach..", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { /* Navigate to Components Screen */ },
            shape = RoundedCornerShape(10.dp)
        ) {
            Text("I'm Ready")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    Week_3_1Theme {
        WelcomeScreen()
    }
}
@Composable
fun WelcomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual image
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(text = "Jetpack Compose", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Modern UI toolkit for building native Android apps.",
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )
        Button(
            onClick = { navController.navigate("components_list") },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "I'm Ready")
        }
    }
}
@Composable
fun ComponentsListScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        // Title
        Text(
            text = "UI Components List",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display Section
        SectionHeader("Display")
        ComponentCard(title = "Text", description = "Displays text") {
            navController.navigate("text_detail")
        }
        ComponentCard(title = "Image", description = "Displays an image") { /* TODO */ }

        // Input Section
        SectionHeader("Input")
        ComponentCard(title = "TextField", description = "Input field for text") { /* TODO */ }
        ComponentCard(title = "PasswordField", description = "Input field for passwords") { /* TODO */ }

        // Layout Section
        SectionHeader("Layout")
        ComponentCard(title = "Column", description = "Arranges elements vertically") { /* TODO */ }
        ComponentCard(title = "Row", description = "Arranges elements horizontally") { /* TODO */ }
    }
}

// Section Header
@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}

// Component Card
@Composable
fun ComponentCard(title: String, description: String, onClick: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD0E8FF)), // Light Blue
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 14.sp)
        }
    }
}

@Composable
fun TextDetailScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Text Detail", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                    append("The ")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp, textDecoration = TextDecoration.LineThrough)) {
                    append("quick ")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp, color = Color(0xFFB87333))) {
                    append("Brown ")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)) {
                    append("fox ")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp, textDecoration = TextDecoration.Underline)) {
                    append("jumps ")
                }
                withStyle(style = SpanStyle(fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)) {
                    append("over ")
                }
                withStyle(style = SpanStyle(
                    fontSize = 18.sp,

                )) {
                    append("the lazy dog.")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "welcome") {
                composable("welcome") { WelcomeScreen(navController) }
                composable("components_list") { ComponentsListScreen(navController) }
                composable("text_detail") { TextDetailScreen(navController) }
            }
        }
    }
}
package dme.systems.mentalhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dme.systems.mentalhealth.ui.theme.MentalHealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultView()
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Preview(showSystemUi = true)
@Composable
fun DefaultView() {

    val symptoms = listOf("25 yo", "Depression", "Takes meds")
    val complaints =
        listOf(
            "bad mood",
            "insomnia",
            "apathy",
            "anger",
            "mood swings",
            "panic attacks",
            "anger",
            "mood swings",
            "panic attacks"
        )
    val general_info = listOf(
        Information("First name", "Luku"),
        Information("Last name", "Geraud"),
        Information("Date of Birth", "07 June 1995"),
        Information("Gender", "Male"),
    )
    val menus = listOf(
        Menu("Information", true),
        Menu("Medicine"),
        Menu("Diagnoses")
    )

    MentalHealthTheme {
    }
}
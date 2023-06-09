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
        // A surface container using the 'background' color from the theme
        Scaffold(
            topBar = {
                // Top Action bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )

                    }

                    Text("Session Info", color = Color.DarkGray)

                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = "Search Icon",
                            tint = Color.Gray
                        )
                    }
                }
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it)
                    .padding(horizontal = 16.dp)
            ) {

                // User Information
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // circular image
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                    )

                    // Information Section
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        Text(text = "Tom Stuart", fontSize = 16.sp, color = Color.Black)

                        LazyRow {
                            itemsIndexed(symptoms) { index, symptom ->
                                Text(text = symptom, color = Color.Gray, fontSize = 12.sp)
                                //if its not the last item add space to the end
                                if (index < symptoms.size - 1)
                                    Spacer(modifier = Modifier.size(20.dp))
                            }
                        }

                        Text(
                            text = "11 Feb 2021 16:00 - 17:00",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = colorResource(R.color.green)
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                // Complaints Section
                Text(
                    text = "Complaints",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                //complaints flow row
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                ) {
                    complaints.forEach { complain ->
                        Text(
                            text = complain,
                            color = Color.White,
                            modifier = Modifier
                                .background(
                                    color = colorResource(R.color.green),
                                    shape = RoundedCornerShape(30)
                                )
                                .padding(8.dp)
                                .padding(bottom = 8.dp),
                            fontSize = 12.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                LazyRow {
                    items(menus) { item: Menu ->

                        // save the selectinf state of the menu item
                        var isSelected by remember {
                            mutableStateOf(item.isSelected)
                        }

                        ClickableText(
                            modifier = Modifier.padding(end = 20.dp),
                            text = AnnotatedString(
                                item.name, spanStyle = SpanStyle(
                                    color = if (isSelected) colorResource(id = R.color.green) else Color.Gray,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ), onClick = { isSelected = !isSelected }
                        )
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(5))
                        .background(color = colorResource(id = R.color.green_lt))
                        .padding(15.dp)
                ) {
                    Text(
                        text = "General",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    LazyColumn {
                        items(general_info) { item: Information ->
                            Column(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = item.key, fontSize = 13.sp, color = Color.Gray)
                                    Text(text = item.value, fontSize = 13.sp)
                                }
                                Divider(thickness = 1.dp, color = Color.Gray)
                            }
                        }
                    }

                }

                Spacer(modifier = Modifier.size(20.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(5))
                        .background(color = colorResource(id = R.color.green_lt))
                        .padding(15.dp)
                ) {
                    Text(
                        text = "Additional",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    Text(
                        text = "Therapist Notes:",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    Text(
                        text = stringResource(R.string.text),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .verticalScroll(rememberScrollState())
                    )
                }

            }
        }
    }
}
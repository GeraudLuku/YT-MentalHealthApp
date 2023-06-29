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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            "mood swings",
            "apathy",
            "panic attacks",
            "anger",
        )
    val general_info = listOf(
        Information("First name", "Luku"),
        Information("Last name", "Geraud"),
        Information("Date of Birth", "07 June 1995"),
        Information("Gender", "Male"),
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

                    TopBarIcon(resourceId = Icons.Filled.ArrowBack)

                    Text("Session Info", color = Color.DarkGray, fontWeight = FontWeight.Bold)

                    TopBarIcon(resourceId = Icons.Outlined.Edit)

                }
            }
        ) {
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
                    // Circular Profile Image
                    Image(
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                    )

                    // Information Section
                    Column(
                        modifier = Modifier
                            .padding(start = 15.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(text = "Tom Stuart", fontSize = 16.sp, color = Color.Black)

                        LazyRow(
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                                .padding(bottom = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            itemsIndexed(symptoms) { index, symptom ->
                                Text(text = symptom, color = Color.Gray, fontSize = 12.sp)
                                //if its not the last item add space to the end
                                if (index < symptoms.size - 1) {
                                    Spacer(modifier = Modifier.size(5.dp))
                                    DotSeparator()
                                    Spacer(modifier = Modifier.size(5.dp))
                                }
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

                Spacer(modifier = Modifier.size(30.dp))

                // Complaints Section
                Text(
                    text = "Complaints",
                    fontSize = 20.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Complaints Flow Row
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    complaints.forEach { complain ->
                        ComplaintItem(complain = complain)
                    }
                }

                Spacer(modifier = Modifier.size(20.dp))

                //create tab layout
                InformationSection()
            }
        }
    }
}


@Composable
fun TopBarIcon(resourceId: ImageVector) {
    IconButton(
        modifier = Modifier
            .padding(10.dp)
            .background(
                colorResource(id = R.color.green_lt), shape = RoundedCornerShape(10)
            ),
        onClick = { }
    ) {
        Icon(
            resourceId,
            contentDescription = "Search Icon",
            tint = Color.Gray
        )

    }
}

@Composable
fun DotSeparator() {
    Box(
        modifier = Modifier
            .size(3.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun ComplaintItem(complain: String) {

    Box(
        modifier = Modifier
            .padding(vertical = 3.dp)
            .background(
                color = colorResource(id = R.color.green),
                shape = RoundedCornerShape(30)
            )
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = complain,
            color = Color.White,
            fontSize = 12.sp
        )
    }

}

@Composable
fun InformationSection() {
    var tabIndex by remember {
        mutableStateOf(0)
    }

    val menus = listOf(
        Menu("Informatio", true),
        Menu("Medicine"),
        Menu("Diagnoses")
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent,
            divider = {
                      Divider(color = Color.Transparent, thickness = 0.dp)
            },
        ) {
            menus.forEachIndexed { index, menu ->

                Tab(
                    text = {
                        Text(
                            text = menu.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (tabIndex == index) colorResource(id = R.color.green) else Color.Gray
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )

            }
        }

        // select composable depending on the selection
        when (tabIndex) {
            0 -> InformationScreen()
            else -> {
                Text(text = "Other Page")
            }
        }
    }
}


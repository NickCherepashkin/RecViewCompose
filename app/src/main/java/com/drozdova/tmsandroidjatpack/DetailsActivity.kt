package com.drozdova.tmsandroidjatpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drozdova.tmsandroidjatpack.ui.theme.TMSAndroidJatPackTheme

class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMSAndroidJatPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val intent  = getIntent()
                    val image = intent.getIntExtra("image", R.drawable.break_dance)
                    val title = intent.getStringExtra("title") ?: ""
                    val description = intent.getStringExtra("description") ?: ""
                    val time = intent.getStringExtra("time") ?: ""

                    showDetails(image = image, title = title, time = time, description = description)
                }
            }
        }
    }
}

@Composable
fun showDetails(image: Int, title: String, description: String, time: String) {
    Column (
        modifier = Modifier.fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    )  {
        Image(
            painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(vertical = 20.dp, horizontal = 30.dp)
                .weight(2F)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

        )
        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(3F)
        )
        Text(
            text = time,
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.weight(1F)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    TMSAndroidJatPackTheme {
        showDetails(R.drawable.break_dance, "", "", "")
    }
}
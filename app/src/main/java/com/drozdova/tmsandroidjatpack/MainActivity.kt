package com.drozdova.tmsandroidjatpack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drozdova.tmsandroidjatpack.model.DanceModel
import com.drozdova.tmsandroidjatpack.ui.theme.TMSAndroidJatPackTheme
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMSAndroidJatPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    setRecyclerView()
                }
            }
        }
    }
}

@Composable
fun setRecyclerView() {
    val list = remember{DataProvider.dancesList}
    Column (horizontalAlignment = CenterHorizontally) {
        Text(
            text = stringResource(R.string.style_dance_text),
            fontSize = 26.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(all = 5.dp)
        )
        LazyColumn(contentPadding = PaddingValues(horizontal = 15.dp, vertical = 10.dp)) {
            items(
                items = list,
                itemContent = { dance ->
                    DanceListItem(danceItem = dance)
                }
            )
        }
    }

}


@Composable
fun DanceListItem(danceItem: DanceModel) {
    var isChecked = false
    lateinit var time :String
    val context = LocalContext.current
    val img = remember {
        mutableStateOf(danceItem.stateImg)
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.Green,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row (
            modifier = Modifier.clickable {
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(KEY_IMAGE, danceItem.image)
                intent.putExtra(KEY_TITLE, danceItem.title)
                intent.putExtra(KEY_DESCRIPTION, danceItem.description)
                intent.putExtra(KEY_TIME, time)
                context.startActivity(intent)
            }
        )  {
            Row (
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Image(
                    painterResource(danceItem.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(84.dp)
                        .padding(vertical = 5.dp, horizontal = 20.dp)
                        .weight(1.5F)
                        .align(CenterVertically)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))

                )
                Column(
                    modifier = Modifier.weight(3F)
                ) {
                    time = SimpleDateFormat(stringResource(R.string.date_format)).format(Date())
                    Text(
                        text = danceItem.title,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = time,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                    )
                }
                Image(
                    painter = painterResource(id = img.value),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(30.dp)
                        .weight(1F)
                        .align(CenterVertically)
                        .clickable {
                            if (isChecked) {
                                img.value = R.drawable.black_star
                                isChecked = false
                            } else {
                                img.value = R.drawable.yellow_star
                                isChecked = true
                            }
                            danceItem.stateImg = img.value
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TMSAndroidJatPackTheme {
        setRecyclerView()
    }
}
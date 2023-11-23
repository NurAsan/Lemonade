package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LemonApp() {
    var currentStep by remember { mutableStateOf(1) }
    var tapsRequired by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
            .height(100.dp) // Adjust the height as needed
            .background(Color.Yellow)
    ) {
        Text(text = "Lemonade",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            fontSize = 32.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {

        when (currentStep) {
            1 -> LemonTreeStep {
                currentStep = 2
            }
            2 -> LemonSqueezeStep {
                tapsRequired = (2..4).random()
                currentStep = 3
            }
            3 -> LemonadeGlassStep {
                currentStep = 4
            }
            4 -> EmptyGlassStep {
                currentStep = 1
            }



        }
    }
}

@Composable
fun LemonTreeStep(onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onTap.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(18.dp))
        ){
            Image(
                painter = painterResource(id = R.drawable.lemon_tree),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.lemon_tree_description))
    }
}

@Composable
fun LemonSqueezeStep(onTap: () -> Unit) {
    var taps by remember { mutableStateOf(0) }
    var tapsRequired by remember { mutableStateOf(0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                taps++
                if (taps >= tapsRequired) {
                    onTap.invoke()
                    taps = 0
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(18.dp))
        ){
            Image(
                painter = painterResource(id = R.drawable.lemon_squeeze),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.lemon_juice_description))
    }
}

@Composable
fun LemonadeGlassStep(onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onTap.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(18.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.lemon_drink),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.lemon_drink_description))

    }
}

@Composable
fun EmptyGlassStep(onTap: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onTap.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Gray, shape = RoundedCornerShape(18.dp))
        ) {
            Image(
                painter = painterResource(id = R.drawable.lemon_restart),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.empty_glass_description))

    }
}
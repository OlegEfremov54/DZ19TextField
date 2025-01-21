package com.example.dz19textfield

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dz19textfield.ui.theme.DZ19TextFieldTheme
import java.time.format.TextStyle
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.compose.ui.input.key.onKeyEvent

class MainActivity() : ComponentActivity(), Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            DZ19TextFieldTheme {
                Start()
            }
        }
    }

    @Composable
    fun Item(text: String, onItemClick: () -> Unit) {

        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { onItemClick() }
                .padding(26.dp, 6.dp)
        ) {
            Text(
                text = text,
                fontSize = 20.sp
            )
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun Start() {

        val itemList = remember {
            mutableStateListOf<String>()
        }


        var currentValue by remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Динамический список",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Red
            )

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp))
                    .background(Color.LightGray)
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                itemsIndexed(itemList) { index, item ->
                    Item(
                        item
                    ) {
                        itemList.removeAt(index)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text= "Добавить элемент списка",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = currentValue,
                onValueChange = {currentValue = it},
                singleLine = true,
                maxLines = 1,
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp),
                modifier = Modifier.onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                        itemList.add(currentValue)
                        currentValue = ""
                        true
                    } else false
                }
            )


            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text= "Текст вводится по нажатию на ENTER",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text= "Для удаления элемента кликните по нему",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Blue
            )

        }
    }
    }
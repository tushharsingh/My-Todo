package com.example.todo

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun TaskScreen(viewModel: todoViewModel) {
    val context = LocalContext.current
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember{ mutableStateOf("") }
   Column(modifier = Modifier
       .fillMaxHeight()
       .padding(8.dp)) {
 Row (modifier = Modifier.fillMaxWidth().padding(8.dp),
     horizontalArrangement = Arrangement.SpaceEvenly

 ){
     OutlinedTextField(value = inputText, onValueChange = {
         inputText=it
     }, modifier = Modifier.weight(1f))
     Spacer(modifier = Modifier.width(5.dp))
     Button(onClick = {
         if(inputText.isEmpty()){
             val toast=Toast.makeText(context,"You didn't type any task stupid \uD83D\uDD95 ",Toast.LENGTH_SHORT).show()
         }
       else{
           viewModel.addAllTodo(inputText)
             inputText=""
         }

     }) {
         Text("Add")
     }


 }
       todoList?.let {
           LazyColumn(
               content = {
                   itemsIndexed(it){index:Int, item :Task->
                       taskBoard(item, ondelete = {viewModel.delTodo(item.id)})
                   }
               }
           )


       }?: Text(modifier=Modifier.fillMaxWidth(),textAlign=TextAlign.Center,text="You have no task to do.Add task", fontSize = 16.sp)




       }

   }
@Composable
fun taskBoard(item:Task,ondelete:()->Unit){
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.primary).padding(16.dp)){
        Column(modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .padding(8.dp)) {
            Text(text=SimpleDateFormat("HH:mm:aa,dd/MM", Locale.ENGLISH).format(item.created)
            , fontSize = 10.sp, color = Color.LightGray)
            Text(text=item.title, fontWeight = FontWeight.Bold, fontSize=20.sp
            ,color=Color.White)
        }
        IconButton(onClick = {
          ondelete()
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription ="delete", tint = Color.White)
        }
    }
}




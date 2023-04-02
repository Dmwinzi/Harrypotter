package com.example.harrypotter.Presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.Domain.Models.Harrycharactersparcelables
import com.example.harrypotter.Domain.Models.HarrycharsItem
import com.example.harrypotter.Domain.Models.Resource
import com.example.harrypotter.R

@Composable
fun Characters(viewModel: Mainviewmodel,navController: NavHostController){

    var context = LocalContext.current

    var isloading  by remember { mutableStateOf(false)}

    var characters  = viewModel._characters.collectAsState()

    var allcharacters = remember { mutableStateOf(listOf<HarrycharsItem>()) }
   var searchtext  by remember { mutableStateOf("")}
   var filteredcharacters by remember { mutableStateOf(listOf<HarrycharsItem>()) }
   var listoffilteredcharacters by  remember { mutableStateOf(mutableListOf<HarrycharsItem>()) }

    when(characters.value){
        is Resource.Success -> {
            isloading = false
            allcharacters.value = (characters.value as Resource.Success).data
        }
        is Resource.Error -> {
             isloading = false
            Toast.makeText(context, (characters.value as Resource.Error).message,Toast.LENGTH_LONG).show()
        }
        Resource.Loading -> {
            isloading  = true
        }
    }


       if (searchtext.isEmpty()){
           filteredcharacters  = allcharacters.value
       } else {
           filteredcharacters = allcharacters.value.filter {
               it.actor.contains(searchtext, ignoreCase = true)
           }
       }




   Scaffold(modifier = Modifier.fillMaxSize(),
       topBar = {
           OutlinedTextField(modifier = Modifier
               .height(50.dp)
               .fillMaxWidth()
              ,value = searchtext, onValueChange = {searchtext = it}, placeholder = { Text(text = "Search")}, leadingIcon = {
               IconButton(onClick = { /*TODO*/ }) {
                  Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = null, tint = Color.Blue)
               }
           }, trailingIcon = {
               IconButton(onClick = { searchtext = "" }) {
                  Icon(painter = painterResource(id = R.drawable.baseline_cancel_24) , contentDescription = null, tint = Color.Red)
               }
           },singleLine = true, shape = RoundedCornerShape(15.dp), colors = TextFieldDefaults.outlinedTextFieldColors(
               focusedBorderColor = Color.Blue,
               unfocusedBorderColor = Color.Transparent
           ))
       }
   ) { paddingValues ->
    LazyColumn(modifier = Modifier.padding(paddingValues)){
        item {
            Circularprogress(isloading = isloading)
        }
         items(filteredcharacters){ item ->
             Row(modifier = Modifier
                 .fillMaxWidth()
                 .clickable(onClick = {
                     var character = Harrycharactersparcelables(
                         item.actor,
                         item.alive,
                         item.alternate_actors,
                         item.alternate_names,
                         item.ancestry,
                         item.dateOfBirth,
                         item.eyeColour,
                         item.gender,
                         item.hairColour,
                         item.hogwartsStaff,
                         item.hogwartsStudent,
                         item.house,
                         item.id,
                         item.image,
                         item.name,
                         item.patronus,
                         item.species,
                         item.wand,
                         item.wizard,
                         item.yearOfBirth
                     )
                     navController.currentBackStackEntry?.savedStateHandle?.set(
                         "characters",
                         character
                     )
                     navController.navigate(Screens.Characterinfo.route)
                 })){
                 Row(modifier = Modifier
                     .fillMaxWidth()
                     .padding(20.dp))
                 {
                     AsyncImage(model = ImageRequest.Builder(context).data(item.image).build(), contentDescription = null,
                         error = painterResource(id = R.drawable.baseline_person_24),
                         contentScale = ContentScale.Crop,
                         modifier = Modifier
                             .clip(CircleShape)
                             .width(80.dp)
                             .height(80.dp)

                     )

                     Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(start = 20.dp)) {
                         Text(text = item.actor, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                         Text(text = item.gender, style = TextStyle(fontSize = 15.sp))
                         Text(text = item.species, style = TextStyle(fontSize = 15.sp))
                     }

                 }


             }
             Spacer(modifier = Modifier.width(20.dp))
         }
       }
   }

}
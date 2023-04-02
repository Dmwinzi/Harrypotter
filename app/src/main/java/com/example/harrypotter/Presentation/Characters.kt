package com.example.harrypotter.Presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.Domain.Models.HarrycharsItem
import com.example.harrypotter.Domain.Models.Resource
import com.example.harrypotter.R
import retrofit2.Response

@Composable
fun Characters(navController: NavHostController){

   var context = LocalContext.current
   var viewModel  : Mainviewmodel  = hiltViewModel()

    var isloading  by remember { mutableStateOf(false)}



    var characters  = viewModel._characters.collectAsState()

    var allcharacters = remember { mutableStateOf(listOf<HarrycharsItem>()) }

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



    //Log.d("characters",characters.toString())

   Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

    LazyColumn(modifier = Modifier.padding(paddingValues)){
        item {
            Circularprogress(isloading = isloading)
        }
         items(allcharacters.value){ item ->
             Row(modifier = Modifier
                 .fillMaxWidth()
                 .clickable(onClick = {
                     Toast
                         .makeText(context, item.actor, Toast.LENGTH_LONG)
                         .show()
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
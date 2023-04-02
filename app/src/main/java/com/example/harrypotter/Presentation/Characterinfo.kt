package com.example.harrypotter.Presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.R
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harrypotter.Domain.Models.HarrycharsItem


@Composable
fun Characterinfo(navcostController: NavHostController){

    var characters = navcostController.previousBackStackEntry?.savedStateHandle?.get<HarrycharsItem>("characters")
    var results  by remember { mutableStateOf(mutableSetOf<HarrycharsItem>()) }
    var context  = LocalContext.current

        if (characters != null) {
            results.add(characters)
        }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = {
            if (characters != null) {
                Text(text = characters.actor, style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }
        })}
    ) { paddingvalues ->
        LazyColumn(modifier = Modifier.padding(paddingvalues)){
            items(results.toList()){ item ->
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxSize()) {
                    AsyncImage(model = ImageRequest.Builder(context).data(item.image).build(), contentDescription = null,
                        error = painterResource(id = com.example.harrypotter.R.drawable.baseline_person_24),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(80.dp)
                            .height(80.dp)
                        )
                    Spacer(modifier = Modifier.height(10.dp) )
                    Text(text = "Name:\t ${item.actor}", style = TextStyle(fontSize = 15.sp))
                    Spacer(modifier = Modifier.height(10.dp) )
                    Text(text = "Gender:\t ${item.gender}", style = TextStyle(fontSize = 15.sp))
                    Spacer(modifier = Modifier.height(15.dp) )
                    Text(text = "Date of Birth:\t ${item.dateOfBirth.toString()}",style = TextStyle(fontSize = 15.sp))

                    Column(horizontalAlignment = Alignment.Start,modifier = Modifier.padding(start = 15.dp,top = 40.dp)) {
                        Text(text = "MoreInfo",style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Species:\t ${item.species}",style = TextStyle(fontSize = 18.sp))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Alive:\t ${item.alive}",style = TextStyle(fontSize = 18.sp))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Ancenstry:\t ${item.ancestry}",style = TextStyle(fontSize = 18.sp))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Wand:\t ${item.wand.core}",style = TextStyle(fontSize = 18.sp))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Wand Length:\t ${item.wand.length}",style = TextStyle(fontSize = 18.sp))
                        Spacer(modifier = Modifier.height(10.dp) )
                        Text(text = "Wand Type:\t ${item.wand.wood}",style = TextStyle(fontSize = 18.sp))
                    }

                }

            }
        }
    }

}
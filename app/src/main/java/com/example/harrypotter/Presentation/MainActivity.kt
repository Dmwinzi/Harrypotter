package com.example.harrypotter.Presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harrypotter.Data.RepositoryImpl
import com.example.harrypotter.Domain.Repository.Repository
import com.example.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var viewModel : Mainviewmodel  = hiltViewModel()
                     Navigate(viewModel)
                }
            }
        }
    }
}

@Composable
fun Navigate(viewModel: Mainviewmodel){
      var navController  = rememberNavController()

      NavHost(navController = navController, startDestination = Screens.Characters.route ){
          composable(Screens.Characters.route){
                Characters(viewModel,navController)
          }

          composable(Screens.Characterinfo.route){
              Characterinfo(navController)
          }


      }

}
package com.banco.cuscatlan.pokemoncuscatlan.navigation


import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.Room
import com.banco.cuscatlan.pokemoncuscatlan.data.local.PokemonDatabase
import com.banco.cuscatlan.pokemoncuscatlan.data.remote.PokemonApi
import com.banco.cuscatlan.pokemoncuscatlan.data.repository.PokemonRepository
import com.banco.cuscatlan.pokemoncuscatlan.ui.screens.DetailScreen
import com.banco.cuscatlan.pokemoncuscatlan.ui.screens.MainScreen
import com.banco.cuscatlan.pokemoncuscatlan.ui.screens.SplashScreen
import com.banco.cuscatlan.pokemoncuscatlan.viewmodel.*

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun NavGraph(navController: NavHostController) {
    val context = LocalContext.current
    val repository = rememberRepository(context)

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(
                onTimeout = {
                    navController.navigate("main") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("main") {
            val factory = PokemonViewModelFactory(repository)
            val viewModel: PokemonViewModel = viewModel(factory = factory)

            MainScreen(
                viewModel = viewModel,
                onItemClick = { name ->
                    navController.navigate("detail/$name")
                }
            )
        }

        composable("detail/{name}") { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: return@composable

            val factory = PokemonDetailViewModelFactory(name)
            val viewModel: PokemonDetailViewModel = viewModel(factory = factory)

            val pokemonDetail by viewModel.pokemonDetail.collectAsState()

            pokemonDetail?.let {
                DetailScreen(
                    pokemon = it,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
private fun rememberRepository(context: Context): PokemonRepository {
    return remember {
        val db = Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon-db"
        ).fallbackToDestructiveMigration().build()

        val api = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)

        PokemonRepository(api, db.pokemonDao())
    }
}
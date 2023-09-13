package com.xxmrk888ytxx.securespace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator
import com.xxmrk888ytxx.coreandroid.runOnUiThread
import javax.inject.Inject
import javax.inject.Provider

internal class ActivityViewModel @Inject constructor(

) : ViewModel(),Navigator {


    var navController:NavController? = null



    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val viewModel: Provider<ActivityViewModel>
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return viewModel.get() as T
        }
    }

    override fun toPokemonDetailsScreen(id: Int) = runOnUiThread {
        navController?.navigate("${Screen.PokemonDetailsScreen.route}/$id")
    }

    override fun backScreen() = runOnUiThread {
        navController?.navigateUp()
    }


}
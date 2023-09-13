package com.xxmrk888ytxx.securespace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Navigator
import javax.inject.Inject
import javax.inject.Provider

class ActivityViewModel @Inject constructor(

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


}
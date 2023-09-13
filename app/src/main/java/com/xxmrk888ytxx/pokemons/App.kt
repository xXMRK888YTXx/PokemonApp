package com.xxmrk888ytxx.pokemons

import android.app.Application
import com.xxmrk888ytxx.pokemons.DI.AppComponent
import com.xxmrk888ytxx.pokemons.DI.DaggerAppComponent

internal class App : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(this) }
}
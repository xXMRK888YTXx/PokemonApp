package com.xxmrk888ytxx.pokemons.DI

import android.content.Context
import com.xxmrk888ytxx.pokemons.DI.modules.PokemonApiModule
import com.xxmrk888ytxx.pokemons.presentation.MainActivity
import com.xxmrk888ytxx.securespace.DI.scopes.AppScope
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        PokemonApiModule::class
    ]
)
@AppScope
internal interface AppComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context) : AppComponent
    }
}
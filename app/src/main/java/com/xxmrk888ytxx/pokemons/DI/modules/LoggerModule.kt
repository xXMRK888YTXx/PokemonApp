package com.xxmrk888ytxx.pokemons.DI.modules

import com.xxmrk888ytxx.coreandroid.AndroidLogger
import com.xxmrk888ytxx.coreandroid.ShareInterfaces.Logger
import dagger.Module
import dagger.Provides

@Module
class LoggerModule {

    @Provides
    fun provideLogger() : Logger {
        return AndroidLogger
    }
}
package com.mysticism.core.di

import android.app.Application
import android.content.Context
import org.koin.core.module.Module

abstract class BaseKoinInjector(private val application: Application) {

    abstract val modules: List<Module>

    val androidContext: Context get() = application
}

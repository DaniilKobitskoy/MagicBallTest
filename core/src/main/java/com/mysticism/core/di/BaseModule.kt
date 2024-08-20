package com.mysticism.core.di

import org.koin.core.module.Module
import org.koin.dsl.ScopeDSL

abstract class BaseModule {

    abstract val module: Module

}
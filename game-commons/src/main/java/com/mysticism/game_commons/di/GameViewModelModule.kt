package com.mysticism.game_commons.di

import com.mysticism.core.di.BaseModule
import com.mysticism.game_commons.DoubleGameViewModel
import com.mysticism.game_commons.GameViewModel
import org.koin.dsl.module

class GameViewModelModule: BaseModule() {
    override val module = module{
        single { GameViewModel() }
        single { DoubleGameViewModel() }
    }

}
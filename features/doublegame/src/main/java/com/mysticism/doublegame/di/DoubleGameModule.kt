package com.mysticism.doublegame.di

import com.mysticism.core.di.BaseModule
import com.mysticism.doublegame.interactor.DoubleGameInteractor
import com.mysticism.doublegame.interactor.DoubleGameInteractorImpl
import com.mysticism.doublegame.interactor.DoubleGamePresenter
import com.mysticism.doublegame.interactor.DoubleGamePresenterImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import com.mysticism.doublegame.databinding.FragmentDoubleGameBinding

class DoubleGameModule : BaseModule() {
    override val module: Module = module {
        single<DoubleGameInteractor> { DoubleGameInteractorImpl() }

        factory<DoubleGamePresenter> { (binding: FragmentDoubleGameBinding) ->
            DoubleGamePresenterImpl(binding, get(), get(), get())
        }
    }
}

package com.mysticism.settingsscreen.di


import android.content.Context
import android.content.SharedPreferences
import com.mysticism.core.di.BaseModule
import com.mysticism.settingsscreen.domain.SettingsInteractor
import com.mysticism.settingsscreen.domain.SettingsRepository
import com.mysticism.settingsscreen.presenter.SettingsPresenter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class SettingsModule : BaseModule() {
    override val module = module {
        single<SharedPreferences> {
            androidContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        }

        single { SettingsRepository(get()) }

        single { SettingsInteractor(get()) }

        factory { SettingsPresenter(get(), get(), get()) }
    }
}
package com.goodideas.projectcube

import android.app.Application
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.goodideas.projectcube.data.repo.register.RegisterRepo
import com.goodideas.projectcube.ui.Login.RegisterViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import timber.log.Timber

class CubeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        setupKoin()
    }

    private fun setupKoin() {

        val repoModule = module {
            single { com.goodideas.projectcube.data.network.ApiService }
            single<IRegisterRepo> { RegisterRepo(get()) }

        }

        val vmModule = module {
            viewModel { RegisterViewModel(get()) }
        }

        startKoin {
            androidContext(this@CubeApp)
            modules(
                repoModule,
                vmModule
            )
        }

    }
}
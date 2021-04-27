package com.goodideas.projectcube

import android.app.Application
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.goodideas.projectcube.data.repo.register.RegisterRepo
import com.goodideas.projectcube.ui.ArticleListViewModel
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

        val apiModule = module {
            single { com.goodideas.projectcube.data.network.ApiService }
        }

        val repoModule = module {
            single<IRegisterRepo> { RegisterRepo(get()) }
            single<IPostsRepo> {  PostsRepo(get())}
        }

        val vmModule = module {
            viewModel { RegisterViewModel(get()) }
            viewModel { ArticleListViewModel(get()) }
        }

        startKoin {
            androidContext(this@CubeApp)
            modules(
                apiModule,
                repoModule,
                vmModule
            )
        }

    }
}
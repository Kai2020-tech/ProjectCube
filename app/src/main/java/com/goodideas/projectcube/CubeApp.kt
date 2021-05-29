package com.goodideas.projectcube

import android.app.Application
import com.goodideas.projectcube.data.repo.login.ILoginRepo
import com.goodideas.projectcube.data.repo.login.LoginRepo
import com.goodideas.projectcube.data.repo.posts.IPostsRepo
import com.goodideas.projectcube.data.repo.posts.PostsRepo
import com.goodideas.projectcube.data.repo.profile.IProfileRepo
import com.goodideas.projectcube.data.repo.profile.ProfileRepo
import com.goodideas.projectcube.data.repo.register.IRegisterRepo
import com.goodideas.projectcube.data.repo.register.RegisterRepo
import com.goodideas.projectcube.ui.articleList.ArticleListViewModel
import com.goodideas.projectcube.ui.CreatePost.CreatePostViewModel
import com.goodideas.projectcube.ui.Login.StartViewModel
import com.goodideas.projectcube.ui.ReadArticle.ArticleDetailViewModel
import com.goodideas.projectcube.ui.Login.Register.RegisterViewModel
import com.goodideas.projectcube.ui.SearchPost.SearchViewModel
import com.goodideas.projectcube.ui.updatePost.UpdatePostViewModel
import com.goodideas.projectcube.ui.profile.ProfileViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
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
            single { com.goodideas.projectcube.data.network.ApiService() }
        }

        val repoModule = module {
            single<IRegisterRepo> { RegisterRepo(get()) }
            single<IPostsRepo> { PostsRepo(get()) }
            single<ILoginRepo> { LoginRepo(get()) }
            single<IProfileRepo> { ProfileRepo(get()) }
        }

        val vmModule = module {
            viewModel { RegisterViewModel(get()) }
            viewModel { ArticleListViewModel(get()) }
            viewModel { StartViewModel(get()) }
            viewModel { ArticleDetailViewModel(get()) }
            viewModel { CreatePostViewModel(get(), get()) }
            viewModel { UpdatePostViewModel(get(),get()) }
            viewModel { SearchViewModel(get()) }
            viewModel { ProfileViewModel(get(), get()) }
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
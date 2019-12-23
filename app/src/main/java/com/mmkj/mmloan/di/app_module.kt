package com.mmkj.mmloan.di

import com.mmkj.mmloan.api.ApiService
import com.mmkj.mmloan.api.apiService
import com.mmkj.mmloan.repository.ApiRepository
import com.mmkj.mmloan.view.main.MainViewModel
import com.mmkj.mmloan.view.splash.SplashViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author zhangshuai
 * @date 2019-12-19 15:00
 * @description
 */

val localModule = module {

}

val remoteModule = module {
    single<ApiService> { apiService }
}

val repoModule = module {
    single { ApiRepository(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { MainViewModel(get()) }
}

val appModule = listOf(localModule, remoteModule, repoModule, viewModelModule)
package com.example.interestingflights.di

import com.example.interestingflights.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
@Module
interface ActivityBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ActivityModule::class, FragmentBuilder::class])
    fun contributeMainActivity(): MainActivity
}
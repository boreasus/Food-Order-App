package poyraz.apps.foodorder.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import poyraz.apps.foodorder.data.repo.FoodsDaoRepository
import poyraz.apps.foodorder.retrofit.ApiUtils
import poyraz.apps.foodorder.retrofit.FoodsDao
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodsDaoRepository(fdao: FoodsDao) : FoodsDaoRepository{
        return FoodsDaoRepository(fdao)
    }



    @Provides
    @Singleton
    fun provideFoodsDao() : FoodsDao{
        return ApiUtils.getFoodsDao()
    }
}
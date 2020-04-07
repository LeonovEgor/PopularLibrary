package ru.geekbrains.poplib.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.geekbrains.poplib.ui.App
import ru.geekbrains.poplib.ui.network.NetworkStatus
import javax.inject.Named

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl() = "https://api.github.com"

    @Provides
    fun networkStatus(app: App) = NetworkStatus(app)

    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

}
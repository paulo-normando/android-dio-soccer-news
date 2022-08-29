package me.dio.catalog.data;

import androidx.room.Room;

import me.dio.catalog.App;
import me.dio.catalog.data.local.SolutionsDb;
import me.dio.catalog.data.remote.SolutionsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SolutionsRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://paulo-normando.github.io/imerys-products-api/";
    private static final String LOCAL_DB_NAME = "products";
    //endregion

    //region Atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local (Room).
    private SolutionsApi remoteApi;
    private SolutionsDb localDb;

    public SolutionsApi getRemoteApi() {
        return remoteApi;
    }

    public SolutionsDb getLocalDb() {
        return localDb;
    }
    //endregion

    //region Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private SolutionsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SolutionsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), SolutionsDb.class, LOCAL_DB_NAME).build();
    }

    public static SolutionsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final SolutionsRepository INSTANCE = new SolutionsRepository();
    }
    //endregion
}

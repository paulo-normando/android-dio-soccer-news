package me.dio.catalog.data.remote;

import java.util.List;

import me.dio.catalog.domain.Solutions;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SolutionsApi {

    @GET("products.json")
    Call<List<Solutions>> getNews();
}

package com.example.middlepro;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitAPI {

    @GET("/api/admin/getParentsPe/{period}")
    Call<HashMap<String, Integer>> getParentsPe(@Path("period") int period) ;

    @GET("/api/admin/getKidsPe/{period}")
    Call<HashMap<String, Integer>> getNewKids(@Path("period") Integer period) ;

    @GET("/api/admin/getTotal/{period}")
    Call<HashMap<String, Integer>> getTotal(@Path("period") Integer period) ;
    @GET("/api/admin/AddAll")
    Call< Integer> getallData() ;
    @GET("/api/admin/getActivityTime/{period}")
    Call <HashMap<String, Double>> getActivityTime(@Path("period") Integer period) ;

}

package com.hdj.frame;



import com.hdj.data.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET(".")
   Observable<TestInfo> getInfoData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);

}

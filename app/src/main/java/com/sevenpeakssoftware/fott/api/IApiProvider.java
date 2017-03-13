package com.sevenpeakssoftware.fott.api;


import com.sevenpeakssoftware.fott.api.response.ApiResponse;
import com.sevenpeakssoftware.fott.models.Article;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by razir on 1/3/2017.
 */

public interface IApiProvider {

    @GET("article/get_articles_list")
    Observable<ApiResponse<List<Article>>> getFeed();


}

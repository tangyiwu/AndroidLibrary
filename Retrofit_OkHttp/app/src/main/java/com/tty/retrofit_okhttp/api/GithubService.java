package com.tty.retrofit_okhttp.api;

import com.tty.retrofit_okhttp.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: tangyiwu
 * create: 16/3/2
 * email: tangyiwu@haizhi.com
 * description: ...
 */
public interface GithubService {
    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}

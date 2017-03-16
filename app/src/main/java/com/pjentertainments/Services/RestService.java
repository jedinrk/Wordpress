package com.pjentertainments.Services;

import com.pjentertainments.Models.Location;
import com.pjentertainments.Models.Media;
import com.pjentertainments.Models.Movie;
import com.pjentertainments.Models.Post;
import com.pjentertainments.Models.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Naveen on 2/4/2017.
 */

public interface RestService {

    String ENDPOINT = "http://pjentertainments.com/wp-json/wp/v2/";

    @GET("/posts")
    void getPostsAsync( Callback<List<Post>> callback);

    @GET("/posts/{post_id}")
    void getPostAsync(@Path("post_id") int post_id, Callback<Post> callback); // Async

    @GET("/media")
    void getImageAsync(@Query("parent") int parent_id, Callback<List<Media>> callback); // Async

    @GET("/media/{media_id}")
    void getMediaAsync(@Path("media_id") int media_id, Callback<Media> callback); // Async

    @GET("/users/{user_id}")
    void getUserAsync(@Path("user_id") int user_id, Callback<User> callback); // Async

    @GET("/location")
    void getLocationAsync(@Query("id") int id, Callback<List<Location>> callback); // Async

    @GET("/location")
    void getCountryAsync(@Query("parent") int parent_id, Callback<List<Location>> callback); // Async

    @GET("/location")
    void getCityAsync(@Query("search") String cityName, Callback<List<Location>> callback); // Async

    @GET("/movie")
    void getMovieAsync(@Query("location") List<Integer> location_id, Callback<List<Movie>> callback); // Async

}

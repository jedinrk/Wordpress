package com.pjentertainments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.pjentertainments.Models.Post;
import com.pjentertainments.Services.RestService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Prepare API REST Calls
        RestService restService = new RestAdapter.Builder()
                .setEndpoint(RestService.ENDPOINT)
                .build()
                .create(RestService.class);

        restService.getPostsAsync(new Callback<List<Post>>() {
            @Override
            public void success(List<Post> posts, Response response) {
                System.out.println("Success");

                showPosts(posts);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Fail");
                System.out.println(error);
            }
        });
    }

    public void showPosts(List<Post> posts) {
        Toast.makeText(this, "nombre d'articles : " + posts.size(), Toast.LENGTH_SHORT).show();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArchivePostAdaptater adapter = new ArchivePostAdaptater( posts, getBaseContext() );
        mRecyclerView.setAdapter(adapter);

    }
}

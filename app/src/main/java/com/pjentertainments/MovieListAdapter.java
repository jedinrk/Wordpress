package com.pjentertainments;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pjentertainments.Models.Media;
import com.pjentertainments.Models.Movie;
import com.pjentertainments.Services.RestService;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Naveen on 2/6/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CardView cv;
        TextView postTitle;
        TextView postDate;
        ImageView postPhoto;
        Button postReadMoreBtn;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.post_card);
            postTitle = (TextView)itemView.findViewById(R.id.post_title);
            postDate = (TextView)itemView.findViewById(R.id.post_date);
            postPhoto = (ImageView)itemView.findViewById(R.id.post_photo);
            postReadMoreBtn = (Button)itemView.findViewById(R.id.readMoreBtn);
        }
    }

    private List<Movie> movie;

    private RestService restService = new RestAdapter.Builder()
            .setEndpoint(RestService.ENDPOINT)
            .build()
            .create(RestService.class);

    private Context context;

    public MovieListAdapter(List<Movie> movie, Context context){
        this.movie = movie;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_posts, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder( final ViewHolder holder, int i ) {
        final Context ctxt = this.context; // Save the context locally
        final Movie currentMovie = this.movie.get(i);

        /*if ( i == 0 ) {
            // Restore preferences
            SharedPreferences settings = ctxt.getSharedPreferences("myShare", Context.MODE_PRIVATE);
            int last_post_id = settings.getInt("last_post_id", 0);

            if ( last_post_id != currentMovie.getId() ) {
                // We need an Editor object to make preference changes.
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("last_post_id", currentMovie.getId());

                // Commit the edits!
                editor.apply();

                *//*if ( last_post_id != 0 ) {
                    // Push notification goes here
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(context)
                                    .setContentTitle("Un nouvel article est disponible")
                                    .setContentText(currentMovie.getTitle().getRendered());
                    // Sets an ID for the notification
                    int mNotificationId = 001;
                    // Gets an instance of the NotificationManager service
                    NotificationManager mNotifyMgr =
                            (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    // Builds the notification and issues it.
                    mNotifyMgr.notify(mNotificationId, mBuilder.build());
                }*//*

            }
        }*/

        holder.postTitle.setText( currentMovie.getTitle().getRendered() );


        this.restService.getMediaAsync(currentMovie.getFeaturedMedia(), new Callback<Media>() {
            @Override
            public void success(Media media, Response response) {
                System.out.println( response.getUrl() );
                Picasso.with(ctxt).load(media.getMedia_details().getSizes().getMedium().getSource_url())
                        .into(holder.postPhoto);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println( error );
            }
        });

        // Handle read more click
        holder.postReadMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText( ctxt, "Bonne lecture", Toast.LENGTH_SHORT).show();

                // Launch a single post activity
                /*Intent intentSinglePost = new Intent( ctxt, SinglePostActivity.class );
                intentSinglePost.putExtra("post_id", currentMovie.getId() );
                intentSinglePost.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctxt.startActivity(intentSinglePost);*/
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.movie.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
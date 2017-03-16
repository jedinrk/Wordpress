package com.pjentertainments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pjentertainments.Models.Location;
import com.pjentertainments.Models.Movie;
import com.pjentertainments.PJModels.Country;
import com.pjentertainments.Services.RestService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Naveen on 2/5/2017.
 */

public class HomeActivity extends AppCompatActivity {

    private RestService restService;
    private List<Integer> mIds;
    private LocationManager locationManager;
    private LocationListener listener;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {

            @Override
            public void onLocationChanged(android.location.Location loc) {
                String longitude = "Longitude: " + loc.getLongitude();
                Log.v("HOMEACT", longitude);
                String latitude = "Latitude: " + loc.getLatitude();
                /*----------to get City-Name from coordinates ------------- */
                String cityName = null;
                Geocoder gcd = new Geocoder(getBaseContext(),
                        Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(loc.getLatitude(), loc
                            .getLongitude(), 1);
                    if (addresses.size() > 0)
                        System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String s = longitude + "\n" + latitude +
                        "\n\nMy Currrent City is: " + cityName;

                toolbar.setTitle(cityName);

                restService.getCityAsync(cityName, new Callback<List<Location>>() {
                    @Override
                    public void success(List<Location> locations, Response response) {
                        System.out.println(response.getUrl());
                        mIds = new ArrayList<>();
                        for (Location l : locations) {
                            mIds.add(l.getId());
                        }

                        if(mIds.size() > 0){
                            restService.getMovieAsync(mIds, new Callback<List<Movie>>() {
                                @Override
                                public void success(List<Movie> movies, Response response) {
                                    System.out.println(response.getUrl());
                                    showMovies(movies);
                                }

                                @Override
                                public void failure(RetrofitError error) {

                                }
                            });
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }


            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        final Spinner spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                CountryViewHolder vh = (CountryViewHolder) view.getTag();

                restService.getLocationAsync(vh.country.id, new Callback<List<Location>>() {

                    @Override
                    public void success(List<Location> movies, Response response) {
                        System.out.println(response.getUrl());
                        mIds = new ArrayList<Integer>();
                        for (Location l : movies) {
                            mIds.add(l.getId());
                        }

                        restService.getMovieAsync(mIds, new Callback<List<Movie>>() {
                            @Override
                            public void success(List<Movie> movies, Response response) {
                                System.out.println(response.getUrl());
                                showMovies(movies);
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });

                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Prepare API REST Calls
        restService = new RestAdapter.Builder()
                .setEndpoint(RestService.ENDPOINT)
                .build()
                .create(RestService.class);

        restService.getCountryAsync(0, new Callback<List<Location>>() {
            @Override
            public void success(List<Location> locations, Response response) {
                System.out.println("Success");

                List<Country> countryList = new ArrayList<>();
                for (Location l : locations) {
                    countryList.add(Country.get(l));
                }

                spinnerCountry.setAdapter(new CountryListAdapter(HomeActivity.this,
                        countryList));

            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Fail");
                System.out.println(error);
            }
        });
    }

    private class CountryListAdapter extends BaseAdapter {

        private Context context;
        private List<Country> countryList;

        public CountryListAdapter(Context context, List<Country> countryList) {
            this.context = context;
            this.countryList = countryList;
        }

        @Override
        public int getCount() {
            return countryList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,
                        parent, false);

                CountryViewHolder vh = new CountryViewHolder();
                vh.countryName = (TextView) v.findViewById(R.id.countryName);
                v.setTag(vh);
            }

            CountryViewHolder vh = (CountryViewHolder) v.getTag();
            vh.country = countryList.get(position);
            vh.countryName.setText(countryList.get(position).name);

            return v;
        }

    }

    static class CountryViewHolder {
        TextView countryName;
        Country country;

    }

    public void showMovies(List<Movie> movies) {
        Toast.makeText(this, "movies : " + movies.size(), Toast.LENGTH_SHORT).show();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getBaseContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        MovieListAdapter adapter = new MovieListAdapter(movies, getBaseContext());
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_location) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                            ,10);
                }
            }
            locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

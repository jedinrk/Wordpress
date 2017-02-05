package com.pjentertainments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.pjentertainments.Models.Location;
import com.pjentertainments.Services.RestService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Naveen on 2/5/2017.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Prepare API REST Calls
        RestService restService = new RestAdapter.Builder()
                .setEndpoint(RestService.ENDPOINT)
                .build()
                .create(RestService.class);

        restService.getLocationAsync(0,new Callback<List<Location>>() {
            @Override
            public void success(List<Location> locations, Response response) {
                System.out.println("Success");

                List<String> countryList = new ArrayList<String>();
                for(Location l: locations){
                    countryList.add(l.getName());
                }

                Spinner spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);

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

    private class CountryListAdapter extends BaseAdapter{

        private Context context;
        private List<String> countryList;

        public CountryListAdapter(Context context, List<String> countryList) {
            this.context = context;
            this.countryList= countryList;
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
            if(v == null){
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,
                        parent,false);
            }
            TextView textView = (TextView) v.findViewById(R.id.countryName);
            textView.setText(countryList.get(position));

            return v;
        }

    }

}

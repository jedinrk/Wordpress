package com.pjentertainments.PJModels;

import com.pjentertainments.Models.Location;

/**
 * Created by Naveen on 2/6/2017.
 */

public class Country {

    public int id;
    public String name;

    public static Country get(Location loc) {
        Country country = new Country();
        country.id = loc.getId();
        country.name = loc.getName();

        return country;
    }
}

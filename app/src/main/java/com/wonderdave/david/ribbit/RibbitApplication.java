package com.wonderdave.david.ribbit;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by david on 21/10/14.
 */
public class RibbitApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Parse.initialize(this, "IDZ6mUDxTRD1Tmwk3TvtB8rh4gqHnm7OcfitkcEY", "MH9YjEGJJXrTIJLbsGtJ97hKKX7zaK3ijCaIxt8o");

    }

}

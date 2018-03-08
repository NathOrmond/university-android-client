package com.example.user.trainclientapp.stationlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by User on 06/03/2018.
 */

public class StationsAdapter extends ArrayAdapter<TrainStation> {


    public StationsAdapter(@NonNull Context context, int resource, @NonNull List<TrainStation> objects) {
        super(context, resource, objects);
    }


}

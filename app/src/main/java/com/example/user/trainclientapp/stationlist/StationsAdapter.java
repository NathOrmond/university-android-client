package com.example.user.trainclientapp.stationlist;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 06/03/2018.
 */

public class StationsAdapter extends ArrayAdapter<TrainStation> {

    HashMap<TrainStation, Integer> mIDMap = new HashMap<TrainStation, Integer>();
    int listLength = 5;

    public StationsAdapter(Context context, int textViewResourceId, List<TrainStation> objects) {
        super(context, textViewResourceId, objects);

        for(int i = 0; i < listLength; i++) {
            mIDMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position){
        TrainStation item = getItem(position);
        return mIDMap.get(item);
    }


    @Override
    public boolean hasStableIds(){
        return true;
    }

}

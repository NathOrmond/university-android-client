package com.example.user.trainclientapp.listdisplay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.trainclientapp.R;
import com.example.user.trainclientapp.activities.NearestStationListActivity;
import com.example.user.trainclientapp.stationlist.TrainStation;

import java.util.ArrayList;

/**
 * Created by User on 06/03/2018.
 */

public class StationsAdapter extends ArrayAdapter<TrainStation> {

    private NearestStationListActivity activity;
    private ArrayList<TrainStation> stationList;
    private static LayoutInflater inflater = null;

    public StationsAdapter (NearestStationListActivity activity, int textViewResourceId,ArrayList<TrainStation> stationList) {
        super(activity, textViewResourceId, stationList);
        try {
            this.activity = activity;
            this.stationList = stationList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public static class ViewHolder {
        public TextView display_station_name;
        public TextView display_station_distance;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_layout, null);
                holder = new ViewHolder();

                holder.display_station_name = (TextView) vi.findViewById(R.id.stationName);
                holder.display_station_distance = (TextView) vi.findViewById(R.id.distance);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            DistanceDisplayForm distanceDisplayForm = new DistanceDisplayForm();

            holder.display_station_distance.setText(stationList.get(position).getStationName());
            holder.display_station_name.setText(distanceDisplayForm.displayProper(stationList.get(position).getDistanceNum()));


        } catch (Exception e) {


        }
        return vi;
    }




}

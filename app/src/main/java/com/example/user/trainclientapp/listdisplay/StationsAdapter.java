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


    /**
     * Creates text views to be calibrated against xml text views
     */
    public static class ViewHolder {
        public TextView display_station_name;
        public TextView display_station_distance;
        public TextView display_station_latitude;
        public TextView display_station_longitude;

    }


    /**
     *
     * Calibrates against xml_layout input data for list view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.list_layout, null);
                holder = new ViewHolder();

                holder.display_station_name = (TextView) vi.findViewById(R.id.name);
                holder.display_station_distance = (TextView) vi.findViewById(R.id.distance);
                holder.display_station_latitude = (TextView) vi.findViewById(R.id.latitude);
                holder.display_station_longitude = (TextView) vi.findViewById(R.id.longitude);


                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            DistanceDisplayForm distanceDisplayForm = new DistanceDisplayForm();

            holder.display_station_name.setText(stationList.get(position).getStationName());
            holder.display_station_distance.setText(distanceDisplayForm.displayProper(stationList.get(position).getDistanceNum()));

            holder.display_station_latitude.setText(Double.toString(stationList.get(position).getStationLat()));
            holder.display_station_longitude.setText(Double.toString(stationList.get(position).getStationLong()));
            holder.display_station_latitude.setVisibility(View.INVISIBLE);
            holder.display_station_longitude.setVisibility(View.INVISIBLE);

        } catch (Exception e) {


        }
        return vi;
    }




}

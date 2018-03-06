package stationList;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by User on 06/03/2018.
 */

public class StationsAdapter extends ArrayAdapter<TrainStation> {
    public StationsAdapter( Context context, int resource, List<TrainStation> objects) {
        super(context, resource, objects);
    }


}

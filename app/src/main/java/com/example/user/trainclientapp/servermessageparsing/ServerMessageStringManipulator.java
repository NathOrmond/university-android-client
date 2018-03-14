package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

/**
 * Created by User on 08/03/2018.
 */

public class ServerMessageStringManipulator {

    /**
     *
     * splits raw server data into JSON parseable strings
     * populating array, each array position contains data for one TrainStation()
     *
     * @param rawData
     * @return splitJSON
     */

    public String[] splitJSON(String rawData){
        String[] splitJSON = rawData.split("\\}");
        Log.i("JSON_ITEMS", String.valueOf(splitJSON.length));

        for(int i = 0; i < splitJSON.length; i++) {
            splitJSON[i] = splitJSON[i].replace("[","");
            splitJSON[i] = splitJSON[i].replace("]","");
            splitJSON[i] = splitJSON[i].replace(",{","{" );
            splitJSON[i] = splitJSON[i] +"}";
        }

        return splitJSON;
    }

}

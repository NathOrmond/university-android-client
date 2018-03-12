package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

/**
 * Created by User on 08/03/2018.
 */

public class ServerMessageStringManipulator {


    public String[] formatServerData(String rawServerData){

        String delimiters = "[-\\t,;?!:@\\[\\](){}_*/]";
        String[] formattedData = rawServerData.split(delimiters);
        Log.v("Stations List", formattedData[5]);
             return formattedData;
    }
}

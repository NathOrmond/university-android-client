package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

/**
 * Created by User on 08/03/2018.
 */

public class ServerMessageStringManipulator {

    int messageItems = 6;

    public String[][] formatServerData(String rawServerData, int listLength){
        String[][] formattedData = new String[listLength][messageItems];
        String delimiters = "[-\\t,;?!:@\\[\\](){}_*/]";
        String[] partFormattedData = rawServerData.split(delimiters);

        int k = 0;
        for(int i = 0; i < listLength; i++) {
            for(int j = 0; j < messageItems; j++){
               formattedData[listLength][messageItems] = partFormattedData[k];
               Log.v("Message Item", formattedData[listLength][messageItems]);
            }
        }

        return formattedData;
    }
}

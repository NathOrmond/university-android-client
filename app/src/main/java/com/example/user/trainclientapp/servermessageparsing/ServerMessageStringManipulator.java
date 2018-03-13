package com.example.user.trainclientapp.servermessageparsing;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by User on 08/03/2018.
 */

public class ServerMessageStringManipulator {

    public String jSONString(String rawServerData) throws JSONException {
        String output;
        String[] splitJSON = splitJSON(rawServerData);


        Log.i("JSON_SPLIT", splitJSON[0]);
        Log.i("JSON_SPLIT", splitJSON[1]);
        Log.i("JSON_SPLIT", splitJSON[2]);
        Log.i("JSON_SPLIT", splitJSON[3]);
        Log.i("JSON_SPLIT", splitJSON[4]);
        Log.i("JSON_SPLIT", splitJSON[5]);

        JSONObject json = new JSONObject(splitJSON[0]);
        output = json.getString("StationName");
        return output;
    }

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

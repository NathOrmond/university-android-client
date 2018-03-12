package com.example.user.trainclientapp.servermessageparsing;

/**
 * Created by User on 08/03/2018.
 */

public class ServerMessageStringManipulator {

    /**
     *
     * Takes the raw server data as a string
     * removes any delimeters, checks for empty values
     * and returns each data as a position of a String[]
     *
     * @param rawServerData
     * @return formattedData
     */

    public String[] formatServerData(String rawServerData){

        String delimiters = "[-\\t,;:\\[\\](){}]";
        String[] formattedData = rawServerData.split(delimiters);

        for(int i = 0; i < formattedData.length; i++) {
            formattedData[i] = formattedData[i].replace("\"", "");
            if(formattedData[i].equals("")) {
                formattedData[i] = formattedData[i].replace("", "NO_VALUE");
            }
        }
        return formattedData;
    }
}

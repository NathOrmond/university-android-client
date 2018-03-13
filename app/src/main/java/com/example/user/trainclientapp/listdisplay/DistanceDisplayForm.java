package com.example.user.trainclientapp.listdisplay;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Nathan on 13/03/2018.
 */

public class DistanceDisplayForm {

    public String displayProper(Double input){
        String output, unit;
        Double i = (input*1000);
        Double rounded;
        int j;

        if(i > 1000){
            BigDecimal bd = new BigDecimal(input);
            bd = bd.round(new MathContext(3));
            rounded = bd.doubleValue();
            unit = "kilometers";
        } else {
            BigDecimal bd = new BigDecimal(i);
            bd = bd.round(new MathContext(4));
            rounded = bd.doubleValue();
            unit = "meters";
        }

        output = Double.toString(rounded) + " " + unit;
        return output;
    }

}

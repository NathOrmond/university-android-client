package com.example.user.trainclientapp.listdisplay;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by Nathan on 13/03/2018.
 */

public class DistanceDisplayForm {

    /**
     * Formats units and significant figures of
     * display distance
     *
     * @param input
     * @return output
     */


    public String displayProper(Double input){
        String output, unit;
        Double i = (input*1000);
        Double rounded;
        int precision;
        int k;
        BigDecimal bd;

        if(i > 1000){
            precision = 3;
             bd = new BigDecimal(input);
            unit = " kilometers";
        } else {
             bd = new BigDecimal(i);
            if(i > 100) {
                precision = 3;
                bd = bd.round(new MathContext(3));
            } else {
                precision = 2;
                bd = bd.round(new MathContext(2));
            }
            unit = " meters";
        }

        bd = bd.round(new MathContext(precision));
        rounded = bd.doubleValue();

        if(i > 1000) {
            output = rounded + "";
        } else {
            k = rounded.intValue();
            output = k + "";
        }

        output = output + unit;
        return output;
    }

}

package rhm.com.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stanley on 03/10/2017.
 */

public class DateConverter {

    public static Date convertLongToDate(long dateInLongFormat){
        Date d = new Date(dateInLongFormat);
        return d;
    }

    public static String convertDateToString(Date d){
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
        try{
            dateString = sdfr.format( d );
        }catch (Exception ex ){
        }
        return dateString;
    }


    public static String convertDateToStringFormatYMD(Date d){
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
        try{
            dateString = sdfr.format( d );
        }catch (Exception ex ){
        }
        return dateString;
    }

    public static String convertDateToStringFormatYMDT(Date d){
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-d");
        try{
            dateString = sdfr.format( d );
        }catch (Exception ex ){
        }
        return dateString;
    }
}

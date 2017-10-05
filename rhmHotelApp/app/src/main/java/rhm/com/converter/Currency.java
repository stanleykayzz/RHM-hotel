package rhm.com.converter;

/**
 * Created by alvin.ondzounga on 11/07/2017.
 */

public class Currency {
    public static final double convertXafToEuro(double v){
        double euroValue = 0.001525327;
        double newPrice = v*euroValue;
        return newPrice;
    }
    public static final double convertXafToDollar(double v){
        double dollarValue = 0.001702388;
        double newPrice = v*dollarValue;
        return newPrice;
    }
    public static final double convertDollarToEuro(double v){
        double euroValue = 0.851346;
        double newPrice = v*euroValue;
        return newPrice;
    }
    public static final double convertDollarToXaf(double v){
        double dollarValue = 587.41;
        double newPrice = v*dollarValue;
        return newPrice;
    }
    public static final double convertEuroToDollar(double v){
        double euroValue = 1.174680;
        double newPrice = v * euroValue;
        return newPrice;
    }
    public static final double convertEuroToXaf(double v){
        double dollarValue = 655.957;
        double newPrice = v*dollarValue;
        return newPrice;
    }
}

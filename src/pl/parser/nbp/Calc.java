package pl.parser.nbp;

import java.util.List;


public class Calc {

    public static float averageBuyCourse(List<Currency> baseCurrencies) {

        float sum = 0;
        int count = 0;
        for (Currency currency : baseCurrencies) {
            sum += currency.getKurs_kupna();
            count++;
        }
        return sum / count;
    }

    public static float averageSellCourse(List<Currency> baseCurrencies) {

        float sum = 0;
        int count = 0;
        for (Currency currency : baseCurrencies) {
            sum += currency.getKurs_sprzedazy();
            count++;
        }


        return sum / count;
    }

    public static double standardDeviation(List<Currency> baseCurrencies) {


        int count = baseCurrencies.size();
        float average = averageSellCourse(baseCurrencies);
        float temp = 0;

        for (Currency currency : baseCurrencies) {
            temp += Math.pow((currency.getKurs_sprzedazy() - average), 2);
        }

        float variance = temp / count;
        return Math.sqrt(variance);
    }

    static String roundFourDecimals(double d) {
        return String.format("%.4f", d);
    }

    static String roundFourDecimals(float d) {
        return String.format("%.4f", d);
    }


}
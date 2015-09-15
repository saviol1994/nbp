package pl.parser.nbp;


import java.util.ArrayList;
import java.util.List;


public class MainClass {
    private static List<String> xmlFileNames = new ArrayList();
    private final static int CURRENT_YEAR=2015;
    public static void main(String[] args) {
        String currency = args[0];
        String dateBegin = args[1];
        String dateEnd = args[2];
        run(currency, dateBegin, dateEnd);
    }

    public static void run(String currency, String dateBegin, String dateEnd) {


        String dateBeginConverted = convertDate(dateBegin);
        String dateEndConverted = convertDate(dateEnd);
        HttpFile hf = new HttpFile();
        hf.filterByDate(dateBeginConverted, dateEndConverted);

        for (int i = 2002; i <= (CURRENT_YEAR - 1); i++) {
            String url = "http://www.nbp.pl/kursy/xml/dir" + i + ".txt";
            hf.HttpFile(url, xmlFileNames);
        }
        String url = "http://www.nbp.pl/kursy/xml/dir.txt";
        hf.HttpFile(url, xmlFileNames);

        ParseXML ParseXML = new ParseXML();
        List<Currency> baseCurrencies = new ArrayList();
        for (String address : xmlFileNames) {
            baseCurrencies.add(ParseXML.parseXmlFile(address, currency));
        }

        float average = Calc.averageBuyCourse(baseCurrencies);
        double standardDeviation = Calc.standardDeviation(baseCurrencies);

        System.out.println(Calc.roundFourDecimals(average));
        System.out.println(Calc.roundFourDecimals(standardDeviation));


    }


    public static String convertDate(String date) {
        String[] dates = date.split("-");
        boolean first = true;
        String result = "";
        for (String t : dates) {
            if (first) {
                result += t.subSequence(2, 4);
                first = false;
            } else
                result += t;
        }
        return result;
    }

    public static int[] customParseDate(String date) {
        String[] dateP = new String[3];
        dateP[0] = date.substring(0, 2);
        dateP[1] = date.substring(2, 4);
        dateP[2] = date.substring(4, 6);

        int[] dateInt = new int[3];
        dateInt[0] = Integer.parseInt(dateP[0]);
        dateInt[1] = Integer.parseInt(dateP[1]);
        dateInt[2] = Integer.parseInt(dateP[2]);
        return dateInt;
    }

    public static String dateConvertToString(int[] dateArray) {
        String result = "";
        result += String.valueOf(dateArray[0]);
        if (dateArray[1] < 10) {
            result += "0";
            result += String.valueOf(dateArray[1]);
        } else {
            result += String.valueOf(dateArray[1]);
        }

        if (dateArray[2] < 10) {
            result += "0";
            result += String.valueOf(dateArray[2]);
        } else {
            result += String.valueOf(dateArray[2]);
        }
        return result;

    }
}


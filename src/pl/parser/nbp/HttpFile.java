package pl.parser.nbp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;


public class HttpFile {

    private final int CURRENT_YEAR=2015;
    private final String baseAddress = "http://www.nbp.pl/kursy/xml/";
    private List<String> xmlBeforeSearch=new ArrayList();

    public void HttpFile(String address, List<String> xmlFileNames) {

        try {

            URL url = new URL(address);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {

                for(String s : xmlBeforeSearch) {
                    if( line.contains(s) && line.contains("c") )
                        xmlFileNames.add(baseAddress+line+".xml");
                }

            }
            in.close();

        } catch (MalformedURLException e) {

            System.out.println("Malformed URL: " + e.getMessage());

        } catch (IOException e) {

            System.out.println("I/O Error: " + e.getMessage());

        }
    }

    public void filterByDate(String dateBegin, String dateEnd) {

        xmlBeforeSearch.add(dateBegin);
        int[] from = MainClass.customParseDate(dateBegin);
        int[] to = MainClass.customParseDate(dateEnd);

        while(!(from[0]==to[0]&&from[1]==to[1]&&from[2]==to[2]) ){
            if(from[2]<=30){
                from[2]++;
            }else if(from[1]<=12){
                from[1]++;
                from[2]=1;
            }else if(from[0]<CURRENT_YEAR){
                from[0]++;
                from[2]=1;
                from[1]=1;
            }
            xmlBeforeSearch.add(MainClass.dateConvertToString(from));
        }


    }

}

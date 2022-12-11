package net.kaleoweb.newappcpi.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CleanDate {
    
    private String mois;
    String dateToString;
    
    public CleanDate() {
    }
    
    /**
     * @return
     * @params
     */
    
    
    public String returndate(String myDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd",Locale.FRANCE).parse(myDate);
            System.out.println("DATE : " + date);
            
            SimpleDateFormat sf = new SimpleDateFormat("EEE d MMM yyyy", Locale.FRANCE);
          
            dateToString = sf.format(date);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return dateToString.toString();
        
    }
    
    public String dateinverse(String dte) {
        System.out.println(dte);
        try {
            String result = null;
            String[] fre = dte.split(" ");
            String month = fre[1];
            if (month.equals("janvier")) {
                result = "1";
            }
            if (month.equals("février")) {
                result = "2";
            }
            if (month.equals("mars")) {
                result = "3";
            }
            if (month.equals("avril")) {
                result = "4";
            }
            if (month.equals("mai")) {
                result = "5";
            }
            if (month.equals("juin")) {
                result = "6";
            }
            if (month.equals("juillet")) {
                result = "7";
            }
            if (month.equals("aout")) {
                result = "8";
            }
            if (month.equals("septembre")) {
                result = "9";
            }
            if (month.equals("octobre")) {
                result = "10";
            }
            if (month.equals("novembre")) {
                result = "11";
            }
            if (month.equals("décembre")) {
                result = "12";
            }
            return fre[2] + "-" + result + "-" + fre[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return " ";
    }
}

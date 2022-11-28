package net.kaleoweb.newappcpi.utilities;

public class CleanDate {
    
    private String mois;
    public String datecomplete;
    public CleanDate() {}
    
    /** @day
     /* @month
     /* @year
     */
     
    public String returndate(int day,int month, int year){
        
        switch (month){
            case 1:
                mois="janvier";
                break;
            case 2:
                mois="février";
                break;
            case 3:
                mois="mars";
                break;
            case 4:
                mois="avril";
                break;
            case 5:
                mois="mai";
                break;
            case 6:
                mois="juin";
                break;
            case 7:
                mois="juillet";
                break;
            case 8:
                mois="aout";
                break;
            case 9:
                mois="septembre";
                break;
            case 10:
                mois="octobre";
                break;
            case 11:
                mois="novembre";
                break;
            case 12:
                mois="décembre";
                break;
                
        }
        
        datecomplete = day +" "+ mois + " "+ year;
        
        
        return datecomplete;
        
    }
    
    public String dateinverse(String dte){
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
            return fre[2]+"-" + result +"-" +fre[0];
        }catch(Exception e){e.printStackTrace();}
    
        return " ";
    }
}

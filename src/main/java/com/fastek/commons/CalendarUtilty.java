package com.fastek.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class CalendarUtilty {
	 static int leapYearMonthDay[] = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	 static int nonLeapYearMonthDay[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	 
	 static  String[] day30 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
			"19","20","21","22","23","24","25","26","27","28","29","30"};
	 static  String[] day31 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
		 "19","20","21","22","23","24","25","26","27","28","29","30","31"};
	 static  String[] day28 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
		 "19","20","21","22","23","24","25","26","27","28"};
	 static  String[] day29 = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18",
		 "19","20","21","22","23","24","25","26","27","28","29"};
	 public static String getDate()
		{	  
		    DateFormat df = null;
		    String newDateString="";	    	       
		    df = new SimpleDateFormat("dd-MM-yyyy");
		    newDateString = df.format(new Date().getTime());	       	    
			return newDateString; 
		}
	 public static String getTodayDate()
	 {	  
		 DateFormat df = null;
		 String newDateString="";	    	       
		 df = new SimpleDateFormat("yyyy-MM-dd");
		 newDateString = df.format(new Date().getTime());	       	    
		 return newDateString; 
	 }
	 public static String getFirstDayOfMonth(int month, int year) {
		        String firstDayOfMonth = "";
		        try {
		            Calendar cal = Calendar.getInstance();
		            cal.set(Calendar.MONTH, month);
		            cal.set(Calendar.YEAR, year);
		            cal.set(Calendar.DAY_OF_MONTH, 1);
		            Date dayOfMonth = cal.getTime();
		            DateFormat sdf = new SimpleDateFormat("EEE");
		            firstDayOfMonth = sdf.format(dayOfMonth);
		            System.out.println("First Day of Month: " + firstDayOfMonth);
		        } catch (Exception e) {
		        }
		        return firstDayOfMonth;
		    }
	
	 public ArrayList getCalenderData(String nextOrPeviousDate)
	 {
		 
		 String[] dateArray=null;
		 if(nextOrPeviousDate==null)
		 {
		
			 System.out.println("=>"+getDate());
			 dateArray = getDate().split("-");
		 }
		 else
		 {
			 //System.out.println("nextOrPeviousDate=>"+nextOrPeviousDate);
			 dateArray = nextOrPeviousDate.split("-");
		 }
			//String[] dateArray = "01-11-2012".split("-");
			int date = Integer.parseInt(dateArray[0]);
			int month = Integer.parseInt(dateArray[1]);
			int year = Integer.parseInt(dateArray[2]);
			ArrayList arrcal = new ArrayList();
			if(year%4==0)
			{
				int value =leapYearMonthDay[month-1];
				System.out.println("no of day "+value);
				if(value==30)
				{
					//System.out.println(Arrays.toString(day30));
					arrcal.add(day30);
				}
				else if(value==31)
				{
					//System.out.println(Arrays.toString(day31));
					arrcal.add(day31);
				}
				else if(value==29)
				{
					//System.out.println(Arrays.toString(day29));
					arrcal.add(day29);
				}
				
			}
			else
			{
				int value = nonLeapYearMonthDay[month-1];
				System.out.println("no of day "+value);
				if(value==30)
				{
					//System.out.println(Arrays.toString(day30));
					arrcal.add(day30);
				}
				else if(value==31)
				{
					//System.out.println(Arrays.toString(day31));
					arrcal.add(day31);
				}
				else if(value==28)
				{
					//System.out.println(Arrays.toString(day28));
					arrcal.add(day28);
				}
				
			}		
			//System.out.println("==>"+getFirstDayOfMonth(month-1, year));
			arrcal.add(getFirstDayOfMonth(month-1, year));
			//System.out.println(Arrays.toString((String[])arrcal.get(0)));
			//System.out.println(arrcal.get(1));
			return arrcal;
			
	 }
	 
	 public String  getCurrentdate()
	 {
		 DateFormat df = null;
		    String newDateString="";	    	       
		    //df = new SimpleDateFormat("dd MMMM yyyy"); change vasant formate 15 jaunary 2014
                    df = new SimpleDateFormat("dd-MM-yyyy");
		    newDateString = df.format(new Date().getTime());	       	    
			return newDateString;
	 }
	 public String  getCurrentMonthandYear()
	 {
		 DateFormat df = null;
		 String newDateString="";	    	       
		 df = new SimpleDateFormat("yyyy-MM-");
		 newDateString = df.format(new Date().getTime());	       	    
		 return newDateString;
	 }
	 public  String  getShortFormatDate(String dateString)
	 {
	  System.out.println("getShortForamtDate==>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);    	       
			 df = new SimpleDateFormat("yyyy-MM-");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	
	 
	 public  String getPreviousRawDate(String dateString)
		{
		 System.out.println("from getPreviousRawDate()--"+dateString);
		  DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("dd MMMM yyyy");
		    Date startDate;
		    String newDateString="";	    
		    try {	    	    	
		        startDate = df.parse(dateString);
		        Calendar cal = Calendar.getInstance();
		        //System.out.println(startDate);
		        cal.setTime(startDate);
		        cal.add(Calendar.DAY_OF_MONTH, -1);	       	       
		        df = new SimpleDateFormat("dd-MM-yyyy"); // change V("yyyy-MM-dd");
		        newDateString = df.format(cal.getTime());	        
		    } catch (ParseException e) {
		        e.printStackTrace();
		    }
			return newDateString; 
		}
	 public  String getNextMonthWithYear(String dateString)
	 {
		 // String dateString = getCurrentdate();
		 System.out.println("inputdate==>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);
			 cal.add(Calendar.MONTH, 1);	       	       
			 df = new SimpleDateFormat("yyyy-MM-");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	 
	 public  String getYearStatRawDate(String dateString)
	 {	
		 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);       	       
			 df = new SimpleDateFormat("yyyy-MM-");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	 
	 public  String getPreviousMonthWithYear(String dateString)
	 {
		// String dateString = getCurrentdate();
		 System.out.println("getPreviousMonthWithYear==>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);
			 cal.add(Calendar.MONTH, -1);	       	       
			 df = new SimpleDateFormat("yyyy-MM-");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	 public String getNextMonthRawDate(String dateString)
	 {
		 
		 // String dateString = getCurrentdate();
		 System.out.println("==>"+dateString);
		 
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);	       	       
			 cal.add(Calendar.MONTH, 1);	       	       
			 df = new SimpleDateFormat("dd-MM-yyyy");  // change--V ("yyyy-MM-dd");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
                 System.out.println("--getNextMonthRawDate--return--"+newDateString);
		 return newDateString; 
	 }
	 public String getPreviousMonthRawDate(String dateString)
	 {
		 
		// String dateString = getCurrentdate();
		 System.out.println("from-getPreviousMonthRawDate()--"+dateString);
				 
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);	       	       
			 cal.add(Calendar.MONTH, -1);	       	       
			 df = new SimpleDateFormat("dd-MM-yyyy");  // change--V ("yyyy-MM-dd");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
                 System.out.println("--getPreviousMonthRawDate--return--"+newDateString);
		 return newDateString; 
	 }
	 
	 public  String getDateNext(String dateString)
	 {	  
		 System.out.println("==>"+dateString);
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 Date startDate;
		 String newDateString="";	 
		 try {	
			 startDate = df.parse(dateString);
			 df = new SimpleDateFormat("dd-MM-yyyy");
			 newDateString = df.format(startDate);
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }	    	       	    
		 return newDateString;
	 }
	 public  String getDatePevious(String dateString)
		{	  
		 System.out.println("==>"+dateString);
	 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 		Date startDate;
	 		String newDateString="";	 
	 		try {	
	    	 startDate = df.parse(dateString);
	    	 df = new SimpleDateFormat("dd-MM-yyyy");
	    	 newDateString = df.format(startDate);
	     } catch (ParseException e) {
	    	 	e.printStackTrace();
	     }	    	       	    
		return newDateString;
		}
	
	 public  String getNextRawDate(String dateString)
	 {
		 System.out.println("==>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("dd MMMM yyyy");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Calendar cal = Calendar.getInstance();
			 //System.out.println(startDate);
			 cal.setTime(startDate);
			 cal.add(Calendar.DAY_OF_MONTH, 1);	       	       
			 df = new SimpleDateFormat("dd-MM-yyyy"); // change V("yyyy-MM-dd");
			 newDateString = df.format(cal.getTime());	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	 
	 public String  getBackFullMonthFormat(String dateString)
	 {		 System.out.println("==>"+dateString);
	 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	 		Date startDate;
	 		String newDateString="";	 
	 		try {	
	    	 startDate = df.parse(dateString);
	    	 df = new SimpleDateFormat("dd MMMM yyyy");
	    	 newDateString = df.format(startDate);
	     } catch (ParseException e) {
	    	 	e.printStackTrace();
	     }	    	       	    
		return newDateString;
	 }
	 
	 public String  getBackShortMonthFormat(String dateString)
	 {		 System.out.println("==>"+dateString);
	 
	 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
	 Date startDate;
	 String newDateString="";	 
	 try {	
		 startDate = df.parse(dateString);
		 df = new SimpleDateFormat("yyyy-MM-dd");
		 newDateString = df.format(startDate);
	 } catch (ParseException e) {
		 e.printStackTrace();
	 }	    	       	    
	 return newDateString;
	 }
	 
	 public String  getBackddmmyyFormat(String dateString)
	 {		 System.out.println("==>"+dateString);
	 
	 DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
	 Date startDate;
	 String newDateString="";	 
	 try {	
		 startDate = df.parse(dateString);
		 df = new SimpleDateFormat("dd-MM-yyyy");
		 newDateString = df.format(startDate);
	 } catch (ParseException e) {
		 e.printStackTrace();
	 }	    	       	    
	 return newDateString;
	 }
	 
	 public  String getNextMonthFirstDate(String dateString)
	 {
		 System.out.println("=getNextMonthFirstDate()=>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("yyyy-MM-dd");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Date today = new Date();  
			 Calendar c = Calendar.getInstance();
			    c.setTime(today);
			    c.add(Calendar.MONTH, 1);
			    c.set(Calendar.DATE, c.getMinimum(Calendar.DATE));
			    Date nextDate = c.getTime();			    
			 newDateString = df.format(nextDate);	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
                 System.out.println("getNextMonthFirstDate() return--"+newDateString);
		 return newDateString; 
	 }
         
         public  String getPreviousMonthFirstDate(String dateString)
	 {
		 System.out.println("=getPreviousMonthFirstDate=>"+dateString);
		 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); // change V("yyyy-MM-dd");
		 Date startDate;
		 String newDateString="";	    
		 try {	    	    	
			 startDate = df.parse(dateString);
			 Date today = new Date();  
			 Calendar c = Calendar.getInstance();
			    c.setTime(today);
			    c.add(Calendar.MONTH, -1);
			    c.set(Calendar.DATE, c.getMinimum(Calendar.DATE));
			    Date nextDate = c.getTime();			    
			 newDateString = df.format(nextDate);	        
		 } catch (ParseException e) {
			 e.printStackTrace();
		 }
		 return newDateString; 
	 }
	 
	  public boolean checkTodayDay(String currentDate) {
		  DateFormat df = null;
		    String newDateString="";	    	       
		    df = new SimpleDateFormat("dd"); 
		    newDateString = df.format(new Date().getTime());	       	    
			System.out.println(newDateString);
			
			if(currentDate.equals(newDateString))
			{
				return true;
			}
			else
			{
				return false;
			}
	}
	  
	  
	  public String getSecondPreviousDate()
	  {
		    DateFormat df = null;
		    String curentMonthDate="";	    	       
		    df = new SimpleDateFormat("dd MMM yyyy"); 
		    curentMonthDate = df.format(new Date().getTime());	       	    
			System.out.println(curentMonthDate);
			
			 df = new SimpleDateFormat("dd MMM yyyy");
			    Date startDate;
			    String newDateString="";	    
			    try {	    	    	
			        startDate = df.parse(curentMonthDate);
			        Calendar cal = Calendar.getInstance();
			        //System.out.println(startDate);
			        cal.setTime(startDate);
			        cal.add(Calendar.MONTH, -2);	       	       
			        df = new SimpleDateFormat("dd MMMM yyyy");
			        newDateString = df.format(cal.getTime());	        
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }
			    return newDateString;
	  }
	  
	  
	  public static String getLastThirdDate()
	  {
		  DateFormat df = null;
		    String curentMonthDate="";	    	       
		    df = new SimpleDateFormat("yyyy-MM-dd"); 
		    curentMonthDate = df.format(new Date().getTime());	       	    
			System.out.println(curentMonthDate);
			
			 df = new SimpleDateFormat("yyyy-MM-dd");
			    Date startDate;
			    String newDateString="";	    
			    try {	    	    	
			        startDate = df.parse(curentMonthDate);
			        Calendar cal = Calendar.getInstance();
			        //System.out.println(startDate);
			        cal.setTime(startDate);
			        cal.add(Calendar.MONTH, -3);	       	       
			        df = new SimpleDateFormat("yyyy-MM-");
			        newDateString = df.format(cal.getTime());	        
			    } catch (ParseException e) {
			        e.printStackTrace();
			    }
			    return newDateString;
	  }
	  
	  
	  
	  public static String  getRequiredFormatDate()
		 {
		  String dateDetails1="";try {
			 
				Calendar now = Calendar.getInstance();
				java.util.Date d = new java.util.Date(now.getTimeInMillis());
				String suff = getSuffix(now.get(Calendar.DATE));		
				dateDetails1 = now.get(Calendar.DATE) + "" + suff + ","
						+ new SimpleDateFormat("MMM").format(d) + " "
						+ now.get(Calendar.YEAR);

				System.out.println("Current full date time is : " + dateDetails1

				);
			} catch (Exception e) {
				System.out.println("ex in date.." + e);
			}
		return dateDetails1;
		 }
	   
	   public static String getSuffix(int dayOfMonth) {
			String suffix = "";
			switch (dayOfMonth) {
			case 1:
			case 21:
			case 31:
				suffix = "st";
				break;
			case 2:
			case 22:
				suffix = "nd";
				break;
			case 3:
			case 23:
				suffix = "rd";
				break;
			default:
				suffix = "th";
			}
			return suffix;
		}

	  
	   public static String getDBFormatDate(String dateStr)
	   {
	 	  if(dateStr.contains("st") || dateStr.contains("nd") || dateStr.contains("rd") || dateStr.contains("th"))
	   	{
	 		  String date =dateStr ;
	 		    String [] temp = date.split(",");
	 		   	    
	 		    int daylength = temp[0].length();
	 		    String day="";
	 		    if(daylength==4)
	 		    {
	 		    	day=temp[0].substring(0,2);
	 		    }
	 		    else
	 		    {
	 		    	day=temp[0].substring(0,1);
	 		    }
	 		    String date1 = day+" "+temp[1];
	 		    System.out.println(date1);
	 		    
	 		    DateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	 		    Date dateObj = null;
	 		   try {
	 			   dateObj = (Date)formatter.parse(date1);
	 		   } catch (ParseException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		 }
	 		          
	 		  Calendar cal = Calendar.getInstance();
	 		  cal.setTime(dateObj);
	 		  String formatedDate =  cal.get(Calendar.YEAR)+ "-" + (cal.get(Calendar.MONTH) + 1) + "-" +cal.get(Calendar.DATE);
	 		  //System.out.println(formatedDate);
	 		  return formatedDate;   
	   	}
	 	  else
	 	  {
	 		  return dateStr;
	 	  }
	 	    
	   }
           
           public int monthRowValue(int daysOfMonth,String day)
           {
               int row=0;
               System.out.println("daysOfMonth-monthRowValue-"+daysOfMonth);
               switch(daysOfMonth)
               {
                   case 31: {
                       if(day.equalsIgnoreCase("fri")||day.equalsIgnoreCase("sat"))
                       row = 6;}
                     break;
                   default: row=5;
//                   case 30:  row = 5;
//                     break;
//                   case 28:  row = 4;
//                     break;
//                   case 29:  row = 4;
//                     break;
               }
               System.out.println("monthRowValue roww--"+row);
               return row;
           }
           
           public String getMonthName(String daysOfMonth)
           {
               String monthName="";
               System.out.println("daysOfMonth--"+daysOfMonth);
               HashMap hm=new HashMap();
               hm.put("01","January");
               hm.put("02","February");
               hm.put("03","March");
               hm.put("04","April");
               hm.put("05","May");
               hm.put("06","June");
               hm.put("07","July");
               hm.put("08","August");
               hm.put("09","September");
               hm.put("10","October");
               hm.put("11","November");
               hm.put("12","December");
               
               monthName=hm.get(daysOfMonth).toString();
               System.out.println("roww--"+monthName);
               return monthName;
           }
}

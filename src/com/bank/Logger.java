package com.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public aspect Logger {

	pointcut success() : call(* money*(..) );    
	after() : success() {
		
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("log.txt"),true))){
    		String var="transaction";
    		if(thisJoinPointStaticPart.getSignature().getName().equals("moneyMakeTransaction")) {
    			Calendar cal = new GregorianCalendar();
        		String fecha = Calendar.HOUR_OF_DAY+":"+Calendar.MINUTE+":"+Calendar.SECOND+":"+Calendar.MILLISECOND;
        		bw.append("transaccion: "+fecha);
        		System.out.println("Transaction: "+fecha);    		
        		bw.newLine();
        		}else {
        			try(BufferedWriter bw2 = new BufferedWriter(new FileWriter(new File("log.txt"),true))){
        	    		SimpleDateFormat dateFormat  = new SimpleDateFormat("HH:mm:ss");
        	    		Date date = new Date();    		
        	    		bw.append("Withdrawal: "+dateFormat.format(date));
        	    		System.out.println("Withdrawal: "+dateFormat.format(date));    		
        	    		bw.newLine();
        	    		}catch(Exception e){
        	    			System.out.println("Exception: "+e);
        	    		}        
        	    	System.out.println("** Withdrawal created **");
        		}        		        
    		}catch(Exception e){
        			System.out.println("Exception: "+e);
        			System.out.println("** Transaction created **");
        		}	
    		}	    
    
}
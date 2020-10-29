package com.bank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public aspect Logger {

	pointcut successT() : call(* moneyMakeTransaction*(..) );    
	after() : successT() {
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("log.txt"),true))){
    		Calendar cal = new GregorianCalendar();
    		String fecha = Calendar.HOUR_OF_DAY+":"+Calendar.MINUTE+":"+Calendar.SECOND+":"+Calendar.MILLISECOND;
    		bw.append("transaccion: "+fecha);
    		System.out.println("Transaction: "+fecha);    		
    		bw.newLine();
    		}catch(Exception e){
    			System.out.println("Exception: "+e);
    		}        
    	System.out.println("** Transaction created **");
	}
    
    pointcut successR() : call(* moneyWithdrawal*(..) );    
    after() : successR() {
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("log.txt"),true))){
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
    
}
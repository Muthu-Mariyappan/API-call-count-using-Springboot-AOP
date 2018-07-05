package com.gmm.base.rest;
/*
 * Author: Muthu Mariyappan
 * Date : 05.07.2018
 * Aspect which logs the count of API calls in the apicountlog file
 * Uses Before advice to count api execution
 * */

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.BufferedReader;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class APICountAspect {
	private final String API_COUNT_LOG_PATH = "absolute path of log file";
	private HashMap<String,Integer> registry = new HashMap<>(); // To store the method signature and its execution count
	
	APICountAspect(){ // constructor which prepares the hashmap with the stored log data
		BufferedReader sn=null;
		try {
			sn = new BufferedReader(new FileReader(API_COUNT_LOG_PATH));
			while(sn.ready()){
				String line[] = sn.readLine().split(" ");
				String methodName = line[0]+" "+line[1];
	
				int count = Integer.parseInt(line[2]);
				
				registry.put(methodName, count);
			}
		} catch (IOException e) {
			System.out.println("Error in file processing");
			e.printStackTrace();
		}
		
		try {
			sn.close();
		} catch (IOException e) {
			System.out.println("Error Closing the log file");
			e.printStackTrace();
		}
	}
	
	private void writeToLog(){ // utility method which writes method count to log file
		FileWriter fw = null;
		try {
			fw = new FileWriter(API_COUNT_LOG_PATH);
			for(Map.Entry<String,Integer> e:registry.entrySet()){
				fw.write(e.getKey()+" "+e.getValue()+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				fw.close();
			} catch (IOException e) {
				System.out.println("Error Closing the log file");
				e.printStackTrace();
			}
		}
		
	}
	
	@After("(execution(* APICountController.*(..)))") // This pointcut logs all the methods of APICountCore
	public void logApiCount(JoinPoint jp){
		
		String methodSignature = jp.getSignature().toString(); //gets method signature
		if(registry.containsKey(methodSignature))
			registry.put(methodSignature,registry.get(methodSignature)+1); // if present increment by 1
		else
			registry.put(methodSignature,1); // else add new entry
		
		writeToLog();
	}
}

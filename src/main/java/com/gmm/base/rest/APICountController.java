package com.gmm.base.rest;
/*
 * Author: Muthu Mariyappan
 * Date : 05.07.2018
 * Target object - simple calculator - to demonstrate the api 
 * 4 different methods gets executed and the number of executions also counted
 * */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APICountController {
		
	@RequestMapping(value = "/add")   
	public String add(@RequestParam("x") int x,@RequestParam("y") int y){
		   return "<h2>The sum of "+x+" and "+y+" is "+(x+y)+"</h2>";
	   }
	   	
	@RequestMapping(value = "/subtract")   
	public String subtract(@RequestParam("x") int x,@RequestParam("y") int y){
		return "<h2>The difference between "+x+" and "+y+" is "+(x-y)+"</h2>";
	   }
	   
	@RequestMapping(value = "/multiply")   
	public String multiply(@RequestParam("x") int x,@RequestParam("y") int y){
		return "<h2>The product of "+x+" and "+y+" is "+(x*y)+"</h2>";
	   }
	   
	@RequestMapping(value = "/divide")   
	public String divide(@RequestParam("x") int x,@RequestParam("y") int y){
		return "<h2>The quotient after dividing "+x+" with "+y+" is "+(x/y)+"</h2>";
	   }
	
}

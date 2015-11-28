package com.shinian.util;

import org.apache.log4j.Logger;


public class RandomUtil {
	private static Logger log = Logger.getLogger(HttpRequest.class);

	public static int random(int range)
	{
		return (int)(Math.random() * range);
	}

	public static int[] random(int min, int max, int n)
	{
	    if (n > (max - min + 1) || max < min) {  
	           return null;  
	       }  
	    int[] result = new int[n];  
	    int count = 0;  
	    while(count < n) {  
	        int num = (int) (Math.random() * (max - min)) + min;  
	        boolean flag = true;  
	        for (int j = 0; j < n; j++) {  
	            if(num == result[j]){  
	                flag = false;  
	                break;  
	            }  
	        }  
	        if(flag){  
	            result[count] = num;  
	            count++;  
	        }  
	    }  
	    return result;
	}
}

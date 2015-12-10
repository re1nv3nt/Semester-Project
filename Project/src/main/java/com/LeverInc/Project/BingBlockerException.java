package com.LeverInc.Project;

public class BingBlockerException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -460348633111306084L;

	public BingBlockerException(){
		super("Error: We've sold our souls to Google and prohibit access to Microsoft's Bing search.");
	}
	
	public BingBlockerException(String url){
		super("Error: \n" 
				+ "We've sold our souls to Google and \n" 
				+ "prohibit access to Microsoft's Bing search.\n\n" 
				+ url + "  -->  is a restricted domain.\n\n");
	}
}

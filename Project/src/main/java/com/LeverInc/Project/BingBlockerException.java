package com.LeverInc.Project;

// REQ #12
public class BingBlockerException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -460348633111306084L;

	public BingBlockerException(){
		super("We've sold our souls to Google and prohibit access to Microsoft's Bing search.");
	}
	
	public BingBlockerException(String url){
		super("We've sold our souls to Google and \n" 
				+ "prohibit access to Microsoft's Bing search.\n\n" 
				+ url + "  -->  is a restricted domain.\n\n");
	}
}

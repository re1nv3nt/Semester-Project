package com.LeverInc.Project;

// REQ #6
public class HTTPS extends URL {
	private String name = "";
	
	public HTTPS(String name, String url){
		this.name = name;
		super.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean isHTTPS(String url){
		boolean isHttps = false;
		
		if(url != null && url.startsWith("https://")){
			isHttps = true;
		}
		
		return isHttps;	
	}
	
}

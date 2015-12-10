package com.LeverInc.Project;

public class EDU extends URL{
	private String name = "";
	
	public EDU(String name, String url){
		this.setName(name);
		super.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean isEDU(String url){
		boolean isEdu = false;
		
		if(url != null && (url.endsWith(".edu") || url.endsWith(".edu/"))
				&& (url.startsWith("http://") || url.startsWith("https://"))){
			isEdu = true;
		}
		
		return isEdu;	
	}

}

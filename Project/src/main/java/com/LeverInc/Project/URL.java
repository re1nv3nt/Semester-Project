package com.LeverInc.Project;

// REQ #5
public class URL {
	protected String url = "";
	
	public void setURL(String url){
		this.url = url;
	}
	
	public String getURL(){
		return url;
	}
	
	@Override	// Compares two URL objects
	public boolean equals(Object other) {
	    if (!(other instanceof URL)) {
	        return false;
	    }

	    URL that = (URL) other;

	    // Custom equality check here.
	    return this.url.equals(that.url);
	}
	
}

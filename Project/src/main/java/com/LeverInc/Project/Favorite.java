package com.LeverInc.Project;

// REQ #6
public class Favorite extends URL{
	private String name = "";

	public Favorite(String name, String url){
		this.name = name;
		super.url = url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override	// Compares two Favorite objects
	public boolean equals(Object other) {
	    if (!(other instanceof Favorite)) {
	        return false;
	    }

	    Favorite that = (Favorite) other;

	    // Custom equality check here.
	    return this.name.equals(that.name)
	        && this.url.equals(that.url);
	}
	
}

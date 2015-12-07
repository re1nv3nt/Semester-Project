package com.LeverInc.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class EliteBrowserController extends Region{
	public String address = "http://www.google.com";
	
    @FXML
    private WebView webView;

    @FXML
    private TextField tfAddressBar;

    @FXML
    private Button btnGo;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private MenuItem close;

    public void initialize(){
	    WebEngine webEngine = webView.getEngine();
	    tfAddressBar.setText(address);
	    webEngine.load(address);
	    // Address bar helpful tooltip
	    tfAddressBar.setTooltip(new Tooltip("Enter your destination URL, and may the force be with you"));
    }
    
    // Corrects user input URL with proper HTML prefix
    public String addressCorrection(){
    	if(address.startsWith("http://") || address.startsWith("https://")){
    		return address;
    	} else{
    		address = "http://" + address;
    		return address;
    	}
    }
    
    @FXML
    void onEnterPress(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER){
    		// Assigns user input text from address bar to "address" String
    		address = tfAddressBar.getText();
    		WebEngine webEngine = webView.getEngine();
    		// Properly loads new URL with addressCorrection method
    		webEngine.load(addressCorrection());
    		// Displays the current URL after successful page load
    		tfAddressBar.setText(webEngine.getLocation());
    	}
    }

    // Exits the program
    @FXML
    void browserClose(ActionEvent event) {
    	System.exit(0);
    }

    
}

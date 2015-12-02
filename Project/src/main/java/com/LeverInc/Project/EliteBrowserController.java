package com.LeverInc.Project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EliteBrowserController {
	String address = "http://www.google.com";
	
    @FXML
    private WebView webView;

    @FXML
    private TextField tfAddressBar;

    @FXML
    private Button btnGo;

    public void initialize(){
        WebEngine webEngine = webView.getEngine();
        tfAddressBar.setText(address);
        webEngine.load(tfAddressBar.getText());
    }
    
    @FXML
    void onEnterPress(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER){
    		address = tfAddressBar.getText();
    		WebEngine webEngine = webView.getEngine();
    		webEngine.load(addressCorrection());
    	}
    }
    
    public String addressCorrection(){
    	if(address.substring(0, 7).equals("http://")){
    		return address;
    	} else{
    		address = "http://" + address;
    		return address;
    	}
    }
    
}

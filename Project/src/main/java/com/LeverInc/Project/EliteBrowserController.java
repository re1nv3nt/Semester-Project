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
    }
    
    public String addressCorrection(){
    	if(address.startsWith("https://")){
    		return address;
    	}else{
    		address = "http://" + address;
    	}
    	
    	if(address.startsWith("http://")){
    		return address;
    	} else{
    		address = "http://" + address;
    		return address;
    	}
    	
    }
    
    @FXML
    void onEnterPress(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER){
    		address = tfAddressBar.getText();
    		WebEngine webEngine = webView.getEngine();
    		webEngine.load(addressCorrection());
    	}
    }


    @FXML
    void browserClose(ActionEvent event) {
    	System.exit(0);
    }

    
}

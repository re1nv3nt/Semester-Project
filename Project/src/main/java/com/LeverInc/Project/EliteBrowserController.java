package com.LeverInc.Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EliteBrowserController extends Region{
	private String address = "https://google.com/";
    private WebEngine engine;
	
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
    
    public TextField getAddress() {
        return tfAddressBar;
    }

	public WebView getView() {
	    return webView;
	}

    public void initialize(){
    	engine = getView().getEngine();
	    tfAddressBar.setText(address);
	    engine.load(address);
	    // Displays the current URL after successful page load
		tfAddressBar.setText(engine.getLocation());
		// Address bar helpful tool-tip
	    tfAddressBar.setTooltip(new Tooltip("Enter your destination URL, and may the force be with you"));
	    // Updates address bar on link clicks
	    engine.locationProperty().addListener(new ChangeListener<String>() {
	        @Override public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
//	          getHistory().executeNav(newLoc); // update the history lists.
	          getAddress().setText(newLoc);   // update the location field.
//	          favicon.set(favIconHandler.fetchFavIcon(newLoc));
	        }
	      });
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
    		// Properly loads new URL with addressCorrection method
    		engine.load(addressCorrection());
    		// Displays the current URL after successful page load
    		tfAddressBar.setText(engine.getLocation());
    	}
    }

    @FXML	// Exits the program
    void browserClose(ActionEvent event) {
    	System.exit(0);
    }
 
}

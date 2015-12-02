package com.LeverInc.Project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.layout.*;

public class EliteBrowserController extends Region{

    @FXML
    private WebView webView;

    @FXML
    private TextField tfAddressBar;
    
    public void initialize(){
    WebEngine webEngine = webView.getEngine();
    webEngine.load("http://www.google.com");
    }
}

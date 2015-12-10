package com.LeverInc.Project;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// REQ #4
public class App extends Application implements Launch
{
	public static void main(String[] args) {
		// JavaFX GUI to browse the web
		launch(args);		
	}
	// REQ #9
	public void start(Stage stage) throws IOException {
		Parent parent = FXMLLoader.load(getClass().getResource("EliteBrowser.fxml"));
		Scene scene = new Scene(parent);
		stage.setTitle("Elite Browser");
		stage.setScene(scene);
		stage.show();
	}
	
}
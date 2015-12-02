package com.LeverInc.Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	public static void main(String[] args) {
		// JavaFX GUI to lookup and update records
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("EliteBrowser.fxml"));
		Scene scene = new Scene(parent);
		stage.setTitle("Elite Browser");
		stage.setScene(scene);
		stage.show();
	}
	
}
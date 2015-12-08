package com.LeverInc.Project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EliteBrowserController extends Region {
	private String address = "https://google.com/";
	private WebEngine engine;
	boolean loadSuccess = true;

	@FXML
	private WebView webView;
	@FXML
	private TextField tfAddressBar;
	@FXML
	private Button btnRefresh;
	@FXML
	private MenuBar MenuBar;
	@FXML
	private ComboBox<?> comboHistory;
	@FXML
	private MenuItem close;

	public TextField getAddress() {
		return tfAddressBar;
	}

	public WebView getView() {
		return webView;
	}

	public void initialize() {
		engine = getView().getEngine();
		tfAddressBar.setText(address);
		engine.load(address);

		// Displays the current URL after successful page load
		tfAddressBar.setText(engine.getLocation());

		// Address bar helpful tool-tip
		tfAddressBar.setTooltip(new Tooltip("Enter your destination URL, and may the force be with you"));

		// Updates address bar on link clicks
		engine.locationProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldLoc, String newLoc) {
				// getHistory().executeNav(newLoc); // update the history lists.
				getAddress().setText(newLoc); // update the location field.
				// favicon.set(favIconHandler.fetchFavIcon(newLoc));
			}
		});

		// Worker object used to track load progress
		Worker<?> worker = engine.getLoadWorker();
		worker.exceptionProperty().addListener(new ChangeListener<Throwable>() {
			@Override
			public void changed(ObservableValue<? extends Throwable> observableValue, Throwable oldThrowable,
					Throwable newThrowable) {
				System.out.println("Browser encountered a load exception: " + newThrowable);
				loadSuccess = false;
				// If page cannot be loaded due to exception, will load a PAGE NOT FOUND message
				if (!loadSuccess) {
					engine.load(EliteBrowserController.class.getResource("404.htm").toExternalForm());
					tfAddressBar.setText(address);
					loadSuccess = true;
				}
			}
		});
	}

	// Corrects user input URL with proper HTML prefix
	public String addressCorrection() {
		if (address == null) {address = "";}

		if (address.contains(" ")) {
			address = "https://www.google.com/search?q=" + address.trim().replaceAll(" ", "+");
		} else if (!(address.startsWith("http://") || address.startsWith("https://")) && !address.isEmpty()) {
			address = "http://" + address; // default to http prefix
		}

		return address;
	}
	
	@FXML	// Loads text from address bar on ENTER press
	public void onEnterPress(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			// Assigns user input text from address bar to "address" String
			address = tfAddressBar.getText();
			// Properly loads new URL with addressCorrection method
			engine.load(addressCorrection());
			// Displays the current URL after successful page load
			tfAddressBar.setText(engine.getLocation());
		}
	}
	
	@FXML	// Refreshes the page
	void refreshClickListener(ActionEvent event) {
		engine.reload();
	}

	@FXML // Exits the program
	public void browserClose(ActionEvent event) {
		System.exit(0);
	}

}

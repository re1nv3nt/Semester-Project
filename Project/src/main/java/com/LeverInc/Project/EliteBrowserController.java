package com.LeverInc.Project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.StringTokenizer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebHistory.Entry;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class EliteBrowserController extends Region {
	private String address = "https://google.com/"; // set to default home page
	private WebEngine engine;
	private boolean loadSuccess = true;

	@FXML
    private AnchorPane anchor;
	@FXML
	private WebView webView;
	@FXML
	private TextField tfAddressBar;
	@FXML
    private Button btnBack;
	@FXML
    private Button btnForward;
	@FXML
    private Label lblURLNote;
	@FXML
	private Button btnRefresh;
	@FXML
    private Button btnFav;
	@FXML
	private ComboBox<String> comboHistory;
	@FXML
    private MenuButton menuButtonFavorites;
	@FXML
	private MenuBar MenuBar;
	@FXML
    private MenuItem favItem;
	@FXML
    private MenuItem about;
    @FXML
    private MenuItem newWindow;
	@FXML
	private MenuItem close;
	@FXML
    private MenuItem menuPrefGray;
	@FXML
    private MenuItem menuPrefBlue;
    @FXML
    private MenuItem menuPrefGreen;
    @FXML
    private MenuItem menuPrefPurple;
    @FXML
    private MenuItem menuPrefBlack;
    @FXML
    private MenuItem menuPrefOrange;
    

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

		// COLOR PREFERENCES
        anchor.setStyle("-fx-background-color: " + Environment.getWindowColor() + ";");
		
		menuPrefGray.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #F2F2F2;");
		    	Environment.updatePreference("#F2F2F2");}});
		menuPrefBlue.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #0088CC;");
				Environment.updatePreference("#0088CC");}});
		menuPrefGreen.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #33CC33;");
				Environment.updatePreference("#33CC33");}});
		menuPrefPurple.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #CC99FF;");
				Environment.updatePreference("#CC99FF");}});
		menuPrefBlack.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #000000;");
				Environment.updatePreference("#000000");}});
		menuPrefOrange.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent e) {
		    	anchor.setStyle("-fx-background-color: #FF9900;");
				Environment.updatePreference("#FF9900");}});

		// Displays the current URL after successful page load
		tfAddressBar.setText(engine.getLocation());

		// Address bar helpful tool-tip text and image
		final Tooltip addressTooltip = new Tooltip("Enter your destination URL, and may the force be with you");
		Image vaderImg = new Image(getClass().getResourceAsStream("Resources/Vader.jpg"));
		addressTooltip.setGraphic(new ImageView(vaderImg));
		tfAddressBar.setTooltip(addressTooltip);
		
		// URL notification label setup
		Image sslImg = new Image(getClass().getResourceAsStream("Resources/SSL.png"));
		Tooltip sslTip = new Tooltip("This site uses a secure connection");
		Image eduImg = new Image(getClass().getResourceAsStream("Resources/EDU.png"));
		Tooltip eduTip = new Tooltip("This is an education web site");
		Image deathStarImg = new Image(getClass().getResourceAsStream("Resources/DeathStar.png"));
		Tooltip normTip = new Tooltip("Death Star is watching");

		// Refresh button setup
		Image refreshImage = new Image(getClass().getResourceAsStream("Resources/Refresh.png"));
		btnRefresh.setGraphic(new ImageView(refreshImage));
		btnRefresh.setTooltip(new Tooltip("Reload the current page"));
		
		// Favorite button setup
		Image favoriteImage = new Image(getClass().getResourceAsStream("Resources/Fav.png"));
		btnFav.setGraphic(new ImageView(favoriteImage));
		btnFav.setTooltip(new Tooltip("Mark page as a favorite"));
		
		// Back button setup
		Image backImage = new Image(getClass().getResourceAsStream("Resources/Back.png"));
		btnBack.setGraphic(new ImageView(backImage));
		btnBack.setTooltip(new Tooltip("Go back one page"));
		btnBack.setOnAction(e -> engine.executeScript("history.back()"));
		
		// Forward button setup
		Image forwardImage = new Image(getClass().getResourceAsStream("Resources/Forward.png"));
		btnForward.setGraphic(new ImageView(forwardImage));
		btnForward.setTooltip(new Tooltip("Go forward one page"));
        btnForward.setOnAction(e -> engine.executeScript("history.forward()"));
		
		// Clears default MenuButton menu items
		menuButtonFavorites.getItems().clear();
		
		// Populates Favorites
		for(int i = 0; i < Environment.getFavorites().size(); i++){
			final int index = i;
			MenuItem fav = new MenuItem(Environment.getFavorites().get(index).getName());
			
			// Sets a click event handler for each new favorite item in the favorite menu
			fav.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			        System.out.println(Environment.getFavorites().get(index).getName());
			        System.out.println(Environment.getFavorites().get(index).getURL() + "\n");
			        engine.load(Environment.getFavorites().get(index).getURL());
			    }
			});
			
			menuButtonFavorites.getItems().addAll(fav);
		}
		
		// Updates address bar on link clicks
		engine.locationProperty().addListener((observableValue, oldLac, newLoc) ->
			getAddress().setText(newLoc));	// update the address field

		// Stores current page via WebHistory into History combo box
		final WebHistory history = engine.getHistory();
		history.getEntries().addListener(new ListChangeListener<WebHistory.Entry>() {
		        @Override
		        public void onChanged(Change<? extends Entry> c) {
		            c.next();
		            StringTokenizer strTokenizer;
		            
		            // Changes Notification icon to the right of address bar 
		            if(engine.getLocation().startsWith("https://www.bing")
		            		|| engine.getLocation().startsWith("http://www.bing")
		            		|| engine.getLocation().startsWith("www.bing")){
		            	try {
							throw new BingBlockerException(engine.getLocation());
						} catch (BingBlockerException e1) {
							engine.load("https://www.google.com/");
							Alert alert = new Alert(AlertType.WARNING);
							alert.setTitle("Restricted Access");
							alert.setHeaderText("Were you trying to search Bing?");
							alert.setContentText(e1.getMessage());
							//Optional<ButtonType> result = 
									alert.showAndWait();
							e1.getMessage();
							//e1.printStackTrace();
						}
		            } else if (HTTPS.isHTTPS(engine.getLocation())){
		    			//System.out.println("Browsing HTTPS site!\n");
		    			lblURLNote.setGraphic(new ImageView(sslImg));
		    			lblURLNote.setTooltip(sslTip);
		    		} else if (EDU.isEDU(engine.getLocation())){
		    			lblURLNote.setGraphic(new ImageView(eduImg));
		    			lblURLNote.setTooltip(eduTip);
		    		} else{
		    			lblURLNote.setGraphic(new ImageView(deathStarImg));
		    			lblURLNote.setTooltip(normTip);
		    		}
		            
		            // Get url from WebHistory and adds it to History ComboBox
		            for (Entry e : c.getRemoved()) {
		                comboHistory.getItems().remove(e.getUrl());
		            }
		            for (Entry e : c.getAddedSubList()) {
		            	// REQ #2
		                strTokenizer = new StringTokenizer(e.getUrl(), "?");
		            	comboHistory.getItems().add(strTokenizer.nextToken());
		            }
		        }
		    }
		);
		
		// Loads page from History combo box
		comboHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent ev) {
                int offset =
                        comboHistory.getSelectionModel().getSelectedIndex()
                        - history.getCurrentIndex();
                history.go(offset);
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
				// If page cannot be loaded due to load exception, will load a PAGE NOT FOUND message
				if (!loadSuccess) {
					engine.load(EliteBrowserController.class.getResource("Resources/404.htm").toExternalForm());
					tfAddressBar.setText(address);
					loadSuccess = true;
				}
			}
		});
	}

	// Corrects user input URL with proper HTML prefix, or defers to Google search
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
	public void refreshClickListener(ActionEvent event) {
		engine.reload();
	}
	
	// Accesses DOM API to get the title of the current web page
	public String getTitle() {
	    Document doc = engine.getDocument();
	    NodeList heads = doc.getElementsByTagName("head");
	    String titleText = engine.getLocation() ; // use location if page does not define a title
	    if (heads.getLength() > 0) {
	        Element head = (Element)heads.item(0);
	        NodeList titles = head.getElementsByTagName("title");
	        if (titles.getLength() > 0) {
	            org.w3c.dom.Node title = titles.item(0);
	            titleText = title.getTextContent();
	        }
	    }
	    return titleText ;
	}
	
	@FXML	// Stores current URL as a Favorite
    public void favoriteClickListener(ActionEvent event) throws SQLException {
		Favorite newFav = new Favorite(getTitle(), engine.getLocation());
		
		boolean unique = true;
		for(int i = 0; i < Environment.getFavorites().size(); i++){
			if((newFav.equals(Environment.getFavorites().get(i)))){
				unique = false;
			}
		}
		if(unique){
			System.out.printf("Title: %s\n", newFav.getName());
			System.out.printf("Address: %s\n\n", newFav.getURL());
			
			// Adds favorite to Favorites database
			Environment.addFavorite(newFav.getName(), newFav.getURL());
			
			// Adds new favorites to MenuButton list
			MenuItem fav = new MenuItem(newFav.getName());
			
			// Sets a click event handler for each new favorite item in the favorite menu
			fav.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			        System.out.println(newFav.getName());
			        System.out.println(newFav.getURL());
			        engine.load(newFav.getURL());
			    }
			});
			
			menuButtonFavorites.getItems().addAll(fav);
		}
    }
	
	// REQ #1
	@FXML	// Displays an "About" window
    public void aboutButton(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
    	Stage stage = new Stage();
    	stage.setScene(new Scene(root));
    	stage.show();
    }
	
	@FXML	// Opens an entirely new browser window
    public void openNewWindow(ActionEvent Event) throws IOException {
    	Stage stage = new Stage();
    	Parent parent = FXMLLoader.load(getClass().getResource("EliteBrowser.fxml"));
		Scene scene = new Scene(parent);
		stage.setTitle("Elite Browser");
		stage.setScene(scene);
		stage.show();
    }

	@FXML // Exits the program
	public void browserClose(ActionEvent event) {
		System.exit(0);
	}

}

package it.unipi.giar.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import it.unipi.giar.Data.Game;
import it.unipi.giar.Data.User;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuController {
	
	public static User user;

    @FXML
    private SplitPane splitPaneLeft;
    
    @FXML
    private AnchorPane homepageMenuPane;

    @FXML
    private AnchorPane logoMenuPanel;
    
    @FXML
    private JFXButton userNameMenuPanel;

    @FXML
    private JFXButton socialMenuPanel;

    @FXML
    private JFXButton wishlistMenuPanel;

    @FXML
    private JFXButton myGamesMenuPanel;

    @FXML
    private JFXComboBox<String> topPerPlatformMenuPanel;

    @FXML
    private JFXComboBox<String> platformBrowseMenuPanel;

    @FXML
    private JFXComboBox<String> yearBrowseMenuPanel;

    @FXML
    private JFXComboBox<String> genreBrowseMenuPanel;

    @FXML
    private ImageView logout;

    @FXML
    private SplitPane splitPaneRight;
    
    @FXML
    private AnchorPane anchorPaneRight;
    
    @FXML
    private AnchorPane anchorPaneLeft;
    
    
    @FXML
    void openHomepage(MouseEvent event) {
       	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);    	
    	try {
    		
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UserHomepage.fxml"));         
            AnchorPane pane = loader.load();
			           
			anchorPaneRight.getChildren().setAll(pane);
			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

    }

    @FXML
    void logout(MouseEvent event) {
    	try {
    		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/SignIn.fxml"));
			Parent root = loader.load();
			
			Stage stage = (Stage)logout.getScene().getWindow();
			stage.setScene(new Scene(root));
			stage.show();	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    
    
    @FXML
    void openTopPlatform(ActionEvent event) {
    	String platform = topPerPlatformMenuPanel.getValue();
    
    	genreBrowseMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UserTopPerPlatform.fxml"));         
            AnchorPane pane = loader.load();
			
		    UserTopPerPlatformController controller = loader.getController();            
	    	controller.initialize(platform);
	    	anchorPaneRight.getChildren().setAll(pane);
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    
    }
     
    void loadBrowsePage(String value, String browseType) {
    	try {
     		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UserBrowse.fxml"));         
            AnchorPane pane = loader.load();
			           
            UserBrowseController controller = loader.getController();            
    	    controller.initialize(value,browseType);
    	    
			anchorPaneRight.getChildren().setAll(pane);
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @FXML
    void openYearBrowse(ActionEvent event) {
    	String yearValue = yearBrowseMenuPanel.getValue();
    	
    	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	
    	loadBrowsePage(yearValue, "year");
   
    }

    @FXML
    void openPlatformBrowse(ActionEvent event) {
    	String platformValue = platformBrowseMenuPanel.getValue();
    	
    	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);
    	
    	
    	loadBrowsePage(platformValue, "platform");
    	
    }
    
    @FXML
    void openGenreBrowse(ActionEvent event) {
    	String genreValue = genreBrowseMenuPanel.getValue();

    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);
    	
    	loadBrowsePage(genreValue,"genre");
    }
    
    
    @FXML
    void openWishlist(ActionEvent event) {
    	loadListPage("Wishlist");
    }
    
    @FXML
    void openMyGames(ActionEvent event) {
    	loadListPage("MyGames");
    }
    
    void loadListPage(String listType) {        
    	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);	
    	
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UserList.fxml"));         
            AnchorPane pane = loader.load();
			           
            UserListController controller = loader.getController();            
    	    controller.initialize(listType);
    	    
			anchorPaneRight.getChildren().setAll(pane);
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @FXML
    void openUser(ActionEvent event) {
    	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);
    	
    	
    	try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/UserProfile.fxml"));
			anchorPaneRight.getChildren().setAll(pane);
		
        			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @FXML
    void openSocial(ActionEvent event) {
    	genreBrowseMenuPanel.valueProperty().set(null);
    	topPerPlatformMenuPanel.valueProperty().set(null);
    	platformBrowseMenuPanel.valueProperty().set(null);
    	yearBrowseMenuPanel.valueProperty().set(null);
    	
    	try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/fxml/UserSocial.fxml"));
			anchorPaneRight.getChildren().setAll(pane);
    	
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }

    public void initialize(User user) {

    	try {
    		
    		this.user = user;
        	userNameMenuPanel.setText(user.getNickname());
    	
          	logoMenuPanel.setCursor(Cursor.HAND); //Change cursor to hand
        	userNameMenuPanel.setCursor(Cursor.HAND);
        	socialMenuPanel.setCursor(Cursor.HAND);
        	wishlistMenuPanel.setCursor(Cursor.HAND);
        	myGamesMenuPanel.setCursor(Cursor.HAND);
        	topPerPlatformMenuPanel.setCursor(Cursor.HAND);
        	platformBrowseMenuPanel.setCursor(Cursor.HAND);
        	yearBrowseMenuPanel.setCursor(Cursor.HAND);
        	genreBrowseMenuPanel.setCursor(Cursor.HAND);
        	logout.setCursor(Cursor.HAND);
        	
        	
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/UserHomepage.fxml"));
            AnchorPane pane = loader.load();
			           
			anchorPaneRight.getChildren().setAll(pane);  		
    		
			
    		//populate the combobox for platforms
    		ObservableList<String> platforms = FXCollections.observableArrayList(Game.getAllPlatformsList()); //mongo   		
    		topPerPlatformMenuPanel.setItems(platforms);
			platformBrowseMenuPanel.setItems(platforms);
			
			//TO DO 
			//populate the combobox for years
			ObservableList<String> years = FXCollections.observableArrayList(Game.getAllYearsList());			
			yearBrowseMenuPanel.setItems(years);
			
	        //TO DO
			//populate the combobox for genres
	        ObservableList<String> genres = FXCollections.observableArrayList(Game.getAllGenresList());	        
			genreBrowseMenuPanel.setItems(genres);
			
			
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
    }
}

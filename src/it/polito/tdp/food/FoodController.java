/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.food.db.Condiment;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="boxIngrediente"
    private ComboBox<?> boxIngrediente; // Value injected by FXMLLoader

    @FXML // fx:id="btnDietaEquilibrata"
    private Button btnDietaEquilibrata; // Value injected by FXMLLoader
    
    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcolaDieta(ActionEvent event) {

    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	int calorie = Integer.parseInt(txtCalorie.getText());
    	model.creaGrafo(calorie);
    	
    	txtResult.appendText("Ingredienti con meno di " + calorie + " calorie: \n\n");
    	List<Condiment> condiment = model.getCondiment();
    	Collections.sort(condiment);
    	for(Condiment c : condiment) {
    		txtResult.appendText(c.getDisplay_name() +" - " + c.getCondiment_calories() + " - " + c.getNFood()+"\n");
    	}
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxIngrediente != null : "fx:id=\"boxIngrediente\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnDietaEquilibrata != null : "fx:id=\"btnDietaEquilibrata\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}

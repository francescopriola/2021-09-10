/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.yelp;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.yelp.model.Business;
import it.polito.tdp.yelp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;
	private List<String> cities;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnDistante"
    private Button btnDistante; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaPercorso"
    private Button btnCalcolaPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtX2"
    private TextField txtX2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCitta"
    private ComboBox<String> cmbCitta; // Value injected by FXMLLoader

    @FXML // fx:id="cmbB1"
    private ComboBox<Business> cmbB1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbB2"
    private ComboBox<?> cmbB2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	cmbB1.getItems().clear();
    	String city = this.cmbCitta.getValue();
    	
    	if(city == null) {
    		txtResult.appendText("Seleziona una città valida!\n");
    		return;
    	}
    	
    	this.model.creaGrafo(city);
    	
    	txtResult.appendText("GRAFO CREATO!\n");
    	txtResult.appendText("#VERTICI: " + this.model.getNVetici() + "\n");
    	txtResult.appendText("#ARCHI: " + this.model.getNArchi() + "\n");
    	
    	cmbB1.getItems().addAll(this.model.getVertici());
    }

    @FXML
    void doCalcolaLocaleDistante(ActionEvent event) {
    	txtResult.clear();
    	
    	if(cmbB1.getItems().isEmpty()) {
    		txtResult.appendText("Seleziona prima una città e crea il grafico!\n");
    		return;
    	}
    	
    	Business b1 = cmbB1.getValue();
    	
    	if(b1 == null) {
    		txtResult.appendText("Seleziona un locale b1 valido!\n");
    		return;
    	}
    	
    	txtResult.appendText("LOCALE PIU' DISTANTE: \n");
    	txtResult.appendText(this.model.getLocaleAdiacentePiuLontano(b1).getBusinessName() + " = " + this.model.getMaxDist() + "\n");
    	
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {

    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDistante != null : "fx:id=\"btnDistante\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtX2 != null : "fx:id=\"txtX2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCitta != null : "fx:id=\"cmbCitta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbB1 != null : "fx:id=\"cmbB1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbB2 != null : "fx:id=\"cmbB2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	this.cities = this.model.getAllCities();
    	Collections.sort(cities);
    	this.cmbCitta.getItems().addAll(cities);
    }
}

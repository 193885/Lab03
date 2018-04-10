/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

package it.polito.tdp.spellchecker.controller;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class SpellCheckerController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="listchoice"
    private ChoiceBox<String> listchoice; // Value injected by FXMLLoader

    @FXML // fx:id="txtInsert"
    private TextArea txtInsert; // Value injected by FXMLLoader

    @FXML // fx:id="btnSpellCheck"
    private Button btnSpellCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtWrong"
    private TextArea txtWrong; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrors"
    private Label txtErrors; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="txtPerformance"
    private Label txtPerformance; // Value injected by FXMLLoader
    
   private List <String> paroleinserite;
   
   private Dictionary d = new Dictionary();
    
    @FXML
    void doClearText(ActionEvent event) {
    
    	txtInsert.clear();
    	txtWrong.clear();
    	txtErrors.setText("");
    	paroleinserite.clear();

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	
    	paroleinserite = new LinkedList <>();
    	
    	txtWrong.clear();

    	paroleinserite.clear();
    	
    	int cont=0;
    	
    	String insert = txtInsert.getText().replaceAll("[.,\\/#!$%?\\^&\\*;:{}=\\-_`~()\\[\\]\"]" , "");
    	
    	StringTokenizer st = new StringTokenizer(insert," ");
		
    	while(st.hasMoreTokens()) {
    	
    		String parola = st.nextToken(); 
		
    		paroleinserite.add(parola.toLowerCase());  
		
    	}
    	
    	d.loadDictionary(listchoice.getValue());
    	long startTime = System.nanoTime();
	
		d.spellCheckTextDichotomic(paroleinserite);
		
		long stopTime = System.nanoTime();
    	
    	for(RichWord r : d.spellCheckText(paroleinserite)) {
    		
    		if(r.isCorretta()==false) {
    			
    			cont++;
    			
    			txtWrong.appendText(""+r.getParola()+"\n");
    		}
    	}
    	
    	
    	txtErrors.setText("Il testo contiene "+cont+" errori");
    	long estimatedTime = stopTime - startTime;
    	
    	txtPerformance.setText("SpellCheck completato in "+estimatedTime/ 1e9+ " secondi");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert listchoice != null : "fx:id=\"listchoice\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtInsert != null : "fx:id=\"txtInsert\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtWrong != null : "fx:id=\"txtWrong\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtPerformance != null : "fx:id=\"txtPerformance\" was not injected: check your FXML file 'SpellChecker.fxml'.";
         
    }

	public void setModel(Dictionary model) {
					
        listchoice.getItems().addAll("Italian", "English");
       
		d= model;
		
	}
}

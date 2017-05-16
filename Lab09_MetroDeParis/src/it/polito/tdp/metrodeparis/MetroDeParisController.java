package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.dao.MetroDAO;
import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Linea;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {
Model m;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Fermata> comboPartenza;

    @FXML
    private Button btnCalcola;

    @FXML
    private ComboBox<Fermata> comboArrivo;
    @FXML
    private Button btnReset;
    @FXML
    private TextArea txtArea;
    @FXML
    void doReset(ActionEvent event) {
    		txtArea.clear();
    		
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	/*btnCalcola.setDisable(false);
    	if(comboPartenza.getValue()!=null && comboArrivo.getValue()!=null){
    		btnCalcola.setDisable(true);
        	txtArea.appendText("percorso:\n"+ m.CamminoMinimo(comboPartenza.getValue(), comboArrivo.getValue()) );
        	txtArea.appendText("\n tempo impiegato: " +(m.calcolaTempoTot(comboPartenza.getValue(), comboArrivo.getValue())/3600));
    	}else if(comboPartenza.getValue()==null){ // CONTROLLI
    		System.out.println("ERR non hai inserito la partenza");
    	}
    	if(comboArrivo.getValue()==null){
    		System.out.println("ERR non hai inserito l'arrivo");
    	
    	}*/
    	//List<Linea> linee= m.getLinea();
    	MetroDAO dao= new MetroDAO();
    	if(comboPartenza.getValue()!=null && comboArrivo.getValue()!=null){
    		List<Fermata> path=m.CamminoMinimo(comboPartenza.getValue(), comboArrivo.getValue());
    		Fermata iniziale=path.get(0);
    		txtArea.appendText("prendo metro "+ dao.getNomeLinea(iniziale.getIdlinea())+"\n");
			txtArea.appendText(iniziale.toString()+"\n");
    		for(int i=1;i<path.size();i++){

				
    			if(path.get(i).getIdFermata()== iniziale.getIdFermata() && path.get(i).getIdlinea()!=iniziale.getIdlinea()){
    				txtArea.appendText("prendo metro "+dao.getNomeLinea( path.get(i).getIdlinea()) +"\n");
    			}else{
    				txtArea.appendText(path.get(i)+"\n");
    				iniziale= path.get(i);
    			}
    		}
    		txtArea.appendText("\n tempo impiegato: " +(m.calcolaTempoTot(comboPartenza.getValue(), comboArrivo.getValue())/3600));
        	
    		
    		
    	}
    }


    @FXML
    void initialize() {
        assert comboPartenza != null : "fx:id=\"comboPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert comboArrivo != null : "fx:id=\"comboArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }

	public void setM(Model m) {
		this.m = m;
		this.comboArrivo.getItems().addAll(m.getFermate());
		this.comboPartenza.getItems().addAll(m.getFermate());
	}
}

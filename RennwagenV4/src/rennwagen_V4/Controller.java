package rennwagen_V4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button btnSave;

    @FXML
    private Button btnEnde;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModell;

    @FXML
    private TextField txtHersteller;

    @FXML
    private TextField txtLeistung;

    @FXML
    private TextField txtHubraum;

    @FXML
    private TextField txtMaxtankinhalt;

    @FXML
    private TextField txtIsttankinhalt;

    @FXML
    private ListView<Rennwagen> listview;

    private ObservableList<Rennwagen> rennwagenliste = FXCollections.observableArrayList();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        this.ladeStartObjekte();
        this.initDetails();
        this.listview.setItems(this.rennwagenliste);
    }

    @FXML
    public void handleButtonSave(ActionEvent actionEvent) {

        this.updateRennwagen();
    }

    @FXML
    public void handleButtonEnde(ActionEvent actionEvent) {

        Stage stage = (Stage) this.btnEnde.getScene().getWindow();
        stage.close();;
    }

    @FXML
    public void handleItemSelected(Event event) {

        this.setDetails(this.listview.getSelectionModel().getSelectedItem());
    }

    private void ladeStartObjekte() {

        Rennwagen rennwagen_01 = new Rennwagen("Carrera GT", "Porsche");
        Rennwagen rennwagen_02 = new Rennwagen("GP", "Ferrari");
        Rennwagen rennwagen_03 = new Rennwagen("B191", "Benetton");

        this.rennwagenliste.add(rennwagen_01);
        this.rennwagenliste.add(rennwagen_02);
        this.rennwagenliste.add(rennwagen_03);
    }

    private void getDetails(Rennwagen rennwagen) {

        /**
         * Die ID eines Rennwagen wird nicht verändert
         */
        rennwagen.setModell(this.txtModell.getText());
        rennwagen.setHersteller(this.txtHersteller.getText());
        rennwagen.setLeistung(Integer.parseInt(this.txtLeistung.getText()));
        rennwagen.setHubraum(Integer.parseInt(this.txtHubraum.getText()));
        rennwagen.setMaxtankinhalt(Float.parseFloat(this.txtMaxtankinhalt.getText()));
        rennwagen.setIsttankinhalt(Float.parseFloat(this.txtIsttankinhalt.getText()));

        this.initDetails();
    }

    private void setDetails(Rennwagen rennwagen) {

        this.initDetails();

        this.txtId.setText(rennwagen.getId());
        this.txtModell.setText(rennwagen.getModell());
        this.txtHersteller.setText(rennwagen.getHersteller());
        this.txtLeistung.setText(Integer.toString(rennwagen.getLeistung()));
        this.txtHubraum.setText(Integer.toString(rennwagen.getHubraum()));
        this.txtMaxtankinhalt.setText(Float.toString(rennwagen.getMaxtankinhalt()));
        this.txtIsttankinhalt.setText(Float.toString(rennwagen.getIsttankinhalt()));
    }

    private void initDetails() {

        this.txtId.setText("");
        this.txtModell.setText("");
        this.txtHersteller.setText("");
        this.txtLeistung.setText("0");
        this.txtHubraum.setText("0");
        this.txtMaxtankinhalt.setText("5.0");
        this.txtIsttankinhalt.setText("5.0");
    }

    private void updateRennwagen() {

        Rennwagen rennwagen = null;

        if (this.txtId.getText().equals("")) {
            /**
             * Neuen Rennwagen einfügen
             */
            rennwagen = new Rennwagen();
            this.getDetails(rennwagen);
            this.rennwagenliste.add(rennwagen);

        } else {
            /**
             * Update eines existierenden Rennwagens
             */
            this.getDetails(this.listview.getSelectionModel().getSelectedItem());
            /**
             * Veränderungen am Objekt selbst werden in der ObservableList nicht registriert.
             * daher ist ein Refresh der ListView erforderlich!
             */
            this.listview.refresh();
        }
    }
}

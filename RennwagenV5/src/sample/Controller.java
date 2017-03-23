package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
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

public class Controller implements Initializable {

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnAdd;

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

        ReadOnlyObjectProperty listViewSelection = this.listview.getSelectionModel().selectedItemProperty();

        BooleanBinding fieldsIncomplete = this.txtModell.textProperty().isEmpty()
                .or(this.txtHersteller.textProperty().isEmpty())
                .or(this.txtLeistung.textProperty().isEmpty())
                .or(this.txtHubraum.textProperty().isEmpty())
                .or(this.txtMaxtankinhalt.textProperty().isEmpty())
                .or(this.txtIsttankinhalt.textProperty().isEmpty());

        this.btnAdd.disableProperty().bind(listViewSelection.isNotNull().or(this.txtModell.textProperty().isEmpty()));
        this.btnUpdate.disableProperty().bind(listViewSelection.isNull().or(fieldsIncomplete));
    }

    @FXML
    public void handleButtonAdd(ActionEvent actionEvent) {
        this.addRennwagen();
    }

    @FXML
    public void handleButtonUpdate(ActionEvent actionEvent) {
        this.updateRennwagen();
    }

    @FXML
    public void handleButtonEnde(ActionEvent actionEvent) {
        Stage stage = (Stage) this.btnEnde.getScene().getWindow();
        stage.close();
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
         *    Die ID eines Rennwagen_v4.Rennwagen wird nicht verandert
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

        try {
            this.txtId.setText(rennwagen.getId());
            this.txtModell.setText(rennwagen.getModell());
            this.txtHersteller.setText(rennwagen.getHersteller());
            this.txtLeistung.setText(Integer.toString(rennwagen.getLeistung()));
            this.txtHubraum.setText(Integer.toString(rennwagen.getHubraum()));
            this.txtMaxtankinhalt.setText(Float.toString(rennwagen.getMaxtankinhalt()));
            this.txtIsttankinhalt.setText(Float.toString(rennwagen.getIsttankinhalt()));
        } catch (Exception e) {
            //Fängt den "'Strg' + Links-Klick"-deselect ab
        }

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

    private void addRennwagen() {
        Rennwagen rennwagen = new Rennwagen();
        this.getDetails(rennwagen);
        this.rennwagenliste.add(rennwagen);
    }

    private void updateRennwagen() {
        this.getDetails(this.listview.getSelectionModel().getSelectedItem());
        this.listview.refresh();
        this.listview.getSelectionModel().clearSelection();
    }
}
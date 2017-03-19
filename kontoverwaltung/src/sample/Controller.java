package sample;

import com.sun.corba.se.spi.orbutil.fsm.ActionBase;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Label lblGesamtsaldo;
    @FXML
    TextField txtPrivatBetrag;
    @FXML
    TextField txtGeschaeftBetrag;

    Konto privat;
    Konto business;

    @Override
// This method is called by the FXMLLoader when initialization is complete

    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        privat = new Konto();
        business = new Konto();
        privat.setStand(1000);
        business.setStand(10000);
        NumberBinding gesamt = Bindings.add(privat.standProperty(), business.standProperty());
        lblGesamtsaldo.textProperty().bind(gesamt.asString());
    }

    @FXML
    public void handleBtnAuszahlen(ActionEvent event) {

        double privatbetrag = normalizeBetrag(txtPrivatBetrag.getText());
        double businessbetrag = normalizeBetrag(txtGeschaeftBetrag.getText());
        privat.auszahlen(privatbetrag);
        business.auszahlen(businessbetrag);
        setLabelsToZero();
    }

    public void handleBtnEinzahlen(ActionEvent event) {
        double privatbetrag = normalizeBetrag(txtPrivatBetrag.getText());
        double businessbetrag = normalizeBetrag(txtGeschaeftBetrag.getText());
        privat.einzahlen(privatbetrag);
        business.einzahlen(businessbetrag);
        setLabelsToZero();
    }

    private double normalizeBetrag(String s) {
        return s.isEmpty() ? 0 : Double.parseDouble(s);
    }

    private void setLabelsToZero() {
        txtPrivatBetrag.textProperty().setValue("0");
        txtGeschaeftBetrag.textProperty().setValue("0");
    }
}

package rennwagen_v6;

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

import java.net.ConnectException;
import java.net.URL;
import java.sql.*;
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

    private Connection mysql_conn;

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        // Setup Database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/db_rennwagen?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            this.mysql_conn = DriverManager.getConnection(url, "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }


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

        /*Rennwagen rennwagen_01 = new Rennwagen("Carrera GT", "Porsche");
        Rennwagen rennwagen_02 = new Rennwagen("GP", "Ferrari");
        Rennwagen rennwagen_03 = new Rennwagen("B191", "Benetton");

        this.rennwagenliste.add(rennwagen_01);
        this.rennwagenliste.add(rennwagen_02);
        this.rennwagenliste.add(rennwagen_03);*/

        try {
            Statement stmt = this.mysql_conn.createStatement();
            String sql = "SELECT * FROM t_rennwagen";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                String id = result.getString("id");
                String modell = result.getString("modell");
                String hersteller = result.getString("hersteller");
                int leistung = result.getInt("leistung");
                int hubraum = result.getInt("hubraum");
                float isttankinhalt = result.getFloat("isttankinhalt");
                float maxtankinhalt = result.getFloat("maxtankinhalt");

                this.rennwagenliste.add(new Rennwagen(id, modell, hersteller, leistung, hubraum, isttankinhalt, maxtankinhalt));
            }


        } catch(SQLException e) {
            System.out.println(e);
        }
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

        Statement stmt = null;

        try {
            stmt = this.mysql_conn.createStatement();
            Rennwagen rennwagen = null;

            if (this.txtId.getText().equals("")) {
                /**
                 * Neuen Rennwagen einfügen
                 */
                rennwagen = new Rennwagen();
                this.getDetails(rennwagen);
                this.rennwagenliste.add(rennwagen);

                String sql = "INSERT INTO t_rennwagen " +
                        "(id, modell, hersteller, leistung, hubraum, isttankinhalt, maxtankinhalt) " +
                        "VALUES ('"+
                        rennwagen.getId() +"', '"+
                        rennwagen.getModell() +"', '"+
                        rennwagen.getHersteller() +"', "+
                        rennwagen.getLeistung() +", "+
                        rennwagen.getHubraum() +", "+
                        rennwagen.getIsttankinhalt() +", "+
                        rennwagen.getMaxtankinhalt() +");";

                System.out.println(sql);

                int result = stmt.executeUpdate(sql);

                if (result == 0) throw new Error("Cannot insert rennwagen");
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

                String sql = "UPDATE t_rennwagen SET " +
                        "modell='"+ rennwagen.getModell() +"', "+
                        "hersteller='"+ rennwagen.getHersteller() +"', "+
                        "leistung='"+ rennwagen.getLeistung() +"', "+
                        "hubraum='"+ rennwagen.getHubraum() +"', "+
                        "isttankinhalt='"+ rennwagen.getIsttankinhalt() +"', "+
                        "maxtankinhalt='"+ rennwagen.getMaxtankinhalt() +"' "+
                        "WHERE id='"+ rennwagen.getId() +"';";

                int result = stmt.executeUpdate(sql);
                System.out.println(result);

                if (result == 0) throw new Error("Cannot update rennwagen");
            }

            stmt.close();

        } catch (Exception e) {
            System.out.println("ERROR:");
            System.out.println(e);
        }
    }
}

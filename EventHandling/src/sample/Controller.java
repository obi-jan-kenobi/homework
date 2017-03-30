package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Circle circleLeft;

    @FXML
    Circle circleRight;

    @FXML
    ListView listView;

    @FXML
    GridPane gridPane;

    private ObservableList<String> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setItems(observableList);

        circleLeft.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Handler - " + event.getEventType());
            }
        });

        circleLeft.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Filter - " + event.getEventType());
            }
        });

        circleRight.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Filter - " + event.getEventType());
            }
        });

        circleRight.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Handler - " + event.getEventType());
            }
        });

        circleRight.setOnMouseDragged((event) -> {
            observableList.add(event.getSource() + " - Property - " + event.getEventType());
        });

        gridPane.addEventHandler(MouseEvent.ANY, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Handler - " + event.getEventType());
            }
        });
        gridPane.addEventFilter(MouseEvent.ANY, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                observableList.add(event.getSource() + " - Filter - " + event.getEventType());
            }
        });

        gridPane.addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                event.consume();
            }
        });

       observableList.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                listView.scrollTo(observableList.size());
            }
        });

    }

    public void onDrag(Event event) {
        observableList.add(event.getSource() + " - Property - " + event.getEventType());
    }
}

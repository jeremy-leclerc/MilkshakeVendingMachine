package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartOverButton extends Pane implements MilkshakeModelListener {

    MilkshakeModel model;
    MilkshakeController controller;

    public StartOverButton(HBox parent){

        Button resetButton = new Button("Start Over");
        resetButton.setStyle(Main.TEXT_STYLE);

        resetButton.setOnAction(e -> {this.model.resetButtonPressed();});

        resetButton.prefWidthProperty().bind(parent.widthProperty());
        resetButton.prefHeightProperty().bind(parent.heightProperty());

        this.setMinWidth(parent.getPrefWidth());
        this.getChildren().addAll(resetButton);
    }

    public void modelChanged(){
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
    }




}

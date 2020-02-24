package sample;

import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class CheckoutButton extends Pane implements MilkshakeModelListener {

    MilkshakeModel model;
    MilkshakeController controller;

    public CheckoutButton(HBox parent){


        Button checkoutButton = new Button("Checkout");
        checkoutButton.setStyle(Main.TEXT_STYLE);
        checkoutButton.setOnAction(e -> {this.model.resetButtonPressed();});
        checkoutButton.prefWidthProperty().bind(parent.widthProperty());
        checkoutButton.prefHeightProperty().bind(parent.heightProperty());
        this.setMinWidth(parent.getPrefWidth());
        this.getChildren().add(checkoutButton);
    }

    public void modelChanged(){
    };

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
    }

}

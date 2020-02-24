package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import java.lang.Exception;
import java.util.Hashtable;

import javafx.stage.Screen;

public class IceCreamView extends Pane implements MilkshakeModelListener{



    MilkshakeModel model;
    MilkshakeController controller;

    Hashtable<String, Text> servingsTracker;

    Text servingsHeading;
    Text chocolateServings;
    Text vanillaServings;
    Text strawberryServings;
    Text lemonServings;
    Text coffeeServings;
    Text mintServings;

    HBox chocolateButtonBox;
    HBox vanillaButtonBox;
    HBox strawberryButtonBox;
    HBox lemonButtonBox;
    HBox coffeeButtonBox;
    HBox mintButtonBox;

    Text warningMessage;


    public IceCreamView(VBox parent){



        servingsTracker = new Hashtable<>();

        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight() / 2);

        VBox iceCreamSelectorBox = new VBox();
        iceCreamSelectorBox.setPrefHeight(parent.getHeight() / 2);
        iceCreamSelectorBox.prefWidthProperty().bind(this.widthProperty());
        iceCreamSelectorBox.setBackground(new Background(new BackgroundFill(Main.SECONDARY_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        //iceCreamSelectorBox.setStyle("-fx-border-color: hotpink;\n");

        HBox iceCreamSelectorHeadingBox = new HBox();
        Text iceCreamSelectorHeading = new Text("Choose Your Ice Cream:");
        iceCreamSelectorHeading.setTextAlignment(TextAlignment.LEFT);
        iceCreamSelectorHeadingBox.getChildren().addAll(iceCreamSelectorHeading);
        iceCreamSelectorBox.getChildren().add(iceCreamSelectorHeadingBox);


        AnchorPane tableBox = new AnchorPane();
        //tableBox.setAlignment(Pos.CENTER);
        //tableBox.setStyle("-fx-border-color: greenyellow");


        VBox servingsBox = new VBox();
        AnchorPane.setLeftAnchor(servingsBox,45.0);
        servingsBox.setAlignment(Pos.CENTER);
        //servingsBox.setStyle(Main.BORDER_STYLE2);
        servingsBox.setSpacing(4);

        servingsHeading = new Text("Servings (0/8)");

        chocolateServings = new Text("0");
        servingsTracker.put("Chocolate", chocolateServings);
        chocolateServings.setStyle(Main.TEXT_STYLE);

        vanillaServings = new Text("0");
        servingsTracker.put("Vanilla", vanillaServings);
        vanillaServings.setStyle(Main.TEXT_STYLE);

        strawberryServings = new Text("0");
        servingsTracker.put("Strawberry", strawberryServings);
        strawberryServings.setStyle(Main.TEXT_STYLE);

        lemonServings = new Text("0");
        servingsTracker.put("Lemon", lemonServings);
        lemonServings.setStyle(Main.TEXT_STYLE);

        coffeeServings = new Text("0");
        servingsTracker.put("Coffee", coffeeServings);
        coffeeServings.setStyle(Main.TEXT_STYLE);

        mintServings = new Text("0");
        servingsTracker.put("Mint", mintServings);
        mintServings.setStyle(Main.TEXT_STYLE);

        VBox servingsTextBox = new VBox();
        servingsTextBox.setMinWidth(80);
        servingsTextBox.getChildren().addAll(chocolateServings, vanillaServings, strawberryServings,
                                                lemonServings, coffeeServings, mintServings);
        servingsTextBox.setSpacing(9);
        servingsTextBox.setAlignment(Pos.CENTER);
        servingsBox.getChildren().addAll(servingsHeading, servingsTextBox);

        VBox buttonBox = new VBox();
        AnchorPane.setRightAnchor(buttonBox, 72.0);
        //buttonBox.setStyle(Main.BORDER_STYLE2);
        Text buttonBoxHeading = new Text(" Flavor");
        buttonBoxHeading.setTextAlignment(TextAlignment.CENTER);
        chocolateButtonBox = getButtonBox("Chocolate");
        vanillaButtonBox = getButtonBox("Vanilla");
        strawberryButtonBox = getButtonBox("Strawberry");
        lemonButtonBox = getButtonBox("Lemon");
        coffeeButtonBox = getButtonBox("Coffee");
        mintButtonBox = getButtonBox("Mint");
        buttonBox.getChildren().addAll(buttonBoxHeading, chocolateButtonBox, vanillaButtonBox, strawberryButtonBox,
                                        lemonButtonBox, coffeeButtonBox, mintButtonBox);

        tableBox.getChildren().addAll(servingsBox, buttonBox);
        iceCreamSelectorBox.getChildren().addAll(tableBox);

        HBox warningMessageBox = new HBox();
        warningMessage = new Text();
        warningMessageBox.setStyle("-fx-font-size: 8px;\n");
        warningMessage.setStroke(Color.RED);
        warningMessageBox.setAlignment(Pos.CENTER);
        warningMessageBox.getChildren().add(warningMessage);
        iceCreamSelectorHeadingBox.getChildren().add(warningMessageBox);

        this.getChildren().add(iceCreamSelectorBox);
    }

    public void modelChanged(){

        this.servingsHeading.setText("Servings ("+ model.curScoopsIceCream + "/8)");

        chocolateServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Chocolate")));
        vanillaServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Vanilla")));
        strawberryServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Strawberry")));
        lemonServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Lemon")));
        coffeeServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Coffee")));
        mintServings.setText(Integer.toString(model.iceCreamScoopsTracker.get("Mint")));

        if(this.model.iceCreamOverflowWarning){
            warningMessage.setText("Warning: already at maximum amount of scoops");
        }
        else if(this.model.iceCreamUnderflowWarning){
            warningMessage.setText("Warning: scoops already at 0");
        }
        else{
            warningMessage.setText("");
        }
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
        return;
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
        return;
    }

    public HBox getButtonBox(String flavor){

        HBox buttonBox = new HBox();
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Text flavorName = new Text(flavor);
        buttonBox.getChildren().addAll(minusButton, plusButton, flavorName);

        plusButton.setOnAction(e -> {this.controller.handleIceCreamPlusButton(flavor);});
        minusButton.setOnAction(e -> {this.controller.handleIceCreamMinusButton(flavor);});

        return buttonBox;
    }
}

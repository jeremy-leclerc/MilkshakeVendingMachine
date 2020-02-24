package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import java.util.Hashtable;

public class ToppingsView extends Pane implements MilkshakeModelListener{

    MilkshakeModel model;
    MilkshakeController controller;

    Hashtable<String, Text> servingsTracker;

    Text servingsHeading;
    Text chocolateChipsServings;
    Text sprinklesServings;
    Text cherriesServings;
    Text whippedCreamServings;
    Text coconutServings;
    Text marshmallowsServings;

    HBox chocolateChipsButtonBox;
    HBox sprinklesButtonBox;
    HBox cherriesButtonBox;
    HBox whipepdCreamButtonBox;
    HBox coconutButtonBox;
    HBox marshmallowButtonBox;

    Text warningMessage;

    public ToppingsView(VBox parent){

        servingsTracker = new Hashtable<>();

        this.setWidth(parent.getWidth());
        this.setHeight(parent.getHeight() / 2);

        VBox toppingSelectorBox = new VBox();
        toppingSelectorBox.prefWidthProperty().bind(this.widthProperty());
        toppingSelectorBox.setBackground(new Background(new BackgroundFill(Main.SECONDARY_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        //toppingSelectorBox.setStyle("-fx-border-color: pink;\n");

        HBox toppingSelectorHeadingBox = new HBox();
        Text toppingSelectorHeading = new Text("Choose Your Toppings:");
        toppingSelectorHeadingBox.getChildren().add(toppingSelectorHeading);
        toppingSelectorBox.getChildren().add(toppingSelectorHeadingBox);

        AnchorPane tableBox = new AnchorPane();
        //tableBox.setStyle("-fx-border-color: greenyellow");

        VBox servingsBox = new VBox();
        AnchorPane.setLeftAnchor(servingsBox,45.0);
        servingsBox.setAlignment(Pos.CENTER);
        //servingsBox.setStyle(Main.BORDER_STYLE2);
        servingsBox.setSpacing(4);

        servingsHeading = new Text("Servings (0/8)");

        chocolateChipsServings = new Text("0");
        servingsTracker.put("Chocolate Chips", chocolateChipsServings);
        chocolateChipsServings.setStyle(Main.TEXT_STYLE);

        sprinklesServings = new Text("0");
        servingsTracker.put("Sprinkles", sprinklesServings);
        sprinklesServings.setStyle(Main.TEXT_STYLE);

        cherriesServings = new Text("0");
        servingsTracker.put("Cherries", cherriesServings);
        cherriesServings.setStyle(Main.TEXT_STYLE);

        whippedCreamServings = new Text("0");
        servingsTracker.put("Whipped Cream", whippedCreamServings);
        whippedCreamServings.setStyle(Main.TEXT_STYLE);

        coconutServings = new Text("0");
        servingsTracker.put("Coconut", coconutServings);
        coconutServings.setStyle(Main.TEXT_STYLE);

        marshmallowsServings = new Text("0");
        servingsTracker.put("Marshmallow", marshmallowsServings);
        marshmallowsServings.setStyle(Main.TEXT_STYLE);

        VBox servingsTextBox = new VBox();
        servingsTextBox.setMinWidth(80);
        servingsTextBox.getChildren().addAll(chocolateChipsServings, sprinklesServings,
                                            cherriesServings, whippedCreamServings, coconutServings,
                                             marshmallowsServings);
        servingsTextBox.setSpacing(8);
        servingsTextBox.setAlignment(Pos.CENTER);
        servingsBox.getChildren().addAll(servingsHeading, servingsTextBox);

        VBox buttonBox = new VBox();
        AnchorPane.setRightAnchor(buttonBox, 45.0);
        //buttonBox.setStyle(Main.BORDER_STYLE2);
        Text buttonBoxHeading = new Text(" Topping");
        buttonBoxHeading.setTextAlignment(TextAlignment.CENTER);
        chocolateChipsButtonBox = getButtonBox("Chocolate Chips");
        sprinklesButtonBox = getButtonBox("Sprinkles");
        cherriesButtonBox = getButtonBox("Cherries");
        whipepdCreamButtonBox = getButtonBox("Whipped Cream");
        coconutButtonBox = getButtonBox("Coconut");
        marshmallowButtonBox = getButtonBox("Marshmallow");
        buttonBox.getChildren().addAll(buttonBoxHeading, chocolateChipsButtonBox, sprinklesButtonBox,
                cherriesButtonBox, whipepdCreamButtonBox, coconutButtonBox, marshmallowButtonBox);

        tableBox.getChildren().addAll(servingsBox, buttonBox);
        toppingSelectorBox.getChildren().addAll(tableBox);

        HBox warningMessageBox = new HBox();
        warningMessage = new Text();
        warningMessageBox.setStyle("-fx-font-size: 8px;\n");
        warningMessage.setStroke(Color.RED);
        warningMessageBox.setAlignment(Pos.CENTER);
        warningMessageBox.getChildren().add(warningMessage);
        toppingSelectorHeadingBox.getChildren().add(warningMessageBox);

        this.getChildren().add(toppingSelectorBox);
    }

    public HBox getButtonBox(String flavor){

        HBox buttonBox = new HBox();
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Text flavorName = new Text(flavor);
        buttonBox.getChildren().addAll(minusButton, plusButton, flavorName);
        plusButton.setOnAction(e -> {this.controller.handleToppingPlusButton(flavor);});
        minusButton.setOnAction(e -> {this.controller.handleToppingMinusButton(flavor);});

        return buttonBox;
    }

    public void modelChanged(){
        this.servingsHeading.setText("Servings (" + model.curServingsTopping + "/8)");

        Text chocolateChipsText = this.servingsTracker.get("Chocolate Chips");
        chocolateChipsText.setText(Integer.toString(model.toppingServingsTracker.get("Chocolate Chips")));

        Text sprinklesText = this.servingsTracker.get("Sprinkles");
        sprinklesText.setText(Integer.toString(model.toppingServingsTracker.get("Sprinkles")));

        Text cherriesText = this.servingsTracker.get("Cherries");
        cherriesText.setText(Integer.toString(model.toppingServingsTracker.get("Cherries")));

        Text whippedCreamText = this.servingsTracker.get("Whipped Cream");
        whippedCreamText.setText(Integer.toString(model.toppingServingsTracker.get("Whipped Cream")));

        Text coconutText = this.servingsTracker.get("Coconut");
        coconutText.setText(Integer.toString(model.toppingServingsTracker.get("Coconut")));

        Text marshmallowsText = this.servingsTracker.get("Marshmallow");
        marshmallowsText.setText(Integer.toString(model.toppingServingsTracker.get("Marshmallow")));

        if(this.model.toppingOverflowWarning){
            warningMessage.setText("    Warning: already at maximum amount of scoops");
        }
        else if(this.model.toppingUnderflowWarning){
            warningMessage.setText("    Warning: scoops already at 0");
        }
        else{
            warningMessage.setText("");
        }
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
    }

}

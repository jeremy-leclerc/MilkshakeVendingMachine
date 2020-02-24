package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Hashtable;

public class DescriptionView extends Pane implements MilkshakeModelListener{

    MilkshakeModel model;
    MilkshakeController controller;


    VBox iceCreamBox;
    VBox iceCreamInfo;
    Text chocolateServings;
    Text chocolatePrice;
    Text vanillaServings;
    Text vanillaPrice;
    Text strawberryServings;
    Text strawberryPrice;
    Text lemonServings;
    Text lemonPrice;
    Text coffeeServings;
    Text coffeePrice;
    Text mintServings;
    Text mintPrice;

    VBox toppingBox;
    VBox toppingInfo;
    Text chocolateChipsServings;
    Text chocolateChipsPrice;
    Text sprinklesServings;
    Text sprinklesPrice;
    Text cherriesServings;
    Text cherriesPrice;
    Text whippedCreamServings;
    Text whippedCreamPrice;
    Text coconutServings;
    Text coconutPrice;
    Text marshmallowServings;
    Text marshmallowPrice;

    Text noIceCreamSelected;
    Text noToppingsSelected;

    public DescriptionView(VBox parent){

        //main layout
        VBox shoppingCartBox = new VBox();
        shoppingCartBox.prefWidthProperty().bind(parent.widthProperty());
        shoppingCartBox.setPadding(new Insets(10, 0, 0, 10));
        shoppingCartBox.setSpacing(10);

        //Initialize fields
        noIceCreamSelected = new Text("     No Ice Cream Selected");
        noToppingsSelected = new Text("     No Toppings Selected");
        iceCreamInfo = new VBox();
        toppingInfo = new VBox();

        //Ice cream box
        VBox topBox = new VBox();
        VBox iceCreamBox = new VBox();
        iceCreamBox.setSpacing(2);
        //iceCreamBox.setStyle(Main.BORDER_STYLE2);
        HBox iceCreamHeaderBox = new HBox();
        Text iceCreamHeader = new Text("Ice Cream:");
        iceCreamHeaderBox.getChildren().add(iceCreamHeader);
        iceCreamBox.getChildren().add(iceCreamHeaderBox);
        iceCreamInfo.getChildren().add(noIceCreamSelected);
        iceCreamBox.getChildren().add(iceCreamInfo);
        topBox.getChildren().add(iceCreamBox);
        shoppingCartBox.getChildren().add(topBox);

        //toppings box
        VBox bottomBox = new VBox();
        VBox toppingsBox = new VBox();
        toppingsBox.setSpacing(2);
        //toppingsBox.setStyle(Main.BORDER_STYLE2);
        HBox toppingsHeaderBox = new HBox();
        Text toppingsHeader = new Text("Toppings:");
        toppingsHeaderBox.getChildren().add(toppingsHeader);
        toppingsBox.getChildren().add(toppingsHeaderBox);
        toppingInfo.getChildren().add(noToppingsSelected);
        toppingsBox.getChildren().add(toppingInfo);
        bottomBox.getChildren().add(toppingsBox);
        shoppingCartBox.getChildren().add(bottomBox);

        this.getChildren().add(shoppingCartBox);

        return;
    }

    public void modelChanged(){

        //Get ice cream information
        Hashtable<String, Integer> iceCreamScoopsTracker = model.iceCreamScoopsTracker;
        int numChocolate = iceCreamScoopsTracker.get("Chocolate");
        int numVanilla = iceCreamScoopsTracker.get("Vanilla");
        int numStrawberry = iceCreamScoopsTracker.get("Strawberry");
        int numLemon = iceCreamScoopsTracker.get("Lemon");
        int numCoffee = iceCreamScoopsTracker.get("Coffee");
        int numMint = iceCreamScoopsTracker.get("Mint");

        iceCreamInfo.getChildren().clear();

        //Check that no scoops selected
        if(numChocolate + numVanilla + numStrawberry + numLemon + numCoffee + numMint == 0){
            iceCreamInfo.getChildren().add(noIceCreamSelected);
        }else{

            addIceCream("Chocolate", numChocolate, chocolateServings, chocolatePrice);
            addIceCream("Vanilla", numVanilla, vanillaServings, vanillaPrice);
            addIceCream("Strawberry", numStrawberry, strawberryServings, strawberryPrice);
            addIceCream("Lemon", numLemon, lemonServings, lemonPrice);
            addIceCream("Coffee", numCoffee, coffeeServings, chocolatePrice);
            addIceCream("Mint", numMint, mintServings, mintPrice);
        }

        //Get topping information
        Hashtable<String, Integer> toppingServingsTracker = model.toppingServingsTracker;
        int numChocolateChips = toppingServingsTracker.get("Chocolate Chips");
        int numSprinkles = toppingServingsTracker.get("Sprinkles");
        int numCherries = toppingServingsTracker.get("Cherries");
        int numWhippedCream = toppingServingsTracker.get("Whipped Cream");
        int numCoconut = toppingServingsTracker.get("Coconut");
        int numMarshmallows = toppingServingsTracker.get("Marshmallow");

        toppingInfo.getChildren().clear();

        //Check that no toppings are selected
        if(numChocolateChips + numSprinkles + numCherries + numWhippedCream + numCoconut + numMarshmallows == 0){
            toppingInfo.getChildren().add(noToppingsSelected);
        }else{
            addToppings("Chocolate Chips", numChocolateChips, chocolateChipsServings, chocolateChipsPrice);
            addToppings("Sprinkles", numSprinkles, sprinklesServings, sprinklesPrice);
            addToppings("Cherries", numCherries, cherriesServings, cherriesPrice);
            addToppings("Whipped Cream", numWhippedCream, whippedCreamServings, whippedCreamPrice);
            addToppings("Coconut", numCoconut, coconutServings, coconutPrice);
            addToppings("Marshmallow", numMarshmallows, marshmallowServings, marshmallowPrice);
        }


    }

    public void addIceCream(String iceCreamFlavor, int numScoops, Text flavorServings, Text flavorPrice){
        if(numScoops == 0){
            return;
        }
        HBox flavorBox = new HBox();
        flavorBox.setAlignment(Pos.CENTER);

        HBox flavorServingsBox = new HBox();
        flavorServingsBox.setMinWidth(35);
        flavorServingsBox.setAlignment(Pos.CENTER);
        flavorServings = new Text(Integer.toString(numScoops) + "x");
        flavorServingsBox.getChildren().add(flavorServings);

        HBox flavorTitleBox = new HBox();
        flavorTitleBox.setMinWidth(120);
        flavorTitleBox.setAlignment(Pos.CENTER);
        Text flavorTitle = new Text(iceCreamFlavor);
        flavorTitleBox.getChildren().add(flavorTitle);

        HBox flavorPriceBox = new HBox();
        flavorPriceBox.setMinWidth(60);
        flavorPriceBox.setAlignment(Pos.CENTER);
        flavorPrice = new Text("$" + numScoops + ".00");
        flavorPriceBox.getChildren().add(flavorPrice);
        flavorBox.getChildren().addAll(flavorServingsBox, flavorTitleBox, flavorPriceBox);
        iceCreamInfo.getChildren().add(flavorBox);
    }

    public void addToppings(String toppingName, int numServings, Text toppingServings, Text toppingPrice){
        if(numServings == 0){
            return;
        }
        HBox toppingBox = new HBox();
        toppingBox.setAlignment(Pos.CENTER);

        HBox toppingServingsBox = new HBox();
        toppingServingsBox.setMinWidth(35);
        toppingServingsBox.setAlignment(Pos.CENTER);
        toppingServings = new Text(Integer.toString(numServings) + "x");
        toppingServingsBox.getChildren().add(toppingServings);

        HBox toppingTitleBox = new HBox();
        toppingTitleBox.setMinWidth(120);
        toppingTitleBox.setAlignment(Pos.CENTER);
        Text toppingTitle = new Text(toppingName);
        toppingTitleBox.getChildren().add(toppingTitle);

        HBox toppingPriceBox = new HBox();
        toppingPriceBox.setAlignment(Pos.CENTER);
        toppingPriceBox.setMinWidth(60);
        double toppingCost = (double)numServings / 2;
        toppingPrice = new Text("$" + toppingCost + "0");
        toppingPriceBox.getChildren().add(toppingPrice);
        toppingBox.getChildren().addAll(toppingServingsBox, toppingTitleBox, toppingPriceBox);
        toppingInfo.getChildren().add(toppingBox);
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
    }

}

package sample;


import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class MilkshakeModel {

    ArrayList<MilkshakeModelListener> subscribers;

    Hashtable<String, Integer> iceCreamScoopsTracker;
    Hashtable<String, Integer> toppingServingsTracker;

    int maxScoopsIceCream;
    int curScoopsIceCream;
    boolean iceCreamOverflowWarning;
    boolean iceCreamUnderflowWarning;

    int maxServingsTopping;
    int curServingsTopping;
    boolean toppingOverflowWarning;
    boolean toppingUnderflowWarning;

    Stage window;

    public MilkshakeModel(Stage primaryStage){

        this.window = primaryStage;

        subscribers = new ArrayList<>();

        //Ice cream info
        curScoopsIceCream   = 0;
        maxScoopsIceCream   = 8;
        iceCreamScoopsTracker = new Hashtable<>();
        iceCreamScoopsTracker.put("Chocolate", 0);
        iceCreamScoopsTracker.put("Vanilla", 0);
        iceCreamScoopsTracker.put("Strawberry", 0);
        iceCreamScoopsTracker.put("Lemon", 0);
        iceCreamScoopsTracker.put("Coffee", 0);
        iceCreamScoopsTracker.put("Mint", 0);
        iceCreamOverflowWarning = false;
        iceCreamUnderflowWarning = false;

        //Topping info
        maxServingsTopping = 8;
        curServingsTopping = 0;
        toppingServingsTracker = new Hashtable<>();
        toppingServingsTracker.put("Chocolate Chips", 0);
        toppingServingsTracker.put("Sprinkles", 0);
        toppingServingsTracker.put("Cherries", 0);
        toppingServingsTracker.put("Whipped Cream", 0);
        toppingServingsTracker.put("Coconut", 0);
        toppingServingsTracker.put("Marshmallow", 0);
        toppingOverflowWarning = false;
        toppingUnderflowWarning = false;

    }


    public void addSubscriber(MilkshakeModelListener newSub){
        subscribers.add(newSub);
    }

    private void notifySubscribers(){
        for(MilkshakeModelListener sub : this.subscribers){
            sub.modelChanged();
        }
    }

    public void recordIceCreamPlusButton(String itemName){
        if(this.curScoopsIceCream == this.maxScoopsIceCream){
            this.iceCreamOverflowWarning = true;
            this.iceCreamUnderflowWarning = false;
        }
        else {

            Integer numScoops = iceCreamScoopsTracker.get(itemName);
            iceCreamScoopsTracker.replace(itemName, numScoops + 1);
            this.curScoopsIceCream++;
            this.iceCreamOverflowWarning = false;
            this.iceCreamUnderflowWarning = false;
        }
        notifySubscribers();
    }

    public void recordIceCreamMinusButton(String itemName){

        Integer numScoops = iceCreamScoopsTracker.get(itemName);
        if(numScoops == 0){
            this.iceCreamUnderflowWarning = true;
            this.iceCreamOverflowWarning = false;
        }
        else {
            iceCreamScoopsTracker.replace(itemName, numScoops - 1);
            this.curScoopsIceCream--;
            this.iceCreamOverflowWarning = false;
            this.iceCreamUnderflowWarning = false;
        }

        notifySubscribers();
    }

    public void recordToppingPlusButton(String itemName){
        if(this.curServingsTopping == this.maxServingsTopping){
            this.toppingOverflowWarning = true;
            this.toppingUnderflowWarning = false;
        }
        else {

            Integer numScoops = toppingServingsTracker.get(itemName);
            toppingServingsTracker.replace(itemName, numScoops + 1);
            this.curServingsTopping++;
            this.toppingOverflowWarning = false;
            this.toppingUnderflowWarning = false;
        }
        notifySubscribers();
    }

    public void recordToppingMinusButton(String itemName){
        Integer numScoops = toppingServingsTracker.get(itemName);
        if(numScoops == 0){
            this.toppingUnderflowWarning = true;
            this.toppingOverflowWarning = false;
        }
        else {
            toppingServingsTracker.replace(itemName, numScoops - 1);
            this.curServingsTopping--;
            this.toppingOverflowWarning = false;
            this.toppingUnderflowWarning = false;
        }

        notifySubscribers();
    }

    public void resetButtonPressed(){

        Set<String> iceCreamKeys = iceCreamScoopsTracker.keySet();
        for(String key : iceCreamKeys){
            this.iceCreamScoopsTracker.replace(key, 0);
        }

        Set<String> toppingKeys = toppingServingsTracker.keySet();
        for(String key : toppingKeys){
            this.toppingServingsTracker.replace(key, 0);
        }

        this.curScoopsIceCream = 0;
        this.iceCreamOverflowWarning = false;
        this.iceCreamUnderflowWarning = false;

        this.curServingsTopping = 0;
        this.toppingUnderflowWarning = false;
        this.toppingOverflowWarning = false;

        notifySubscribers();

    }
}

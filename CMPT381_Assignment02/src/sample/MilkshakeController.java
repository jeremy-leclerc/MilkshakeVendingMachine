package sample;

import java.lang.Exception;

public class MilkshakeController {

    MilkshakeModel model;

    public MilkshakeController(){
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void handleIceCreamPlusButton(String itemName){
        this.model.recordIceCreamPlusButton(itemName);
    }

    public void handleIceCreamMinusButton(String itemName){
        this.model.recordIceCreamMinusButton(itemName);
    }

    public void handleToppingPlusButton(String itemName){
        this.model.recordToppingPlusButton(itemName);
    }

    public void handleToppingMinusButton(String itemName){
        this.model.recordToppingMinusButton(itemName);
    }

}

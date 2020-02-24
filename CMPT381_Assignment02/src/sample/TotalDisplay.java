package sample;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Collection;
import java.util.Hashtable;


public class TotalDisplay extends Pane implements MilkshakeModelListener {

    MilkshakeModel model;
    MilkshakeController controler;

    Text total;

    public TotalDisplay(HBox parent){

        VBox totalBox = new VBox();
//        totalBox.setStyle("-fx-border-color: red;\n");
        totalBox.setStyle(Main.TEXT_STYLE);
        totalBox.setAlignment(Pos.CENTER);
        totalBox.prefHeightProperty().bind(parent.heightProperty());
        total = new Text("Total: $0.00");
        totalBox.getChildren().add(total);
        this.getChildren().add(totalBox);
    }

    public void modelChanged(){

        double totalSum = 0;

        Hashtable<String, Integer> iceCreamServings = model.iceCreamScoopsTracker;
        Collection<Integer> scoops = iceCreamServings.values();
        for(Integer numScoops : scoops){
            totalSum += numScoops;
        }

        Hashtable<String, Integer> toppingServings = model.toppingServingsTracker;
        Collection<Integer> servings = toppingServings.values();
        for(Integer numServings : servings){
            totalSum += (double)numServings / 2;
        }

        total.setText("Total: $" + Double.toString(totalSum) + "0");


    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }

    public void setControler(MilkshakeController controler){
        this.controler = controler;
    }

}


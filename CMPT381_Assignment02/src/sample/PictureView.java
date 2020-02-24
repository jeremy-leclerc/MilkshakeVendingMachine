package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class PictureView extends Pane implements MilkshakeModelListener {

    MilkshakeModel model;
    MilkshakeController controller;

    Canvas myCanvas;
    GraphicsContext gc;
    double width, height, radiusH, radiusW;

    VBox cupBox;
    HBox canvasBox;

    String debugCode = "-fx-border-color: red;\n";


    public PictureView(double newWidth, double newHeight, VBox parent){

        canvasBox = new HBox();
        //canvasBox.setStyle(debugCode);
        canvasBox.setAlignment(Pos.CENTER);

        cupBox = new VBox();
        cupBox.setStyle("-fx-border-color: black;\n");
        //cupBox.setStyle(debugCode);
        cupBox.setBackground(new Background(new BackgroundFill(Color.POWDERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        canvasBox.getChildren().add(cupBox);

        myCanvas = new Canvas();
        //myCanvas.setStyle(debugCode);
        gc = myCanvas.getGraphicsContext2D();
        cupBox.getChildren().add(myCanvas);

        this.getChildren().add(canvasBox);

        canvasBox.prefWidthProperty().bind(parent.widthProperty());
        canvasBox.prefHeightProperty().bind(parent.heightProperty());
        cupBox.prefWidthProperty().bind(canvasBox.widthProperty().multiply(.35));
        myCanvas.setWidth(cupBox.getWidth());
        myCanvas.setHeight(cupBox.getHeight());

    }

    public void draw(){

        myCanvas.setWidth(cupBox.getWidth()-2);
        myCanvas.setHeight(cupBox.getHeight()-2);

        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());

        width = myCanvas.getWidth();
        height = myCanvas.getHeight();

        int scoopNum = 0;
        Hashtable<String, Integer> iceCreamScoops = model.iceCreamScoopsTracker;
        Set<String> iceCreamKeys = iceCreamScoops.keySet();
        for(String key : iceCreamKeys){
            if(iceCreamScoops.get(key) != 0){
                Color iceCreamColor = getIceCreamColor(key);
                for(int i = 0; i < iceCreamScoops.get(key); i++){
                    scoopNum++;
                    putScoop(scoopNum, iceCreamColor);
                }
            }
        }

        int servingNum = 0;
        Hashtable<String, Integer> toppingServings = model.toppingServingsTracker;
        Set<String> toppingKeys = toppingServings.keySet();
        for(String key : toppingKeys){
            if(toppingServings.get(key) != 0){
                Color toppingColor = getToppingColor(key);
                for(int i = 0; i < toppingServings.get(key); i++){
                    servingNum++;
                    putTopping(servingNum, toppingColor);
                }
            }
        }


    }

    public void putTopping(Integer servingNum, Color toppingColor){
        gc.setFill(toppingColor);
        if(servingNum == 1){
            gc.fillRect(width-width/3, height-(height/3), width/10, width/10);
        }
        if(servingNum == 2){
            gc.fillRect(width-width/3*2, height-(height/3), width/10, width/10);
        }
        if(servingNum == 3){
            gc.fillRect(width-width/3*3, height-(height/3), width/10, width/10);
        }
        if(servingNum == 4){
            gc.fillRect(width-width/3, height-(height/3*2), width/10, width/10);
        }
        if(servingNum == 5){
            gc.fillRect(width-width/3*2, height-(height/3*2), width/10, width/10);
        }
        if(servingNum == 6){
            gc.fillRect(width-width/3*3, height-(height/3*2), width/10, width/10);
        }
        if(servingNum == 7){
            gc.fillRect(width-width/3, 0, width/10, width/10);
        }
        if(servingNum == 8){
            gc.fillRect(width-width*.75, 0, width/10, width/10);
        }
    }

    public Color getToppingColor(String toppingName){

        if(toppingName.equals("Chocolate Chips")){
            return Color.CHOCOLATE;
        }
        else if(toppingName.equals("Sprinkles")){
            return Color.PURPLE;
        }
        else if(toppingName.equals("Cherries")){
            return Color.INDIANRED;
        }
        else if(toppingName.equals("Whipped Cream")){
            return Color.NAVAJOWHITE;
        }
        else if(toppingName.equals("Coconut")){
            return Color.ANTIQUEWHITE;
        }
        else{
            return Color.WHITESMOKE;
        }
    }

    public void putScoop(int scoopNum, Color iceCreamColor){
        gc.setFill(iceCreamColor);
        if(scoopNum == 1){
            gc.fillOval(width-(width/3*1), height-(height/3), width/3, width/3);
        }
        if(scoopNum == 2){
            gc.fillOval(width-(width/3*2), height-(height/3), width/3, width/3);
        }
        if(scoopNum == 3){
            gc.fillOval(width-(width/3*3), height-(height/3), width/3, width/3);
        }
        if(scoopNum == 4){
            gc.fillOval(width-(width/3*1), height-(height/3*2), width/3, width/3);
        }
        if(scoopNum == 5){
            gc.fillOval(width-(width/3*2), height-(height/3*2), width/3, width/3);
        }
        if(scoopNum == 6){
            gc.fillOval(width-(width/3*3), height-(height/3*2), width/3, width/3);
        }
        if(scoopNum == 7){
            gc.fillOval(width-(width/2*1), 0, width/3, width/3);
        }
        if(scoopNum == 8){
            gc.fillOval(width/8, 0, width/3, width/3);
        }
    }

    public Color getIceCreamColor(String flavor){
        if (flavor.equals("Chocolate")){
            return Color.BROWN;
        }
        else if(flavor.equals("Vanilla")){
            return Color.WHITE;
        }
        else if(flavor.equals("Strawberry")){
            return Color.RED;
        }
        else if(flavor.equals("Lemon")){
            return Color.YELLOW;
        }
        else if(flavor.equals("Coffee")){
            return Color.BEIGE;
        }
        else{
            return Color.MINTCREAM;
        }
    }

    public void modelChanged(){
        this.draw();
    }

    public void setController(MilkshakeController controller){
        this.controller = controller;
    }

    public void setModel(MilkshakeModel model){
        this.model = model;
    }
}

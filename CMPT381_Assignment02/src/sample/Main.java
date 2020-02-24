package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static int SCREEN_WIDTH = 1200;
    public static int SCREEN_HEIGHT = 800;
    public static Color BASE_COLOR = Color.LIGHTPINK;
    public static Color SECONDARY_COLOR = Color.PAPAYAWHIP;
    public static Color BORDER_COLOR = Color.BLANCHEDALMOND;
    public static String BORDER_STYLE1 =  "-fx-border-color: blanchedalmond;\n" +
            "-fx-border-insets: 0;\n" +
            "-fx-border-width: 2;\n" +
            "-fx-border-style: solid;\n";
    public static String BORDER_STYLE2 =  "-fx-border-color: lightblue;\n" +
            "-fx-border-insets: 0;\n" +
            "-fx-border-width: 2;\n" +
            "-fx-border-style: solid;\n";
    public static String TEXT_STYLE = "-fx-font: 16 arial;";


    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setMinWidth(710);
        primaryStage.setMinHeight(565);

        //Main layout
        VBox root = new VBox();
        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(BASE_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().add(borderPane);

        //Set banner
        HBox bannerBox = new HBox();
        bannerBox.setAlignment(Pos.CENTER);
        bannerBox.setBackground(new Background(new BackgroundFill(BASE_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        bannerBox.setStyle(BORDER_STYLE1);
        bannerBox.setPrefHeight(30);
        Text bannerTitle = new Text("Milkshake Vending Machine");
        bannerTitle.setStyle(TEXT_STYLE);
        bannerBox.getChildren().addAll(bannerTitle);
        borderPane.setTop(bannerBox);

        //Create left side box
        VBox leftSide = new VBox();
        leftSide.prefWidthProperty().bind(primaryStage.widthProperty().multiply(.50));
        leftSide.prefHeightProperty().bind(primaryStage.heightProperty());
        leftSide.setBackground(new Background(new BackgroundFill(BASE_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        leftSide.setStyle(BORDER_STYLE1);
        leftSide.setPadding(new Insets(10));
        leftSide.setSpacing(5);
        borderPane.setLeft(leftSide);

        //Create left side header "Create your milkshake"
        HBox createYourMilkshakeTitleBox = new HBox();
        createYourMilkshakeTitleBox.setAlignment(Pos.CENTER);
        Text createYourMilkshakeTitle = new Text("Create Your Milkshake");
        createYourMilkshakeTitle.setStyle(TEXT_STYLE);
        createYourMilkshakeTitleBox.getChildren().addAll(createYourMilkshakeTitle);
        leftSide.getChildren().addAll(createYourMilkshakeTitleBox);

        //Create create milkshake section
        VBox createMilkshakeSection = new VBox();
        createMilkshakeSection.setMinHeight(442);
        createMilkshakeSection.prefWidthProperty().bind(leftSide.widthProperty());
        createMilkshakeSection.prefHeightProperty().bind(leftSide.heightProperty().multiply(.8));
        createMilkshakeSection.setBackground(new Background(new BackgroundFill(SECONDARY_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        createMilkshakeSection.setPadding(new Insets(10));
        createMilkshakeSection.setSpacing(10);
        leftSide.getChildren().add(createMilkshakeSection);

        //Ice cream Section
        VBox iceCreamSection = new VBox();
        iceCreamSection.prefWidthProperty().bind(createMilkshakeSection.widthProperty());
        iceCreamSection.prefHeightProperty().bind(createMilkshakeSection.heightProperty().multiply(.5));
        createMilkshakeSection.getChildren().add(iceCreamSection);

        //Topping section
        VBox toppingSection = new VBox();
        toppingSection.setAlignment(Pos.BOTTOM_CENTER);
        toppingSection.prefWidthProperty().bind(createMilkshakeSection.widthProperty());
        toppingSection.prefHeightProperty().bind(createMilkshakeSection.heightProperty().multiply(.5));
        createMilkshakeSection.getChildren().add(toppingSection);

        //Start over button section
        HBox startOverButtonSection = new HBox();
        startOverButtonSection.setAlignment(Pos.CENTER);
        startOverButtonSection.prefHeightProperty().bind(leftSide.heightProperty().multiply(.15));
        leftSide.getChildren().add(startOverButtonSection);

        //Right side
        VBox rightSide = new VBox();
        rightSide.prefWidthProperty().bind(primaryStage.widthProperty().multiply(.5));
        rightSide.prefHeightProperty().bind(primaryStage.heightProperty());
        rightSide.setBackground(new Background(new BackgroundFill(BASE_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        rightSide.setStyle(BORDER_STYLE1);
        rightSide.setPadding(new Insets(10));
        rightSide.setSpacing(5);
        borderPane.setRight(rightSide);

        //create "your Milkshake" section"
        HBox yourMilkshakeTitleBox = new HBox();
        yourMilkshakeTitleBox.setAlignment(Pos.CENTER);
        Text yourMilkshakeTitle = new Text("Your Milkshake");
        yourMilkshakeTitle.setStyle(TEXT_STYLE);
        yourMilkshakeTitleBox.getChildren().add(yourMilkshakeTitle);
        rightSide.getChildren().add(yourMilkshakeTitleBox);

        //Your milkshake section
        VBox yourMilkshakeSection = new VBox();
        yourMilkshakeSection.setMinHeight(442);
        yourMilkshakeSection.prefWidthProperty().bind(rightSide.widthProperty());
        yourMilkshakeSection.prefHeightProperty().bind(rightSide.heightProperty().multiply(.8));
        yourMilkshakeSection.setBackground(new Background(new BackgroundFill(SECONDARY_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        yourMilkshakeSection.setPadding(new Insets(10));
        yourMilkshakeSection.setSpacing(10);
        rightSide.getChildren().add(yourMilkshakeSection);

        //create description section
        VBox descriptionSection = new VBox();
        //descriptionSection.setStyle(BORDER_STYLE2);//!!!!!!
        descriptionSection.prefWidthProperty().bind(yourMilkshakeSection.widthProperty());
        descriptionSection.prefHeightProperty().bind(yourMilkshakeSection.heightProperty().multiply(.5));
        yourMilkshakeSection.getChildren().add(descriptionSection);

        //create picture section
        VBox pictureSection = new VBox();
        //pictureSection.setStyle(BORDER_STYLE2);//!!!!!!
        yourMilkshakeSection.getChildren().add(pictureSection);

        primaryStage.setScene(new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT));
        primaryStage.setTitle("MSVM");
        primaryStage.show();

        //fix!!!!!1
        pictureSection.prefWidthProperty().bind(yourMilkshakeSection.widthProperty());
        pictureSection.prefHeightProperty().bind(yourMilkshakeSection.heightProperty().multiply(.35));
        //pictureSection.setStyle(BORDER_STYLE2);

        //Total section
        HBox totalSection = new HBox();
        //totalSection.setStyle("-fx-border-color: pink;\n");
        totalSection.setAlignment(Pos.CENTER);
        //totalSection.setStyle(BORDER_STYLE2);
        totalSection.prefWidthProperty().bind(yourMilkshakeSection.widthProperty());
        totalSection.prefHeightProperty().bind(yourMilkshakeSection.heightProperty().multiply(.15));
        yourMilkshakeSection.getChildren().add(totalSection);

        //Checkout button section
        HBox checkoutButtonSection = new HBox();
        //checkoutButtonSection.setStyle(BORDER_STYLE1); //!!!!!!!!!
        checkoutButtonSection.setAlignment(Pos.CENTER);
        checkoutButtonSection.prefHeightProperty().bind(rightSide.heightProperty().multiply(.15));
        rightSide.getChildren().add(checkoutButtonSection);

        //Get MVC components
        MilkshakeModel model = new MilkshakeModel(primaryStage);
        MilkshakeController controller = new MilkshakeController();

        //get views
        IceCreamView iceCreamView = new IceCreamView(iceCreamSection);
        iceCreamSection.getChildren().add(iceCreamView);

        ToppingsView toppingsView = new ToppingsView(toppingSection);
        toppingSection.getChildren().add(toppingsView);

        StartOverButton startOverButton = new StartOverButton(startOverButtonSection);
        startOverButtonSection.getChildren().add(startOverButton);

        DescriptionView description = new DescriptionView(descriptionSection);
        descriptionSection.getChildren().add(description);

        PictureView pictureView = new PictureView(1000, 1000, pictureSection);
        pictureSection.getChildren().add(pictureView);

        TotalDisplay totalDisplay = new TotalDisplay(totalSection);
        totalSection.getChildren().add(totalDisplay);
        //totalSection.setStyle("-fx-border-color: red;\n");

        CheckoutButton checkoutButton = new CheckoutButton(checkoutButtonSection);
        checkoutButtonSection.getChildren().add(checkoutButton);

        //Connect MVC components
        controller.setModel(model);
        iceCreamView.setModel(model);
        toppingsView.setModel(model);
        startOverButton.setModel(model);
        description.setModel(model);
        pictureView.setModel(model);
        totalDisplay.setModel(model);
        checkoutButton.setModel(model);
        model.addSubscriber(iceCreamView);
        model.addSubscriber(toppingsView);
        model.addSubscriber(startOverButton);
        model.addSubscriber(description);
        model.addSubscriber(pictureView);
        model.addSubscriber(totalDisplay);
        model.addSubscriber(checkoutButton);
        iceCreamView.setController(controller);
        toppingsView.setController(controller);
        startOverButton.setController(controller);
        description.setController(controller);
        pictureView.setController(controller);
        totalDisplay.setControler(controller);
        checkoutButton.setController(controller);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

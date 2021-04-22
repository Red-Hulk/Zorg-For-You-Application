package application.abstractClasses;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/*This class is required for almost every scene in application, this is the set up for LEFT PANEL & TOP SIDE part with LOGO side of scene*/

public abstract class LeftPanelGUI {

    //Containers
    protected BorderPane root;
    protected Pane containerButton;
    protected GridPane containerRight;
    protected Button question, btnHome;
    protected Image logo;
    protected ImageView logoContainer;
    protected Rectangle rect, rectLogo;
    protected Label lbLogo;
    protected Circle crLogo, crLogoB;

    public void setUpLeftPanel() {
        //Containers
        root = new BorderPane();
        containerButton = new Pane();
        containerRight = new GridPane();

        //Building Blocks
        rect = new Rectangle();
        rect.setId("rectLeftPanel");
        rect.setWidth(150);
        rect.setHeight(400);

        crLogoB = new Circle();
        crLogoB.setId("crLogoB");
        crLogoB.setRadius(60);
        crLogoB.setCenterX(35);
        crLogoB.setCenterY(30);

        crLogo = new Circle();
        crLogo.setId("crLogo");
        crLogo.setRadius(48);
        crLogo.setCenterX(35);
        crLogo.setCenterY(30);
        crLogo.setStroke(Color.WHITE);

        rectLogo = new Rectangle();
        rectLogo.setId("rectLogo");
        rectLogo.setHeight(40);
        rectLogo.setWidth(80);
        rectLogo.setLayoutX(10);
        rectLogo.setLayoutY(12.5);

        lbLogo = new Label("4-You");
        lbLogo.setId("lbLogo");
        lbLogo.setLayoutX(7.50);
        lbLogo.setLayoutY(10);

        question = new Button("?");
        question.setId("btnQuestion");

        btnHome = new Button("Home");
        btnHome.setLayoutX(7.50);
        btnHome.setLayoutY(340);

        /*-------------------------------------------------*/
        //Place elements in Borderpane
        root.setLeft(containerButton);
        root.setRight(containerRight);

        //Placement left vbox
        containerButton.getChildren().addAll(rect, crLogoB, crLogo, rectLogo, lbLogo, btnHome ,question);

        //Placements overige childeren in het algemeen Buttons [eb]
        setUpLeftPanelButtons();

        //Placement main top borderpane
        containerRight.add(question,0,0);
        containerRight.setPadding(new Insets(7.5, 10, 10, 10));

        /*--------------------------------------------------*/
        //Stylesheet link
        root.getStylesheets().add("application/stylesheets/style.css");

        //Styling
        containerRight.getStyleClass().add("Right");
        containerButton.getStyleClass().add("LeftPanel");
    }

    protected abstract void setUpLeftPanelButtons();

}
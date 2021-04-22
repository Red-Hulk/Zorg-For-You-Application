package application.GUIClasses;

import application.BackEndClasses.Admin;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class GUIAdmin {

    public GUIAdmin(Stage primaryStage) throws SQLException, ClassNotFoundException {
        Stage secondaryStage = new Stage();
        Pane root = new Pane();

        Label lbPersonen = new Label("Personen");
        lbPersonen.setLayoutX(5);
        lbPersonen.setLayoutY(5);

        Label lbVragen = new Label("Vragen");
        lbVragen.setLayoutX(205);
        lbVragen.setLayoutY(5);

        Label lbForum = new Label("Forum onderwerpen");
        lbForum.setLayoutX(405);
        lbForum.setLayoutY(5);

        Button btnPersonen = new Button("Personen");
        btnPersonen.setId("btnPersonen");
        btnPersonen.setLayoutX(5);
        btnPersonen.setLayoutY(25);

        TextArea taVragen = new TextArea();
        taVragen.editableProperty().setValue(false);
        taVragen.setLayoutX(205);
        taVragen.setLayoutY(25);

        TextArea taForum = new TextArea();
        taForum.editableProperty().setValue(false);
        taForum.setLayoutX(405);
        taForum.setLayoutY(25);

        TextField txtDelVraag = new TextField();
        txtDelVraag.setPromptText("voer vraag id in");
        txtDelVraag.setLayoutX(205);
        txtDelVraag.setLayoutY(300);

        TextField txtDelForum = new TextField();
        txtDelForum.setPromptText("voer forum id in");
        txtDelForum.setLayoutX(405);
        txtDelForum.setLayoutY(300);

        Button btnDelVraag = new Button("Verwijder");
        btnDelVraag.setId("btnDelVraag");
        btnDelVraag.setLayoutX(205);
        btnDelVraag.setLayoutY(350);

        Button btnDelForum = new Button("Verwijder");
        btnDelForum.setId("btnDelForum");
        btnDelForum.setLayoutX(405);
        btnDelForum.setLayoutY(350);

        root.getChildren().addAll(btnPersonen, taVragen, taForum, txtDelForum, txtDelVraag, btnDelForum, btnDelVraag, lbForum, lbPersonen, lbVragen);
        root.getStylesheets().add("application/stylesheets/styleAdmin.css");

        secondaryStage.setTitle("Hello World");
        secondaryStage.setScene(new Scene(root, 600, 400));
        secondaryStage.show();

        new Admin(btnPersonen, taVragen, taForum, txtDelForum,  txtDelVraag, btnDelForum, btnDelVraag);
    }
}

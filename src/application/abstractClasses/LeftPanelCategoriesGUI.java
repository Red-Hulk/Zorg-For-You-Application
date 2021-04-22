package application.abstractClasses;

import javafx.scene.control.Button;

public abstract class LeftPanelCategoriesGUI extends LeftPanelGUI {

    protected Button btnYouth, btnSenior, btnRefugee, btnProfessional;

    @Override
    protected void setUpLeftPanelButtons() {

        btnYouth = new Button("Jongeren -23");
        btnYouth.setLayoutX(7.50);
        btnYouth.setLayoutY(100);

        btnSenior = new Button("Ouderen 65+");
        btnSenior.setLayoutX(7.50);
        btnSenior.setLayoutY(155);

        btnRefugee = new Button("Vluchteling");
        btnRefugee.setLayoutX(7.50);
        btnRefugee.setLayoutY(210);

        btnProfessional = new Button("Professional");
        btnProfessional.setLayoutX(7.50);
        btnProfessional.setLayoutY(265);

        containerButton.getChildren().addAll(btnYouth, btnSenior, btnRefugee, btnProfessional);

    }
}

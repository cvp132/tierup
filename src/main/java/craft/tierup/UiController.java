/*
RULE: If it is working, don't touch it
*/
package craft.tierup;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class UiController {
    @FXML
    private FXMLLoader loader;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;

    public void uiCraft(MouseEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("app.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("TierUp");
        stage.show();
    }

    public void hyperLinkOne() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://icons8.com/icon/1AFRKhbttYBx/sword"));

    }

    public void hyperLinkTwo() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://icons8.com/icon/3XxgEdROIniT/carving"));

    }

    public void hyperLinkThree() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://icons8.com/icon/hve05CyaEd2a/hand-holding-heart"));

    }

    public void hyperLinkFour() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://icons8.com/icon/12780/calculator"));

    }

    public void tutorialLink() throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(java.net.URI.create("https://youtube.com"));

    }


}


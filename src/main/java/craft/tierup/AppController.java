/*
RULE: If it is working, don't touch it
*/
package craft.tierup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private ComboBox<String> in_equipment;

    @FXML
    private final ObservableList<String> in_equipmentOptions = FXCollections.observableArrayList("Armor", "Main Weapon / Accessories", "Off-hand Weapon / Earrings", "Dragon Artifact");

    @FXML
    private ComboBox<String> in_rarity;

    @FXML
    private final ObservableList<String> in_rarityOptions = FXCollections.observableArrayList("Rare", "Epic", "Legendary");

    @FXML
    private ComboBox<String> in_tier;

    @FXML
    private final ObservableList<String> in_tierOptions = FXCollections.observableArrayList("I", "II", "III", "IV");

    @FXML
    private CheckBox in_tierOne, in_tierTwo, in_tierThree, in_tierFour;

    @FXML
    private TextField in_rareMatOne, in_rareMatTwo, in_rareMatThree, in_rareMatFour, in_epicMatOne, in_epicMatTwo, in_epicMatThree, in_epicMatFour, in_legendMatOne, in_legendMatTwo, in_legendMatThree, in_legendMatFour, in_matFive;

    @FXML
    private TextField in_copper, in_darkSteel, in_oldSilver, in_glittering, in_elixir, in_dragonSteel;

    @FXML
    private Label out_rareMatOne, out_rareMatTwo, out_rareMatThree, out_rareMatFour, out_epicMatOne, out_epicMatTwo, out_epicMatThree, out_epicMatFour, out_legendMatOne, out_legendMatTwo, out_legendMatThree, out_legendMatFour, out_matFive;

    @FXML
    private Label out_copper, out_darkSteel, out_oldSilver, out_glittering, out_elixir, out_dragonSteel;

    @FXML
    private Label lab_tierOneMultiplier, lab_tierTwoMultiplier, lab_tierThreeMultiplier;

    @FXML
    private Label lab_materialFour1, lab_materialFive1, lab_materialFour2, lab_materialFive2;

    @FXML
    private Label lab_epic1, lab_legendary1, lab_epic2, lab_legendary2;

    @FXML
    private Label lab_elixir1, lab_elixir2, lab_dragonSteel1, lab_dragonSteel2;

    @FXML
    private Label errorMessage;

    @FXML
    private FXMLLoader loader;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    String equipment, rarity, tier;

    final long OLD_SILVER_VALUE = 10_000;

    int owned_rareMatOne, owned_rareMatTwo, owned_rareMatThree, owned_rareMatFour;

    int owned_epicMatOne, owned_epicMatTwo, owned_epicMatThree, owned_epicMatFour;

    int owned_legendMatOne, owned_legendMatTwo, owned_legendMatThree, owned_legendMatFour;

    int owned_matFive;

    long owned_oldSilver, owned_glittering, owned_elixir, owned_dragonSteel;

    long owned_copper, owned_darkSteel;

    int tierMultiplier;

    int req_rareMatOne, req_rareMatTwo, req_rareMatThree, req_rareMatFour;

    int req_epicMatOne, req_epicMatTwo, req_epicMatThree, req_epicMatFour;

    int req_legendMatOne, req_legendMatTwo, req_legendMatThree, req_legendMatFour;

    int req_matFive;

    long req_glittering, req_elixir, req_dragonSteel;

    long req_copper, req_darkSteel;

    int tierOneMultiplier, tierTwoMultiplier, tierThreeMultiplier, tierFourMultiplier;

    long tierCopperCost, tierDarkSteelCost, tierDragonSteelCost;

    @FXML
    public void uiAppreciate(MouseEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("appreciate.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("TierUp - Donation");
        stage.show();
    }

    Stage mcStage = new Stage();
    Image icon = new Image("calc.png");
    FXMLLoader mcLoader;

    @FXML
    public void uiMarketCalculator() throws IOException {
        mcLoader = new FXMLLoader(getClass().getResource("market-calc.fxml"));
        mcStage.setTitle("TierUp - Market Calculator");
        mcStage.getIcons().add(icon);
        mcStage.setScene(new Scene(mcLoader.load()));
        mcStage.setResizable(false);
        mcStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        in_equipment.setItems(in_equipmentOptions);
        in_rarity.setItems(in_rarityOptions);
        in_tier.setItems(in_tierOptions);
        in_equipment.setOnAction(this::itemInfo);
        in_rarity.setOnAction(this::itemInfo);
        in_tier.setOnAction(this::itemInfo);
    }

    public void itemInfo(ActionEvent event){
        equipment = in_equipment.getValue();
        rarity = in_rarity.getValue();
        tier = in_tier.getValue();


        try {
            if (!equipment.equals("Dragon Artifact")){
                in_rareMatFour.setText("");
                in_rareMatFour.setDisable(true);
                in_epicMatFour.setText("");
                in_epicMatFour.setDisable(true);
                in_legendMatFour.setText("");
                in_legendMatFour.setDisable(true);
                in_matFive.setText("");
                in_matFive.setDisable(true);
                in_elixir.setText("");
                in_elixir.setDisable(true);
                in_dragonSteel.setText("");
                in_dragonSteel.setDisable(true);
                out_rareMatFour.setText("");
                out_rareMatFour.setDisable(true);
                out_epicMatFour.setText("");
                out_epicMatFour.setDisable(true);
                out_legendMatFour.setText("");
                out_legendMatFour.setDisable(true);
                out_matFive.setText("");
                out_matFive.setDisable(true);
                out_elixir.setText("");
                out_elixir.setDisable(true);
                out_dragonSteel.setText("");
                out_dragonSteel.setDisable(true);
                lab_materialFour1.setDisable(true);
                lab_materialFive1.setDisable(true);
                lab_materialFour2.setDisable(true);
                lab_materialFive2.setDisable(true);
                lab_elixir1.setDisable(true);
                lab_dragonSteel1.setDisable(true);
                lab_elixir2.setDisable(true);
                lab_dragonSteel2.setDisable(true);
                in_matFive.setLayoutX(66);
                in_matFive.setLayoutY(356);
                out_matFive.setLayoutX(67);
                out_matFive.setLayoutY(360);
            }
            else{
                in_tier.setValue("I");
                in_rareMatFour.setDisable(false);
                in_epicMatFour.setDisable(false);
                in_legendMatFour.setDisable(false);
                in_matFive.setDisable(false);
                in_elixir.setDisable(false);
                in_dragonSteel.setDisable(false);
                out_rareMatFour.setDisable(false);
                out_epicMatFour.setDisable(false);
                out_legendMatFour.setDisable(false);
                out_matFive.setDisable(false);
                out_elixir.setDisable(false);
                out_dragonSteel.setDisable(false);
                lab_materialFour1.setDisable(false);
                lab_materialFive1.setDisable(false);
                lab_materialFour2.setDisable(false);
                lab_materialFive2.setDisable(false);
                lab_elixir1.setDisable(false);
                lab_dragonSteel1.setDisable(false);
                lab_elixir2.setDisable(false);
                lab_dragonSteel2.setDisable(false);

                switch (rarity) {
                    case "Rare" ->{
                        in_epicMatFour.setDisable(true);
                        in_legendMatFour.setDisable(true);
                        in_epicMatFour.setText("");
                        in_legendMatFour.setText("");
                        out_epicMatFour.setDisable(true);
                        out_legendMatFour.setDisable(true);
                        out_epicMatFour.setText("");
                        out_legendMatFour.setText("");
                        out_matFive.setText("");
                        in_matFive.setText("");
                        in_matFive.setLayoutX(66);
                        in_matFive.setLayoutY(356);
                        out_matFive.setLayoutX(67);
                        out_matFive.setLayoutY(360);
                    }
                    case "Epic" ->{
                        in_epicMatFour.setDisable(false);
                        in_legendMatFour.setDisable(true);
                        in_legendMatFour.setText("");
                        out_epicMatFour.setDisable(false);
                        out_legendMatFour.setDisable(true);
                        out_legendMatFour.setText("");
                        out_matFive.setText("");
                        in_matFive.setText("");
                        in_matFive.setLayoutX(143);
                        in_matFive.setLayoutY(356);
                        out_matFive.setLayoutX(145);
                        out_matFive.setLayoutY(360);
                    }
                    case "Legendary" ->{
                        in_epicMatFour.setDisable(false);
                        in_legendMatFour.setDisable(false);
                        out_epicMatFour.setDisable(false);
                        out_legendMatFour.setDisable(false);
                        out_matFive.setText("");
                        in_matFive.setText("");
                        in_matFive.setLayoutX(220);
                        in_matFive.setLayoutY(356);
                        out_matFive.setLayoutX(223);
                        out_matFive.setLayoutY(360);
                    }
                }
            }

            switch (rarity){
                case "Rare" -> {
                    in_epicMatOne.setDisable(true);
                    in_epicMatTwo.setDisable(true);
                    in_epicMatThree.setDisable(true);
                    in_legendMatOne.setDisable(true);
                    in_legendMatTwo.setDisable(true);
                    in_legendMatThree.setDisable(true);
                    in_epicMatOne.setText("");
                    in_epicMatTwo.setText("");
                    in_epicMatThree.setText("");
                    in_legendMatOne.setText("");
                    in_legendMatTwo.setText("");
                    in_legendMatThree.setText("");
                    out_epicMatOne.setDisable(true);
                    out_epicMatTwo.setDisable(true);
                    out_epicMatThree.setDisable(true);
                    out_legendMatOne.setDisable(true);
                    out_legendMatTwo.setDisable(true);
                    out_legendMatThree.setDisable(true);
                    out_epicMatOne.setText("");
                    out_epicMatTwo.setText("");
                    out_epicMatThree.setText("");
                    out_legendMatOne.setText("");
                    out_legendMatTwo.setText("");
                    out_legendMatThree.setText("");
                    lab_epic1.setDisable(true);
                    lab_legendary1.setDisable(true);
                    lab_epic2.setDisable(true);
                    lab_legendary2.setDisable(true);
                }
                case "Epic" -> {
                    in_epicMatOne.setDisable(false);
                    in_epicMatTwo.setDisable(false);
                    in_epicMatThree.setDisable(false);
                    in_legendMatOne.setDisable(true);
                    in_legendMatTwo.setDisable(true);
                    in_legendMatThree.setDisable(true);
                    in_legendMatOne.setText("");
                    in_legendMatTwo.setText("");
                    in_legendMatThree.setText("");
                    out_epicMatOne.setDisable(false);
                    out_epicMatTwo.setDisable(false);
                    out_epicMatThree.setDisable(false);
                    out_legendMatOne.setDisable(true);
                    out_legendMatTwo.setDisable(true);
                    out_legendMatThree.setDisable(true);
                    out_legendMatOne.setText("");
                    out_legendMatTwo.setText("");
                    out_legendMatThree.setText("");
                    lab_epic1.setDisable(false);
                    lab_legendary1.setDisable(true);
                    lab_epic2.setDisable(false);
                    lab_legendary2.setDisable(true);
                }
                case "Legendary" -> {
                    in_epicMatOne.setDisable(false);
                    in_epicMatTwo.setDisable(false);
                    in_epicMatThree.setDisable(false);
                    in_legendMatOne.setDisable(false);
                    in_legendMatTwo.setDisable(false);
                    in_legendMatThree.setDisable(false);
                    out_epicMatOne.setDisable(false);
                    out_epicMatTwo.setDisable(false);
                    out_epicMatThree.setDisable(false);
                    out_legendMatOne.setDisable(false);
                    out_legendMatTwo.setDisable(false);
                    out_legendMatThree.setDisable(false);
                    lab_epic1.setDisable(false);
                    lab_legendary1.setDisable(false);
                    lab_epic2.setDisable(false);
                    lab_legendary2.setDisable(false);
                }
            }

            switch (tier) {
                case "I" -> {
                    tierMultiplier = 1;
                    in_tierOne.setDisable(false);
                    in_tierTwo.setDisable(true);
                    in_tierThree.setDisable(true);
                    in_tierFour.setDisable(true);
                    in_tierTwo.setSelected(false);
                    in_tierThree.setSelected(false);
                    in_tierFour.setSelected(false);
                    lab_tierOneMultiplier.setText("");
                    lab_tierTwoMultiplier.setText("");
                    lab_tierThreeMultiplier.setText("");
                }
                case "II" -> {
                    tierMultiplier = 2;
                    in_tierOne.setDisable(false);
                    in_tierTwo.setDisable(false);
                    in_tierThree.setDisable(true);
                    in_tierFour.setDisable(true);
                    in_tierThree.setSelected(false);
                    in_tierFour.setSelected(false);
                    lab_tierOneMultiplier.setText("(x2)");
                    lab_tierTwoMultiplier.setText("");
                    lab_tierThreeMultiplier.setText("");
                }
                case "III" -> {
                    tierMultiplier = 4;
                    in_tierOne.setDisable(false);
                    in_tierTwo.setDisable(false);
                    in_tierThree.setDisable(false);
                    in_tierFour.setDisable(true);
                    in_tierFour.setSelected(false);
                    lab_tierOneMultiplier.setText("(x4)");
                    lab_tierTwoMultiplier.setText("(x2)");
                    lab_tierThreeMultiplier.setText("");
                }
                case "IV" -> {
                    tierMultiplier = 8;
                    in_tierOne.setDisable(false);
                    in_tierTwo.setDisable(false);
                    in_tierThree.setDisable(false);
                    in_tierFour.setDisable(false);
                    lab_tierOneMultiplier.setText("(x8)");
                    lab_tierTwoMultiplier.setText("(x4)");
                    lab_tierThreeMultiplier.setText("(x2)");
                }
            }
        }
        catch (NullPointerException e){/**/}
        catch (Exception e){
            errorMessage.setStyle("-fx-padding: 1;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 4;" +
                    "-fx-border-color: #ff4343");
            errorMessage.setText("ERROR: Something went wrong. :(");
        }
    }



    public void calculate(){
        try {
            Calc c = new Calc();

            in_default();

            switch (tier) {
                case "I" -> tierOneMultiplier = 1;
                case "II" -> {
                    tierOneMultiplier = 2;
                    tierTwoMultiplier = 1;
                }
                case "III" -> {
                    tierOneMultiplier = 4;
                    tierTwoMultiplier = 2;
                    tierThreeMultiplier = 1;
                }
                case "IV" -> {
                    tierOneMultiplier = 8;
                    tierTwoMultiplier = 4;
                    tierThreeMultiplier = 2;
                    tierFourMultiplier = 1;
                }
            }

            if (equipment.equals("Dragon Artifact")) {
                switch (rarity) {
                    case "Rare" -> {
                        req_rareMatOne
                                = c.getMaterial(200, tierMultiplier, owned_rareMatOne);
                        req_rareMatTwo
                                = c.getMaterial(200, tierMultiplier, owned_rareMatTwo);
                        req_rareMatThree
                                = c.getMaterial(200, tierMultiplier, owned_rareMatThree);
                        req_rareMatFour
                                = c.getMaterial(100, tierMultiplier, owned_rareMatFour);
                        req_matFive
                                = c.getMaterialD(10, owned_matFive);

                        req_copper
                                = c.getCopperCost(
                                2_000,(req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10, req_rareMatFour,
                                owned_glittering
                        );
                        req_elixir
                                = c.getElixirCost(10, req_rareMatFour, owned_elixir);
                        req_dragonSteel
                                = c.getDragonSteelCost(1, req_matFive, owned_dragonSteel);
                    }
                    case "Epic" -> {
                        req_rareMatOne
                                = c.getMaterial(2_000, tierMultiplier, owned_rareMatOne, owned_epicMatOne, 10);
                        req_rareMatTwo
                                = c.getMaterial(2_000, tierMultiplier, owned_rareMatTwo, owned_epicMatTwo, 10);
                        req_rareMatThree
                                = c.getMaterial(2_000, tierMultiplier, owned_rareMatThree, owned_epicMatThree, 10);
                        req_rareMatFour
                                = c.getMaterial(1_000, tierMultiplier, owned_rareMatFour, owned_epicMatFour, 10);

                        req_epicMatOne
                                = c.getMaterial(200, tierMultiplier, owned_epicMatOne);
                        req_epicMatTwo
                                = c.getMaterial(200, tierMultiplier, owned_epicMatTwo);
                        req_epicMatThree
                                = c.getMaterial(200, tierMultiplier, owned_epicMatThree);
                        req_epicMatFour
                                = c.getMaterial(100, tierMultiplier, owned_epicMatFour);

                        req_matFive
                                = c.getMaterialD(30, owned_matFive);

                        req_copper
                                = c.getCopperCost(
                                2_000,(req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                20_000,(req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                100_000, req_epicMatFour,
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                5_000, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                50_000, req_epicMatFour,
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10, req_rareMatFour,
                                25, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                50, req_epicMatFour,
                                owned_glittering
                        );
                        req_elixir
                                = c.getElixirCost(
                                10, req_rareMatFour,
                                50, req_epicMatFour,
                                owned_elixir
                        );
                        req_dragonSteel
                                = c.getDragonSteelCost(5, req_matFive, owned_dragonSteel);
                    }
                    case "Legendary" -> {
                        req_rareMatOne
                                = c.getMaterial(20_000, tierMultiplier, owned_rareMatOne, owned_epicMatOne, 10, owned_legendMatOne, 100);
                        req_rareMatTwo
                                = c.getMaterial(20_000, tierMultiplier, owned_rareMatTwo, owned_epicMatTwo, 10, owned_legendMatTwo, 100);
                        req_rareMatThree
                                = c.getMaterial(20_000, tierMultiplier, owned_rareMatThree, owned_epicMatThree, 10, owned_legendMatThree, 100);
                        req_rareMatFour
                                = c.getMaterial(10_000, tierMultiplier, owned_rareMatFour, owned_epicMatFour, 10, owned_legendMatFour, 100);

                        req_epicMatOne
                                = c.getMaterial(2_000, tierMultiplier, owned_epicMatOne, owned_legendMatOne, 10);
                        req_epicMatTwo
                                = c.getMaterial(2_000, tierMultiplier, owned_epicMatTwo, owned_legendMatTwo, 10);
                        req_epicMatThree
                                = c.getMaterial(2_000, tierMultiplier, owned_epicMatThree, owned_legendMatThree, 10);
                        req_epicMatFour
                                = c.getMaterial(1_000, tierMultiplier, owned_epicMatFour, owned_legendMatFour, 10);

                        req_legendMatOne
                                = c.getMaterial(200, tierMultiplier, owned_legendMatOne);
                        req_legendMatTwo
                                = c.getMaterial(200, tierMultiplier, owned_legendMatTwo);
                        req_legendMatThree
                                = c.getMaterial(200, tierMultiplier, owned_legendMatThree);
                        req_legendMatFour
                                = c.getMaterial(100, tierMultiplier, owned_legendMatFour);

                        req_matFive
                                = c.getMaterialD(50, owned_matFive);

                        req_copper
                                = c.getCopperCost(
                                2_000,(req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                20_000,(req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                100_000, req_epicMatFour,
                                100_000,(req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                1_000_000, req_legendMatFour,
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10_000, req_rareMatFour,
                                5_000, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                50_000, req_epicMatFour,
                                25_000, (req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                250_000, req_legendMatFour,
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                10, req_rareMatFour,
                                25, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                50, req_epicMatFour,
                                125, (req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                250, req_legendMatFour,
                                owned_glittering
                        );
                        req_elixir
                                = c.getElixirCost(
                                10, req_rareMatFour,
                                50, req_epicMatFour,
                                250, req_legendMatFour,
                                owned_elixir
                        );
                        req_dragonSteel
                                = c.getDragonSteelCost(25, req_matFive, owned_dragonSteel);
                    }
                }
            }
            else {
                switch (rarity) {
                    case "Rare" -> {
                        req_rareMatOne
                                = c.getMaterial(75, tierMultiplier, owned_rareMatOne);
                        req_rareMatTwo
                                = c.getMaterial(25, tierMultiplier, owned_rareMatTwo);
                        req_rareMatThree
                                = c.getMaterial(25, tierMultiplier, owned_rareMatThree);

                        req_copper
                                = c.getCopperCost(
                                2_000,(req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                owned_glittering
                        );
                    }
                    case "Epic" -> {
                        req_rareMatOne
                                = c.getMaterial(3_000, tierMultiplier, owned_rareMatOne, owned_epicMatOne, 10);
                        req_rareMatTwo
                                = c.getMaterial(1_000, tierMultiplier, owned_rareMatTwo, owned_epicMatTwo, 10);
                        req_rareMatThree
                                = c.getMaterial(1_000, tierMultiplier, owned_rareMatThree, owned_epicMatThree, 10);


                        req_epicMatOne
                                = c.getMaterial(300, tierMultiplier, owned_epicMatOne);
                        req_epicMatTwo
                                = c.getMaterial(100, tierMultiplier, owned_epicMatTwo);
                        req_epicMatThree
                                = c.getMaterial(100, tierMultiplier, owned_epicMatThree);

                        req_copper
                                = c.getCopperCost(
                                2_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                20_000, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                5_000, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                25, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                owned_glittering
                        );
                    }
                    case "Legendary" -> {
                        req_rareMatOne
                                = c.getMaterial(30_000, tierMultiplier, owned_rareMatOne, owned_epicMatOne, 10, owned_legendMatOne, 100);
                        req_rareMatTwo
                                = c.getMaterial(10_000, tierMultiplier, owned_rareMatTwo, owned_epicMatTwo, 10, owned_legendMatTwo, 100);
                        req_rareMatThree
                                = c.getMaterial(10_000, tierMultiplier, owned_rareMatThree, owned_epicMatThree, 10, owned_legendMatThree, 100);

                        req_epicMatOne
                                = c.getMaterial(3_000, tierMultiplier, owned_epicMatOne, owned_legendMatOne, 10);
                        req_epicMatTwo
                                = c.getMaterial(1_000, tierMultiplier, owned_epicMatTwo, owned_legendMatTwo, 10);
                        req_epicMatThree
                                = c.getMaterial(1_000, tierMultiplier, owned_epicMatThree, owned_legendMatThree, 10);

                        req_legendMatOne
                                = c.getMaterial(300, tierMultiplier, owned_legendMatOne);
                        req_legendMatTwo
                                = c.getMaterial(100, tierMultiplier, owned_legendMatTwo);
                        req_legendMatThree
                                = c.getMaterial(100, tierMultiplier, owned_legendMatThree);

                        req_copper
                                = c.getCopperCost(
                                2_000,(req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                20_000,(req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                100_000,(req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                (owned_oldSilver * OLD_SILVER_VALUE), owned_copper
                        );
                        req_darkSteel
                                = c.getDarksteelOrGlitteringCost(
                                1_000, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                5_000, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                25_000, (req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                owned_darkSteel
                        );
                        req_glittering
                                = c.getDarksteelOrGlitteringCost(
                                2, (req_rareMatOne + req_rareMatTwo + req_rareMatThree),
                                25, (req_epicMatOne + req_epicMatTwo + req_epicMatThree),
                                125, (req_legendMatOne + req_legendMatTwo + req_legendMatThree),
                                owned_glittering
                        );
                    }
                }
            }

            long copperCraftCost = 0, darkSteelCraftCost = 0, dragonSteelCraftCost = 0;

            if (equipment.equals("Armor")){
                if (in_tierOne.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 25_000;
                            tierDarkSteelCost = 5_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 400_000;
                            tierDarkSteelCost = 50_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 5_000_000;
                            tierDarkSteelCost = 500_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                    }
                }
                if (in_tierTwo.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 50_000;
                            tierDarkSteelCost = 7_500;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 800_000;
                            tierDarkSteelCost = 100_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 10_000_000;
                            tierDarkSteelCost = 1_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                    }
                }
                if (in_tierThree.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 75_000;
                            tierDarkSteelCost = 10_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 1_200_000;
                            tierDarkSteelCost = 150_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 15_000_000;
                            tierDarkSteelCost = 1_500_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                    }
                }
                if (in_tierFour.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 100_000;
                            tierDarkSteelCost = 12_500;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 1_600_000;
                            tierDarkSteelCost = 200_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 20_000_000;
                            tierDarkSteelCost = 2_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                    }
                }
            }
            if (equipment.equals("Main Weapon / Accessories")){
                if (in_tierOne.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 50_000;
                            tierDarkSteelCost = 10_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 800_000;
                            tierDarkSteelCost = 100_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 10_000_000;
                            tierDarkSteelCost = 1_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                    }
                }
                if (in_tierTwo.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 100_000;
                            tierDarkSteelCost = 15_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 1_600_000;
                            tierDarkSteelCost = 200_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 20_000_000;
                            tierDarkSteelCost = 2_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                    }
                }
                if (in_tierThree.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 150_000;
                            tierDarkSteelCost = 20_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 2_400_000;
                            tierDarkSteelCost = 300_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 30_000_000;
                            tierDarkSteelCost = 3_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                    }
                }
                if (in_tierFour.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 200_000;
                            tierDarkSteelCost = 25_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 3_200_000;
                            tierDarkSteelCost = 400_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 40_000_000;
                            tierDarkSteelCost = 4_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                    }
                }
            }
            if (equipment.equals("Off-hand Weapon / Earrings")){
                if (in_tierOne.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 50_000;
                            tierDarkSteelCost = 50_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 800_000;
                            tierDarkSteelCost = 800_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 10_000_000;
                            tierDarkSteelCost = 10_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierOneMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                    }
                }
                if (in_tierTwo.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 100_000;
                            tierDarkSteelCost = 100_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 1_600_000;
                            tierDarkSteelCost = 1_600_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 20_000_000;
                            tierDarkSteelCost = 20_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierTwoMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierTwoMultiplier);
                        }
                    }
                }
                if (in_tierThree.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 150_000;
                            tierDarkSteelCost = 150_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 2_400_000;
                            tierDarkSteelCost = 2_400_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 30_000_000;
                            tierDarkSteelCost = 30_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierThreeMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierThreeMultiplier);
                        }
                    }
                }
                if (in_tierFour.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierCopperCost = 200_000;
                            tierDarkSteelCost = 200_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Epic" -> {
                            tierCopperCost = 3_200_000;
                            tierDarkSteelCost = 3_200_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                        case "Legendary" -> {
                            tierCopperCost = 40_000_000;
                            tierDarkSteelCost = 40_000_000;
                            copperCraftCost += c.getEquipmentCost(tierCopperCost, tierFourMultiplier);
                            darkSteelCraftCost += c.getEquipmentCost(tierDarkSteelCost, tierFourMultiplier);
                        }
                    }
                }
            }
            if (equipment.equals("Dragon Artifact")){
                if (in_tierOne.isSelected()){
                    switch (rarity) {
                        case "Rare" -> {
                            tierDarkSteelCost = 500_000;
                            tierDragonSteelCost = 50;
                            dragonSteelCraftCost = c.getEquipmentCost(tierDragonSteelCost, tierOneMultiplier);
                            darkSteelCraftCost = c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Epic" -> {
                            tierDarkSteelCost = 5_000_000;
                            tierDragonSteelCost = 1_000;
                            dragonSteelCraftCost = c.getEquipmentCost(tierDragonSteelCost, tierOneMultiplier);
                            darkSteelCraftCost = c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                        case "Legendary" -> {
                            tierDarkSteelCost = 50_000_000;
                            tierDragonSteelCost = 20_000;
                            dragonSteelCraftCost = c.getEquipmentCost(tierDragonSteelCost, tierOneMultiplier);
                            darkSteelCraftCost = c.getEquipmentCost(tierDarkSteelCost, tierOneMultiplier);
                        }
                    }
                }
            }

            out_rareMatOne.setText(String.format("%,d", c.getTotalCost(req_rareMatOne)));
            out_rareMatTwo.setText(String.format("%,d", c.getTotalCost(req_rareMatTwo)));
            out_rareMatThree.setText(String.format("%,d", c.getTotalCost(req_rareMatThree)));
            out_rareMatFour.setText(String.format("%,d", c.getTotalCost(req_rareMatFour)));
            out_epicMatOne.setText(String.format("%,d", c.getTotalCost(req_epicMatOne)));
            out_epicMatTwo.setText(String.format("%,d", c.getTotalCost(req_epicMatTwo)));
            out_epicMatThree.setText(String.format("%,d", c.getTotalCost(req_epicMatThree)));
            out_epicMatFour.setText(String.format("%,d", c.getTotalCost(req_epicMatFour)));
            out_legendMatOne.setText(String.format("%,d", c.getTotalCost(req_legendMatOne)));
            out_legendMatTwo.setText(String.format("%,d", c.getTotalCost(req_legendMatTwo)));
            out_legendMatThree.setText(String.format("%,d", c.getTotalCost(req_legendMatThree)));
            out_legendMatFour.setText(String.format("%,d", c.getTotalCost(req_legendMatFour)));
            out_matFive.setText(String.format("%,d", c.getTotalCost(req_matFive)));
            out_copper.setText(String.format("%,d", c.getTotalCost(req_copper, copperCraftCost)));
            out_darkSteel.setText(String.format("%,d", c.getTotalCost(req_darkSteel, darkSteelCraftCost)));
            out_oldSilver.setText(String.format("%,d", c.getTotalCost(req_copper, copperCraftCost) / OLD_SILVER_VALUE));
            out_glittering.setText(String.format("%,d", c.getTotalCost(req_glittering)));
            out_elixir.setText(String.format("%,d", c.getTotalCost(req_elixir)));
            out_dragonSteel.setText(String.format("%,d", c.getTotalCost(req_dragonSteel, dragonSteelCraftCost)));

            switch (rarity) {
                case "Rare" -> {
                    out_epicMatOne.setText("");
                    out_epicMatTwo.setText("");
                    out_epicMatThree.setText("");
                    out_epicMatFour.setText("");
                    out_legendMatOne.setText("");
                    out_legendMatTwo.setText("");
                    out_legendMatThree.setText("");
                    out_legendMatFour.setText("");
                }
                case "Epic" -> {
                    out_legendMatOne.setText("");
                    out_legendMatTwo.setText("");
                    out_legendMatThree.setText("");
                    out_legendMatFour.setText("");
                }
            }

            if (!equipment.equals("Dragon Artifact")){
                out_rareMatFour.setText("");
                out_epicMatFour.setText("");
                out_legendMatFour.setText("");
                out_matFive.setText("");
                out_elixir.setText("");
                out_dragonSteel.setText("");
            }

            errorMessage.setText("");
            errorMessage.setStyle("");
        }
        catch (NullPointerException e) {
            errorMessage.setText("[ ! ] - Please select an item");
        }
        catch (NumberFormatException e) {
            errorMessage.setText("[ ! ] - Invalid value");
        }
        catch (Exception e) {
            errorMessage.setStyle("-fx-padding: 1;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 4;" +
                    "-fx-border-color: #ff4343");
            errorMessage.setText("[ ERROR ] - Sorry, something went wrong :(");
        }

    }


    private void in_default() {
        if (in_rareMatOne.getText().isEmpty())
            owned_rareMatOne = 0;
        else
            owned_rareMatOne = Integer.parseInt(in_rareMatOne.getText());

        if (in_rareMatTwo.getText().isEmpty())
            owned_rareMatTwo = 0;
        else
            owned_rareMatTwo = Integer.parseInt(in_rareMatTwo.getText());

        if (in_rareMatThree.getText().isEmpty())
            owned_rareMatThree = 0;
        else
            owned_rareMatThree = Integer.parseInt(in_rareMatThree.getText());

        if (in_rareMatFour.getText().isEmpty())
            owned_rareMatFour = 0;
        else
            owned_rareMatFour = Integer.parseInt(in_rareMatFour.getText());


        if (in_epicMatOne.getText().isEmpty())
            owned_epicMatOne = 0;
        else
            owned_epicMatOne = Integer.parseInt(in_epicMatOne.getText());

        if (in_epicMatTwo.getText().isEmpty())
            owned_epicMatTwo = 0;
        else
            owned_epicMatTwo = Integer.parseInt(in_epicMatTwo.getText());

        if (in_epicMatThree.getText().isEmpty())
            owned_epicMatThree = 0;
        else
            owned_epicMatThree = Integer.parseInt(in_epicMatThree.getText());

        if (in_epicMatFour.getText().isEmpty())
            owned_epicMatFour = 0;
        else
            owned_epicMatFour = Integer.parseInt(in_epicMatFour.getText());


        if (in_legendMatOne.getText().isEmpty())
            owned_legendMatOne = 0;
        else
            owned_legendMatOne = Integer.parseInt(in_legendMatOne.getText());

        if (in_legendMatTwo.getText().isEmpty())
            owned_legendMatTwo = 0;
        else
            owned_legendMatTwo = Integer.parseInt(in_legendMatTwo.getText());

        if (in_legendMatThree.getText().isEmpty())
            owned_legendMatThree = 0;
        else
            owned_legendMatThree = Integer.parseInt(in_legendMatThree.getText());

        if (in_legendMatFour.getText().isEmpty())
            owned_legendMatFour = 0;
        else
            owned_legendMatFour = Integer.parseInt(in_legendMatFour.getText());


        if (in_matFive.getText().isEmpty())
            owned_matFive = 0;
        else
            owned_matFive = Integer.parseInt(in_matFive.getText());


        if (in_copper.getText().isEmpty())
            owned_copper = 0;
        else
            owned_copper = Integer.parseInt(in_copper.getText());

        if (in_oldSilver.getText().isEmpty())
            owned_oldSilver = 0;
        else
            owned_oldSilver = Integer.parseInt(in_oldSilver.getText());

        if (in_darkSteel.getText().isEmpty())
            owned_darkSteel = 0;
        else
            owned_darkSteel = Integer.parseInt(in_darkSteel.getText());

        if (in_glittering.getText().isEmpty())
            owned_glittering = 0;
        else
            owned_glittering = Integer.parseInt(in_glittering.getText());

        if (in_elixir.getText().isEmpty())
            owned_elixir = 0;
        else
            owned_elixir = Integer.parseInt(in_elixir.getText());

        if (in_dragonSteel.getText().isEmpty())
            owned_dragonSteel = 0;
        else
            owned_dragonSteel = Integer.parseInt(in_dragonSteel.getText());
    }

    public void reset(){
        in_rareMatOne.setText("");
        in_rareMatTwo.setText("");
        in_rareMatThree.setText("");
        in_rareMatFour.setText("");
        in_epicMatOne.setText("");
        in_epicMatTwo.setText("");
        in_epicMatThree.setText("");
        in_epicMatFour.setText("");
        in_legendMatOne.setText("");
        in_legendMatTwo.setText("");
        in_legendMatThree.setText("");
        in_legendMatFour.setText("");
        in_matFive.setText("");
        in_copper.setText("");
        in_oldSilver.setText("");
        in_darkSteel.setText("");
        in_glittering.setText("");
        in_elixir.setText("");
        in_dragonSteel.setText("");
    }
}
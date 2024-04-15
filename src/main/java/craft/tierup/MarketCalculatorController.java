/*
RULE: If it is working, don't touch it
*/
package craft.tierup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MarketCalculatorController implements Initializable {
    @FXML
    private TextField mc_ucMaterial, mc_rareMaterial, mc_epicMaterial, mc_legendMaterial;

    @FXML
    private TextField mc_ucUnitPrice, mc_rareUnitPrice, mc_epicUnitPrice, mc_legendUnitPrice;

    @FXML
    private TextField mc_ucTotal, mc_rareTotal, mc_epicTotal, mc_legendTotal;

    @FXML
    private Label mc_ucUnitPriceResult, mc_rareUnitPriceResult, mc_epicUnitPriceResult, mc_legendUnitPriceResult;

    @FXML
    private Label mc_ucTotalResult, mc_rareTotalResult, mc_epicTotalResult, mc_legendTotalResult;

    @FXML
    private Label mc_overallTotal;

    @FXML
    private RadioButton modeOne, modeTwo;

    @FXML
    private Label errorMsg;

    double ucMaterial, rareMaterial, epicMaterial, legendMaterial;

    double ucUnitPrice, rareUnitPrice, epicUnitPrice, legendUnitPrice;

    double ucPriceTotal, rarePriceTotal, epicPriceTotal, legendPriceTotal;

    double overAllTotal;

    public void calculate() {
        try {
            inDefault();
            if (modeOne.isSelected()) {
                ucPriceTotal = totalPrice(ucMaterial, ucUnitPrice);
                rarePriceTotal = totalPrice(rareMaterial, rareUnitPrice);
                epicPriceTotal = totalPrice(epicMaterial, epicUnitPrice);
                legendPriceTotal = totalPrice(legendMaterial, legendUnitPrice);

                overAllTotal = overAllTotal(ucPriceTotal, rarePriceTotal, epicPriceTotal, legendPriceTotal);

                mc_ucTotalResult.setText(String.format("%,d", (int) ucPriceTotal));
                mc_rareTotalResult.setText(String.format("%,d", (int) rarePriceTotal));
                mc_epicTotalResult.setText(String.format("%,d", (int) epicPriceTotal));
                mc_legendTotalResult.setText(String.format("%,d", (int) legendPriceTotal));
                mc_overallTotal.setText(String.format("%,d", (int) overAllTotal) + " Gold");


            }
            if (modeTwo.isSelected()) {
                ucUnitPrice = pricePerUnit(ucPriceTotal, ucMaterial);
                rareUnitPrice = pricePerUnit(rarePriceTotal, rareMaterial);
                epicUnitPrice = pricePerUnit(epicPriceTotal, epicMaterial);
                legendUnitPrice = pricePerUnit(legendPriceTotal, legendMaterial);

                overAllTotal = overAllTotal(ucPriceTotal, rarePriceTotal, epicPriceTotal, legendPriceTotal);

                mc_ucUnitPriceResult.setText(String.format("%,.2f", ucUnitPrice));
                mc_rareUnitPriceResult.setText(String.format("%,.2f", rareUnitPrice));
                mc_epicUnitPriceResult.setText(String.format("%,.2f",  epicUnitPrice));
                mc_legendUnitPriceResult.setText(String.format("%,.2f",  legendUnitPrice));
                mc_overallTotal.setText(String.format("%,d", (int) overAllTotal) + " Gold");
            }
            errorMsg.setStyle("");
            errorMsg.setText("");
        } catch (NumberFormatException e) {
            errorMsg.setText("[ ! ] - Invalid value");
        } catch (Exception e) {
            errorMsg.setStyle("-fx-padding: 1;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 4;" +
                    "-fx-border-color: #ff4343");
            errorMsg.setText("[ ERROR ] - Something went wrong. :(");
        }
    }

    public double totalPrice(double material, double unitPrice) {
        return Math.ceil(material * unitPrice);
    }

    public double pricePerUnit(double totalPrice, double material) {
        double total;

        if (!(totalPrice == 0) && !(material == 0)) {
            total = totalPrice / material;

            if (total < 0.01)
                total = 0.01;
        }
        else
            total = 0;

        return total;
    }

    public double overAllTotal(double ucPriceTotal, double rarePriceTotal, double epicPriceTotal, double legendPriceTotal) {
        return Math.ceil(ucPriceTotal + rarePriceTotal + epicPriceTotal + legendPriceTotal);
    }

    public void mode(){
        try {
            if (modeOne.isSelected()) {
                modeSelectionReset();
                modeOne.setStyle("-fx-text-fill: #e5934e");
                modeTwo.setStyle("");
                mc_ucTotal.setVisible(false);
                mc_rareTotal.setVisible(false);
                mc_epicTotal.setVisible(false);
                mc_legendTotal.setVisible(false);
                mc_ucUnitPrice.setVisible(true);
                mc_rareUnitPrice.setVisible(true);
                mc_epicUnitPrice.setVisible(true);
                mc_legendUnitPrice.setVisible(true);
                mc_ucTotalResult.setVisible(true);
                mc_rareTotalResult.setVisible(true);
                mc_epicTotalResult.setVisible(true);
                mc_legendTotalResult.setVisible(true);
                mc_ucUnitPriceResult.setVisible(false);
                mc_rareUnitPriceResult.setVisible(false);
                mc_epicUnitPriceResult.setVisible(false);
                mc_legendUnitPriceResult.setVisible(false);
            } else if (modeTwo.isSelected()) {
                modeSelectionReset();
                modeOne.setStyle("");
                modeTwo.setStyle("-fx-text-fill: #e5934e");
                mc_ucTotal.setVisible(true);
                mc_rareTotal.setVisible(true);
                mc_epicTotal.setVisible(true);
                mc_legendTotal.setVisible(true);
                mc_ucUnitPrice.setVisible(false);
                mc_rareUnitPrice.setVisible(false);
                mc_epicUnitPrice.setVisible(false);
                mc_legendUnitPrice.setVisible(false);
                mc_ucTotalResult.setVisible(false);
                mc_rareTotalResult.setVisible(false);
                mc_epicTotalResult.setVisible(false);
                mc_legendTotalResult.setVisible(false);
                mc_ucUnitPriceResult.setVisible(true);
                mc_rareUnitPriceResult.setVisible(true);
                mc_epicUnitPriceResult.setVisible(true);
                mc_legendUnitPriceResult.setVisible(true);
            }
        } catch (Exception e) {
            errorMsg.setStyle("-fx-padding: 1;" +
                    "-fx-border-style: solid inside;" +
                    "-fx-border-width: 1;" +
                    "-fx-border-radius: 4;" +
                    "-fx-border-color: #ff4343");
            errorMsg.setText("ERROR: Something went wrong. :(");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modeOne.setStyle("-fx-text-fill: #e5934e");
        modeTwo.setStyle("");
        mc_ucTotal.setVisible(false);
        mc_rareTotal.setVisible(false);
        mc_epicTotal.setVisible(false);
        mc_legendTotal.setVisible(false);
        mc_ucUnitPrice.setVisible(true);
        mc_rareUnitPrice.setVisible(true);
        mc_epicUnitPrice.setVisible(true);
        mc_legendUnitPrice.setVisible(true);
        mc_ucTotalResult.setVisible(true);
        mc_rareTotalResult.setVisible(true);
        mc_epicTotalResult.setVisible(true);
        mc_legendTotalResult.setVisible(true);
        mc_ucUnitPriceResult.setVisible(false);
        mc_rareUnitPriceResult.setVisible(false);
        mc_epicUnitPriceResult.setVisible(false);
        mc_legendUnitPriceResult.setVisible(false);
    }
    public void modeSelectionReset(){
        mc_ucUnitPrice.setText("");
        mc_rareUnitPrice.setText("");
        mc_epicUnitPrice.setText("");
        mc_legendUnitPrice.setText("");
        mc_ucTotal.setText("");
        mc_rareTotal.setText("");
        mc_epicTotal.setText("");
        mc_legendTotal.setText("");
        mc_ucUnitPriceResult.setText("0.00");
        mc_rareUnitPriceResult.setText("0.00");
        mc_epicUnitPriceResult.setText("0.00");
        mc_legendUnitPriceResult.setText("0.00");
        mc_ucTotalResult.setText("0");
        mc_rareTotalResult.setText("0");
        mc_epicTotalResult.setText("0");
        mc_legendTotalResult.setText("0");
        mc_overallTotal.setText("0 Gold");
        errorMsg.setStyle("");
        errorMsg.setText("");
    }
    public void reset(){
        mc_ucMaterial.setText("");
        mc_rareMaterial.setText("");
        mc_epicMaterial.setText("");
        mc_legendMaterial.setText("");
        mc_ucUnitPrice.setText("");
        mc_rareUnitPrice.setText("");
        mc_epicUnitPrice.setText("");
        mc_legendUnitPrice.setText("");
        mc_ucTotal.setText("");
        mc_rareTotal.setText("");
        mc_epicTotal.setText("");
        mc_legendTotal.setText("");
        mc_ucUnitPriceResult.setText("0.00");
        mc_rareUnitPriceResult.setText("0.00");
        mc_epicUnitPriceResult.setText("0.00");
        mc_legendUnitPriceResult.setText("0.00");
        mc_ucTotalResult.setText("0");
        mc_rareTotalResult.setText("0");
        mc_epicTotalResult.setText("0");
        mc_legendTotalResult.setText("0");
        mc_overallTotal.setText("0 Gold");
        errorMsg.setStyle("");
        errorMsg.setText("");
    }

    public void inDefault(){
        if (mc_ucMaterial.getText().isEmpty())
            ucMaterial = 0;
        else
            ucMaterial = Double.parseDouble(mc_ucMaterial.getText());

        if (mc_rareMaterial.getText().isEmpty())
            rareMaterial = 0;
        else
            rareMaterial = Double.parseDouble(mc_rareMaterial.getText());

        if (mc_epicMaterial.getText().isEmpty())
            epicMaterial = 0;
        else
            epicMaterial = Double.parseDouble(mc_epicMaterial.getText());

        if (mc_legendMaterial.getText().isEmpty())
            legendMaterial = 0;
        else
            legendMaterial = Double.parseDouble(mc_legendMaterial.getText());

        if (mc_ucUnitPrice.getText().isEmpty())
            ucUnitPrice = 0;
        else
            ucUnitPrice = Double.parseDouble(mc_ucUnitPrice.getText());

        if (mc_rareUnitPrice.getText().isEmpty())
            rareUnitPrice = 0;
        else
            rareUnitPrice = Double.parseDouble(mc_rareUnitPrice.getText());

        if (mc_epicUnitPrice.getText().isEmpty())
            epicUnitPrice = 0;
        else
            epicUnitPrice = Double.parseDouble(mc_epicUnitPrice.getText());

        if (mc_legendUnitPrice.getText().isEmpty())
            legendUnitPrice = 0;
        else
            legendUnitPrice = Double.parseDouble(mc_legendUnitPrice.getText());

        if (mc_ucTotal.getText().isEmpty())
            ucPriceTotal = 0;
        else
            ucPriceTotal = Double.parseDouble(mc_ucTotal.getText());

        if (mc_rareTotal.getText().isEmpty())
            rarePriceTotal = 0;
        else
            rarePriceTotal = Double.parseDouble(mc_rareTotal.getText());

        if (mc_epicTotal.getText().isEmpty())
            epicPriceTotal = 0;
        else
            epicPriceTotal = Double.parseDouble(mc_epicTotal.getText());

        if (mc_legendTotal.getText().isEmpty())
            legendPriceTotal = 0;
        else
            legendPriceTotal = Double.parseDouble(mc_legendTotal.getText());
    }
}

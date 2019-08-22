package TipCalcPackage;

import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class TipCalculatorController {

    //Formatter for currency percentage.
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();


    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% Default.

    //GUI controls defined in FXML.
    @FXML
    private TextField tipTextField;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentLabel;

    @FXML
    private TextField totalTextField;

    @FXML
    void calculateButtonPressed(ActionEvent event) {
        try{
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (NumberFormatException ex){
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }

    //Called by FXML Loader to initialize the controller
    public void initialize(){
        currency.setRoundingMode(RoundingMode.HALF_UP);

        //Listener for changes to tipPercentageSlider's value
        //Changes the percentage label when user moves scroller
        tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0);
                tipPercentLabel.setText(percent.format(tipPercentage)); //Sets percent label with scroll value.
            }
        });
    }
}

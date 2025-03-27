import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
public class HelloController {

    @FXML private Label lblDistance;
    @FXML private TextField tfDistance;
    @FXML private Label lblFuel;
    @FXML private TextField tfFuel;
    @FXML private Button btnCalculate;
    @FXML private Label lblResult;

    private ResourceBundle rb;

    private void setLanguage(Locale locale){
        lblResult.setText("");
        try {
            rb = ResourceBundle.getBundle("messages", locale);
            lblDistance.setText(rb.getString("distance"));
            lblFuel.setText(rb.getString("fuel"));
            btnCalculate.setText(rb.getString("calculate"));

        } catch (MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading language resources");
        }
        calculate();
    }

    public void initialize() {
        setLanguage(Locale.ENGLISH);
    }

    public void calculate() {
        if (tfDistance.getText().isEmpty() || tfFuel.getText().isEmpty()) {
            lblResult.setText(rb.getString("empty"));
            return;
        }

        try {
            double distance = Double.parseDouble(tfDistance.getText());
            double fuel = Double.parseDouble(tfFuel.getText());
            double result = (fuel / distance) * 100;
            lblResult.setText(MessageFormat.format(rb.getString("result"), result));
        } catch (NumberFormatException e) {
            lblResult.setText(rb.getString("invalid"));
        }
    }

    public void onCalculateClick(ActionEvent event) {
        calculate();
    }

    public void onENClick(ActionEvent event) {
        setLanguage(new Locale("en", "EN"));
    }

    public void onFRClick(ActionEvent event) {
        setLanguage(new Locale("fr", "FR"));
    }

    public void onJPClick(ActionEvent event) {
        setLanguage(new Locale("jp", "JP"));
    }

    public void onIRClick(ActionEvent event) {
        setLanguage(new Locale("ir", "PA"));
    }
}

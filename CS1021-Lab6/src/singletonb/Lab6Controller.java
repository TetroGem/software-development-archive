/*
 * Course: CS1021 - 021
 * Winter 2021
 * Lab 6 - Exceptions
 * Name: Benjamin Singleton
 * Created: 01/19/2022
 * Modified: 01/24/2022
 */
package singletonb;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import edu.msoe.se1021.Lab6.WebsiteTester;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Controller for the lab6.fxml file
 * @author singletonb
 */
public class Lab6Controller {
    private final WebsiteTester websiteTester = new WebsiteTester();

    @FXML
    private Button analyzeButton;

    @FXML
    private Button setTimeoutButton;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField urlField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField downloadTimeField;

    @FXML
    private TextField hostnameField;

    @FXML
    private TextField portField;

    @FXML
    private TextField timeoutField;

    @FXML
    private Label status;

    @FXML
    private void initialize() {
        timeoutField.setText(websiteTester.getTimeout());
    }

    @FXML
    private void analyze() {
        try {
            websiteTester.openURL(urlField.getText());
            websiteTester.openConnection();
            websiteTester.downloadText();
            sizeField.setText("" + websiteTester.getSize());
            downloadTimeField.setText("" + websiteTester.getDownloadTime());
            hostnameField.setText("" + websiteTester.getHostname());
            portField.setText("" + websiteTester.getPort());
            textArea.setText(websiteTester.getContent());
        } catch(MalformedURLException | UnknownHostException e) {
            alertURLError();
            urlField.setText("");
        } catch(SocketTimeoutException e) {
            alertTimeout();
        } catch(IOException e) {
            alertFileError();
        }
    }

    @FXML
    private void setTimeout() {
        setTimeout(timeoutField.getText(), false);
    }

    private void setTimeout(String timeout, boolean inExtendedTimeout) {
        try {
            websiteTester.setTimeout(timeout);
            timeoutField.setText(timeout);
        } catch(NumberFormatException e) {
            if(e.getLocalizedMessage().charAt(0) == 'T') {
                alertInvalidTimeout("negative");
            } else{
                alertInvalidTimeout("NaN");
            }

            if(inExtendedTimeout) {
                promptExtendedTimeout();
            }
        }
    }

    private void alertTimeout() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Timeout Dialog");
        alert.setHeaderText("Wait longer?");
        alert.setContentText("There has been a timeout reaching this site. " +
                "Click OK to extend the timeout period?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                String previousTimeout = websiteTester.getTimeout();
                promptExtendedTimeout();
                if(!websiteTester.getTimeout().equals(previousTimeout)) {
                    analyze();
                }
                setTimeout(previousTimeout, false);
            }
        }
    }

    private void promptExtendedTimeout() {
        TextInputDialog textInputDialog = new TextInputDialog("10");
        textInputDialog.setTitle("Set timeout");
        textInputDialog.setHeaderText("Set extended timeout");
        textInputDialog.setContentText("Desired timeout:");

        Optional<String> input = textInputDialog.showAndWait();
        input.ifPresent(s -> setTimeout(s, true));
    }

    private void alertInvalidTimeout(String mode) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Invalid Timeout Error");
        alert.setContentText(mode.equals("NaN") ?
                "Please enter an integer." : "Timeout must be greater than or equal to 0.");
        alert.showAndWait();
    }

    private void alertURLError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("URL Error");
        alert.setContentText("The URL entered in the text box is invalid");
        alert.showAndWait();
    }

    private void alertFileError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("File Error");
        alert.setContentText("Error: File not found on the server, " + urlField.getText());
        alert.showAndWait();
    }

}
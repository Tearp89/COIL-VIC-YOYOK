package logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class CharLimitValidator {


    public static void setCharLimitTextField(TextField textField, int charLimit) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && newValue.length() >= charLimit) {
                    textField.setText(oldValue);
                } 
            }
        });
    }

    public static <T> void setCharLimitComboBox(ComboBox<T> comboBox, int itemsLimit) {
        comboBox.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue && comboBox.getItems().size() >= itemsLimit) {
                    comboBox.hide();
                }
            }
        });
    }

    public static void setCharLimitEditableComboBox(TextField comboBoxEditor, int charLimit){
        TextFormatter<String> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.length() > charLimit) {
                change.setText(newText.substring(0, charLimit));
                int oldLength = change.getControlText().length();
                change.setRange(0, oldLength);
            }
            return change; 
        });

        comboBoxEditor.setTextFormatter(formatter);
    }

    public static void setCharLimitTextArea(TextArea textArea, int charLimit) {
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null && newValue.length() >= charLimit) {
                    textArea.setText(oldValue);
                } 
            }
        });
    }

}

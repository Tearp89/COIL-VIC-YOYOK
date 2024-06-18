package logic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

}

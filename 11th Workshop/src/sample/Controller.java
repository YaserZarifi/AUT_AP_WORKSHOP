package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;



public class Controller {

    private double number1;
    private String operator = "";
    private boolean start = true;

    @FXML
    private Label output;

    public Controller() {
    }



    @FXML
    private void numberBtn(ActionEvent event){
        if(start){
            output.setText("");
            start=false;
        }
        String value = ((Button) event.getSource()).getText();
        output.setText(output.getText() + value);
    }

    @FXML
    private void actionBtn(ActionEvent event){
        if (output.getText().equals("Error !")){
            return;
        }
        String value = ((Button)event.getSource()).getText();
        if (!value.equals("=")){
            if (!operator.isEmpty()){
                return;
            }
            operator = value;
            number1 = Double.parseDouble(output.getText());
            output.setText("");
        }else{
            if (operator.isEmpty()){
                return;
            }
            if (output.getText().isEmpty()){
                output.setText("Error !");
                operator = "";
                start = true;
                return;
            }
            output.setText(calculate(number1, Double.parseDouble(output.getText()), operator));
            operator = "";
            start = true;
        }

    }
    @FXML
    private void clearAll(ActionEvent event){
        output.setText("0");
        start=true;
        operator = "";
    }

    private String calculate(double number1, double number2, String operator){
        switch (operator){
            case "+":
                return String.valueOf(number1+number2);
            case "-":
                return String.valueOf(number1-number2);
            case "×":
                return String.valueOf(number1*number2);
            case "÷":
                if(number2 ==0){
                    return "Error !";
                }
                return String.valueOf( number1/number2);
            case "%":
                return String.valueOf(((number1*100)/number2))+"%";

        }
        return "Error !";
    }

}

package com.example.lab7;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Random;

public class Controller {

    int[] range = new int[2];

    int guess;
    int counter;
    @FXML
    private Button yesBtn, btnStart, applyBtn, lessBtn, biggerBtn;
    @FXML
    private Label labelWarning, lblGuess;
    @FXML
    private VBox numGueeserVBox;
    @FXML
    private HBox btnHBox;
    @FXML
    private TextField numFrom, numTo;

    public Controller() {
    }

    @FXML
    protected void OnClickApply(){
        try{

            if( Integer.parseInt(numFrom.getText()) <= Integer.parseInt(numTo.getText())){
                range[0] = Integer.parseInt(numFrom.getText());
                range[1] = Integer.parseInt(numTo.getText());
                counter = 0;

                labelWarning.setText("Your number between: " + range[0] + " and " + range[1]);
                applyBtn.setText("Apply");
                btnStart.setVisible(true);
            }
            else applyBtn.setText("Make 1st number less than 2nd!");
        }catch (Exception e){
            applyBtn.setText("Enter a number!!!");
        }
    }

    @FXML
    protected void OnClickYes(){

        Alert winMessage = new Alert(Alert.AlertType.INFORMATION);
        winMessage.setTitle("Win!");
        winMessage.setHeaderText("Number was: " + guess + "! \n" +
                                 "We did it in " + counter + " answers!");
        winMessage.showAndWait();


        yesBtn.setDisable(true);
        biggerBtn.setDisable(true);
        lessBtn.setDisable(true);
        labelWarning.setText("Set Range First!");

    }
    @FXML
    protected void OnClickLess(){


        if(range[0] < range[1]){
            range[1] = guess-1;
            Guesser(range);
            labelWarning.setText("Your number between: " + range[0] + " and " + range[1]);

        }else {
            cheaterMessage();
        }

    }
    @FXML
    protected void OnClickBigger(){
        if(range[0] < range[1]){
            range[0] = guess+1;
            Guesser(range);
            labelWarning.setText("Your number between: " + range[0] + " and " + range[1]);
        }else {
            cheaterMessage();
        }

    }
    @FXML
    protected void OnClickStart(){
        btnStart.setVisible(false);

        yesBtn.setDisable(false);
        biggerBtn.setDisable(false);
        lessBtn.setDisable(false);
        Guesser(range);
    }

    @FXML
    void Guesser(int[] range){
        Random random = new Random();
        try {
            if(range[0] != range[1]){
                guess = random.nextInt(range[1] - range[0])+range[0];
                lblGuess.setText(guess + "?");
                counter++;
            } else {
                guess = range[0];
                lblGuess.setText(guess + "?");
            }
        }catch (Exception e){
            cheaterMessage();
        }

    }
    @FXML
    void cheaterMessage(){
        Alert cheaterMessage = new Alert(Alert.AlertType.ERROR);
        cheaterMessage.setTitle("You are a cheater!!!");
        cheaterMessage.setHeaderText("Don't lie with answers!");
        cheaterMessage.showAndWait();

        yesBtn.setDisable(true);
        biggerBtn.setDisable(true);
        lessBtn.setDisable(true);
        labelWarning.setText("Set Range First!");

    }

}
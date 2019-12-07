package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rate {

    public Button backBtn;
    public Button clrBtn;
    public Button saveBtn;

    public TextField rcsrTF;
    public TextField ccsrTF;
    public TextField rcurTF;
    public TextField ccurTF;
    public TextField rnsrTF;
    public TextField cnsrTF;
    public TextField rnurTF;
    public TextField cnurTF;

    public void handleBackBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage curStage = (Stage) backBtn.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle("Electric Usage Billing System");
            newStage.setScene(new Scene(root));
            newStage.show();
            curStage.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void handleClrBtn(){
        rnsrTF.clear();
        cnsrTF.clear();
        rnurTF.clear();
        cnurTF.clear();
    }
    public void handleSaveBtn(){
        if(rnsrTF.getText().equals(""))
            rnsrTF.setText(rcsrTF.getText());
        if(rnurTF.getText().equals(""))
            rnurTF.setText(rcurTF.getText());
        if(cnsrTF.getText().equals(""))
            cnsrTF.setText(ccsrTF.getText());
        if(cnurTF.getText().equals(""))
            cnurTF.setText(ccurTF.getText());
        try {
            File out = new File("rates.dat");
            PrintWriter output = new PrintWriter(out);
            output.println(rnsrTF.getText());
            output.println(rnurTF.getText());
            output.println(cnsrTF.getText());
            output.println(cnurTF.getText());
            output.close();

            //Update values
            initialize();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void initialize(){
        try {
            File file = new File("rates.dat");
            Scanner input = new Scanner(file);
            rcsrTF.setText("" + input.nextDouble());
            rcurTF.setText("" + input.nextDouble());
            ccsrTF.setText("" + input.nextDouble());
            ccurTF.setText("" + input.nextDouble());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

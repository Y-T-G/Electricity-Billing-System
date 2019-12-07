package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class NewEntry {

    public Button backBtn;
    public Button clrBtn;
    public Button entryBtn;
    public Button saveBtn;

    public TextField accTF;
    public TextField refTF;
    public TextField fnTF;
    public TextField lnTF;
    public TextField cnTF;
    public TextField addrTF;
    public TextField ecTF;
    public TextField rateTF;
    public TextField calcBillTF;

    public RadioButton subsYRB;
    public RadioButton subsNRB;
    public RadioButton resRB;
    public RadioButton comRB;
    public DatePicker dueDF;
    public ToggleGroup subsTG;
    public Pane resPane;
    public Pane comPane;

    //Global variable to store passed ArraylSist
    ArrayList<Entries> passedEntry;
    boolean isResEntry;

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
        accTF.clear();
        refTF.clear();
        fnTF.clear();
        lnTF.clear();
        cnTF.clear();
        addrTF.clear();
        ecTF.clear();
        rateTF.clear();
        calcBillTF.clear();
        subsYRB.setSelected(false);
        subsNRB.setSelected(false);
        dueDF.setValue(null);
    }

    public void handleEntryBtn() {
        //Get data from file and store in ArrayList
        ArrayList<Entries> entries = Entries.getEntries();


        try {
            //Check if required fields are filled
            if(accTF.getText().isEmpty() || refTF.getText().isEmpty() || addrTF.getText().isEmpty() || ecTF.getText().isEmpty()
                    || dueDF.getValue() == null || (fnTF.getText().isEmpty() && resRB.isSelected()) || (cnTF.getText().isEmpty() && !resRB.isSelected()))
                throw new Exception("One or more required fields are empty!");

            File out;
            //Check whether Residential or Commercial
            if(resRB.isSelected())
                out = new File("ResEntries.dat");
            else
                out = new File("ComEntries.dat");

            //Check whether file exists
            PrintWriter output;
            if (!out.exists())
                output = new PrintWriter(out);
            else
                output = new PrintWriter(new FileWriter(out, true));

            //Check for existing Ref No
            for(int i = 0; i < entries.size(); i++)
                if(Integer.parseInt(refTF.getText()) == entries.get(i).getRef())
                    throw new Exception("Entered Ref No. already exists!");

            output.println(accTF.getText());
            output.println(resRB.isSelected());
            if(resRB.isSelected()) {
                output.println(fnTF.getText());
                output.println(lnTF.getText());
            }
            else
                output.println(cnTF.getText());
            output.println(addrTF.getText());
            output.println(refTF.getText());
            output.println(ecTF.getText());
            output.println(subsYRB.isSelected());
            output.println(dueDF.getValue());
            output.println(rateTF.getText());
            output.println(calcBillTF.getText());

            output.close();

            new Alert(Alert.AlertType.INFORMATION, "Entry added successfully").show();

        }
        catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    public void handleKeyPress(){
        if (!ecTF.getText().equals("") && !rateTF.getText().equals("")) {
            double ec = Double.parseDouble(ecTF.getText());
            double rate = Double.parseDouble(rateTF.getText());
            calcBillTF.setText(""+ Math.round(ec * rate * 100.0)/100.0);
        }
    }

    public void handleSubsRB(){
        try {
            File file = new File("rates.dat");
            Scanner input = new Scanner(file);
            if (resRB.isSelected())
                if(subsYRB.isSelected())
                    rateTF.setText("" + input.nextDouble());
                else {
                    input.nextDouble(); //Skip first input
                    rateTF.setText("" + input.nextDouble());
                }
            else
                if(subsYRB.isSelected()){
                //Skip first two inputs
                    input.nextDouble();
                    input.nextDouble();
                    rateTF.setText("" + input.nextDouble());
            }
                else {
                    //Skip first three inputs
                    input.nextDouble();
                    input.nextDouble();
                    input.nextDouble();
                    rateTF.setText("" + input.nextDouble());
                }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        handleKeyPress();
    }

    public void handleAccType(){
        if(resRB.isSelected()){
            comPane.setVisible(false);
            resPane.setVisible(true);
        }
        else {
            resPane.setVisible(false);
            comPane.setVisible(true);
        }
        //Update rate
        handleSubsRB();
    }

    public void handleSaveBtn(){
        //Remove modified entry in file
        if(isResEntry)
            Residential.writeBack(passedEntry);
        else
            Commercial.writeBack(passedEntry);

        //Call entry method to check and save modified entry
        handleEntryBtn();
    }

    //Polymorphism
    /*
    * To be called from another class
     */
    public void modify(Entries e){
            this.accTF.setText("" + e.getRef());
            if (e instanceof Residential) {
                this.resRB.setSelected(true);
                this.fnTF.setText(((Residential) e).getFn());
                this.lnTF.setText(((Residential) e).getLn());
            } else {
                this.comRB.setSelected(true);
                this.cnTF.setText(((Commercial) e).getCn());
            }
            this.addrTF.setText("" + e.getAddr());
            this.refTF.setText("" + e.getRef());
            this.refTF.setText("" + e.getRef());
            this.refTF.setText("" + e.getRef());
            this.ecTF.setText("" + e.getEc());
            if (e.isSubs())
                subsYRB.setSelected(true);
            else
                subsNRB.setSelected(true);
            this.dueDF.setValue(LocalDate.parse(e.getDueDate()));
            this.rateTF.setText("" + e.getRate());
            this.calcBillTF.setText("" + e.getTotal());
            handleAccType();
            handleSubsRB();
            entryBtn.setVisible(false);
            saveBtn.setVisible(true);
            handleSubsRB();
    }
}

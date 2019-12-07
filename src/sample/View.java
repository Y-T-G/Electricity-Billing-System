package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

public class View{

    public Button backBtn;
    public Button deleteBtn;
    public Button modifyBtn;
    public Button searchBtn;

    public TextField searchTF;

    public TableView viewTbl;

    public TableColumn refCol;
    public TableColumn accCol;
    public TableColumn accNCol;
    public TableColumn addrCol;
    public TableColumn ecCol;
    public TableColumn subsCol;
    public TableColumn rateCol;
    public TableColumn totalCol;

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
    public void handleDeleteBtn(){
        //Store selected row object
        Entries selected = (Entries) viewTbl.getSelectionModel().getSelectedItem();
        //Get all entries
        ArrayList <Entries> rEntries =  Residential.getEntries();
        ArrayList <Entries> cEntries =  Commercial.getEntries();

        //Search in ResEntries
        for(int i = 0; i< rEntries.size(); i++)
            if(selected.getRef() == rEntries.get(i).getRef()) {
                rEntries.remove(i);
                //Overwrite file with new entries
                Residential.writeBack(rEntries);
            }
        for(int i = 0; i < cEntries.size(); i++)
            if(selected.getRef() == cEntries.get(i).getRef()) {
                cEntries.remove(i);
                //Overwrite file with new entries
                Commercial.writeBack(cEntries);
            }
        new Alert(Alert.AlertType.INFORMATION, "Entry deleted successfully").show();
        this.populate();
    }

    public void handleModifyBtn(){
        Entries selected = (Entries) viewTbl.getSelectionModel().getSelectedItem();
        //Get all entries
        ArrayList <Entries> rEntries =  Residential.getEntries();
        ArrayList <Entries> cEntries =  Commercial.getEntries();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("newEntry.fxml"));
                Parent root = loader.load();
                //Create controller object
                NewEntry controller = loader.getController();
                //Search in ResEntries
                for(int i = 0; i < rEntries.size(); i++)
                    if(selected.getRef() == rEntries.get(i).getRef()) {
                        controller.modify(rEntries.get(i));
                        //Remove entry from arraylist
                        rEntries.remove(i);
                        //Pass modified arraylist, not overwritten to the file yet
                        controller.passedEntry = rEntries;
                        //Tell its a residential entry
                        controller.isResEntry = true;
                    }
                for(int i = 0; i < cEntries.size(); i++)
                    if(selected.getRef() == cEntries.get(i).getRef()) {
                        controller.modify(cEntries.get(i));
                        //Remove entry from Arraylist
                        cEntries.remove(i);
                        //Pass modified arraylist, not overwritten to the file yet
                        controller.passedEntry = cEntries;
                        //Tell its a commercial entry
                        controller.isResEntry = false;
                    }
                Stage curStage = (Stage) modifyBtn.getScene().getWindow();
                Stage newStage = new Stage();
                newStage.setTitle("Make a new entry");
                newStage.setScene(new Scene(root));
                newStage.show();
                curStage.close();
            } catch (Exception e) {
                e.printStackTrace();
            }



    }

    public void initialize(){
        //Set CellValueFactory for table columns
        refCol.setCellValueFactory(new PropertyValueFactory<>("ref"));
        accCol.setCellValueFactory(new PropertyValueFactory<>("acc"));
        accNCol.setCellValueFactory(new PropertyValueFactory<>("accName"));
        addrCol.setCellValueFactory(new PropertyValueFactory<>("addr"));
        ecCol.setCellValueFactory(new PropertyValueFactory<>("ec"));
        subsCol.setCellValueFactory(new PropertyValueFactory<>("subs"));
        rateCol.setCellValueFactory(new PropertyValueFactory<>("rate"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        this.populate();
    }

    public void populate(){
        ArrayList <Entries> entries =  Entries.getEntries();
        viewTbl.getItems().clear();
        for(int i = 0; i < entries.size(); i++) {
            //Check search field
            if(!searchTF.getText().isEmpty()){
                if(entries.get(i).getRef() == Integer.parseInt(searchTF.getText()))
                    viewTbl.getItems().addAll(entries.get(i));
            }
            else
                  viewTbl.getItems().addAll(entries.get(i));
        }
    }

    public void handleSearchBtn(){
        populate();
    }
}

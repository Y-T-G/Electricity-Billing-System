package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainScreen  {

    public Button newBtn;
    public Button vexistBtn;
    public Button vrateBtn;
    public Button modBtn;

    public void handleNewBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("newEntry.fxml"));
            Stage curStage = (Stage) newBtn.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle("Make a new entry");
            newStage.setScene(new Scene(root));
            newStage.show();
            curStage.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void handleVExistBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
            Stage curStage = (Stage) newBtn.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle("View existing entries");
            newStage.setScene(new Scene(root));
            newStage.show();
            curStage.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void handleVRateBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("rate.fxml"));
            Stage curStage = (Stage) newBtn.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle("View/Make changes to billing rates");
            newStage.setScene(new Scene(root));
            newStage.show();
            curStage.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void handleModBtn(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
            Stage curStage = (Stage) newBtn.getScene().getWindow();
            Stage newStage = new Stage();
            newStage.setTitle("View existing entries");
            newStage.setScene(new Scene(root));
            newStage.show();
            curStage.close();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}

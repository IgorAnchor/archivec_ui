package ua.chillcrew.archivec.ui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.java2d.SurfaceDataProxy;
import ua.chillcrew.archivec.core.ArchivatedFile;
import ua.chillcrew.archivec.core.ArchivecCore;

import java.io.File;
import java.util.ArrayList;

public class MainController {

    private static Stage stage;

    private ArchivecCore archivecCore;

    private FileChooser fileChooser;


    @FXML
    TableView<ArchivatedFile> tableArchiveContent;
    @FXML
    TableColumn<ArchivatedFile, Integer> tableColumnId;
    @FXML
    TableColumn<ArchivatedFile, String> tableColumnName;
    @FXML
    TableColumn<ArchivatedFile, Long> tableColumnSize;

    @FXML
    protected void initialize() {
        archivecCore = new ArchivecCore();

        fileChooser = new FileChooser();

        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    public static void setStage(Stage stage) {
        MainController.stage = stage;
    }

    public void extract(ActionEvent event) {
        System.out.println("extract");
    }

    public void crush(ActionEvent event) {
        System.out.println("crush");
    }

    public void openArchive(ActionEvent event) {
        System.out.println("openArchive");

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            tableArchiveContent.getItems().clear();
            tableArchiveContent.setItems(FXCollections.observableArrayList(archivecCore.extractFilesInfo(file.getPath())));
            tableArchiveContent.refresh();
        }
    }

    public void newArchive(ActionEvent event) {
        System.out.println("newArchive");
    }

    public void saveArchive(ActionEvent event) {
        System.out.println("saveArchive");
    }

    public void saveArchiveAs(ActionEvent event) {
        System.out.println("saveArchiveAs");
    }

    public void addFiles(ActionEvent event) {
        System.out.println("addFiles");
    }

    public void removeFiles(ActionEvent event) {
        System.out.println("removeFiles");
    }

    public void close(ActionEvent event) {
        System.out.println("close");
    }

    public void settings(ActionEvent event) {
        System.out.println("settings");
    }

}

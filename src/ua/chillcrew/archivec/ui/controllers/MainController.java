package ua.chillcrew.archivec.ui.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.java2d.SurfaceDataProxy;
import ua.chillcrew.archivec.core.ArchivatedFile;
import ua.chillcrew.archivec.core.ArchivecCore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    private static Stage stage;

    private ArchivecCore archivecCore;

    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;

    private String currentArchive;


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
        directoryChooser = new DirectoryChooser();


        fileChooser.setInitialDirectory(new File("C:\\Users\\IgorTheMLGPro\\CLionProjects\\3-1\\archivec-core\\cmake-build-debug\\1"));

        tableArchiveContent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

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

    public void extractFiles(ActionEvent event) {
        System.out.println("extractFiles");

        if (tableArchiveContent.getSelectionModel().getSelectedItems().size() > 0) {

            File destPath = directoryChooser.showDialog(stage);

            List<Integer> ids = tableArchiveContent.getSelectionModel().getSelectedItems()
                    .stream().map(ArchivatedFile::getId).collect(Collectors.toList());

            archivecCore.extractFiles(currentArchive, destPath.getPath().replace('\\', '/'), (ArrayList<Integer>) ids);
        }
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

            currentArchive = file.getPath();
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

package ua.chillcrew.archivec.ui.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;
import ua.chillcrew.archivec.core.ArchiveItem;
import ua.chillcrew.archivec.core.Archivec;
import ua.chillcrew.archivec.core.ArchivecMode;
import ua.chillcrew.archivec.util.ArchivecMethods;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class MainController {
    private ArchivecMode currentMode;
    private Archivec archivec;

    //toolbar
    @FXML
    private Button buttonExtract;
    @FXML
    private Button buttonExtractAll;
    @FXML
    private Button buttonArchivate;

    //edit tab
    @FXML
    private MenuItem menuBarAddFiles;
    @FXML
    private MenuItem menuBarAddDirectories;
    @FXML
    private MenuItem menuBarRemoveFiles;
    @FXML
    private MenuItem menuBarSave;

    //label
    @FXML
    private Label labelArchiveInfo;

    //table
    @FXML
    TreeTableView<ArchiveItem> tableArchiveContent;
    @FXML
    TreeTableColumn<ArchiveItem, Number> tableColumnId;
    @FXML
    TreeTableColumn<ArchiveItem, String> tableColumnName;
    @FXML
    TreeTableColumn<ArchiveItem, Number> tableColumnSize;


    @FXML
    public void initialize() {
        archivec = new Archivec();

        tableArchiveContent.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableColumnName.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        tableColumnId.setCellValueFactory(new TreeItemPropertyValueFactory<>("id"));
        tableColumnSize.setCellValueFactory(new TreeItemPropertyValueFactory<>("size"));

//        tableColumnId.setVisible(true);
        tableArchiveContent.setRoot(Archivec.getRoot());
        tableArchiveContent.setShowRoot(false);

        tableArchiveContent.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.DELETE) {
                removeFiles(null);
            }
        });

    }

    private void switchMode(ArchivecMode mode) {
        currentMode = mode;

        menuBarAddFiles.setDisable(false);
        menuBarRemoveFiles.setDisable(false);
        menuBarAddDirectories.setDisable(false);

        if (mode == ArchivecMode.EXISTING_ARCHIVE) {
            buttonArchivate.setDisable(true);
            menuBarSave.setDisable(true);

            buttonExtract.setDisable(false);
            buttonExtractAll.setDisable(false);
        } else if (mode == ArchivecMode.NEW_ARCHIVE) {
            buttonArchivate.setDisable(false);
            menuBarSave.setDisable(false);

            buttonExtract.setDisable(true);
            buttonExtractAll.setDisable(true);
        }
    }

    public void extract(ActionEvent event) {
        System.out.println("extract");

        if (currentMode == ArchivecMode.EXISTING_ARCHIVE)
            archivec.extract();
    }

    public void extractFiles(ActionEvent event) {
        System.out.println("extractFiles");

        if (currentMode == ArchivecMode.EXISTING_ARCHIVE)
            archivec.extractFiles(tableArchiveContent);
    }

    public void crush(ActionEvent event) {
        System.out.println("crush");

        if (currentMode == ArchivecMode.NEW_ARCHIVE) {
            archivec.crush();
        }
    }

    public void openArchive(ActionEvent event) {
        System.out.println("openArchive");
        if (!archivec.openArchive()) return;

        updateLabel();
        switchMode(ArchivecMode.EXISTING_ARCHIVE);
    }

    public void newArchive(ActionEvent event) {
        System.out.println("newArchive");
        archivec.newArchive();
        updateLabel();

        switchMode(ArchivecMode.NEW_ARCHIVE);
    }

    public void addFiles(ActionEvent event) {
        System.out.println("addFiles");
        archivec.addFiles(currentMode, null, ArchivecMode.ADD_FILES);

        updateLabel();
    }

    public void addDirectories(ActionEvent event) {
        System.out.println("addFiles");
        archivec.addFiles(currentMode, null, ArchivecMode.ADD_DIRECTORIES);

        updateLabel();
    }

    public void removeFiles(ActionEvent event) {
        System.out.println("removeFiles");
        archivec.removeFiles(tableArchiveContent, currentMode);

        updateLabel();
        tableArchiveContent.getSelectionModel().clearSelection();
    }

    public void close(ActionEvent event) {
        System.out.println("close");

        if (currentMode == ArchivecMode.NEW_ARCHIVE) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Do you really want to exit without saving file?");

            ButtonType buttonYes = new ButtonType("Yes");
            ButtonType buttonSaveAs = new ButtonType("Save as...");
            ButtonType buttonCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonYes, buttonSaveAs, buttonCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonYes) {
                System.exit(0);
            } else if (result.get() == buttonSaveAs) {
                crush(event);
            }
            return;
        }
        System.exit(0);
    }

    public void filesDropped(DragEvent event) {
        archivec.addFilesFromDrag(tableArchiveContent, event, currentMode);
    }

    public void filesDragOver(DragEvent event) {
        if (event.getGestureSource() != tableArchiveContent && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }

    public void settings(ActionEvent event) {
        System.out.println("settings");
    }

    private void updateLabel() {
        labelArchiveInfo.setText((Archivec.currentArchive.equals("") ? "Unnamed" : Archivec.currentArchive)
                + " | " + Archivec.fileCount + " files | " +
                "total size: ~" + ArchivecMethods.getTotalSize(Archivec.archiveSize));
    }
}

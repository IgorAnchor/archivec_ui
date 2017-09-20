package ua.chillcrew.archivec.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import ua.chillcrew.archivec.core.ArchiveItem;
import ua.chillcrew.archivec.core.Archivec;
import ua.chillcrew.archivec.core.ArchivecMode;
import ua.chillcrew.archivec.core.PathTreeItem;
import ua.chillcrew.archivec.util.ArchivecMethods;

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
    private MenuItem menuBarRemoveFiles;

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

        tableArchiveContent.setRoot(Archivec.getRoot());
        tableArchiveContent.setShowRoot(false);
    }

    private void switchMode(ArchivecMode mode) {
        currentMode = mode;

        menuBarAddFiles.setDisable(false);
        menuBarRemoveFiles.setDisable(false);

        if (mode == ArchivecMode.EXISTING_ARCHIVE) {
            buttonArchivate.setDisable(true);

            buttonExtract.setDisable(false);
            buttonExtractAll.setDisable(false);
        } else if (mode == ArchivecMode.NEW_ARCHIVE) {
            buttonArchivate.setDisable(false);

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

        labelArchiveInfo.setText(archivec.getCurrentArchive() + " | " +
                Archivec.fileCount + " files | " +
                "total size: ~" + ArchivecMethods.getTotalSize(archivec.getArchiveSize()));

        switchMode(ArchivecMode.EXISTING_ARCHIVE);
    }

    public void newArchive(ActionEvent event) {
        System.out.println("newArchive");
        archivec.newArchive();
        labelArchiveInfo.setText("Unnamed | 0 files");

        switchMode(ArchivecMode.NEW_ARCHIVE);
    }

    public void saveArchive(ActionEvent event) {
        System.out.println("saveArchive");
    }

    public void saveArchiveAs(ActionEvent event) {
        System.out.println("saveArchiveAs");
    }

    public void addFiles(ActionEvent event) {
        System.out.println("addFiles");
        archivec.addFiles(currentMode);

        labelArchiveInfo.setText((Archivec.currentArchive.equals("") ? "Unnamed" : Archivec.currentArchive)
                + " | " + Archivec.fileCount + " files | " +
                "total size: ~" + ArchivecMethods.getTotalSize(Archivec.archiveSize));
    }

    public void removeFiles(ActionEvent event) {
        System.out.println("removeFiles");
        archivec.removeFiles(tableArchiveContent, currentMode);

        labelArchiveInfo.setText((Archivec.currentArchive.equals("") ? "Unnamed" : Archivec.currentArchive)
                + " | " + Archivec.fileCount + " files | " +
                "total size: ~" + ArchivecMethods.getTotalSize(Archivec.archiveSize));

        tableArchiveContent.getSelectionModel().clearSelection();
    }

    public void close(ActionEvent event) {
        System.out.println("close");

    }

    public void settings(ActionEvent event) {
        System.out.println("settings");
    }
}

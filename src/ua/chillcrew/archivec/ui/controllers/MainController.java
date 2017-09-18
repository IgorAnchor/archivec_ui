package ua.chillcrew.archivec.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ua.chillcrew.archivec.core.ArchivecCore;

public class MainController {

    private ArchivecCore archivecCore;

    @FXML
    private Button btnExtract;

    public void extract(ActionEvent event) {
        System.out.println("extract");
    }

    public void crush(ActionEvent event) {
        System.out.println("crush");
    }

    public void openArchive(ActionEvent event) {
        System.out.println("openArchive");
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

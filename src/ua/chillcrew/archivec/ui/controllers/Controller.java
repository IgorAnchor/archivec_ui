package ua.chillcrew.archivec.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ua.chillcrew.archivec.core.ArchivecCore;

import java.util.ArrayList;

public class Controller {

    ArchivecCore archivecCore = new ArchivecCore();

    @FXML
    private Button btnExtract;

    public void btnExtractClick(ActionEvent event) {

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(0);
        ids.add(10);
        ids.add(5);


        archivecCore.extractFiles("C:/Users/IgorTheMLGPro/Documents/GitHub/archivec_ui/test.ar",
                "C:/Users/IgorTheMLGPro/Documents/GitHub/archivec_ui/test", ids);
    }
}

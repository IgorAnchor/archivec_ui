package ua.chillcrew.archivec.core;

import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ua.chillcrew.archivec.util.ArchivecMethods;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Archivec {
    private static final PathTreeItem root = new PathTreeItem();
    public static int fileCount = 0;
    public static long archiveSize = 0;
    public static String currentArchive = "";

    private static Stage stage;

    private ArchivecCore archivecCore;

    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;

    private int lastId = 0;
    private ArrayList<PathTreeItem> files;

    public Archivec() {
        archivecCore = new ArchivecCore();
        fileChooser = new FileChooser();
        directoryChooser = new DirectoryChooser();
        files = new ArrayList<>();

        fileChooser.setInitialDirectory(new File("C:\\Users\\IgorTheMLGPro\\CLionProjects\\3-1\\archivec-core\\cmake-build-debug\\1"));
    }

    static void setStage(Stage stage) {
        Archivec.stage = stage;
    }

    public static PathTreeItem getRoot() {
        return root;
    }

    public long getArchiveSize() {
        return archiveSize;
    }

    public String getCurrentArchive() {
        return currentArchive;
    }

    public void extract() {
        File destPath = directoryChooser.showDialog(stage);
        if (destPath == null) return;

        archivecCore.extract(currentArchive, destPath.getPath());
    }

    public void extractFiles(TreeTableView<ArchiveItem> table) {
        if (table.getSelectionModel().getSelectedItems().size() <= 0) return;

        File destPath = directoryChooser.showDialog(stage);
        if (destPath != null) {
            ArrayList<Integer> ids = new ArrayList<>();
            for (TreeItem<ArchiveItem> item : table.getSelectionModel().getSelectedItems()) {
                ArchivecMethods.getFileIds(item, ids);
            }

            archivecCore.extractFiles(currentArchive, destPath.getPath(), ids);
        } else {
            System.out.println("error open directory");
        }

        table.getSelectionModel().clearSelection();
    }

    public boolean openArchive() {
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            files.clear();
            root.getChildren().clear();
            fileCount = 0;
            lastId = archivecCore.getLastId(file.getPath());
            files = archivecCore.extractFilesInfo(file.getPath());

            currentArchive = file.getPath();
            archiveSize = file.length();
            return true;
        }
        return false;
    }

    public void newArchive() {
        archivecCore.reset();
        archiveSize = 0;
        fileCount = 0;
        lastId = 0;
        currentArchive = "";
        root.getChildren().clear();
    }

    public void addFiles(ArchivecMode mode) {
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        if (files == null) return;

        boolean found = false;
        for (File file : files) {
            for (TreeItem<ArchiveItem> item : root.getChildren()) {
                if (Objects.equals(item.getValue().getName(), file.getName()) && Long.parseLong(item.getValue().getSize()) == file.length()) {
                    found = true;
                    break;
                }
            }
            if (found) {
                found = false;
                continue;
            }
            //set size and add new file
            archiveSize += file.length();

            new PathTreeItem(new ArchiveItem(++lastId + "", file.getName(), file.length() + ""), root);
        }
        //archivate
        ArrayList<String> filePaths = new ArrayList<>();
        for (File file : files) {
            filePaths.add(file.getPath());
        }
        if (mode == ArchivecMode.EXISTING_ARCHIVE) {
            archivecCore.addToExistingAtchive(filePaths, currentArchive);
            return;
        }
        archivecCore.addToArchive(filePaths);
    }

    public void removeFiles(TreeTableView<ArchiveItem> table, ArchivecMode mode) {
        if (root.getChildren().size() <= 0) return;

        long size = 0;
        for (TreeItem<ArchiveItem> item : table.getSelectionModel().getSelectedItems()) {
            ArchivecMethods.getSelectedSize(item, size);
        }

        ArrayList<Integer> ids = new ArrayList<>();
        for (TreeItem<ArchiveItem> item : table.getSelectionModel().getSelectedItems()) {
            ArchivecMethods.getFileIds(item, ids);
        }

        root.getChildren().removeAll(table.getSelectionModel().getSelectedItems());
        fileCount -= ids.size();

        if (mode == ArchivecMode.EXISTING_ARCHIVE)
            archivecCore.removeFromArchive(ids, currentArchive);
    }

    public void crush() {
        File file = fileChooser.showSaveDialog(stage);
        if (file == null) return;

        archivecCore.crush(file.getPath(), !file.exists());
    }
}

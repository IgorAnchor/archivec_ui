package ua.chillcrew.archivec.util;

import javafx.scene.control.TreeItem;
import ua.chillcrew.archivec.core.ArchiveItem;

import java.util.ArrayList;
import java.util.Objects;

public final class ArchivecMethods {
    public static String getTotalSize(long size) {
        if (size < 1024) {
            return size + "b";
        } else if (size < 1048576) {
            return size / 1024 + "kb ("+size+"b)";
        } else if (size < 1073741824) {
            return size / 1048576 + "mb ("+size+"b)";
        } else {
            return size / 1073741824 + "gb ("+size+"b)";
        }
    }

    public static void getFileIds(TreeItem<ArchiveItem> root, ArrayList<Integer> idsArray) {
        if (root.getChildren().size() > 0) {
            for (TreeItem<ArchiveItem> item : root.getChildren()) {
                if (item.getChildren().size() > 0) {
                    getFileIds(item, idsArray);
                } else {
                    idsArray.add(Integer.parseInt(item.getValue().getId()));
                }
            }
        } else {
            idsArray.add(Integer.parseInt(root.getValue().getId()));
        }

    }

    public static void getSelectedSize(TreeItem<ArchiveItem> root, long size) {
        for (TreeItem<ArchiveItem> item : root.getChildren()) {
            if (item.getChildren().size() > 0) {
                getSelectedSize(item, size);
            } else {
                size += Long.parseLong(Objects.equals(item.getValue().getSize(), "") ? "0" : item.getValue().getSize());
            }
        }
    }
}

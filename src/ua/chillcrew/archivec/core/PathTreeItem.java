package ua.chillcrew.archivec.core;

import javafx.scene.control.TreeItem;

class PathTreeItem extends TreeItem<ArchiveItem> {

    PathTreeItem() {
        super();
    }

    PathTreeItem(ArchiveItem value, PathTreeItem root) {
        super(value);

        Archivec.fileCount++;

        value.setName(value.getName().replace("\\", "/"));
        String[] parts = value.getName().split("/");

        if (parts.length <= 1) {
            root.getChildren().add(new TreeItem<>(value));
            return;
        }

        TreeItem<ArchiveItem> tempRoot = root;
        TreeItem<ArchiveItem> prevRoot = root;

        for (int i = 0; i < parts.length - 1; i++) {
            tempRoot = formTree(parts[i], tempRoot);

            if (tempRoot == null) {
                TreeItem<ArchiveItem> item = new TreeItem<>(new ArchiveItem("", parts[i], "", ""));
                prevRoot.getChildren().add(item);
                prevRoot = item;
                tempRoot = prevRoot;
                continue;
            }
            prevRoot = tempRoot;
        }
        TreeItem<ArchiveItem> item = new TreeItem<>(new ArchiveItem(value.getId(), parts[parts.length - 1], value.getSize(), value.getCompressedSize()));
        prevRoot.getChildren().add(item);
    }

    private TreeItem<ArchiveItem> formTree(String name, TreeItem<ArchiveItem> root) {
        for (TreeItem<ArchiveItem> rootItem : root.getChildren()) {
            if (rootItem.getValue().getName().equals(name)) {
                return rootItem;
            }
        }
        return null;
    }
}

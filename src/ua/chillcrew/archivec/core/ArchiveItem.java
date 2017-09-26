package ua.chillcrew.archivec.core;

import javafx.beans.property.SimpleStringProperty;

public class ArchiveItem {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty size;
    private SimpleStringProperty compressedSize;

    public ArchiveItem(final String id, final String name, final String size, final String compressedSize) {
        this.id = new SimpleStringProperty(id );
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
        this.compressedSize = new SimpleStringProperty(compressedSize);
    }

    void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    String getCompressedSize() {
        return compressedSize.get();
    }

    public SimpleStringProperty compressedSizeProperty() {
        return compressedSize;
    }
}

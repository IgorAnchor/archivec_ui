package ua.chillcrew.archivec.core;

import javafx.beans.property.SimpleStringProperty;

public class ArchiveItem {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty size;

    public ArchiveItem(final String id, final String name, final String size) {
        this.id = new SimpleStringProperty(id );
        this.name = new SimpleStringProperty(name);
        this.size = new SimpleStringProperty(size);
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

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }
}

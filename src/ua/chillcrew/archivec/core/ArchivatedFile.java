package ua.chillcrew.archivec.core;

public class ArchivatedFile {
    private int id;
    private String name;
    private long size;

    public ArchivatedFile(final int id, final String name, final long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }
}

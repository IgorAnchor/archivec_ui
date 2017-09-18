package ua.chillcrew.archivec.core;

import java.util.ArrayList;

public class ArchivecCore {
    static {
        System.load("C:/Users/IgorTheMLGPro/CLionProjects/3-1/archivec-core/cmake-build-debug/libarchivec_core.dll");
    }

    private static native void initNative();

    private static native void crushNative(String archive_path);

    private static native void addToArchiveNative(ArrayList<String> files);

    private static native void addToExistingAtchiveNative(ArrayList<String> files, String pathToArchive);

    private static native void extractNative(String pathRoArchive, String destPath);

    private static native boolean extractFilesNative(String archivePath, String destPath, ArrayList<Integer> ids);

    private static native ArrayList<String> extractFilesInfoNative(String pathToArchive);

    private static native int extractFilesCountNative(String pathToArchive);

    private static native void removeFromArchiveNative(ArrayList<Integer> ids, String pathToArchive);

    private static native void setBufferSizeNative(int newSize);


    public ArchivecCore() {
        initNative();
    }

    public void crush(String archive_path) {
        crushNative(archive_path);
    }

    public void addToArchive(ArrayList<String> files) {
        addToArchiveNative(files);
    }

    public void addToExistingAtchive(ArrayList<String> files, String pathToArchive) {
        addToExistingAtchiveNative(files, pathToArchive);
    }

    public void extract(String pathRoArchive, String destPath) {
        extractNative(pathRoArchive, destPath);
    }

    public boolean extractFiles(String archivePath, String destPath, ArrayList<Integer> ids) {
        return extractFilesNative(archivePath, destPath, ids);
    }

    public ArrayList<ArchivatedFile> extractFilesInfo(String pathToArchive) {
        ArrayList<String> files = extractFilesInfoNative(pathToArchive);
        if (files.size() == 0) return null;

        ArrayList<ArchivatedFile> archivatedFiles = new ArrayList<>(files.size());
        for (String fileInfo : files) {
            String[] fields = fileInfo.split("\\|");

            archivatedFiles.add(new ArchivatedFile(
                    Integer.parseInt(fields[0]),
                    fields[1],
                    Long.parseLong(fields[2])
            ));
        }
        return archivatedFiles;
    }

    public int extractFilesCount(String pathToArchive) {
        return extractFilesCountNative(pathToArchive);
    }

    public void removeFromArchive(ArrayList<Integer> ids, String pathToArchive) {
        removeFromArchiveNative(ids, pathToArchive);
    }

    public void setBufferSize(int newSize) {
        setBufferSizeNative(newSize);
    }
}


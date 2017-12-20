package ua.chillcrew.archivec.core;

import ua.chillcrew.archivec.util.ArchivecMethods;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class ArchivecCore {
    static {
//        System.load("C:\\Users\\IgorTheMLGPro\\Documents\\GitHub\\archivec-core\\cmake-build-debug\\libarchivec_core.dll");
        try {
            System.load(new File(".").getCanonicalPath() + File.separator + "bin" + File.separator + "libarchivec_core.dll");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static native void initNative();

    private static native void crushNative(String archive_path, boolean askReplace);

    private static native void addToArchiveNative(ArrayList<String> files);

    private static native void addToExistingAtchiveNative(ArrayList<String> files, String pathToArchive);

    private static native void extractNative(String pathRoArchive, String destPath, boolean askReplace);

    private static native boolean extractFilesNative(String archivePath, String destPath, ArrayList<Integer> ids, boolean askReplace, boolean isFillPath);

    private static native ArrayList<String> extractFilesInfoNative(String pathToArchive);

    private static native void removeFromArchiveNative(ArrayList<Integer> ids, String pathToArchive);

    private static native void setBufferSizeNative(int newSize);

    private static native void resetNative();

    private static native int getLastIdNative(String path);


    ArchivecCore() {
        initNative();
    }

    void crush(String archive_path, boolean askReplace) {
        crushNative(archive_path, askReplace);
    }

    void addToArchive(ArrayList<String> files) {
        addToArchiveNative(files);
    }

    void addToExistingAtchive(ArrayList<String> files, String pathToArchive) {
        addToExistingAtchiveNative(files, pathToArchive);
    }

    void extract(String pathRoArchive, String destPath, boolean askReplace) {
        extractNative(pathRoArchive, destPath, askReplace);
    }

    void extractFiles(String archivePath, String destPath, ArrayList<Integer> ids, boolean askReplace, boolean isFullPath) {
        extractFilesNative(archivePath, destPath, ids, askReplace, isFullPath);
    }

    ArrayList<PathTreeItem> extractFilesInfo(String pathToArchive) {
        ArrayList<String> files = extractFilesInfoNative(pathToArchive);
        if (files.size() == 0) return null;

        ArrayList<PathTreeItem> archivatedFiles = new ArrayList<>(files.size());
        for (String fileInfo : files) {
            String[] fields = fileInfo.split("\\|");

            archivatedFiles.add(new PathTreeItem(new ArchiveItem(
                    fields[0],
                    fields[1],
                    ArchivecMethods.getTotalSize(Long.parseLong(fields[2])),
                    ArchivecMethods.getTotalSize(Long.parseLong(fields[3]))
            ), Archivec.getRoot()));
        }
        return archivatedFiles;
    }

    void removeFromArchive(ArrayList<Integer> ids, String pathToArchive) {
        removeFromArchiveNative(ids, pathToArchive);
    }

    void setBufferSize(int newSize) {
        setBufferSizeNative(newSize);
    }

    void reset() {
        resetNative();
    }

    int getLastId(String path) {
        return getLastIdNative(path);
    }
}


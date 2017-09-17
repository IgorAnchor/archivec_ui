package ua.chillcrew.archivec.core;

import java.util.ArrayList;

public class ArchivecCore {
    static {
        System.load("C:/Users/IgorTheMLGPro/CLionProjects/3-1/archivec-core/cmake-build-debug/libarchivec_core.dll");
    }

    private static native void extractFilesNative(String archivePath, String destPath, ArrayList<Integer> fileIds);


    public void extractFiles(String archivePath, String destPath, ArrayList<Integer> fileIds){

        System.out.println(fileIds);

        extractFilesNative(archivePath, destPath, fileIds);
    }
}


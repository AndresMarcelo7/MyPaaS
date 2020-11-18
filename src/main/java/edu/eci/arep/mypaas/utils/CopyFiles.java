package edu.eci.arep.mypaas.utils;

import org.apache.commons.io.FileUtils;

import java.io.*;

public class CopyFiles {
    public CopyFiles() {
    }

    public static void copy(String src, String des) throws IOException {
        File source = new File(src);
        File dest = new File(des);
        try {
            FileUtils.copyFile(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

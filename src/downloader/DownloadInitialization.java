/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ratul
 */
public class DownloadInitialization {

    private List<File> filePartList;

    public void initializeDownload(String downloadURL) throws IOException {

        String fileName = "Music";
        URL link = new URL(downloadURL);

        URLConnection urlConnection = link.openConnection();
//        urlConnection.setRequestProperty("Range", "bytes=0-25");
        urlConnection.connect();
        int fileSize = urlConnection.getContentLength();
        System.out.println("bytes: " + fileSize);
        filePartList = new LinkedList();

//        boolean support = urlConnection.getHeaderField("Accept-Ranges").equals("bytes");
//        System.out.println("Partial content retrieval support = " + (support ? "Yes" : "No"));
        System.out.println("fileSize: " + fileSize / (1024 * 1024) + " MB");

        if (fileSize % 2 != 0) {
            fileSize--;
        }
        fileSize = fileSize / 4;

        System.out.println("part(byte): " + fileSize + " part(MB): " + fileSize / (1024 * 1024));
        int endPoint = -1;
        int startPoint;

        for (int i = 1;i < 5; i++) {

            startPoint = endPoint + 1;
            endPoint = startPoint + fileSize;
            urlConnection = link.openConnection();
            if (i == 4) {
                endPoint++;
            }
            System.out.println("startPoint: " + startPoint + " endPoint: " + endPoint);
            urlConnection.setRequestProperty("Range", "bytes=" + startPoint + "-" + endPoint);
            urlConnection.connect();
            new Thread(new DownloadPhase(urlConnection, fileName + i, filePartList)).start();
        }

    }
}

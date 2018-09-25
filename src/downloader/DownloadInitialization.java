/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ratul
 */
public class DownloadInitialization {

    private List<File> filePartList;

    public void initializeDownload(String downloadURL) throws IOException {

        String fileName = "Part";
        URL link = new URL(downloadURL);

        URLConnection urlConnection = link.openConnection();
        urlConnection.connect();
        int fileSize = urlConnection.getContentLength();
        System.out.println("Total Bytes: " + fileSize);
        filePartList = new LinkedList();


        if (fileSize % 2 != 0) {
            fileSize--;
        }
        
        fileSize = fileSize / 4; //Hardcoded File part.We can ask candidates to make it dynamic
        int endPoint = -1;
        int startPoint;

        for (int i = 1;i < 5; i++) {

            startPoint = endPoint + 1;
            endPoint = startPoint + fileSize;
            urlConnection = link.openConnection();
            if (i == 4) {
                endPoint++;
            }
            System.out.println("start byte: " + startPoint + " end byte: " + endPoint);
            urlConnection.setRequestProperty("Range", "bytes=" + startPoint + "-" + endPoint);
            urlConnection.connect();
            new Thread(new DownloadPhase(urlConnection, fileName + i, filePartList)).start();
        }

    }
}

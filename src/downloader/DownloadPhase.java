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
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ratul
 */
public class DownloadPhase implements Runnable {

    private int startByte;
    private int endByte;
    private final URLConnection urlConnection;
    private final String filePartName;
    private final List<File> filePartList;

    public DownloadPhase(URLConnection urlConnection,String filePartName,List<File> filePartList) {

        this.urlConnection = urlConnection;
        this.filePartName = filePartName;
        this.filePartList = filePartList;
    }

    @Override
    public void run() {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String start = sdf.format(cal.getTime());

        try {
            
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int index = 0;
            while (-1 != (index = stream.read(buf))) {
                out.write(buf, 0, index);
            }
            out.close();
            stream.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(filePartName);
            fos.write(response);
            fos.close();
            
            filePartList.add(new File(filePartName));
            
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
        cal = Calendar.getInstance();
        String end = sdf.format(cal.getTime());
        System.out.println(filePartName+" Downloading....Start Time: " + start + " End Time: " + end);

        try {
            new MergeFiles().defragmentation(filePartList);
        } catch (IOException ex) {
            Logger.getLogger(DownloadPhase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

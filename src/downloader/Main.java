/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloader;

import java.io.IOException;

/**
 *
 * @author ratul
 */
public class Main {

    public static void main(String[] args) throws IOException  {

        String downloadURL = "http://download.music.com.bd/Music/W/Warfaze/Poth%20Chola/12%20-%20Warfaze%20-%20Oshamajik%20(music.com.bd).mp3";
        
        DownloadInitialization downloadInitialization = new DownloadInitialization();
        downloadInitialization.initializeDownload(downloadURL);
    }
}

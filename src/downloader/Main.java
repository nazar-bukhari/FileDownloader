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

        String downloadURL = "http://download.music.com.bd/Music/N/Nagorik/Nagorik/10%20-%20Nagorik%20-%20Shondha%20(music.com.bd).mp3";
        
        DownloadInitialization downloadInitialization = new DownloadInitialization();
        downloadInitialization.initializeDownload(downloadURL);
    }
}

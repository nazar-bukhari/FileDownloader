/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloader;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author ratul
 */
public class MergeFiles {

    private final File finalFile = new File("Rock.mp3");
    
    public void defragmentation(List<File> files) throws IOException {

        try (BufferedOutputStream mergingStream = new BufferedOutputStream(
                new FileOutputStream(finalFile))) {
            for (File f : files) {
                Files.copy(f.toPath(), mergingStream);
            }
        }
    }

}

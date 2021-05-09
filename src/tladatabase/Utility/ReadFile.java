/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Elorm
 */
public class ReadFile {

    private String path;

    public ReadFile(String file_Path) {
      path = file_Path;
    }

    public String[] openFile() throws IOException {

        FileReader fr = new FileReader(path);
        BufferedReader textReader = new BufferedReader(fr);

        int numberOfLines = reasLines();

        String[] textData = new String[numberOfLines];

        int i;

        for (i = 0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }

        textReader.close();
        return textData;

    }

    int reasLines() throws IOException {

        FileReader file_to_Read = new FileReader(path);
        BufferedReader bf = new BufferedReader(file_to_Read);

        String aline;
        int numberOfLines = 0;

        while ((aline = bf.readLine()) != null) {
            numberOfLines++;
        }
        return numberOfLines;
    }

}

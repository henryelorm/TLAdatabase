/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.File;

/**
 *
 * @author Elorm
 */
public class CreateArchiveFolder {

    public CreateArchiveFolder() {
        File file = new File(Paths.createProgramArchiveFolder);
        if (!file.exists()) {
            if (file.mkdir()) {
               // System.out.println("Directory is created!");
            } else {
               
                BugLog.Error_static("Failed to create directory (TLA_Store) at C:\\ProgramData", "ERROR");
            }
        }
    }

}

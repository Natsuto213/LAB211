package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Platform;

public class Platforms extends HashSet<Platform> {

    private String pathFile;

    public Platforms(String pathFile) {
        this.pathFile = pathFile;
        this.readFromFile();
    }

    public Platform findByCode(String code) {
        for (Platform p : this) {
            if (p.getCode().equalsIgnoreCase(code)) {
                return p;
            }
        }
        return null;
    }

    public void showAll() {
        if (!this.isEmpty()) {
            System.out.format("%-4s | %-8s | %-11s\n",
                    "Code", "Platform", "Description");
            System.out.println("-------------------------------");
            for (Platform p : this) {
                System.out.println(p);
            }
        } else {
            System.out.println("--------------------------------------");
            System.out.println("Platform list is currently empty, not loaded yet.");
            System.out.println("--------------------------------------");
        }
    }

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            File f = new File(pathFile);
            if (!f.exists()) {
                System.out.println("File not exist. Please try again");
            }
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                Platform platform = dataToObject(temp);
                if (platform != null) {
                    this.add(platform);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Platforms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Platforms.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Platforms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Platform dataToObject(String temp) {
        Platform platform = null;
        String[] word = temp.split(",");
        if (word.length >= 3) {
            try {
                String code = word[0];
                String name = word[1];
                String des = word[2];
                platform = new Platform(code, name, des);
            } catch (Exception e) {
            }
        }
        return platform;
    }
}

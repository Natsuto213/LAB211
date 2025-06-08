package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Room;

public class Rooms extends TreeMap<String, Room> {

    private String pathFile;

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            // 1. Tao file
            File f = new File(pathFile);
            if (!f.exists()) {
                System.out.println("The file is not exist. Please check again!");
            }
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                Room r = dataToObject(temp);
                if (r != null) {
                    this.put(r.getRoomID(), r);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Room dataToObject(String temp) {
        Room r = null;
        String[] word = temp.split(";");
        if (word.length > 0) {
            try {
                String ID = word[0];
                String name = word[1];
                String type = word[2];
                int rate = Integer.parseInt(word[3]);
                int capacity = Integer.parseInt(word[4]);
                String funiture = word[5];
                r = new Room(ID, name, type, rate, capacity, funiture);
            } catch (Exception e) {
            }
        }
        return r;
    }
}

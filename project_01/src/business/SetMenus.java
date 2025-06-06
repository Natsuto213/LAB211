package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.SetMenu;

public class SetMenus extends TreeMap<String, SetMenu> {

    private String pathFile;

    public SetMenus(String pathFile) {
        this.pathFile = pathFile;
        readFromFile();
    }

    public void readFromFile() {
        FileInputStream fis = null;
        try {
            File f = new File(pathFile);
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                SetMenu sm = dataToObject(temp);
                if (sm != null) {
                    this.put(sm.getMenuId(), sm);
                }
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SetMenus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private SetMenu dataToObject(String temp) {
        SetMenu sm = null;
        String[] word = temp.split(",");

        if (word.length >= 4) {
            try {
                String menuId = word[0];
                String name = word[1];
                Double price = Double.parseDouble(word[2]);
                String ingredient = word[3];
                if (ingredient.startsWith("\"") && ingredient.endsWith("\"")) {
                    ingredient = ingredient.substring(1, ingredient.length() - 1);
                }
                sm = new SetMenu(menuId, name, price, ingredient);
            } catch (Exception e) {
            }
        }
        return sm;
    }

    public void showAll() {
        System.out.println("--------------------------------------");
        System.out.println("List of Set Menus for ordering party:");
        System.out.println("--------------------------------------");
        List<SetMenu> values = new ArrayList(this.values());
        Collections.sort(values);
        for (SetMenu sm : values) {
            System.out.println(sm);
        }
    }

    public SetMenu searchById(String id) {
        return this.get(id);
    }

    public boolean contains(String id) {
        return this.containsKey(id);
    }

    public void func04() {
        try {
            this.readFromFile();

        } catch (Exception e) {
        }
        this.showAll();
    }

}

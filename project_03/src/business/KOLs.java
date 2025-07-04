package business;

import java.util.HashSet;
import models.KOL;
import tools.Inputter;

public class KOLs extends HashSet<KOL> {

    private String pathFile;
    boolean isSave;

    public KOLs(String pathFile) {
        this.pathFile = pathFile;
        isSave = false;
    }

    public boolean isDupplicate(KOL k) {
        return this.contains(k);
    }

    public void addNew(KOL k) {
        if (!this.isDupplicate(k)) {
            this.add(k);
        } else {
            System.out.println("KOL already exist!");
        }
        isSave = false;
    }

    public void showAll() {
        System.out.println("---------------------------------------------------------");
        System.out.format("%-8s | %-20s | %-10s | %-8s | %-9s | %-10s\n",
                "KOL ID", "Name", "Phone", "Platform", "Followers", "Commission");
        System.out.println("---------------------------------------------------------");
        for (KOL k : this) {
            System.out.print(k);
        }
        System.out.println("---------------------------------------------------------");
    }

    public void func01(Inputter ip, Platforms platforms) {
        KOL k = ip.inputKOL(platforms);
        this.addNew(k);
        System.out.println("Add succesfully.");
    }

    public void func03() {
        if (this.isEmpty()) {
            System.out.println("no KOLs have registered yet.");
        } else {
            this.showAll();
        }
    }
}

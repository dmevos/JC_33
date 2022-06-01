import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    static final String fPath = "C://NetologyProject//JavaCore//JC_33//Games//savegames//";

    public static void main(String[] args) {
        openZip(fPath + "zipsave.zip", fPath);

        GameProgress GameProgress1 = openProgress(fPath + "save1.dat");
        GameProgress GameProgress2 = openProgress(fPath + "save2.dat");
        GameProgress GameProgress3 = openProgress(fPath + "save3.dat");

        System.out.println(GameProgress1);
        System.out.println(GameProgress2);
        System.out.println(GameProgress3);
    }

    public static GameProgress openProgress(String inFile) {
        GameProgress gameProgress = null;
        try (FileInputStream f = new FileInputStream(inFile);
             ObjectInputStream o = new ObjectInputStream(f)) {

            gameProgress = (GameProgress) o.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;

    }

    public static void openZip(String inFile, String outPath) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(inFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = outPath + entry.getName(); // получим название файла
                // распаковка
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

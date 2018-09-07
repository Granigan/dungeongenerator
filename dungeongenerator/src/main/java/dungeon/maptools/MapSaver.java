package dungeon.maptools;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * An optional class to save the output to the disk instead of just printing it
 * out.
 *
 * @author tgtapio
 */
public class MapSaver {

    /**
     * Saves the map to a file on disk, under project directory with name
     * 'generated_map.txt'.
     *
     * @param output to be printed to the file
     * @param filename for the output file
     * @throws IOException problem with writing to disk
     */
    public void saveStringToFile(String output, String filename) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename + ".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.print(output);
            pw.close();
        } catch (Exception e) {
            System.out.println("IOException caught!\n" + e);
        }
    }

}

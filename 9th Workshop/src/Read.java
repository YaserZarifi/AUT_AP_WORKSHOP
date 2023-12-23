

import java.io.*;
import java.util.HashSet;

/**
 * @author Yaser Zarifi
 *
 */
public class Read {
    /**
     * Stores all words from file (Used Hashset for preventing the repeat of words)
     */
        HashSet<String> words = new HashSet<>();

    /**
     * @param fileName the file directory
     * Reads the file from the directory and Stores them into the HashSet
     */
        public void readFile(String fileName) {
            try {
                FileReader fr = new FileReader(new File(fileName));
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) {
                    words.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


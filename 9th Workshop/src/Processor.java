import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


/**
 * @author Yaser Zarifi
 */
public class Processor {
    Read read = new Read();
    long count = 0;
    private String longest;
    private String shortest;
    private int sum;
    private int average;
    public Processor(){}


    /**
     * first counts the number of files in the directory with a stream
     * secondly creates the number of treads based on number of files in the directory
     * then reads the files from the directory by passing the directory to Read class
     * finally joins all treads into one
     * */
    private void fileReader() {
        try (Stream<Path> files = Files.list(Paths.get("files"))) {
            count = files.count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 1; i<=count ; i++){
            Thread thread = new Thread();
            read.readFile("files\\file_"+i+".txt");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * first read the files
     * then prints the size of hashset from Read class
     */
    public void countNumberOfWords(){
        fileReader();
        System.out.println("Number of All Words without Repeat : "+ read.words.size()+"\n");
    }

    /**
     * reads the file
     * then puts the longest word in the Hashset of Read class into "longest" field
     */
    public void longestWord(){
        fileReader();
        int maxLength = 0;
        String longestString =null;
        for (String s : read.words) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestString = s;
            }
        }
        longest = longestString;
        System.out.println("Longest Word is : "+longestString);
        System.out.println("The word length is : "+longestString.length()+"\n");
    }

    /**
     * reads the file
     * then finds the shortest word in the Hashset of Read class
     */
    public void shortestWord(){
        fileReader();
        
        int maxLength = 0;
        String longestString =null;
        for (String s : read.words) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestString = s;
            }
        }
        longest = longestString;

        int minLength = longest.length();
        for (String s : read.words) {
            if (s.length() < minLength) {
                minLength = s.length();
                shortest = s;
            }
        }
        System.out.println("Shortest Word is : "+shortest);
        System.out.println("The word length is : "+shortest.length()+"\n");
    }

    /**
     * @return average length of all words inside the Hashset of Read Class
     */
    public int avg(){
        fileReader();
        for (String s: read.words){
            sum+= s.length();
        }
        return sum/ read.words.size();
    }
}

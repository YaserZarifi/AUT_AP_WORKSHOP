import java.util.Scanner;

/**
 * @author Yaser Zarifi
 */
public class Main {

    public static void main(String[] args) {
        Processor processor = new Processor();
        boolean active = true;
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (active) {
            System.out.println("Please Choose from Menu Below: ");
            System.out.println("1. Words Count (Without Repeat)\n" +
                    "2. Longest Word and its Length\n" +
                    "3. Shortest Word and its Lenght\n" +
                    "4. All Words Length Average\n" +
                    "5. Exit\n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    processor.countNumberOfWords();
                    break;
                case 2:
                    processor.longestWord();
                    break;
                case 3:
                    processor.shortestWord();
                    break;
                case 4:
                    System.out.println("Average of words length is : "+ processor.avg()+"\n");
                    break;
                case 5:
                    active = false;
                    break;
            }

        }
    }
}

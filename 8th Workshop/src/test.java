import Back.NoteSystem;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        NoteSystem system = new NoteSystem();
        Scanner scanner = new Scanner(System.in);

        int choice,index;
        String content,title;

        boolean active = true;

        while (active){
            System.out.println("\n\n1: Add\n2: Remove\n3: Notes\n4: Exit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    scanner.nextLine();
                    System.out.print("enter the title: ");
                    title = scanner.nextLine();
                    System.out.print("enter the content: ");
                    content = scanner.nextLine();
                    system.addNote(title,content);
                    break;

                case 2:
                    for (int i = 0;i < system.size();i++){
                        System.out.println((i+1)+": "+system.getTitle(i));
                    }

                    System.out.print("Enter the number: ");
                    index = scanner.nextInt();
                    if (index > system.size() || index < 0){
                        System.out.println("error");
                    }else{
                        system.removeNote(index-1);
                        System.out.println("Removed!!");
                    }break;

                case 3:
                    for (int i = 0;i < system.size();i++){
                        System.out.println((i+1)+": "+system.getTitle(i));
                    }
                    System.out.println("Enter the Number of Note to Show: ");
                    index = scanner.nextInt();
                    System.out.println(system.getContent(index-1));break;

                case 4:
                    System.out.println("Have a nice day :)");
                    active = false;
                    break;

                default:
                    System.out.println("Please Enter a Valid Number of Choice !!");

            }
        }
    }
}

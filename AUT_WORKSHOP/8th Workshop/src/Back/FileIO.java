package Back;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Zarifi
 */
public class FileIO {
    private String name;
    private ArrayList<Note> notes;

    public FileIO() {
        name = "files//notes.bin";
        notes = new ArrayList<>();
        firstRun();
    }

    private void firstRun() {
        File folder = new File("files/");
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles == null || listOfFiles.length == 0) {
            try {
                FileOutputStream file = new FileOutputStream(this.name);
                ObjectOutputStream write = new ObjectOutputStream(file);
                write.writeObject(new ArrayList<Note>());
                write.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            setNotes();
        }

    }

    public void newFile(Note note) {
        notes.add(note);
        try (FileOutputStream file = new FileOutputStream(this.name);
             ObjectOutputStream write = new ObjectOutputStream(file)) {
            write.writeObject(this.notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeFile(Note note) {
        this.notes.remove(note);
        try (FileOutputStream file = new FileOutputStream(this.name);
             ObjectOutputStream write = new ObjectOutputStream(file)) {
            write.writeObject(this.notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    private void setNotes() {
        try (FileInputStream file = new FileInputStream(this.name);
             ObjectInputStream read = new ObjectInputStream(file)) {
            notes = (ArrayList<Note>) read.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

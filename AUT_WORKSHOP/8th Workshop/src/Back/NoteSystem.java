package Back;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Zarifi
 */
public class NoteSystem {
    private ArrayList<Note> notes;
    private FileIO file;

    public NoteSystem() {
        file = new FileIO();
        notes = new ArrayList<>();
        initialize();
    }

    public void initialize(){
        notes = file.getNotes();
    }

    public void addNote(String title, String content){
        Note note = new Note(content);
        note.setTitle(title);
        file.newFile(note);
    }

    public void removeNote(int i){
        file.removeFile(notes.get(i));
    }

    public String getTitle(int i){
        return notes.get(i).getTitle();
    }
    public int size(){
        return notes.size();
    }
    public String getContent(int i){
        return notes.get(i).getContent();
    }

    public Note getNote(int i){
        return notes.get(i);
    }
}

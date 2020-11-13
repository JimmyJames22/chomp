import java.io.*;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReaderWriter {

    public void writeFile(ArrayList<Board> allBoards, String filename)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allBoards);
            oos.close();
            System.out.println("File Saved");
        }
        catch(Exception e) {
            System.out.println("Error in output:" + e.toString());
        }
    }
    public ArrayList<Board> readFile(String filename)
    {
        ArrayList<Board> allBoards = new ArrayList<Board>(){};
        try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            allBoards = (ArrayList<Board>) ois.readObject();
            ois.close();
            System.out.println("File Imported");
        }
        catch(Exception e)
        {
            System.out.println("Error in output:" + e.toString());
        }
        return allBoards;
    }
}

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadWrite {
    public void write(Board[] c, String filename){
        try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(c);
            oos.close();
            System.out.println("File Saved");
        }
        catch(Exception e) {
            System.out.println("Error in output:" + e.toString());
        }
    }
    public Board[] read(String filename)
    {
        Board[] c={};

        try
        {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            c = (Board[]) ois.readObject();
            ois.close();
            System.out.println("File Imported");
        }
        catch(Exception e)
        {
            System.out.println("Error in output:" + e.toString());
        }

        return c;
    }
}

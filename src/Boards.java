import java.util.ArrayList;
import java.util.Collections;

public class Boards {

    ArrayList<Integer> col = new ArrayList<>();
    ArrayList<Integer> copy;

    boolean winner = false;
    int move = 9;

    public Boards(int a, int b, int c, int d, int e, int f, int g, int h, int i, int j, ArrayList loserBoards){
        col.add(a); col.add(b); col.add(c); col.add(d); col.add(e); col.add(f); col.add(g); col.add(h); col.add(i); col.add(j);
        for(int x=col.size()-1; x>=0; x--) {
            copy = (ArrayList<Integer>) col.clone();
            for (int y=copy.get(x)-1; y>=0; y--) {
                for(int z=x; z<col.size(); z++){
                    if(copy.get(z)>y){
                        copy.set(z, y);
                    }
                }
                if (loserBoards.contains(copy)) {
                    winner = true;
                    move = x * 10 + (9 - y);
                    return;
                }
            }
        }
    }
}
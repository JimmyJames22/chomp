import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerLists {

    Boards[] boards = new Boards[184755];
    ArrayList<ArrayList<Integer>> loserBoards;
    long start;
    int counter = 0;

    public PlayerLists() {
        start = System.currentTimeMillis();
        loserBoards = new ArrayList<>();
        possibleBoards(10);
        System.out.println(System.currentTimeMillis()-start);
    }

    public int move(Chip[] piece) {
        ArrayList<Integer> board = convCol(piece);
        System.out.println(board);
        for (int x=0; x<counter; x++) {
            if (boards[x].col.equals(board)) {
                return (boards[x].move);
            }
        }
        if(piece[39].isAlive) {
            return 39;
        } else {
            return 9;
        }
    }

    public ArrayList<Integer> convCol(Chip[] chip) {
        ArrayList<Integer> col = new ArrayList<>();
        int total = 0;
        for (int x = 0; x < 10; x++) {
            col.add(0);
            for (int z = 0; z < 10; z++) {
                if (chip[total].isAlive) {
                    col.set(x, col.get(x)+1);
                }
                total = total + 1;
            }
        }
        return col;
    }

    public void possibleBoards(int col) {
        for (int a = 1; a <= col; a++) {
            for (int b = 0; b <= a; b++) {
                for (int c = 0; c <= b; c++) {
                    for (int d = 0; d <= c; d++) {
                        for (int e = 0; e <= d; e++) {
                            for (int f = 0; f <= e; f++) {
                                for (int g = 0; g <= f; g++) {
                                    for (int h = 0; h <= g; h++) {
                                        for (int i = 0; i <= h; i++) {
                                            for(int j = 0; j <= i; j++) {
                                                boards[counter] = new Boards(a, b, c, d, e, f, g, h, i, j, loserBoards);
                                                if (!boards[counter].winner) {
                                                    loserBoards.add(boards[counter].col);
                                                }
                                                counter++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(counter);
    }
}



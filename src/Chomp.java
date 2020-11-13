import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Chomp extends Frame implements ActionListener {

    public Rectangle mouserec;
    public int CELL_WIDTH = 25;
    public int NUM_ROWS = 10;
    public int NUM_COLLOMS = 10;
    public boolean position = false;
    public int xpos = 0, ypos = 0;
    public Chip[] piece = new Chip[101];
    public int chipNum = 0;
    public boolean youLose = false;
    public boolean legal = false;
    public Button start, myAI, random, notsmart, play;
    public JPanel p = new JPanel();

    public NotSmartPlayer JEMEZ;
    public SmartPlayer DrHales;
    public SmartPlayer smartDude;
    public MyPlayer me;
    public PlayerLists pl;

    public String loser;
    public String base;

    public ArrayList<Board> arrayList = new ArrayList<Board>();

    public static void main(String[] args) {
        Chomp c = new Chomp();
        c.resize(400, 400);
        c.show();
    }

    public Chomp() {
        init();
    }

    public void init() {
        pl = new PlayerLists();

        start = new Button("NewGame");
        start.addActionListener(this);
        p.add(start);

        play = new Button("Play");
        play.addActionListener(this);
        p.add(play);

        random = new Button("random");
        random.addActionListener(this);
        p.add(random);

        myAI = new Button("MyChomp");
        myAI.addActionListener(this);
        p.add(myAI);

        setLayout(new BorderLayout());
        add("South", p);
        for (int i = 0; i < NUM_COLLOMS; i++) {

            for (int z = 0; z < NUM_ROWS; z++) {
                xpos = i * 25;
                ypos = z * 25 + 20;
                piece[chipNum] = new Chip(xpos + 18, ypos + 18, chipNum);
                if (chipNum < 100) {
                    chipNum++;
                }
            }

        }

    }


    public boolean mouseDown(Event evt, int x, int y) {
        mouserec = new Rectangle(x, y, 2, 2);
        for (int z = 0; z < 100; z++) {

            if (mouserec.intersects(piece[z].myRect)) {
                if(z==9) {
                    loser = "player";
                }
                move(z);
                repaint();
            }
        }
        return (true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")) {
            System.out.println("play");
            while (youLose == false) {
                int num;
                loser = "player1";
                num = DrHales.move(piece);
                move(num);

                if (youLose == false) {
                    loser = "player2";
                    num = DrHales.move(piece);
                    move(num);

                }


            }
        }
        if (e.getActionCommand().equals("NewGame")) {
            youLose = false;
            for (int z = 0; z < 100; z++) {
                piece[z].isAlive = true;
            }
            //play(desktopURL, "audio/yahoo2.au");
            repaint();
        }
        if (e.getActionCommand().equals("random")) {
            botMove();
        }
        if(e.getActionCommand().equals("MyChomp")){
            move(me.move(piece));
            repaint();
        }

    }

    private void botMove() {
        int move = pl.move((piece));
        System.out.println(move);
        move(move);
        if(move == 9){
            loser = "bot";
        }
        repaint();
    }

    public void move(int z) {
        //play(getCodeBase(), "audio/return.au");
        notLegal(piece[z], z);
        for (int q = 0; q < 100; q++) {
            if ((piece[z].xpos <= piece[q].xpos) && (piece[z].ypos >= piece[q].ypos)) {
                piece[q].isAlive = false;
            }
        }
        if (youLose == true) {
            System.out.println(loser + "  is the loser");
        }
    }

    public void notLegal(Chip chip, int chipNum) {
        if (chip.isAlive == false) {
            //play(getCodeBase(), "audio/return.au");
            youLose = true;
            //repaint();
        }
        if (chipNum == 9) {
            youLose = true;
        }

    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g)  // was boardPaint
    {//System.out.println("paint");
        if (youLose) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 600, 600);
            g.setColor(Color.red);
            g.drawString("YOU LOSE :(", 100, 100);

        }
        if (youLose == false) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 600, 600);
            chipNum = 0;
            // paint the framework
            g.setColor(Color.lightGray);
            g.fillRect(15, 15 + 20, CELL_WIDTH * NUM_ROWS, CELL_WIDTH * NUM_ROWS);
            g.setColor(Color.black);
            g.drawRect(10, 10 + 20, CELL_WIDTH * NUM_ROWS + 10, CELL_WIDTH * NUM_ROWS + 10);
            for (int c = 0; c < NUM_ROWS; c++)
                for (int r = 0; r < NUM_ROWS; r++)
                    g.drawRect(15 + CELL_WIDTH * c, 15 + 20 + CELL_WIDTH * r, CELL_WIDTH, CELL_WIDTH);


            for (int i = 0; i < 100; i++) {

                g.setColor(Color.white);

                if (piece[i].isAlive) {
                    g.fillOval(piece[i].xpos, piece[i].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                    g.setColor(Color.blue);
                    g.drawOval(piece[i].xpos, piece[i].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                    g.setColor(Color.white);
                    //System.out.println(i);
                    //System.out.println(piece[i].isAlive);

                    g.setColor(Color.blue);
                    if (piece[9].isAlive) {
                        g.fillOval(piece[9].xpos, piece[9].ypos, CELL_WIDTH - 6, CELL_WIDTH - 6);
                    }
                    g.setColor(Color.black);
                    g.drawString(i + " ", piece[i].xpos + 2, piece[i].ypos + 15);
                }


            }

        }//close for (youlose==fales)


    }

    public void printSequence() {
//        int a, b, c, d, e = 0;
//        int z = 0;


//        for (int j =1; j<6; j++){
//            for (int k=0; k<=j; k++){
//                for(int l=0; l<=k; l++) {
//                    for(int m=0; m<=l; m++){
//                        for(int n=0; n<=m;n++){
//                            System.out.println(j+" "+k + " "+l + " "+ m+ " "+n );
//
//                            arrayList.add(new Board(j,k,l));
//                        }
//                    }
//                }
//            }
//        }
//
//       int[] col = {3,2,2};
        for (int j = 1; j < 4; j++) {
            for (int k = 0; k <= j; k++) {
                for (int l = 0; l <= k; l++) {
                    System.out.println(j + " " + k + " " + l);
                    arrayList.add(new Board(j, k, l));

                }
            }
        }

    }


    public void reduceBoard(int[] col) {


        //start out with the last column and reduce it until its zero
        int lastCol = col.length - 1;

        //then you reduce the value of lastCol and start reducing that col
        //making sure that any column does not have more chips than my current column


        //------------------------------

        //first, create a copy of the array
//        int[] copy = new int[col.length];
//        copy[0] = col[0];
//        copy[1]=col [1];
//        copy[2] = col[2];
//
//        int lastCol = copy.length - 1;
        //check if copy does not equal zero, then continue on reducing, or base my while loop off of that

        //-------------------------------

//        int[] col = {3,2,2};


        int[] copy = new int[col.length];

        copy[0] = col[0];
        copy[1] = col[1];
        copy[2] = col[2];
//        copy[3] = col[3];
//        copy[4] = col[4];

        for (int i = col.length - 1; i >= 0; i--) {
            while (copy[i] > 0) {
                copy[i]--;

                for (int w = i + 1; w <= col.length - 1; w++) {
                    if (copy[w] > copy[i]) {
                        copy[w] = copy[i];
                    }
                }
                //save reduction
                //print it out to test it
                System.out.println(Arrays.toString(copy));


            }
            //reset to original board

            for (int j = 0; j < col.length; j++) {
                copy[j] = col[j];
            }
        }
        int size = arrayList.size();
        for (int o = 0; o < size; o++) {
//            int index = arrayList.indexOf(o);
            System.out.println(arrayList.get(o));
//            int col[] = toArray(arrayList.get(o));
//
            //although this for loop is currently not in the right place,
            //what I am trying to do with this for loop is to iterate through each possible board in my arrayList, then convert each board
            //into an array which is col[] so that i can than use my reduceBoard method to find the reductions for all of the possible boards.
            //from there i'd be able to iterate through all of the reductions to check for whether or not I find a specific losing board such as {1,0,0}
            //HeLp! i am having trouble with converting a board in my arraylist into just a simple array. I was looking up ways to try to do that
            //and was also attempting to convert each number in each column, such as 3, 3, 2 for board {3,3,2}, into an integer than place the integers into an array
            //this was all unsuccessful :/

//            for(int p = 0; p<o.length;)
//                arrayList.get(o)= int col[];
//        }


//        if (col[0] == 2 && col[1] == 2 && col[2] ==2) {
//            System.out.println(Arrays.toString(col));
//        }

        }

    }    // close Chomp\
}



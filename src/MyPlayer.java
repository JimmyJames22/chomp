import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyPlayer {
	//MyPlayer is the player template
	public int[] column = new int[10];
	public int[] row = new int[10];
	public Chip[] gameBoard = new Chip[100];
	public int counter;

	public MyPlayer(){

	}


	public int move(Chip[] chip) {

		gameBoard = chip;
		countCols();
		countRows();
		System.out.println("");


		//basic "make the L pattern"
		if(column[0]==row[9]) {
			if(gameBoard[18].isAlive) {
				return (18);
			} else {
				return(10-column[0]);
			}
		}

		//deal w 2x? rectangles
		if(column[2] == 0 && column[1]!=0) {
			if (column[0] == column[1]) {
				return (20-column[1]);
			} else if (column[0] > column[1]+1) {
				return (8-column[1]);
			} else {
				return (10-column[0]);
			}
		}

		if(row[7] == 0 && row[8]!=0) {
			if (row[9] == row[8]) {
				return (((row[8]-1)*10)+8);
			} else if (row[9] > row[8]+1) {
				return (((1+row[8])*10)+9);
			} else {
				return (((row[9]-1)*10)+9);
			}
		}

		if(column[0]==column[1]+1 && column[2]!=0){
			return(29);
		}

		return(9);
	}

	public void countCols() {
		System.out.print("Column counter:");
		for (int i = 0; i<10; i++) {
			for (int a=0; a<10; a++) {
				if(gameBoard[i*10+a].isAlive) {
					counter++;
				}
			}
			column[i]=counter;
			System.out.print(""+column[i]+" ");
			counter = 0;
		}
		System.out.println("");
	}

	public void countRows() {
		System.out.println("Row counter:");
		for(int y=0; y<10; y++){
			row[y]=0;
			for(int x=0; x<10; x++){
				if(gameBoard[(x*10)+y].isAlive){
					counter++;
				}
			}
			row[y]=counter;
			System.out.print(""+column[y]+" ");
			counter=0;
		}
	}

	public ArrayList<Array> oneMoveBoards(int[] col){
		ArrayList oneMoveBoards = new ArrayList<Array>();

		for(int a=col[0]; a>=0; a--){
			if(col.length>1){
				for(int b=a; b>=0; b--){
					if(col.length>2){
						for(int c=b; c>=0; c--){
							int[] addC = new int[3];
							addC[0] = a; addC[1] = b; addC[2] = c;
							oneMoveBoards.add(addC);
						}
					}
//					int[] addB = new int[2];
//					addB[0] = a; addB[1] = b;
//					oneMoveBoards.add(addB);
				}
			}
//			int[] addA = new int[1];
//			addA[0] = a;
//			oneMoveBoards.add(addA);
		}

		return oneMoveBoards;
	}
}//class


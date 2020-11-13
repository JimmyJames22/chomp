import java.io.*;

public class ChunBoard implements java.io.Serializable
{
	int[] col= new int[10];
	boolean winner;
	int move;


	public ChunBoard()
	{
		for(int v=0;v<col.length;v++)
		{
			col[v]=0;
		}

	}


	public ChunBoard(int a,int b,int c,int d, int e,int f, int g, int h, int i, int j)
	{
		col[0]=a;
		col[1]=b;
		col[2]=c;
		col[3]=d;
		col[4]=e;
		col[5]=f;
		col[6]=g;
		col[7]=h;
		col[8]=i;
		col[9]=j;

		winner=true;
		move=999;

	}

	public void printBoard()
	{
		for(int i=0;i<col.length;i++)
		{
			System.out.print(col[i]+" ");

		}
		System.out.println(winner+"  "+ move);

	}



}
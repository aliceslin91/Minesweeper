package com.example.minesweeper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class GameActivity extends Activity {

	private static int ROW = 9;
	private static int COL = 9;
	
	private int mines = 10;
	
	int total;
	
	int time = 0;
	
	boolean inUse[][] = new boolean[COL][ROW];
	boolean isFlagged[][] = new boolean[COL][ROW];
	int surrounding[][] = new int[COL][ROW];
	
	boolean isHappy = true;
	boolean gameOver = false;

	int count = 0;
	
	Timer timer = new Timer();
	


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		total = ROW * COL - mines;
		System.out.print(total);
		FillGame();
		PrintBoard();

		timer.schedule(new GameTimerTask(),0,1000);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

    class GameTimerTask extends TimerTask{
		@Override
		public void run() {
			time++;
		}    	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	
	public void setTime()
	{
		
	}
	
    public void FillGame()
    {
        FillBoard();
        FillSurround();
    }
	
	public void FillBoard()
	{
		int i = 0;
		int colRand, rowRand;
		Random rand = new Random();
		
		while (i<mines)
		{
			colRand = rand.nextInt(COL);
			rowRand = rand.nextInt(ROW);
			
			if (!inUse[colRand][rowRand])
				inUse[colRand][rowRand] = true;
			else
				continue;
			
			i++;			
		}
		
	}

	public int CheckSurround(int r, int c)
	{
		int count = 0;
		
		if (r - 1 >= 0)
		{
			if (inUse[c][r-1])
				count ++;
			
			if (c - 1 >= 0)
				if (inUse[c-1][r-1])
					count++;
			if (c+1 < COL)
				if (inUse[c+1][r-1])
					count++;
		}
		
		if (r + 1 < ROW)
        {
            if (inUse[c][r + 1])
                count++;

            if (c - 1 >= 0)
                if (inUse[c - 1][r + 1])
                    count++;
            if (c + 1 < COL)
                if (inUse[c + 1][r + 1])
                    count++;
        }

        if (c - 1 >= 0)
            if (inUse[c - 1][r])
                count++;

        if (c + 1 < COL)
            if (inUse[c + 1][r])
                count++;

        return count;		
	}
	
    public void FillSurround()
    {

        for (int i = 0; i < COL; i++)
        {
            for (int j = 0; j < ROW; j++)
            {
                surrounding[i][j] = CheckSurround(j, i);
            }
        }
    }
    
    public void PrintBoard()
    {
        for (int i = 0; i < COL; i++)
        {
            for (int j = 0; j < ROW; j++)
            {
                System.out.print(surrounding[j][i] + " ");
            }
            System.out.println();
        }


        int test;
        System.out.println();

        for (int i = 0; i < COL; i++)
        {
            for (int j = 0; j < ROW; j++)
            {
                if (inUse[j][i])
                    test = 1;
                else
                    test = 0;
                System.out.print(test + " ");
            }
            System.out.println();
        }
    }
}

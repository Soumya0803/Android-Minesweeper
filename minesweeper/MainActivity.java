package com.example.soumya.minesweeper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import static android.R.attr.y;


public class MainActivity extends AppCompatActivity  implements View.OnClickListener,View.OnLongClickListener{


    MyButton buttons[][];
    LinearLayout rows[];
    LinearLayout mainLayout;
    int n = 10;
    boolean gameOver;
    int CountMines;
    int NoOfMines=10;
    String userName;
   // boolean isClicked=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.activity_main);


        Intent i2 = getIntent();
        userName = i2.getStringExtra("user_name");
        Toast.makeText(this,"Name " + userName,Toast.LENGTH_SHORT).show();
        setUpBoard();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
   public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.newGame){


                n = 10;
                setUpBoard();



        }
        else if(id == R.id.size10){
            n =10;
            setUpBoard();
        }else if(id == R.id.size11){
            n = 11;
            setUpBoard();
        }else if(id == R.id.size12){
            n = 12;
            setUpBoard();
        }
        return true;
    }







    public void setUpBoard() {

        buttons = new MyButton[n][n];
        rows = new LinearLayout[n];
        gameOver = false;
        mainLayout.removeAllViews();
        CountMines = 0;


        //row layout
        for (int i = 0; i < n; i++) {
            rows[i] = new LinearLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1);
            params.setMargins(1, 1, 1, 1);
            rows[i].setLayoutParams(params);
            rows[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout.addView(rows[i]);
        }
        // buttons layout
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new MyButton(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                params.setMargins(1, 1, 1, 1);
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setText("");
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setOnLongClickListener(this);
                //buttons[i][j].setEnabled(true);
                buttons[i][j].x = i;
                buttons[i][j].y = j;
                //buttons[i][j].setText("h");
                // buttons[i][j].setOnClickListener(this);
                rows[i].addView(buttons[i][j]);
            }
        }
        generate();
    }//Placing Mines
public void generate() {
   // int row=0;//minerow
    //int col=0;
    int [] srow={-1,0,1,1,1,0,-1,-1};
    int [] scol={-1,-1,-1,0,1,1,1,0};

    for (int i = 0; i < NoOfMines; i++) {
        Random r = new Random(); //Randomly generate row and col of button where mine is to be placed

        int MineRow = r.nextInt(n);
        int MineCol = r.nextInt(n);
        if (buttons[MineRow][MineCol].Mine_Status == false) {
           // buttons[MineRow][MineCol].symbol = "*";
            CountMines++;
            buttons[MineRow][MineCol].Mine_Status = true;

        } else {
            i--;
        }

        for (int q = 0; q < 8; q++) {
            int x2 = MineRow + srow[q];
            int y2 = MineCol + scol[q];

            if (inrange(x2, y2, n)) {
                {
                    if (buttons[x2][y2].Mine_Status == true) {
                        continue;
                    } else {
                        buttons[x2][y2].Count_Bomb++;
                    }

                }
            }

        }
    }


        /*for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(buttons[i][j].Mine_Status==true)
                {
                  // if(i-1 >=0 && j-1>=0)
                    if(inrange(i-1,j-1,n))
                   {
                       buttons[i-1][j-1].Count_Bomb++;
                   }
                    //if(i-1>=0 && j<n)
                    if(inrange(i-1,j,n))
                    {
                        buttons[i-1][j].Count_Bomb++;
                    }
                  //  if(i-1 >=0 && j+1<n)
                    if(inrange(i-1,j+1,n))
                    {
                        buttons[i-1][j+1].Count_Bomb++;
                    }
                    //if(i<n && j+1<n)
                    if(inrange(i,j+1,n))
                    {
                        buttons[i][j+1].Count_Bomb++;
                    }
                   // if(i+1<n && j+1<n)
                    if(inrange(i+1,j+1,n))
                    {
                        buttons[i+1][j+1].Count_Bomb++;
                    }
                   // if(i+1<n && j<n)
                    if(inrange(i+1,j,n))
                    {
                        buttons[i+1][j].Count_Bomb++;
                    }
                  //  if(i+1 <n && j-1>=0)
                    if(inrange(i+1,j-1,n))
                    {
                        buttons[i+1][j-1].Count_Bomb++;
                    }
                   // if(i<n && j-1>=0)
                    if(inrange(i,j-1,n))
                    {
                        buttons[i][j-1].Count_Bomb++;
                    }
                }

            */}






    @Override
    public void onClick(View v) {

          MyButton b = (MyButton) v;
         // b.setEnabled(true);

          if (gameOver) {
              return;
          }

          if(b.isClicked) {
              Toast.makeText(this, "Invalid move!",Toast.LENGTH_SHORT ).show();
              return;
          }
          b.isClicked=true;
          if(b.Mine_Status) {
              showallmines();
             b.setText("*");
              gameOver=true;
              Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
              return;

          }
     int status =gamestatus();
          if(status==1)
          {
              Toast.makeText(this,"You Win!",Toast.LENGTH_SHORT);
              gameOver=true;
              return;
          }

          if (b.Count_Bomb!=0)
          {
              b.setText(""+b.Count_Bomb);
              b.isClicked=true;
          }
          //Recurssive function for 0 mines
          if(b.Count_Bomb==0)
          {

            disptile(b);
          }

      }

    private void showallmines() {
        for(int i=0; i<n ; i++)
        {
            for(int j=0; j<n ;j++)
            {
                if(buttons[i][j].Mine_Status==true)
                    buttons[i][j].setText("*");
                else
                    buttons[i][j].setText(buttons[i][j].Count_Bomb+ "");
            }

        }
    }

    private boolean inrange(int x, int y, int n)
    {
        if(x>=0 && x<n && y>=0 && y<n)
        return true;
        else


         return false;
    }

  private void disptile(MyButton p) {
        int i=p.x;
        int j=p.y;
        int[]  steprow={-1,0,1,1,1,0,-1,-1};
        int[]  stepcol={-1,-1,-1,0,1,1,1,0};
       p.setText("0");
        for(int q=0; q<8; q++)
        {
            int x2=i+steprow[q];
            int y2=j+stepcol[q];
            if (inrange(x2,y2,n))
            {
                if(buttons[x2][y2].isFlag==true)
                {
                    return;
                }
               else if(buttons[x2][y2].isClicked==false && buttons[x2][y2].Count_Bomb!=0)
                {
                    buttons[x2][y2].setText(buttons[x2][y2].Count_Bomb+"");
                    buttons[x2][y2].isClicked=true;
                   // return;
                    continue;
                }
                else if(buttons[x2][y2].isClicked==false && buttons[x2][y2].Count_Bomb==0)
                {
                    buttons[x2][y2].setText(buttons[x2][y2].Count_Bomb+"");
                    buttons[x2][y2].isClicked=true;
                    disptile(buttons[x2][y2]);
                }


                /*if(buttons[x2][y2].Mine_Status==true ||buttons[x2][y2].isClicked==true)
                {
                    return;
                }
                else if(buttons[x2][y2].Mine_Status==true || buttons[x2][y2].isClicked==false)

                {
                   // buttons[x2][y2].setText(""+p.Count_Bomb);
                    buttons[x2][y2].isClicked=true;
                    disptile(buttons[x2][y2]);
                }
                else
                {
                    buttons[x2][y2].setText(buttons[x2][y2].Count_Bomb+"");
                    p.isClicked=true;
                    continue;
                }*/
            }


        }



    }

   // private void disptile(MyButton p) {
       /* if (x < 0 || y < 0 || x >= n || y >= n) {
            return;
        }
        if (buttons[x][y].Count_Bomb != 0) {
            return;

        }
        if(buttons[x][y].isClicked==true || buttons[x][y].isFlag==true)
        {
            return;

        }
        buttons[x][y].isClicked=true;
        buttons[x][y].setText(""+buttons[x][y].Count_Bomb);
        disptile(x+1,y);
        disptile(x-1,y);
        disptile(x,y+11);
        disptile(x,y-1);
*/




    //private checkgamestatus


    private int gamestatus() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (buttons[i][j].Mine_Status == false) {
                    if (buttons[i][j].isClicked == false) {
                        return 0;
                    }
                }

            }

        }
        return 1;
//Flag placing


}

    @Override
    public boolean onLongClick(View v) {
        MyButton b1=(MyButton)  v;
        if (b1.isClicked==false) {
            if(gameOver==true){
                return false;
            }
            if (b1.isFlag == false)

            {
                b1.setText("F");
                b1.isFlag = false;

            } else {
                b1.setText("");
                b1.isFlag = false;
            }

        }
        else
        {
            Toast.makeText(this,"Invalid Move",Toast.LENGTH_SHORT).show();

        }

        return true;
    }
}
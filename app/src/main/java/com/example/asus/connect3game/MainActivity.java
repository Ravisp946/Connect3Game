package com.example.asus.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] gamestate={2,2,2,2,2,2,2,2,2};


    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{0,4,8}};

    boolean activestate=true;
    int activeplayer=0;
    public void dropIn(View view)
    {
        ImageView counter=(ImageView)view;

        Log.i("INFO",counter.getTag().toString());

        int tag=Integer.parseInt(counter.getTag().toString());

        counter.setTranslationY(-1500);
        if(gamestate[tag]==2 && activestate) {
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                gamestate[tag] = 0;
                activeplayer = 1;
            } else

            {
                counter.setImageResource(R.drawable.red);
                gamestate[tag] = 1;

                activeplayer = 0;

            }
            counter.animate().translationYBy(1500).setDuration(300);
            for (int[] winningpostion : winningpositions) {
                if (gamestate[winningpostion[0]] == gamestate[winningpostion[1]] && gamestate[winningpostion[1]] == gamestate[winningpostion[2]] && gamestate[winningpostion[0]] != 2)
                {
                    //Someone has won
                    String winner;
                    if (activeplayer == 1)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    Toast.makeText(this, winner + " has won!!", Toast.LENGTH_SHORT).show();
                    activestate = false;

                    Button button=(Button)findViewById(R.id.button);
                    TextView textView=(TextView)findViewById(R.id.textView);


                    textView.setText(winner+" has won!!");
                    textView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    public void playagain(View view)
    {
        Button button=(Button)findViewById(R.id.button);
        TextView textView=(TextView)findViewById(R.id.textView);


//        textView.setText(winner+"has won");
        textView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        activestate=true;
        activeplayer=0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

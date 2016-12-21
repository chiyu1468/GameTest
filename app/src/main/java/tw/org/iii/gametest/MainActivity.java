package tw.org.iii.gametest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
    }

    GameCore GC;
    public void start(View v){
        GC = new GameCore();
        GC.GO();
        GC.addPlayer("Orange");
        GC.addPlayer("Blue");
        GC.GO();
        GC.TrailRun();
    }

    int firstBtn = 0;
    int firstBtnId;
    String[] players = {"null","Orange","Blue"};
    int player = 1;

    public void ChessClick(View v) {
        // 所有按鍵統一呼叫這個方法
        // 然後在直接用 getId 區別按鍵
        if(firstBtn == 0){
            Log.v("chiyu","First Click");
            firstBtn = Integer.parseInt(v.getTag().toString());
            firstBtnId = v.getId();
        } else {
            Log.v("chiyu","Second Click");
            int x = Integer.parseInt(v.getTag().toString());
            if(firstBtn < 100) {Log.v("chiyu","Move Piece : " + GC.playerMove(players[player],firstBtn,x));}
            else if(firstBtn - 100 <100) {
                Log.v("chiyu","Orange New Piece : " + GC.playerMove("Orange",0,x,(byte)(firstBtn-100)));}
            else if(firstBtn - 200 <100) {
                Log.v("chiyu","Blue New Piece : " + GC.playerMove("Blue",0,x,(byte)(firstBtn-200)));}

            if(firstBtn < 100) updateBtnShow(firstBtnId,firstBtn);
            updateBtnShow(v.getId(),Integer.parseInt(v.getTag().toString()));
            firstBtn = 0;

        }


    }

    void updateBtnShow(int BtnId, int tag){
        Log.v("chiyu","updateBtnShow " + tag);
        Button button = (Button) findViewById(BtnId);
        if(GC.gameCheckerBoard.getFaction(tag) == 2) button.setBackgroundColor(Color.BLUE);
        if(GC.gameCheckerBoard.getFaction(tag) == 1) button.setBackgroundColor(Color.YELLOW);
        if(GC.gameCheckerBoard.getFaction(tag) == 0) button.setBackgroundColor(Color.GRAY);
        //button.setText("" + GC.gameCheckerBoard.getSize(tag));
        switch (GC.gameCheckerBoard.getSize(tag)) {
            case 0:
                button.setText("");
                break;
            case 1:
                button.setText("Small");
                break;
            case 2:
                button.setText("Middle");
                break;
            case 3:
                button.setText("Big");
                break;
        }
    }

    public void OrangeClick(View v){
        Log.v("chiyu","First Click Orange");
        final int Orange = 100;
        firstBtn = Integer.parseInt(v.getTag().toString()) + Orange;
    }

    public void BlueClick(View v) {
        Log.v("chiyu","First Click Blue");
        final int Blue = 200;
        firstBtn = Integer.parseInt(v.getTag().toString()) + Blue;
    }

    public void test1(View v){
        GC.GO();
        GC.TrailRun2();
        tv.setText(GC.message);
    }

    public void test2(View v){
        GC.TrailRun3();
    }





}

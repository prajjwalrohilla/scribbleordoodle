package drawingfun.drawingfun;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import static drawingfun.drawingfun.MainActivity.colo;
import static drawingfun.drawingfun.MainActivity.i;
import static drawingfun.drawingfun.MainActivity.prog;

/**
 * Created by lenovo on 03-05-2017.
 */
public class Pop extends Activity {


    ImageButton button5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .8));


        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar1);
        seekBar.setProgress(prog);
        button5 = (ImageButton) findViewById(R.id.neww);
        button5.setBackgroundColor(Color.parseColor(colo));
        android.view.ViewGroup.LayoutParams params = button5.getLayoutParams();
        params.height = prog;
        params.width = prog;
        button5.setLayoutParams(params);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override

            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {


            }

            @Override

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override

            public void onStopTrackingTouch(SeekBar seekBar) {
                prog = seekBar.getProgress();
                android.view.ViewGroup.LayoutParams params = button5.getLayoutParams();
                params.height = prog;
                params.width = prog;
                button5.setLayoutParams(params);




            }

        });



    }

    public void paintClicked(View v) {
        button5 = (ImageButton) findViewById(R.id.neww);

        colo = v.getTag().toString();
        button5.setBackgroundColor(Color.parseColor(colo));

        if (i == 1) {
            colo = "#FFFFFFFF";


            button5.setBackgroundColor(Color.parseColor(colo));
        }


    }




}

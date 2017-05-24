package drawingfun.drawingfun;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

//implements View.OnClickListener
public class MainActivity extends AppCompatActivity  {
    static int prog=3;
    static String colo="#000000";
    static  boolean erase=false;
    static int i=0;
    RelativeLayout relativeLayout;
    Paint paint,paint1;
    View view,view1;
    Path path2,path1;
    Bitmap bitmap;
    Canvas canvas;

    ImageButton button,button2 ,button3, button4;


    ArrayList<Path> paths = new ArrayList<Path>();
    ArrayList<Paint> paintt = new ArrayList<Paint>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout1);

         button = (ImageButton)findViewById(R.id.clear);
         button2 = (ImageButton)findViewById(R.id.erase_btn);
         button3 = (ImageButton)findViewById(R.id.drawb);
        button4 = (ImageButton)findViewById(R.id.save_btn);
        view = new SketchSheetView(MainActivity.this);

        paint = new Paint();

        path2 = new Path();
        paint1=new Paint();
        relativeLayout.addView(view,  new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));

        paint.setAntiAlias(true);

        paint.setDither(true);

        paint.setColor(Color.parseColor("#000000"));

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeJoin(Paint.Join.ROUND);

        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setStrokeWidth(2);
        paths.add(path2);
        paintt.add(paint);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view = new SketchSheetView(MainActivity.this);
                relativeLayout.addView(view,  new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT));
                for (int i = 0; i < paths.size(); i++) {
                    Path patho = paths.get(i);
                    patho.reset();

                }


            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                erase=true;
                i=1;
                startActivity(new Intent(MainActivity.this,Pop.class));


                paint1 = new Paint();
                paint1.setAntiAlias(true);
                paint1.setDither(true);
               // paint1.setColor(Color.WHITE);
                paint1.setStyle(Paint.Style.STROKE);

                paint1.setStrokeJoin(Paint.Join.ROUND);

                paint1.setStrokeCap(Paint.Cap.ROUND);

                paint1.setStrokeWidth(prog);
                path2=new Path();
                paths.add(path2);
                paintt.add(paint1);


            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

        i=0;

                startActivity(new Intent(MainActivity.this,Pop.class));


                erase=false;

                paint1=new Paint();

                paint1.setAntiAlias(true);

                paint1.setDither(true);

                paint1.setColor(Color.BLACK);

                paint1.setStyle(Paint.Style.STROKE);

                paint1.setStrokeJoin(Paint.Join.ROUND);

                paint1.setStrokeCap(Paint.Cap.ROUND);

                paint1.setStrokeWidth(prog);
                path2=new Path();
                paths.add(path2);
                paintt.add(paint1);

            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                captureScreen(view);
                Toast.makeText(MainActivity.this,
                        "SAVED", Toast.LENGTH_LONG).show();
            }
        });


    }




    private void captureScreen(View v) {
       // View v = getWindow().getDecorView().getRootView();
        //View v =findViewById(R.id.view1);
        v.setDrawingCacheEnabled(true);
        bitmap= Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        try {
            FileOutputStream fos = new FileOutputStream(new File(Environment
                    .getExternalStorageDirectory().toString(), "SCREEN"
                    + System.currentTimeMillis() + ".png"));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class SketchSheetView extends View {

        public SketchSheetView(Context context) {

            super(context);

            bitmap = Bitmap.createBitmap(820, 480, Bitmap.Config.ARGB_4444);

            canvas = new Canvas(bitmap);

            this.setBackgroundColor(Color.WHITE);
        }




        protected void onDraw( Canvas canvas2) {

            paint1.setStrokeWidth(prog);
            paint1.setColor(Color.parseColor(colo));
            if(erase==true)
            {
                colo="#FFFFFFFF";
                paint1.setColor(Color.parseColor(colo));
            }
            for (int i = 0; i < paths.size(); i++) {
                Path patho = paths.get(i);
                Paint painto = paintt.get(i);
                canvas2.drawPath(patho, painto);
            }
        }





        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    path2.moveTo(eventX, eventY);

                    return true;

                case MotionEvent.ACTION_MOVE:

                    path2.lineTo(eventX, eventY);

                    break;

                case MotionEvent.ACTION_UP:
                    // nothing to do



                    break;

                default:
                    return false;
            }

            // Schedules a repaint.
            invalidate();
            return true;
        }




    }

}


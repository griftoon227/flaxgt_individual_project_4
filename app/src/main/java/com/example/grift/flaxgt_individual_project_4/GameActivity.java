package com.example.grift.flaxgt_individual_project_4;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.grift.flaxgt_individual_project_4.GameArrays.*;
import static com.example.grift.flaxgt_individual_project_4.LevelType.*;

import com.example.grift.flaxgt_individual_project_4.LevelType;

public class GameActivity extends AppCompatActivity {
    //bind the needed views
    @BindView(R.id.game_image) ImageView gameMap;
    @BindView(R.id.character) ImageView character;
    @BindView(R.id.Btn1) Button btn1;
    @BindView(R.id.Btn2) Button btn2;
    @BindView(R.id.Btn3) Button btn3;
    @BindView(R.id.Btn4) Button btn4;
    @BindView(R.id.Btn5) Button btn5;
    @BindView(R.id.Btn6) Button btn6;

    //extras sent over from the levels screen intent with default values
    LevelType levelType = DEFAULT;
    String childUsername = "";

    //declare listeners for dragging event
    MyStartDraggingListener myStartDraggingListener;
    MyEndDragListener myEndDragListener;

    //winning path
    int[] winningGamePath;
    //where to translate to for this particular board(values 0-1)
    double[] whereToTranslate;

    //Declare MediaPlayer object
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //bind targets to this activity using butter knife api
        ButterKnife.bind(this);

        //Instantiate my MediaPlayer, then start the media, and loop it
        mediaPlayer = MediaPlayer.create(this, R.raw.game_audio);
        startMusic();

        /*grab the intent sent from the levels screen, store extras in a bundle, and if it is not empty,
        set the extras values*/
        Intent intent = getIntent();
        Bundle extrasBundle = intent.getExtras();
        if(!(extrasBundle == null || extrasBundle.isEmpty())) {
            levelType = (LevelType) extrasBundle.getSerializable("levelType");
            childUsername = extrasBundle.getString("childUsername");
        }

        //set the game map depending on the level type
        if(levelType != null) {
            if (levelType.equals(easy_1))
                gameMap.setImageResource(R.drawable.map_easy_1);
            else if (levelType.equals(easy_2))
                gameMap.setImageResource(R.drawable.map_easy_2);
            else if (levelType.equals(easy_3))
                gameMap.setImageResource(R.drawable.map_easy_3);
            else if (levelType.equals(medium_1))
                gameMap.setImageResource(R.drawable.map_medium_1);
            else if (levelType.equals(medium_2))
                gameMap.setImageResource(R.drawable.map_medium_2);
            else if (levelType.equals(medium_3))
                gameMap.setImageResource(R.drawable.map_medium_3);
            else if (levelType.equals(hard_1))
                gameMap.setImageResource(R.drawable.map_hard_1);
            else if (levelType.equals(hard_2))
                gameMap.setImageResource(R.drawable.map_hard_2);
            else if (levelType.equals(hard_3))
                gameMap.setImageResource(R.drawable.map_hard_3);
            else if (levelType.equals(brutal_1))
                gameMap.setImageResource(R.drawable.map_brutal_1);
            else if (levelType.equals(brutal_2))
                gameMap.setImageResource(R.drawable.map_brutal_2);
            else if (levelType.equals(brutal_3))
                gameMap.setImageResource(R.drawable.map_brutal_3);
        }

        //determine the level type and have the appropriate # of boxes to accommodate
        if(levelType != null) {
            if(levelType.toString().contains("easy")) {
                btn4.setVisibility(View.INVISIBLE);
                btn5.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
            }
            else if(levelType.toString().contains("medium")){
                btn5.setVisibility(View.INVISIBLE);
                btn6.setVisibility(View.INVISIBLE);
            }
            else if(levelType.toString().contains("hard")){
                btn6.setVisibility(View.INVISIBLE);
            }
        }

        //instantiate our listeners
        myStartDraggingListener = new MyStartDraggingListener();
        myEndDragListener = new MyEndDragListener();

        //set the listeners
        findViewById(R.id.upBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.downBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.leftBtn).setOnLongClickListener(myStartDraggingListener);
        findViewById(R.id.rightBtn).setOnLongClickListener(myStartDraggingListener);

        btn1.setOnDragListener(myEndDragListener);
        btn2.setOnDragListener(myEndDragListener);
        btn3.setOnDragListener(myEndDragListener);
        btn4.setOnDragListener(myEndDragListener);
        btn5.setOnDragListener(myEndDragListener);
        btn6.setOnDragListener(myEndDragListener);

        //set the winning game path and how to translate
        winningGamePath = getWinningGamePath(levelType.ordinal());
        whereToTranslate = getWhereToTranslate(levelType.ordinal());
    }

    @OnClick(R.id.start_button)
    protected void startGame(View view){
        //flag for if the level is correctly completed
        boolean isCompleted = false;
        //gets the level id
        int levelId = levelType.ordinal();

        //instantiate translate animation class
        Translate translate = new Translate();

        //check if the user properly completed the level depending on its difficulty
        if(levelType.toString().contains("easy"))
        {
            if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePath[0]).getConstantState()) &&
                    (btn2.getBackground().getConstantState() == getDrawable(winningGamePath[1]).getConstantState()) &&
                    (btn3.getBackground().getConstantState() == getDrawable(winningGamePath[2]).getConstantState())){
                isCompleted = true;
            }
        }
        else if(levelType.toString().contains("medium"))
        {
            if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePath[0]).getConstantState()) &&
                    (btn2.getBackground().getConstantState() == getDrawable(winningGamePath[1]).getConstantState()) &&
                    (btn3.getBackground().getConstantState() == getDrawable(winningGamePath[2]).getConstantState()) &&
                    (btn4.getBackground().getConstantState() == getDrawable(winningGamePath[3]).getConstantState())){
                isCompleted = true;
            }
        }
        else if(levelType.toString().contains("hard"))
        {
            if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePath[0]).getConstantState()) &&
                    (btn2.getBackground().getConstantState() == getDrawable(winningGamePath[1]).getConstantState()) &&
                    (btn3.getBackground().getConstantState() == getDrawable(winningGamePath[2]).getConstantState()) &&
                    (btn4.getBackground().getConstantState() == getDrawable(winningGamePath[3]).getConstantState()) &&
                    (btn5.getBackground().getConstantState() == (getDrawable(winningGamePath[4]).getConstantState()))){
                isCompleted = true;
            }
        }
        else
        {
            if ((btn1.getBackground().getConstantState() == getDrawable(winningGamePath[0]).getConstantState()) &&
                    (btn2.getBackground().getConstantState() == getDrawable(winningGamePath[1]).getConstantState()) &&
                    (btn3.getBackground().getConstantState() == getDrawable(winningGamePath[2]).getConstantState()) &&
                    (btn4.getBackground().getConstantState() == getDrawable(winningGamePath[3]).getConstantState()) &&
                    (btn5.getBackground().getConstantState() == getDrawable(winningGamePath[4]).getConstantState()) &&
                    (btn6.getBackground().getConstantState() == getDrawable(winningGamePath[5]).getConstantState())){
                isCompleted = true;
            }
        }

        //show the translation animation for the character if the level is correctly completed
        //toast the success and then complete the animation once it is configured
        if(isCompleted) {
            Toast.makeText(getApplicationContext(), getString(R.string.success_text), Toast.LENGTH_SHORT).show();
            for (int i = 0; i < getNumberOfGameMapMoves(levelType.ordinal()); i++) {
                switch (winningGamePath[i]) {
                    case R.drawable.ic_arrow_left_black_24dp:
                    case R.drawable.ic_arrow_right_black_24dp:
                        translate.translateLeftOrRight(whereToTranslate[i]);
                        break;
                    case R.drawable.ic_arrow_upward_black_24dp:
                    case R.drawable.ic_arrow_downward_black_24dp:
                        translate.translateUpOrDown(whereToTranslate[i]);
                        break;
                }
            }
            translate.beginAnimation();

            //update the child's log file to level complete
            SharedPreferences sharedPreferences = getSharedPreferences(childUsername+"_log_file", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean(levelType.toString(), true);

            editor.apply();
        }
        //if not complete, tell the user their pattern was incorrect
        else
            Toast.makeText(getApplicationContext(), getString(R.string.failure_text), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.go_back_button)
    //stop the music & go back to the previous page
    protected void goToPreviousPage(View view){
        //start the previous screen, but first get rid of the sound and release its resources
        stopMusic();
        Intent intent = new Intent(GameActivity.this, LevelsScreenActivity.class);
        intent.putExtra("childUsername", childUsername);
        startActivity(intent);
    }

    //anonymous class to listen for grabs of the arrows
    private class MyStartDraggingListener implements View.OnLongClickListener{

        //start dragging the arrow view with a shadow trailing behind it
        @Override
        public boolean onLongClick(View view) {
            WithDragShadow shadow = new WithDragShadow(view);
            view.startDrag(null,shadow,view,0);
            return false;
        }
    }

    //anonymous class to listen for the end of a drag
    private class MyEndDragListener implements View.OnDragListener{

        //change the background of the spot the object is dragged onto to the background of the object
        //being dragged
        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {
            if(dragEvent.getAction()==DragEvent.ACTION_DROP)
                view.setBackground(((Button)dragEvent.getLocalState()).getBackground());
            return true;
        }
    }

    //anonymous class defining the shadow for the drag
    private class WithDragShadow extends View.DragShadowBuilder{
        private WithDragShadow(View v){
            super(v);
        }

        @Override
        public void onDrawShadow(Canvas canvas){
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }

    //anonymous class that assists in the creation of dynamic game animations
    private class Translate{
        ArrayList<ObjectAnimator> set;
        private long startOffset = 0;

        private Translate(){
            set = new ArrayList<>();
        }

        //calculates the spot to translate to, and creates that animation, left or right only
        private void translateLeftOrRight(double howMuchToTranslate){
            float toX = ((gameMap.getWidth()-50)*(float)howMuchToTranslate);
            ObjectAnimator animation = ObjectAnimator.ofFloat(character, "translationX", toX);
            animation.setRepeatCount(0);
            animation.setDuration(1000);
            animation.setStartDelay(startOffset);
            set.add(animation);
            startOffset += animation.getDuration();
        }

        //calculates the spot to translate to, and creates that animation, up or down only
        private void translateUpOrDown(double howMuchToTranslate){
            float toY = ((gameMap.getHeight()-50)*(float)howMuchToTranslate);
            ObjectAnimator animation = ObjectAnimator.ofFloat(character, "translationY", toY);
            animation.setRepeatCount(0);
            animation.setDuration(2000);
            animation.setStartDelay(startOffset);
            set.add(animation);
            startOffset += animation.getDuration();
        }

        //begins the animation by creating a set of animations and starting them
        private void beginAnimation(){
            AnimatorSet animatorSet = new AnimatorSet();
            if(levelType.toString().startsWith("easy"))
                animatorSet.playSequentially(set.get(0), set.get(1), set.get(2));
            else if(levelType.toString().startsWith("medium"))
                animatorSet.playSequentially(set.get(0), set.get(1), set.get(2), set.get(3));
            else if(levelType.toString().startsWith("hard"))
                animatorSet.playSequentially(set.get(0), set.get(1), set.get(2), set.get(3), set.get(4));
            else if(levelType.toString().startsWith("brutal"))
                animatorSet.playSequentially(set.get(0), set.get(1), set.get(2), set.get(3), set.get(4), set.get(5));
            //when the animation ends, go back to the previous screen
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    //go back to levels screen on success and stop the music
                    stopMusic();
                    Intent intent = new Intent(GameActivity.this, LevelsScreenActivity.class);
                    intent.putExtra("childUsername", childUsername);
                    startActivity(intent);
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            animatorSet.start();
        }
    }

    //function to start the game activity's music
    protected void startMusic(){
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }

    //function to stop the game activity's music
    protected void stopMusic(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }
}


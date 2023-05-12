package com.jilsfdivbvetwo.jlsat167.snake;

import android.graphics.Point;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.LogUtils;
import com.jilsfdivbvetwo.jlsat167.R;

public class GameActivity extends AppCompatActivity implements View.OnTouchListener {

  private ChickenPanelView mChickenPanelView;
  private TextView mTvScores;

  private GestureDetector mGestureDetector;


  /** 获取屏幕坐标点 **/
  Point startPoint;// 起始点
  Point endPoint;// 终点
  /** 记录按下的坐标点（起始点）**/
  private float mPosX = 0;
  private float mPosY = 0;
  /** 记录移动后抬起坐标点（终点）**/
  private float mCurPosX = 0;
  private float mCurPosY = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);

    mGestureDetector = new GestureDetector(this, new MySimpleOnGestureListener());

    mChickenPanelView = findViewById(R.id.snake_view);
    mTvScores = findViewById(R.id.snake_tv_scores);
    mChickenPanelView.setOnTouchListener(this);

    mChickenPanelView.setOnScoresChangedListener(new ChickenPanelView.OnScoresChangedListener() {
      @Override
      public void toastRestartGame(int scores) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            mTvScores.setText("What a pity! Your scores is : " + scores + " !");
          }
        });

      }

      @Override
      public void scoresChanged(int scores) {
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            mTvScores.setText("scores : " + scores);
          }
        });
      }
    });
  }


//        mChickenPanelView.setSnakeDirection(GameType.LEFT);


  @Override
  public boolean onTouch(View v, MotionEvent event) {
    return mGestureDetector.onTouchEvent(event);
  }


  private class MySimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {

    //用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
    public boolean onDown(MotionEvent e) {
      mChickenPanelView.reStartGame();
      return true;
    }

    /*
     * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
     * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
     *
     * 而onDown也是由一个MotionEventACTION_DOWN触发的，但是他没有任何限制，
     * 也就是说当用户点击的时候，首先MotionEventACTION_DOWN，onDown就会执行，
     * 如果在按下的瞬间没有松开或者是拖动的时候onShowPress就会执行，如果是按下的时间超过瞬间
     * （这块我也不太清楚瞬间的时间差是多少，一般情况下都会执行onShowPress），拖动了，就不执行onShowPress。
     */
    public void onShowPress(MotionEvent e) {
      LogUtils.dTag("MyGesture", "onShowPress");
    }

    // 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
    ///轻击一下屏幕，立刻抬起来，才会有这个触发
    //从名子也可以看出,一次单独的轻击抬起操作,当然,如果除了Down以外还有其它操作,那就不再算是Single操作了,所以这个事件 就不再响应
    public boolean onSingleTapUp(MotionEvent e) {
      LogUtils.dTag("MyGesture", "onSingleTapUp");
      return true;
    }

    // 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
      LogUtils.dTag("MyGesture", "onScroll:" + (e2.getX() - e1.getX()) + "   " + distanceX);

      return true;
    }

    // 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
    public void onLongPress(MotionEvent e) {
      LogUtils.dTag("MyGesture", "onLongPress");
    }

    // 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE, 1个ACTION_UP触发
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
      LogUtils.dTag(getLocalClassName(), "onFling-" + "Fling");
      //手指在触摸屏上迅速移动，并松开的动作。
      int mini_width = 50;
      int mini_speed = 0;
      float distance_right = motionEvent1.getX() - motionEvent.getX();
      float distance_left = motionEvent.getX() - motionEvent1.getX();
      float distance_down = motionEvent1.getY() - motionEvent.getY();
      float distance_up = motionEvent.getY() - motionEvent1.getY();

      //先判度上下 或者 左右
      if(Math.abs(distance_left) > Math.abs(distance_up)){
        if (distance_right > mini_width && Math.abs(v) > mini_speed) {
          LogUtils.dTag(getLocalClassName(), "onFling-" + "right");
          mChickenPanelView.setSnakeDirection(SnakeGameType.SNAKE_RIGHT);
        } else if (distance_left > mini_width && Math.abs(v) > mini_speed) {
          LogUtils.dTag(getLocalClassName(), "onFling-" + "left");
          mChickenPanelView.setSnakeDirection(SnakeGameType.SNAKE_LEFT);
        }
      }else {
        if (distance_down > mini_width && Math.abs(v) > mini_speed) {
          LogUtils.dTag(getLocalClassName(), "onFling-" + "down");
          mChickenPanelView.setSnakeDirection(SnakeGameType.SNAKE_BOTTOM);
        } else if (distance_up > mini_width && Math.abs(v) > mini_speed) {
          LogUtils.dTag(getLocalClassName(), "onFling-" + "up");
          mChickenPanelView.setSnakeDirection(SnakeGameType.SNAKE_TOP);
        }
      }

      return true;
    }

  }

}

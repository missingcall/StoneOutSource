package com.jilsfdivbvetwo.jlsat167.snake;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ScreenUtils;
import com.jilsfdivbvetwo.jlsat167.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zhangman on 2018/1/8 14:43.
 * Email: zhangman523@126.com
 */

public class ChickenPanelView extends View {
    private final static String TAG = ChickenPanelView.class.getSimpleName();
    public static boolean DEBUG = true;

    private List<List<SnakeGridSquare>> mGridSquare = new ArrayList<>();
    private List<SnakeGridPosition> mSnakePositions = new ArrayList<>();//蛇方块

    private SnakeGridPosition mSnakeHeader;//蛇头部位置
    private SnakeGridPosition mFoodPosition;//食物的位置
    private int mSnakeLength = 3;
    private long mSpeed = 8;
    private int mSnakeDirection = SnakeGameType.SNAKE_RIGHT;
    private boolean mIsEndGame = false;
    private int mGridSize = 18;
    private Paint mGridPaint = new Paint();
    private Paint mStrokePaint = new Paint();
    private int mRectSize = dp2px(getContext(), 20);
    private int mStartX, mStartY;
    private Bitmap mBitmapFood;

    private Bitmap mSnakeHeaderBm;
    private Bitmap mSnakeBodyBm;

    private int mScores = 0;


    private Rect mRect = new Rect();

    public ChickenPanelView(Context context) {
        this(context, null);
    }

    public ChickenPanelView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChickenPanelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        List<SnakeGridSquare> squares;
        for (int i = 0; i < mGridSize; i++) {
            squares = new ArrayList<>();
            for (int j = 0; j < mGridSize; j++) {
                squares.add(new SnakeGridSquare(SnakeGameType.SNAKE_GRID));
            }
            mGridSquare.add(squares);
        }
        mSnakeHeader = new SnakeGridPosition(10, 10);
        mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
        mFoodPosition = new SnakeGridPosition(0, 0);
        mIsEndGame = true;

        mBitmapFood= BitmapFactory.decodeResource(getContext().getResources(), R.drawable.food);

        mSnakeHeaderBm = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.snakehead);
        mSnakeBodyBm = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.snakebody);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = w / 2 - mGridSize * mRectSize / 2;
//        mStartY = dp2px(getContext(), 40);
        mStartY = h / 2 - mGridSize * mRectSize / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = mStartY * 2 + mGridSize * mRectSize;
//        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), height);
        setMeasuredDimension(ScreenUtils.getScreenWidth() - 20, ScreenUtils.getScreenWidth() - 20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.WHITE);
        //格子画笔
        mGridPaint.reset();
        mGridPaint.setAntiAlias(true);
        mGridPaint.setStyle(Paint.Style.FILL);

        mStrokePaint.reset();
        mStrokePaint.setColor(Color.parseColor("#22000000"));
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);

        for (int i = 0; i < mGridSize; i++) {
            for (int j = 0; j < mGridSize; j++) {
                int left = mStartX + i * mRectSize;
                int top = mStartY + j * mRectSize;
                int right = left + mRectSize;
                int bottom = top + mRectSize;
                canvas.drawRect(left, top, right, bottom, mStrokePaint);
                mGridPaint.setColor(mGridSquare.get(i).get(j).getColor());

                mRect.left = left;
                mRect.right = right;
                mRect.top = top;
                mRect.bottom = bottom;

                //食物用图片填充
                if (mGridSquare.get(i).get(j).getType() == SnakeGameType.SNAKE_FOOD) {
                    canvas.drawBitmap(mBitmapFood, null, mRect, mGridPaint);
                } else if (mGridSquare.get(i).get(j).getType() == SnakeGameType.SNAKE_SNAKE) {
                    //蛇头
                    if (i == mSnakeHeader.getX() && j == mSnakeHeader.getY()) {
                        canvas.drawBitmap(mSnakeHeaderBm, null, mRect, mGridPaint);
                    } else {
                        //蛇身
                        canvas.drawBitmap(mSnakeBodyBm, null, mRect, mGridPaint);
                    }

                } else {
                    //空格
                    canvas.drawRect(left, top, right, bottom, mGridPaint);
                }
            }
        }
    }

    private void refreshFood(SnakeGridPosition foodPosition) {
        mGridSquare.get(foodPosition.getX()).get(foodPosition.getY()).setType(SnakeGameType.SNAKE_FOOD);
    }

    public void setSpeed(long speed) {
        mSpeed = speed;
    }

    public void setGridSize(int gridSize) {
        mGridSize = gridSize;
    }

    public void setSnakeDirection(int snakeDirection) {
        if (mSnakeDirection == SnakeGameType.SNAKE_RIGHT && snakeDirection == SnakeGameType.SNAKE_LEFT) return;
        if (mSnakeDirection == SnakeGameType.SNAKE_LEFT && snakeDirection == SnakeGameType.SNAKE_RIGHT) return;
        if (mSnakeDirection == SnakeGameType.SNAKE_TOP && snakeDirection == SnakeGameType.SNAKE_BOTTOM) return;
        if (mSnakeDirection == SnakeGameType.SNAKE_BOTTOM && snakeDirection == SnakeGameType.SNAKE_TOP) return;
        mSnakeDirection = snakeDirection;
    }

    private class GameMainThread extends Thread {

        @Override
        public void run() {
            while (!mIsEndGame) {
                moveSnake(mSnakeDirection);
                checkCollision();
                refreshGridSquare();
                handleSnakeTail();
                postInvalidate();//重绘界面
                handleSpeed();
            }
        }

        private void handleSpeed() {
            try {
                sleep(1000 / mSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //检测碰撞
    private void checkCollision() {
        //检测是否咬到自己
        SnakeGridPosition headerPosition = mSnakePositions.get(mSnakePositions.size() - 1);
        for (int i = 0; i < mSnakePositions.size() - 2; i++) {
            SnakeGridPosition position = mSnakePositions.get(i);
            if (headerPosition.getX() == position.getX() && headerPosition.getY() == position.getY()) {
                //咬到自己 停止游戏
                mIsEndGame = true;
                showMessageDialog();
                mOnScoresChangedListener.toastRestartGame(mScores);
                mScores = 0;
                return;
            }
        }

        //判断是否吃到食物
        if (headerPosition.getX() == mFoodPosition.getX()
                && headerPosition.getY() == mFoodPosition.getY()) {
            mSnakeLength++;
            mScores += 1;
            mOnScoresChangedListener.scoresChanged(mScores);
            generateFood();
        }
    }

    private void showMessageDialog() {
        post(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(getContext()).setMessage("Game Over!")
                        .setCancelable(false)
                        .setPositiveButton("ReStart", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                reStartGame();
                            }
                        })
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }

    private OnScoresChangedListener mOnScoresChangedListener;

    public interface OnScoresChangedListener {
        void toastRestartGame(int scores);

        void scoresChanged(int scores);
    }

    public void setOnScoresChangedListener(OnScoresChangedListener onScoresChangedListener) {
        mOnScoresChangedListener = onScoresChangedListener;
    }


    public void reStartGame() {
        if (!mIsEndGame) return;
        mOnScoresChangedListener.scoresChanged(0);
        for (List<SnakeGridSquare> squares : mGridSquare) {
            for (SnakeGridSquare square : squares) {
                square.setType(SnakeGameType.SNAKE_GRID);
            }
        }
        if (mSnakeHeader != null) {
            mSnakeHeader.setX(mGridSize / 2);
            mSnakeHeader.setY(mGridSize / 2);
        } else {
            mSnakeHeader = new SnakeGridPosition(mGridSize / 2, mGridSize / 2);//蛇的初始位置
        }
        mSnakePositions.clear();
        mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
        mSnakeLength = 3;//蛇的长度
        mSnakeDirection = SnakeGameType.SNAKE_RIGHT;
        mSpeed = 8;//速度
        if (mFoodPosition != null) {
            mFoodPosition.setX(0);
            mFoodPosition.setY(0);
        } else {
            mFoodPosition = new SnakeGridPosition(0, 0);
        }
        refreshFood(mFoodPosition);
        mIsEndGame = false;
        GameMainThread thread = new GameMainThread();
        thread.start();
    }

    //生成food
    private void generateFood() {
        Random random = new Random();

        int foodStyle = random.nextInt(4);
        switch (foodStyle) {
            case 0:
                mBitmapFood = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.food);
                break;
            case 1:
                mBitmapFood = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.food2);
                break;
            case 2:
                mBitmapFood = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.food3);
                break;
            case 3:
                mBitmapFood = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.food4);
                break;
        }


        int foodX = random.nextInt(mGridSize - 1);
        int foodY = random.nextInt(mGridSize - 1);
        for (int i = 0; i < mSnakePositions.size() - 1; i++) {
            if (foodX == mSnakePositions.get(i).getX() && foodY == mSnakePositions.get(i).getY()) {
                //不能生成在蛇身上
                foodX = random.nextInt(mGridSize - 1);
                foodY = random.nextInt(mGridSize - 1);
                //重新循环
                i = 0;
            }
        }
        mFoodPosition.setX(foodX);
        mFoodPosition.setY(foodY);
        refreshFood(mFoodPosition);
    }

    private void moveSnake(int snakeDirection) {
        switch (snakeDirection) {
            case SnakeGameType.SNAKE_LEFT:
                if (mSnakeHeader.getX() - 1 < 0) {//边界判断：如果到了最左边 让他穿过屏幕到最右边
                    mSnakeHeader.setX(mGridSize - 1);
                } else {
                    mSnakeHeader.setX(mSnakeHeader.getX() - 1);
                }
                mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
                break;
            case SnakeGameType.SNAKE_TOP:
                if (mSnakeHeader.getY() - 1 < 0) {
                    mSnakeHeader.setY(mGridSize - 1);
                } else {
                    mSnakeHeader.setY(mSnakeHeader.getY() - 1);
                }
                mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
                break;
            case SnakeGameType.SNAKE_RIGHT:
                if (mSnakeHeader.getX() + 1 >= mGridSize) {
                    mSnakeHeader.setX(0);
                } else {
                    mSnakeHeader.setX(mSnakeHeader.getX() + 1);
                }
                mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
                break;
            case SnakeGameType.SNAKE_BOTTOM:
                if (mSnakeHeader.getY() + 1 >= mGridSize) {
                    mSnakeHeader.setY(0);
                } else {
                    mSnakeHeader.setY(mSnakeHeader.getY() + 1);
                }
                mSnakePositions.add(new SnakeGridPosition(mSnakeHeader.getX(), mSnakeHeader.getY()));
                break;
        }
    }

    private void refreshGridSquare() {
        for (SnakeGridPosition position : mSnakePositions) {
            mGridSquare.get(position.getX()).get(position.getY()).setType(SnakeGameType.SNAKE_SNAKE);
        }
    }

    private void handleSnakeTail() {
        int snakeLength = mSnakeLength;
        for (int i = mSnakePositions.size() - 1; i >= 0; i--) {
            if (snakeLength > 0) {
                snakeLength--;
            } else {//将超过长度的格子 置为 GameType.GRID
                SnakeGridPosition position = mSnakePositions.get(i);
                mGridSquare.get(position.getX()).get(position.getY()).setType(SnakeGameType.SNAKE_GRID);
            }
        }
        snakeLength = mSnakeLength;
        for (int i = mSnakePositions.size() - 1; i >= 0; i--) {
            if (snakeLength > 0) {
                snakeLength--;
            } else {
                mSnakePositions.remove(i);
            }
        }
    }

    /**
     * dp转px
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }


}

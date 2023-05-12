package com.jilsfdivbvetwo.jlsat167.snake;

import android.graphics.Color;

/**
 * Created by zhangman on 2018/1/8 14:43
 * Email: zhangman523@126.com
 */

public class SnakeGridSquare {
  private int mType;//元素类型

  public SnakeGridSquare(int type) {
    mType = type;
  }

  public int getColor() {
    switch (mType) {
      case SnakeGameType.SNAKE_GRID://空格子
        return Color.TRANSPARENT;
      case SnakeGameType.SNAKE_FOOD://食物
        return Color.BLUE;
      case SnakeGameType.SNAKE_SNAKE://蛇
        return Color.parseColor("#FF4081");
    }
    return Color.WHITE;
  }

  public void setType(int type) {
    mType = type;
  }

  public int getType() {
    return mType;
  }
}

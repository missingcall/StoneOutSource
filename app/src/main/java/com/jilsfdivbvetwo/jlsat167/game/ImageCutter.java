package com.jilsfdivbvetwo.jlsat167.game;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengw on 2017/5/31.
 */

public class ImageCutter {
    /**
     *
     * @param bitmap
     * @param piece 切成piece块
     * @return List<ImagePiece>
     */
    public static List<ImageBeanPic> splitImage(Bitmap bitmap, int piece){
        List<ImageBeanPic> imageBeanPics = new ArrayList<>();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int pieceWidth = Math.min(width, height) / piece;
        for (int i = 0; i < piece; i++) {
            for (int j = 0; j < piece; j++) {
                ImageBeanPic imageBeanPic = new ImageBeanPic();
                imageBeanPic.setIndex(j + i * piece);

                int x = j * pieceWidth;
                int y = i * pieceWidth;
                imageBeanPic.setBitmap(Bitmap.createBitmap(bitmap, x, y, pieceWidth, pieceWidth));
                imageBeanPics.add(imageBeanPic);
            }
        }
        return imageBeanPics;
    }
}

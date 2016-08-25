package com.zep.rs.image;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ButtonLoader {

    public static TextureRegion txtrRegBtn[];

    private static Texture txtrBtn;

    private static int x, y;
    private static int width, height, gap;


    public static void load() {
        txtrBtn = new Texture("buttons.png");
        txtrRegBtn = new TextureRegion[5];
        x = 20;
        y = 20;
        width = 62;
        height = width;
        gap = 7;

        for (int i = 0; i < 5; i++) {
            txtrRegBtn[i] = new TextureRegion(txtrBtn, x, y, width, height); // tek tek parcalar aliniyor
            txtrRegBtn[i].flip(false, true);
            x += width + gap;
        }

    }

    public static void dispose() {
        txtrBtn.dispose();
    }
}

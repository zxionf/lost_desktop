package zyx.lost.component;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class RectTextue {
    
    public static final int WHITE60 = 0xffffffbb;
    public static final int BLACK60 = 0x000000cc;

    public static Pixmap pixmap;

    public static Texture createRBG(int width, int height, float p) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x00000090);
        pixmap.fillRectangle(4, 4, pixmap.getWidth() - 8, pixmap.getHeight() - 8);//底背景
        baseRectangularFrame(width, height);
        pixmap.fillRectangle((int)(pixmap.getWidth() * p), 0, 4, pixmap.getHeight());//分割竖线
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private static void baseRectangularFrame(int width, int height, int c, int weight) {
        pixmap.setColor(c);
        pixmap.fillRectangle(0, 0, weight, pixmap.getHeight());//左边竖线
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), weight);//下底边
        pixmap.fillRectangle(pixmap.getWidth() - weight, 0, weight, pixmap.getHeight());//右边竖线
        pixmap.fillRectangle(0, pixmap.getHeight() - weight, pixmap.getWidth(), weight);//上底边
    }
    private static void baseRectangularFrame(int width, int height, int weight) {
        baseRectangularFrame(width, height, 0x000000cc, weight);
    }
    private static void baseRectangularFrame(int width, int height) {
        baseRectangularFrame(width, height, 0x000000cc, 4);
    }
    public static Texture RectangularFrame(int width, int height, int weight) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        baseRectangularFrame(width, height, weight);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    public static Texture RectangularFrame(int width, int height, int color, int weight) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        baseRectangularFrame(width, height, color, weight);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture RectangularFrame(int width, int height, int stokcolor,int color, int weight) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        // baseRectangularFrame(width, height, color, weight);

        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, weight, pixmap.getHeight());//左边竖线
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), weight);//下底边
        pixmap.fillRectangle(pixmap.getWidth() - weight, 0, weight, pixmap.getHeight());//右边竖线
        pixmap.fillRectangle(0, pixmap.getHeight() - weight, pixmap.getWidth(), weight);//上底边

        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture RectangularSegmentationFrame(int width, int height, int number,int color) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(4, 4, pixmap.getWidth() - 8, pixmap.getHeight() - 8);//底背景
        baseRectangularFrame(width, height);//基础框
        //分割线
        for (int i = 1;i < number;i++) {
            pixmap.fillRectangle((int)(pixmap.getWidth() / number * i) - 2, 0, 4, pixmap.getHeight());//分割竖线
        }
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    public static Texture RGBArectangle(int width, int height, int color) {
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());//底背景
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static Texture RectangleTexture(int weight, int height, int color) {
        pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
//        pixmap.setColor(0x000000cc);
//        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
//        pixmap.setColor(0xffffffaa);
//        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
        pixmap.setColor(0x000000cc);
        pixmap.fillRectangle(0, 0, 4, pixmap.getHeight());
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), 4);
        pixmap.fillRectangle(pixmap.getWidth() - 4, 0, 4, pixmap.getHeight());
        pixmap.fillRectangle(0, pixmap.getHeight() - 4, pixmap.getWidth(), 4);
        pixmap.setColor(color);
        pixmap.fillRectangle(4, 4, pixmap.getWidth() - 8, pixmap.getHeight() - 8);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    
}

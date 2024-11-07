// package com.zyx.x;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Texture;

// public class MTexture {
    
//     static Pixmap pixmap;
//     static Texture texture;
    
//     public static void init(){
//         pixmap = new Pixmap(50, 50, Pixmap.Format.RGBA8888);
//     }
    
//     //紫色
//     public static Texture RectangleTexture1() {
//         pixmap.setColor(0xff33eeff);
//         //pixmap.drawRectangle(0, 0,pixmap.getWidth(), pixmap.getHeight());
//         pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
//         pixmap.setColor(0x00ff00ff);
//         pixmap.drawRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
//         texture = new Texture(pixmap);
//         //pixmap.dispose();
//         return texture;
//     }
    
//     //绿色
//     public static Texture RectangleTexture2() {
//         pixmap.setColor(0xff00ffff);
//         //pixmap.drawRectangle(0, 0,pixmap.getWidth(), pixmap.getHeight());
//         pixmap.fillRectangle(0,0,120,50);
//         pixmap.drawRectangle(0,0,120,50);
//         texture = new Texture(pixmap);
//         //pixmap.dispose();
//         return texture;
//     }
    
//     //红色
//     public static Texture RectangleTexture3() {
//         pixmap.setColor(0xffff0000);
//         //pixmap.drawRectangle(0, 0,pixmap.getWidth(), pixmap.getHeight());
//         pixmap.fillRectangle(0,0,120,50);
//         pixmap.drawRectangle(0,0,120,50);
//         texture = new Texture(pixmap);
//         //pixmap.dispose();
//         return texture;
//     }
    
// }

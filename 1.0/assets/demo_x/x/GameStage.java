// package com.zyx.x;

// import com.badlogic.gdx.scenes.scene2d.Stage;
// import android.view.TextureView;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;
// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Color;

// public class GameStage extends Stage {

//     Texture texture;
//     Image bg;

//     public void onCreate(){
//         //3840 2160
//         texture = new Texture(Gdx.files.internal("stone.png"));
//         bg = new Image(texture);
//         bg.setSize(Gdx.graphics.getHeight()*3840/2160,Gdx.graphics.getHeight());
//         bg.setPosition((Gdx.graphics.getWidth()-Gdx.graphics.getHeight()*3840/2160)/2,0);
//         this.addActor(bg);
//         this.addActor(new Image(dialogWindow()));
//     }


//     private Texture dialogWindow() {
//         int c = (int)(0.2f*Gdx.graphics.getHeight());
//         Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
//         pixmap.setColor(0xffffffaa);
//         pixmap.fillRectangle(0,(int)(0.6f*pixmap.getHeight()),pixmap.getWidth(),(int)(0.4f*pixmap.getHeight()));
        
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }
// }

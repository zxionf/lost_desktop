// package com.zyx.x;

// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.TextField;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.utils.viewport.StretchViewport;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.scenes.scene2d.Actor;

// public class mEt extends Actor{
    
//     // 视口世界的宽高统使用 480 * 800, 并统一使用伸展视口（StretchViewport）
//     public static final float WORLD_WIDTH = 480;
//     public static final float WORLD_HEIGHT = 800;

//     // 文本框的宽高
//     public static final int TEXT_FIELD_WIDTH = 300;
//     public static final int TEXT_FIELD_HEIGHT = 50;


//     // 文本框背景纹理
//     private Texture bgTexture;

//     // 文本框中的光标纹理
//     private Texture cursorTexture;

//     // 位图字体
//     private BitmapFont bitmapFont;



//     // 文本框（密码）
//     TextField passwordTextField;
    
//     /**
//      * 创建文本框的背景纹理
//      */
//     private Texture createBackgroundTexture() {
//         Pixmap pixmap = new Pixmap(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT, Pixmap.Format.RGBA8888);
//         pixmap.setColor(1, 0, 0, 1);
//         pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }

//     /**
//      * 创建文本框中的光标纹理
//      */
//     private Texture createCursorTexture() {
//         Pixmap pixmap = new Pixmap(1, TEXT_FIELD_HEIGHT - 4, Pixmap.Format.RGBA8888);
//         pixmap.setColor(1, 0, 0, 1);
//         pixmap.fill();
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }
    
//     public void create() {
        
//         // 使用伸展视口（StretchViewport）创建舞台
//         //stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));

//         /*
//          * 第 1 步: 创建文本框背景纹理, 光标纹理, 以及创建位图字体（用于显示文本框中的文本）
//          * 
//          * 为了方便演示, 这里创建纹理不再用图片来创建, 而是使用 Pixmap 来创建
//          */
//         bgTexture = createBackgroundTexture();
//         cursorTexture = createCursorTexture();

//         // 为了方便演示, 这里直接使用 gdx.jar 中自带的字体文件创建位图字体（只要在 BitmapFont 中包含有的字符才能够被输入）
//         bitmapFont = new BitmapFont();

//         // gdx.jar 中自带的字体比较小, 这里放大一下
//         bitmapFont.getData().setScale(2.0F);

//         /*
//          * 第 2 步: 创建 TextFieldStyle
//          */
//         TextField.TextFieldStyle style = new TextField.TextFieldStyle();

//         // 设置背景纹理区域
//         style.background = new TextureRegionDrawable(new TextureRegion(bgTexture));

//         // 设置光标纹理区域
//         style.cursor = new TextureRegionDrawable(new TextureRegion(cursorTexture));

//         // 设置文本框显示文本的字体来源
//         style.font = bitmapFont;

//         // 设置文本框字体颜色为白色
//         style.fontColor = new Color(1, 1, 1, 1);

//         /*
//          * 第 3 步: 创建 TextField
//          */
//         passwordTextField = new TextField("", style);

//         // 设置文本框的宽高
//         passwordTextField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);

//         // 设置文本框的位置
//         passwordTextField.setPosition(90, 430);

//         // 文本框中的文字居中对齐
//         //usernameTextField.setAlignment(Align.center);
//         //passwordTextField.setAlignment(Align.center);

//         // 用于显示密码的文本框, 需要将文本框设置为密码模式
//         //passwordTextField.setPasswordMode(true);
//         // 显示密码时用 * 号代替密码字符
//         //passwordTextField.setPasswordCharacter('*');

//         /*
//          * 第 4 步: 添加 TextField 到舞台
//          */
//         //stage.addActor(passwordTextField);
//     }
    
// }

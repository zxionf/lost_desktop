// package com.zyx.x;



// import com.badlogic.gdx.ApplicationAdapter;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// public class MyGdxGame extends ApplicationAdapter {


//     int i= 0;
//     float r,g,b,a;
//     int rr,gg,bb,aa;
//     BitmapFont font;
//     SpriteBatch batch;



//     //摇杆
//     Stage stage;

//     //绘制游戏摇杆的相关类
//     Touchpad touchPad;
//     Touchpad.TouchpadStyle style;
//     TextureRegionDrawable background;
//     TextureRegionDrawable knobRegion;

//     Texture texture;
//     Texture killer;

//     //SpriteBatch batch;

//     public static int speed = 3;//用于控制图片的移动
//     int x = 0;
//     int y = 0;


//     @Override
//     public void create() {

//         font = new BitmapFont(Gdx.files.internal("font/font-cn.fnt"),Gdx.files.internal("font/font-cn.png"),false);
//         batch = new SpriteBatch();
//         font.setColor(Color.BLACK);
//         font.setScale(2);
//         r = 0.01f;
//         b = 0.06f;
//         g = 0.56f;
//         a = 0;

//         rr=1;
//         gg=1;
//         bb=1;
//         aa=1;

//         //摇杆
//         stage = new Stage();

//         texture = new Texture(Gdx.files.internal("dirt.png"));
//         killer = new Texture(Gdx.files.internal("bobargb8888-32x32.png"));

//         //background = new TextureRegionDrawable(new TextureRegion(texture, 0, 0,128,128));
//         //knobRegion = new TextureRegionDrawable(new TextureRegion(texture,128,0,128,128));

//         background = new TextureRegionDrawable(new TextureRegion(R(1500,1500)));
//         knobRegion = new TextureRegionDrawable(new TextureRegion(R2(100,100)));

//         style = new Touchpad.TouchpadStyle(background, knobRegion);

//         touchPad = new Touchpad(0, style);//初始化游戏摇杆。(摇杆触碰区域的半径大小,TouchPagStyle)
//         touchPad.setBounds(150, 150, 250, 250);//设置摇杆的位置和大小

//         batch = new SpriteBatch();

//         stage.addActor(touchPad);

//         Gdx.input.setInputProcessor(stage);


//     }

//     @Override
//     public void render() {
//         //Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
//         Gdx.gl.glClearColor(r,g,b,a);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//         batch.begin();
//         font.draw(batch,touchPad.getKnobPercentX()+""+touchPad.getKnobPercentY(),0,300);
//         batch.end();

//         rgbback();

//         //摇杆
//         update();//这个方法别漏了

//         batch.begin();
//         batch.draw(killer, x,y, 70,70);
//         batch.end();

//         stage.act();
//         stage.draw();


//     }

//     public void update(){
//         if(touchPad.isTouched()){//判断摇杆是否被按下
//             x += touchPad.getKnobPercentX()*speed;//改变相应的坐标.
//             y += touchPad.getKnobPercentY()*speed;

//         }
//     }


//     void rgbback(){
//         switch(rr){
//             case 1:
//                 r+=0.01f;
//                 break;
//             case -1:
//                 r-=0.01f;
//                 break;
//             default: 
//         }switch(gg){
//             case 1:
//                 g+=0.01f;
//                 break;
//             case -1:
//                 g-=0.01f;
//                 break;
//             default: 
//         }switch(bb){
//             case 1:
//                 b+=0.01f;
//                 break;
//             case -1:
//                 b-=0.01f;
//                 break;
//             default: 
//         }

//         if(r>=1){
//             rr = -1;
//         }if (r<=0) {
//             rr = 1;
//         }if(g>=1){
//             gg= -1;
//         }if (g<=0) {
//             gg = 1;
//         }if(b>=1){
//             bb = -1;
//         }if (b<=0) {
//             bb = 1;
//         }
//     }

//     @Override
//     public void dispose() {
//     }
//     @Override
//     public void resize(int arg0, int arg1) {
//        

//     }

//     private Texture R(int weight,int height) {
//         Pixmap pixmap = new Pixmap(weight,height, Pixmap.Format.RGBA8888);
// //        pixmap.setColor(0x000000cc);
// //        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
// //        pixmap.setColor(0xffffffaa);
// //        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         pixmap.setColor(0x000000cc);
//         pixmap.fillCircle(pixmap.getWidth()/2,pixmap.getHeight()/2,pixmap.getHeight()/2);
//         //pixmap.fillRectangle(0,0,4,pixmap.getHeight());
//         //pixmap.fillRectangle(0,0,pixmap.getWidth(),4);
//         //pixmap.fillRectangle(pixmap.getWidth()-4,0,4,pixmap.getHeight());
//         //pixmap.fillRectangle(0,pixmap.getHeight()-4,pixmap.getWidth(),4);
//         //pixmap.setColor(0xff00ffcc);
//         //pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }

//     private Texture R2(int weight,int height) {
//         Pixmap pixmap = new Pixmap(weight,height, Pixmap.Format.RGBA8888);
// //        pixmap.setColor(0x000000cc);
// //        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
// //        pixmap.setColor(0xffffffaa);
// //        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         pixmap.setColor(0xff00ffcc);
//         pixmap.fillCircle(pixmap.getWidth()/2,pixmap.getHeight()/2,pixmap.getHeight()/2);
//         //pixmap.fillRectangle(0,0,4,pixmap.getHeight());
//         //pixmap.fillRectangle(0,0,pixmap.getWidth(),4);
//         //pixmap.fillRectangle(pixmap.getWidth()-4,0,4,pixmap.getHeight());
//         //pixmap.fillRectangle(0,pixmap.getHeight()-4,pixmap.getWidth(),4);
//         //pixmap.setColor(0xff00ffcc);
//         //pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }

// }

// package com.zyx.x;

// import com.badlogic.gdx.Game;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.OrthographicCamera;
// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Animation;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.Sprite;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
// import com.badlogic.gdx.physics.box2d.BodyDef;
// import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
// import com.badlogic.gdx.physics.box2d.FixtureDef;
// import com.badlogic.gdx.physics.box2d.PolygonShape;
// import com.badlogic.gdx.physics.box2d.World;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;


// public class MyGdxGame extends Game {
//     float size = 1600;
//     final int ipi = 80;

//     BitmapFont font,font2;
//     SpriteBatch batch2;


//     Texture texture;
//     Texture command_block_back;
//     Texture dirt,stone;
//     Animation command_bock_back_animation;
//     SpriteBatch batch;
//     Sprite sprite = new Sprite();
//     ShapeRenderer shapeRenderer;
//     OrthographicCamera camera;
//     int c = 1;
//     int c2 = 1;
//     float y ;
//     float x;
//     float time;
//     String text="";

//     Stage stage;

//     //玩家
//     Player zxionf,wxw;

//     //绘制游戏摇杆的相关类
//     Touchpad touchPad;
//     Touchpad.TouchpadStyle style;
//     TextureRegionDrawable background;
//     TextureRegionDrawable knobRegion;

//     //世界及box2d
//     World world;
//     Box2DDebugRenderer ren;
//     OrthographicCamera cam;
//     Body body1;

//     public static int speed = 3;//用于控制图片的移动

//     //技能键

//     @Override
//     public void create() {

//         world = new World(new Vector2(0, -10), true);
//         cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//         ren = new Box2DDebugRenderer();
//         BodyDef bdef = new BodyDef();
//         bdef.type = BodyDef.BodyType.StaticBody;

//         FixtureDef fdef = new FixtureDef();
//         PolygonShape shape = new PolygonShape();
//         shape.setAsBox(20, 20);
//         fdef.shape = shape;
//         body1 = world.createBody(bdef);
//         body1.createFixture(fdef);
        
//         bdef.type = BodyDef.BodyType.DynamicBody;
//         bdef.position.set(20,50);
//         shape.setAsBox(20,20);
//         fdef.shape = shape;
//         fdef.restitution = 1f;
//         world.createBody(bdef).createFixture(fdef);

//         //摇杆
//         stage = new Stage();

//         background = new TextureRegionDrawable(new TextureRegion(R(1500, 1500)));
//         knobRegion = new TextureRegionDrawable(new TextureRegion(R2(110, 110)));
//         style = new Touchpad.TouchpadStyle(background, knobRegion);

//         touchPad = new Touchpad(0, style);//初始化游戏摇杆。(摇杆触碰区域的半径大小,TouchPagStyle)
//         touchPad.setBounds(50, 50, 250, 250);//设置摇杆的位置和大小
//         batch = new SpriteBatch();

//         stage.addActor(touchPad);

//         Gdx.input.setInputProcessor(stage);

//         //返回有效
//         Gdx.input.setCatchBackKey(true);

//         //初始化玩家
//         zxionf = new Player();
//         wxw = new Player();

//         zxionf.hp = 1000;
//         zxionf.ht = 5;
//         zxionf.x = 1337;
//         zxionf.y = 333;


//         batch2 = new SpriteBatch();
//         font2 = new BitmapFont(Gdx.files.internal("font/font-cn.fnt"), Gdx.files.internal("font/font-cn.png"), false);

//         font = new BitmapFont(Gdx.files.internal("font/font-cn.fnt"), Gdx.files.internal("font/font-cn.png"), false);
//         //三倍
//         font.getData().setScale(2);

//         texture = new Texture(Gdx.files.internal("ps-bobargb8888-32x32.png"));
//         dirt = new Texture(Gdx.files.internal("dirt.png"));
//         stone = new Texture(Gdx.files.internal("stone.png"));
//         command_block_back = new Texture(Gdx.files.internal("command_block_back.png"));
//         sprite.setTexture(dirt);
//         sprite.setOrigin(ipi / 2, ipi / 2);
//         sprite.setSize(ipi, ipi);
//         sprite.setPosition(300, 300);
//         //sprite.setColor(1,0,1,1);
//         //动画分割
//         int FRAME_COLS = 1;
//         int FRAME_ROWS = 4;
//         TextureRegion[][] tmp = TextureRegion.split(command_block_back, command_block_back.getWidth() / FRAME_COLS, command_block_back.getHeight() / FRAME_ROWS);
//         TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
//         int index = 0;
//         for (int i = 0; i < FRAME_ROWS; i++)
//             for (int j = 0; j < FRAME_COLS; j++)
//                 walkFrames[index++] = tmp[i][j];
//         command_bock_back_animation = new Animation(1, walkFrames);

//         batch = new SpriteBatch();
//         camera = new OrthographicCamera();
//         configureCamera();

//         shapeRenderer = new ShapeRenderer();

//         (new UdpReceive()).start();
//     }

//     public class UdpReceive extends Thread {
//         String msg = null;

//         UdpReceive() {
//         }

//         public void run() {
//             // 消息循环
//             while (true) {
//                 try {
//                     DatagramSocket ds = new DatagramSocket(8989);
//                     byte[] data = new byte[1024];
//                     DatagramPacket dp = new DatagramPacket(data, data.length);
//                     dp.setData(data);
//                     ds.receive(dp);
//                     //byte[] data2 = new byte[dp.getLength()];
//                     //System.arraycopy(data, 0, data2, 0, data2.length);// 得到接收的数据
//                     //String msg = (String) toObject(data2);
//                     text = new String(dp.getData(), 0, dp.getLength());
//                     ds.close();


//                 } catch (Exception e) {
//                     text = "error";
//                 }
//             }

//         }
//     }

//     @Override
//     public void render() {        
//         Gdx.gl.glClearColor(1, 1, 1, 1);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//         camera.update();
//         batch.setProjectionMatrix(camera.combined);
// //        shapeRenderer.setProjectionMatrix(camera.combined);
// //        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
// //
// //        shapeRenderer.setColor(0, 0.5f, 0, 1);
// //        shapeRenderer.circle(50, 50, 40);
// //
// //        shapeRenderer.setColor(0.5f, 0, 0, 1);
// //        shapeRenderer.rect(10, 100, 80, 80);
// //
// //        shapeRenderer.setColor(0, 0, 0.5f, 1);
// //        shapeRenderer.triangle(10, 200, 90, 200, 50, 270);
// //      shapeRenderer.end();

//         try {
//             y = Integer.valueOf(text);
//         } catch (Exception e) {}


//         batch.begin();

//         batch.draw(command_block_back, 0, 0, ipi, ipi);
//         for (int i = 0; i < 36;i++) {//10列
//             for (int j = 0; j < 10; j++) {//18行
//                 batch.draw(dirt, i * ipi, j * ipi, ipi, ipi);
//             }
//         }
//         for (int i =0;i < 18;i++) {
//             if (i % 2 == 0) {
//                 batch.draw(stone, ipi * 10, ipi * i, ipi, ipi);
//             }if (i % 2 == 1) {
//                 batch.draw(dirt, ipi * 10, ipi * i, ipi, ipi);
//             }
//         }
//         TextureRegion tex = (TextureRegion) command_bock_back_animation.getKeyFrame(time, true);
//         batch.draw(tex, 400, 200, 400, 264);

//         sprite.draw(batch);

//         int op = ipi + 32;
//         font.setColor(0, 0, 0, 0.8f);
//         font.draw(batch, text, 4, 202 + op - 4);
//         font.setColor(Color.WHITE);
//         font.draw(batch, text, 0 , 202 + op);

//         font.draw(batch, "x" + (int)x + "y" + (int)y, x, y);
//         batch.draw(texture, x, y, 64, 64);

//         batch.end();


//         shapeRenderer.setProjectionMatrix(camera.combined);
//         shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//         shapeRenderer.setColor(Color.GREEN);
//         //shapeRenderer.rect(0,100, 100,100 );
//         shapeRenderer.line(0, 0, x, y);

//         shapeRenderer.end();

//         batch2.begin();
//         font2.getData().setScale(5);
//         font2.setColor(Color.GREEN);
//         font2.draw(batch2, ((Gdx.app.getJavaHeap() / 1048576) + (Gdx.app.getNativeHeap() / 1048576)) + "MB", 0, 666);
//         batch2.setProjectionMatrix(camera.combined);
//         /*for(int i = 0;i<19;i++){
//          font2.draw(batch2,i+"",0,(i+1)*ipi);
//          font2.draw(batch2,i+"",i*ipi,ipi);

//          }*/
//         batch2.end();

//         time += Gdx.graphics.getDeltaTime();


//         sprite.setRotation(time * 50);

//         if (touchPad.isTouched()) {//判断摇杆是否被按下
//             //改变相应的坐标
//             if (x > Gdx.graphics.getWidth()) {
//                 x = Gdx.graphics.getWidth();
//             } else {
//                 x += touchPad.getKnobPercentX() * speed;
//             }

//             if (y > Gdx.graphics.getHeight())
//                 y = Gdx.graphics.getHeight();
//             else {
//                 y += touchPad.getKnobPercentY() * speed;
//             }

//         }

//         /*
//          if(Gdx.input.isTouched()){
//          size = y;
//          configureCamera();
//          camera.update();
//          }*/
//         //camera.translate(0,10*Gdx.graphics.getDeltaTime());
//         stage.act();
//         stage.draw();
        
//         if(time > 5){
//         world.step(Gdx.graphics.getDeltaTime(), 1, 1);
//         ren.render(world, cam.combined);
//         }
//     }


//     private void configureCamera() {
//         if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
//             camera.setToOrtho(false, size, size * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
//         else
//             camera.setToOrtho(false, size * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size);
//     }
//     @Override
//     public void dispose() {
//         shapeRenderer.dispose();
//     }

//     @Override
//     public void resize(int width, int height) {
//         configureCamera();
//     }

//     @Override
//     public void pause() {
//     }

//     @Override
//     public void resume() {
//     }

//     //绘制摇杆大圆
//     private Texture R(int weight, int height) {
//         Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
//         pixmap.setColor(0x000000cc);
//         pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2);
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }

//     //绘制摇杆小圆
//     private Texture R2(int weight, int height) {
//         Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
//         pixmap.setColor(0xffffffcc);
//         pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2);
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }
// }

// package com.zyx.kga.lost;

// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Camera;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.OrthographicCamera;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.Batch;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
// import com.badlogic.gdx.maps.MapLayer;
// import com.badlogic.gdx.maps.MapLayers;
// import com.badlogic.gdx.maps.MapObjects;
// import com.badlogic.gdx.maps.objects.RectangleMapObject;
// import com.badlogic.gdx.maps.tiled.TiledMap;
// import com.badlogic.gdx.maps.tiled.TmxMapLoader;
// import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
// import com.badlogic.gdx.math.Rectangle;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.Body;
// import com.badlogic.gdx.physics.box2d.BodyDef;
// import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
// import com.badlogic.gdx.physics.box2d.FixtureDef;
// import com.badlogic.gdx.physics.box2d.PolygonShape;
// import com.badlogic.gdx.physics.box2d.World;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
// import com.badlogic.gdx.utils.Array;
// import com.badlogic.gdx.utils.viewport.StretchViewport;
// import com.badlogic.gdx.utils.viewport.Viewport;
// import com.zyx.kga.lost.screen.AbstractScreen;
// import java.net.DatagramPacket;
// import java.net.DatagramSocket;
// import java.net.InetAddress;
// import com.badlogic.gdx.scenes.scene2d.ui.Label;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;
// import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


// public class MultipPlayerBattle extends AbstractScreen {

//     public MultipPlayerBattle(MyGdxGame game) {
//         super(game);
//     }
    
//     @Override
//     public void hide() {
//     }

//     float PPM = PublicInfo.PPM;
    
//     float size = 160*2/PPM;
//     OrthographicCamera b2dcam;
//     OrthographicCamera cam;
    
//     TiledMap map;
//     OrthogonalTiledMapRenderer ren;
    
//     SpriteBatch batch;
//     Batch fontbatch;
    
//     World world;
//     Box2DDebugRenderer ren2;
//     Body mybody;
//     TextureRegion me;
    
//     ControlStage controlStage;
    
//     Player player1,player2;
    
//     float nx,ny;
    
//     Stage stage;
   
//     BitmapFont font;
    
//     //TEST
//     ShapeRenderer shapeRenderer;
    
//     String receiveText;
  

//     public static int speed = 40;//用于控制图片的移动

//     //技能键

//     @Override
//     public void show() {
//         batch = new SpriteBatch();
//         fontbatch = new SpriteBatch();
//         font  = MyGdxGame.ass.get("font/fontc_16.fnt",BitmapFont.class);
//         b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//         cam = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//         map = new TmxMapLoader().load("Mp02.tmx");
//         ren = new OrthogonalTiledMapRenderer(map,1/PPM);
//         b2dcam.position.x = Gdx.graphics.getWidth()/2;
//         b2dcam.position.y = Gdx.graphics.getHeight()/2;
        
//         Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//         //viewport.apply(true);
//         viewport.setCamera(cam);
//         //viewport.setWorldSize(Gdx.graphics.getWidth()/PPM,Gdx.graphics.getHeight()/PPM);
//         //viewport.setScreenSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//         stage = new Stage(viewport);
//         //player2 = new Player("player2");
        
//         world = new World(new Vector2(0,-32),true);
//         world.setContactListener(new ListenerClass());
//         ren2 = new Box2DDebugRenderer();
        
//         BodyDef bdef = new BodyDef();
//         bdef.type = BodyDef.BodyType.StaticBody;
//         FixtureDef fdef = new FixtureDef();
        
//         MapLayers layers = map.getLayers();
//         MapLayer layer = layers.get("Collision");
//         MapObjects mapobjs = layer.getObjects();
//         Array<RectangleMapObject> rmos = mapobjs.getByType(RectangleMapObject.class);
//         for(RectangleMapObject rmo: rmos){
//             /*Rectangle rect = rmo.getRectangle();
//             PolygonShape shape = new PolygonShape();
//             shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            
//             fdef.shape = shape;
//             bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
//             world.createBody(bdef).createFixture(fdef);*/
            
//             Rectangle rect = rmo.getRectangle();
//             //if (!mo.getProperties().get("name").equals("im")) {
//             Body body;
//             bdef.type = BodyDef.BodyType.StaticBody;
//             bdef.position.set(rect.getX()/PPM + rect.getWidth()/PPM / 2, rect.getY()/PPM + rect.getHeight()/PPM / 2);
//             body = world.createBody(bdef);
//             PolygonShape r = new PolygonShape();
            
//             r.setAsBox(rect.getWidth()/PPM / 2, rect.getHeight()/PPM / 2);
//             fdef.shape = r;
            
// 			body.createFixture(fdef);
//         }
        
//         bdef.type = BodyDef.BodyType.DynamicBody;
//         bdef.position.set(300/PPM,300/PPM);
        
//         PolygonShape shape = new PolygonShape();
//         shape.setAsBox(6/PPM,12/PPM);
//         fdef.shape = shape;
//         fdef.density = 0;
//         //fdef.isSensor = true;
//         //fdef.restitution = 0.2f;
//         //fdef.friction = 0.1f;
//         mybody = world.createBody(bdef);
//         mybody.createFixture(fdef);
        
//         player1 = new Player(world,stage,mybody);
        
//         controlStage = new ControlStage();
//         controlStage.init_stage();
//         Gdx.input.setInputProcessor(controlStage);
        
//         me = new TextureRegion(new Texture("tile.png"));
//         font.setColor(Color.RED);
        
        
//         //TEST
//         shapeRenderer = new ShapeRenderer();
//         //(new UdpReceive()).start();
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
//                     receiveText = new String(dp.getData(), 0, dp.getLength());
//                     ds.close();


//                 } catch (Exception e) {
//                     receiveText = "error";
//                 }
//             }

//         }
//     }
    
//     @Override
//     public void render(float p) {        
//         Gdx.gl.glClearColor(1, 1, 1, 1);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//         //刷新世界
//         world.step(p,200,200);
//         ren.setView(b2dcam);
//         ren.render();
        
        
        
//         player1.update();
//         player1.move();
//         //刷新Batch
//         batch.setProjectionMatrix(cam.combined);
//         //font.getData().setScale(0.25f);
//         batch.begin();
//         batch.draw(me,mybody.getPosition().x*PPM-6,mybody.getPosition().y*PPM-12,12,24);
//         //font.draw(batch,""+mybody.getPosition().x+"\n"+mybody.getPosition().y,mybody.getPosition().x,mybody.getPosition().y);
//         batch.end();
//         //TEST
//         ren2.render(world,b2dcam.combined);
//         //刷新摄像机
//         update();
//         move();
        
//         //stage.getViewport().apply();
//         player1.HPBar.setPosition((float)mybody.getPosition().x*PPM-12,mybody.getPosition().y*PPM+15);
//         player1.label.setPosition(mybody.getPosition().x*PublicInfo.PPM,mybody.getPosition().y*PublicInfo.PPM);
//         stage.act();
//         stage.draw();
        
//         //刷新控制层
//         controlStage.act();
//         controlStage.draw();
        
//         font.getData().setScale(2);
//         fontbatch.begin();
//         font.draw(fontbatch,""+mybody.getPosition().x+"\n"+mybody.getPosition().y+"\n"+nx+"\n"+ny+"\n#"+receiveText,0,600);
//         fontbatch.end();
        
//         //TEST
//         shapeRenderer.setProjectionMatrix(b2dcam.combined);
//         shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//         shapeRenderer.setColor(Color.GREEN);
//         //shapeRenderer.rect(0,100, 100,100 );
//         shapeRenderer.line(0,0,mybody.getPosition().x,mybody.getPosition().y);
//         shapeRenderer.setProjectionMatrix(cam.combined);
//         shapeRenderer.setColor(Color.RED);
//         shapeRenderer.line(0,0,mybody.getPosition().x*PPM,mybody.getPosition().y*PPM);
//         //shapeRenderer.
//         //for(int i=0 ;i<100;i++){shapeRenderer.point(3,pathx[i],pathy[i]);}
//         shapeRenderer.point(5,mybody.getPosition().x,mybody.getPosition().y);
//         shapeRenderer.end();
        
//         try{
//         mybody.setTransform(Float.valueOf(receiveText.split("#")[0]),Float.valueOf(receiveText.split("#")[1]),0);
//         }catch(Exception e){
            
//         }
//     }
//     public void move()
//     {
//                 try{
//                     DatagramSocket ds=new DatagramSocket(8989);//监听端口 
//                     //将要发送的数据封装到数据包中 
//                     //String str="udp传输演示，go"; 

//                     //BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));//键盘输入 

//                     String line=mybody.getPosition().x+"#"+mybody.getPosition().y; 
//                     //使用DatagramPacket将数据封装到该对象中 
//                     //while((line=bufr.readLine())!=null){ 
//                         byte[] buf=line.getBytes();
//                         DatagramPacket dp= 
//                             new DatagramPacket(buf, buf.length,InetAddress.getByName(PublicInfo.player2IP),8989); 
//                         //通过udp的socket服务将数据包发送出去，通过send方法 
//                         ds.send(dp); 
//                         if("886".equals(line)){ 
//                             //break; 
//                         } 
//                     //} 
//                     //关闭资源 
//                     ds.close();
//                 }catch(Exception e){}
         
//     }
//     //在绘图前更新相机，坐标，世界
//     public void update() {
//         cameraupdate(b2dcam, mybody.getPosition().x, mybody.getPosition().y);
// //        b2dcam.position.x = 0;
// //        b2dcam.position.y = 0;
// //        b2dcam.update();
//         cam.update();
//         //world.step(1 / 60f, 3, 2);
//         //cam.position.add(50,50,0);
//         nx = mybody.getPosition().x - (52) / 2;
//         ny = mybody.getPosition().y - (52) / 2;
        
        
//         cam.zoom = 1/PPM;
//         cameraupdate(cam,mybody.getPosition().x*PPM,mybody.getPosition().y*PPM);

// 	}
// private void cameraupdate(Camera camera,float targetX,float targetY){
//         //float dx = (targetX - camera.position.x) / 32;
//         //float dy = (targetY - camera.position.y) / 16;
//         float dx = (targetX - camera.position.x) / 16;
//         float dy = (targetY - camera.position.y) / 8;
//         camera.position.add(dx, dy, 0);
// 		camera.update();
//     }
//     private void configureCamera() {
//         if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth()){
//             b2dcam.setToOrtho(false, size, size * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
//             cam.setToOrtho(false, size*PPM*PPM, size*PPM*PPM * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
//         }
//         else{
//             b2dcam.setToOrtho(false, size * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size);
//             cam.setToOrtho(false, size*PPM*PPM * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size*PPM*PPM);
//             }
//     }
//     @Override
//     public void dispose() {
//         //shapeRenderer.dispose();
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
    
    
    
// }

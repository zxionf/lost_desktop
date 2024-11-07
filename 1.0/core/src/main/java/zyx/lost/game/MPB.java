package zyx.lost.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import zyx.lost.ControlStage;
import zyx.lost.I;
import zyx.lost.MyGdxGame;

public class MPB extends MyGame {

    int PPM = 16;

    float size = 160 * 2 / PPM ;//* Info.cameraheightscale;


    float nx,ny;


    //PropertyPanelWindow ppw;


    //TEST
    ShapeRenderer shapeRenderer;

    String receiveText;

    Box2DDebugRenderer ren2;

    TextureRegion bullet;

    public float time;



    public static int speed = 40;//用于控制图片的移动

    //技能键

    @Override
    public void create() {
        batch = new SpriteBatch();
        fontbatch = new SpriteBatch();
        font  = MyGdxGame.ass.get("font/fontc_16.fnt", BitmapFont.class);
        b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        map = new TmxMapLoader().load("map/Mp01.tmx");
        //ren = new OrthogonalTiledMapRenderer(map, 4/PPM);
        //b2dcam.position.x = Gdx.graphics.getWidth() / 2;
        //b2dcam.position.y = Gdx.graphics.getHeight() / 2;

        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //viewport.apply(true);
        viewport.setCamera(cam);
        //viewport.setWorldSize(Gdx.graphics.getWidth()/PPM,Gdx.graphics.getHeight()/PPM);
        //viewport.setScreenSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(viewport);

        configureCamera();

        //ppw = new PropertyPanelWindow();

        world = new World(new Vector2(0, -32), true);
        //world.setContactListener(new MyContactListener());
        ren2 = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fdef = new FixtureDef();

        MapLayers layers = map.getLayers();
        MapLayer layer = layers.get("pz");
        MapObjects mapobjs = layer.getObjects();
        Array<RectangleMapObject> rmos = mapobjs.getByType(RectangleMapObject.class);
        for (RectangleMapObject rmo: rmos) {
            /*Rectangle rect = rmo.getRectangle();
             PolygonShape shape = new PolygonShape();
             shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);

             fdef.shape = shape;
             bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
             world.createBody(bdef).createFixture(fdef);*/

            Rectangle rect = rmo.getRectangle();
            //if (!mo.getProperties().get("name").equals("im")) {
            Body body;
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() / PPM + rect.getWidth() / PPM / 2, rect.getY() / PPM + rect.getHeight() / PPM / 2);
            body = world.createBody(bdef);
            PolygonShape r = new PolygonShape();

            r.setAsBox(rect.getWidth() / PPM / 2, rect.getHeight() / PPM / 2);
            fdef.shape = r;

            body.createFixture(fdef);
        }

        //fdef.isSensor = true;
        //fdef.restitution = 0.2f;
        //fdef.friction = 0.1f;

        /*ib = new InformationBar();

        player1 = new Player(world, stage, batch, ib);
        player2 = new Player(world, stage, batch, ib);

        player2.setTransform(5, 5);*/

        controlStage = new ControlStage();
        controlStage.init_stage();
        Gdx.input.setInputProcessor(controlStage);



        bullet = new TextureRegion(new Texture("texture/items/bullet.png"));

        camupdate(false);
        configureCamera();

        //controlStage.addActor(ib);

        //TEST
        shapeRenderer = new ShapeRenderer();
        //(new UdpReceive()).start();
    }

    public class UdpReceive extends Thread {
        String msg = null;

        UdpReceive() {
        }

        public void run() {
            // 消息循环
            while (true) {
                try {
                    DatagramSocket ds = new DatagramSocket(8989);
                    byte[] data = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(data, data.length);
                    dp.setData(data);
                    ds.receive(dp);
                    //byte[] data2 = new byte[dp.getLength()];
                    //System.arraycopy(data, 0, data2, 0, data2.length);// 得到接收的数据
                    //String msg = (String) toObject(data2);
                    receiveText = new String(dp.getData(), 0, dp.getLength());
                    ds.close();


                } catch (Exception e) {
                    receiveText = "error";
                }
            }

        }
    }

    @Override
    public void render(float p) {        
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //刷新摄像机
        //放前面以防出bug
        camupdate(true);

        //刷新世界
        world.step(p, 200, 200);
        //ren.setView(b2dcam);
        //ren.render();
        //ZMapRender.render(batch,qukuai

        batch.setProjectionMatrix(cam.combined);
        //player1.update();
        //player2.update();
        //player1.move();
        //刷新Batch

        //font.getData().setScale(0.25f);
        batch.begin();
        //batch.draw(bullet, I.bulletposition[0] * PPM, I.bulletposition[1] * PPM, 2, 1);
        //font.draw(batch,""+mybody.getPosition().x+"\n"+mybody.getPosition().y,mybody.getPosition().x,mybody.getPosition().y);
        batch.end();
        //TEST
        ren2.render(world, b2dcam.combined);

        stage.act();
        stage.draw();

        //刷新控制层
        controlStage.act();
        controlStage.draw();
        //move();

        //各种状态更新
        //  State.screenstate.update(game);
        //State.stage.update();

        font.getData().setScale(2);
        fontbatch.begin();
        font.setColor(Color.RED);
        //font.draw(fontbatch, "" + player1.getPosition().x + "\n" + player1.getPosition().y + "\n" + controlStage.touchPad.getKnobPercentX() + "\n" + Info.bulleta[0] + "\n#" + receiveText + ib.getY() + "/" + ib.getHeight() + "/" + player1.label.getWidth(), 0, 600);
        fontbatch.end();

        

        //TEST
        shapeRenderer.setProjectionMatrix(b2dcam.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(Color.GREEN);
        //shapeRenderer.rect(0,100, 100,100 );
        //shapeRenderer.line(0,0,mybody.getPosition().x,mybody.getPosition().y);
        shapeRenderer.setProjectionMatrix(cam.combined);
        shapeRenderer.setColor(Color.RED);
        //shapeRenderer.line(0, 0, player1.getPosition().x * PPM, player1.getPosition().y * PPM);
        //shapeRenderer.
        //for(int i=0 ;i<100;i++){shapeRenderer.point(3,pathx[i],pathy[i]);}
        //shapeRenderer.point(5, player1.getPosition().x, player1.getPosition().y);
        shapeRenderer.end();

        try {
            //player1.body.setTransform(Float.valueOf(receiveText.split("#")[0]), Float.valueOf(receiveText.split("#")[1]), 0);
        } catch (Exception e) {

        }
    }
   /* public void move() {
        try {
            DatagramSocket ds=new DatagramSocket(8989);//监听端口 
            //将要发送的数据封装到数据包中 
            //String str="udp传输演示，go"; 

            //BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));//键盘输入 

            String line=player1.getPosition().x + "#" + player1.getPosition().y; 
            //使用DatagramPacket将数据封装到该对象中 
            //while((line=bufr.readLine())!=null){ 
            byte[] buf=line.getBytes();
            DatagramPacket dp= 
                new DatagramPacket(buf, buf.length, InetAddress.getByName(Info.player2IP), 8989); 
            //通过udp的socket服务将数据包发送出去，通过send方法 
            ds.send(dp); 
            if ("886".equals(line)) { 
                //break; 
            } 
            //} 
            //关闭资源 
            ds.close();
        } catch (Exception e) {}

    }*/
    //在绘图前更新相机，坐标，世界
    @SuppressWarnings("unused")
    public void camupdate(boolean smooth) {
        b2dcam.zoom = I.cameraheightscale;
        cam.zoom = 1 / PPM * I.cameraheightscale;
       // nx = player1.getPosition().x;
        //ny = player1.getPosition().y;
        if (true) {
            //cam.position.set(mybody.getPosition().x*PPM,mybody.getPosition().y*PPM,0);
            //b2dcam.position.set(mybody.getPosition().x,mybody.getPosition().y,0);
            //cam.update();
            //b2dcam.update();
            //world.step(1 / 60f, 3, 2);
            cameraupdate(b2dcam, nx, ny);
            cameraupdate(cam, nx * PPM, ny * PPM);
        } else {
            b2dcam.position.set(nx, ny, 0);
            cam.position.set(nx * PPM, ny * PPM, 0);
            b2dcam.update();
            cam.update();
        }

        //float dxx = (player1.getPosition().x - player2.getPosition().x) / 32;
        //float dyy = (player1.getPosition().y - player2.getPosition().y) / 16;
        //player2.body.setTransform(player2.getPosition().x + dxx, player2.getPosition().y + dyy, 0);

    }
    private void cameraupdate(Camera camera, float targetX, float targetY) {
        //float dx = (targetX - camera.position.x) / 32;
        //float dy = (targetY - camera.position.y) / 16;
        float dx = (targetX - camera.position.x) / 8;
        float dy = (targetY - camera.position.y) / 4;
        camera.position.add(dx, dy, 0);
        camera.update();
    }
    private void configureCamera() {

        if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth()) {
            //横屏
            b2dcam.setToOrtho(false, size, size * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
            cam.setToOrtho(false, size * PPM * PPM, size * PPM * PPM * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        } else {
            b2dcam.setToOrtho(false, size * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size);
            cam.setToOrtho(false, size * PPM * PPM * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size * PPM * PPM);
        }
    }
    @Override
    public void dispose() {
        //shapeRenderer.dispose();
    }

    @Override
    public void resize(int width, int height) {
        //configureCamera();
    }

    @Override
    public void pause() {
        //暂停
        //camupdate(false);
	//	Toast.makeText(App.getApp(), "", Toast.LENGTH_SHORT).show();
        //MainActivity.use.showQucikDialog("", "pause()", new Runnable(){@Override public void run(){}});
    }

    @Override
    public void resume() {
        //恢复
        //camupdate(false);
        //MainActivity.use.showQucikDialog("恭喜", "你", new Runnable(){@Override public void run(){}});
    }
    


}

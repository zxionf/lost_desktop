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
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import zyx.lost.ControlStage;
import zyx.lost.I;
import zyx.lost.MyContactListener;
import zyx.lost.MyGdxGame;
import zyx.lost.component.InformationBar;
import zyx.lost.entities.EntitySet;
import zyx.lost.entities.Player;
import zyx.lost.entities.Zombie;
import zyx.lost.rule.QuKuaiManager;
import java.util.Random;

public class MainGame extends MyGame{
    
    float size = 160 * 2 / PPM ;//* Info.cameraheightscale;
    float nx,ny;
    //PropertyPanelWindow ppw;
    Texture testtexture = new Texture("texture/blocks/blast_furnace_top.png");
    //TEST
    ShapeRenderer shapeRenderer;
    String receiveText;
    Box2DDebugRenderer ren2;
    TextureRegion bullet;
    public float time;
    boolean next = false;
    //Music music
    QuKuaiManager qukuaimgr;
    Random seedrandom = new Random(I.mapseed);
    public static int speed = 40;//用于控制图片的移动
    //技能键
    // RayHandler rh;
    @Override
    public void create() {
        batch = new SpriteBatch();
        fontbatch = new SpriteBatch();
        font  = MyGdxGame.ass.get("font/fontc_16.fnt", BitmapFont.class);
        b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        map = new TmxMapLoader().load("map/Mp01.tmx");
        for(int i = 0;i<256;i++){
            I.random[i] = seedrandom.nextInt(256);//-128;
        }
        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(cam);
        stage = new Stage(viewport);
        configureCamera();

        world = new World(new Vector2(0, 0), false);
        ren = new OrthogonalTiledMapRenderer(map, 4/PPM);
        world.setContactListener(new MyContactListener());
        ren2 = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        FixtureDef fdef = new FixtureDef();
        
        fdef.isSensor = true;
        fdef.restitution = 0.2f;
        fdef.friction = 0.1f;

        ib = new InformationBar();

        player1 = new Player(world, stage, batch, ib);
        player2 = new Player(world, stage, batch, ib);

        //初始化实体集
        entityset = new EntitySet(MyGdxGame.GM,world,stage, batch, ib);
        //entityset.create(Etype.zombie.getId());

        player2.setTransform(5, 5);
        //player1.setTransform(64,5);

        controlStage = new ControlStage();
        controlStage.init_stage();
        Gdx.input.setInputProcessor(controlStage);

        //生成多个僵尸
        for(int i = 0;i<100;i++)entityset.create(new Zombie(world,stage, seedrandom.nextInt(64/2), seedrandom.nextInt(64/2), batch, ib));

        bullet = new TextureRegion(new Texture("texture/items/bullet.png"));

        camupdate(false);
        configureCamera();

        controlStage.addActor(ib);

        //TEST
        shapeRenderer = new ShapeRenderer();
        //(new UdpReceive()).start();
        //music = MyGdxGame.ass.get("music/outerspace.mp3");
        //music.play();
        // rh = new RayHandler(world);
        // new PointLight(rh, 3200);
    }

    @Override
    public void render(float p) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time += Gdx.graphics.getDeltaTime();
        I.upCharacters = String.format("%.2f",time);

        //刷新按键状态
        I.KeyUpdate.update();//在player中监听按键点击

        //刷新摄像机
        //放前面以防出bug
        camupdate(true);
        
        //刷新世界
        world.step(p, 200, 200);
        //绘制地图？
        ren.setView(b2dcam);
        ren.render();
        //TEST
        if(I.show_2dbox){ren2.render(world, b2dcam.combined);}

        if(next){
            I.camupdateUseHuanDong = true;
        }
        if(player1.getPosition().x>64){
            I.camupdateUseHuanDong = false;
            next = true;
            player1.setTransform(player1.getPosition().x-64,player1.getPosition().y);
        }if(player1.getPosition().x<0){
            I.camupdateUseHuanDong = false;
            next = true;
            player1.setTransform(player1.getPosition().x+64,player1.getPosition().y);
        }
        batch.setProjectionMatrix(cam.combined);
        //ZMapRender.render(batch,qukuaimgr.cjzqks[4],testtexture);
        
        batch.setProjectionMatrix(cam.combined);
        player1.update();
        player2.update();
        player1.move();

        entityset.update();
        entityset.move();
        //刷新Batch

        //font.getData().setScale(0.25f);
        batch.begin();
        //batch.draw(bullet, I.bulletposition[0] * PPM, I.bulletposition[1] * PPM, 2, 1);
        //font.draw(batch,""+mybody.getPosition().x+"\n"+mybody.getPosition().y,mybody.getPosition().x,mybody.getPosition().y);
        batch.end();
        


        stage.act();
        stage.draw();
        
        // rh.setCombinedMatrix(b2dcam);
		// rh.updateAndRender();
        //刷新控制层
        controlStage.act();
        controlStage.image_up.setDrawable(controlStage.imageup[I.jumpacount]);
        controlStage.draw();

        //各种状态更新
        //  State.screenstate.update(game);
        //State.stage.update();



        if (player1.getPosition().y < -10) {
            player1.setTransform(20, 20);
            ib.addInfo("[RED][GM][YELLOW]" + I.playerName + "[WHITE]掉出了世界");

        }
        if(I.isdebugtest){
            //DEBUG
            font.getData().setScale(2);
            fontbatch.begin();
            font.setColor(Color.RED);
            font.draw(fontbatch, "" +player1.getPosition().x + "\n" + player1.getPosition().y + "\n" + controlStage.touchPad.getKnobPercentX() + "\n"  + "\n#" + receiveText + ib.getY() + "/" + ib.getHeight() + "/" + player1.label.getWidth()+"/"+I.jumpacount+"/"+I.controlstage_isUpa+"/"+I.controlstage_isUp+"/"+"/"+I.playerName+"\n"+I.qukuaiTest.s, 0, 600);
            fontbatch.end();
            //TEST
            shapeRenderer.setProjectionMatrix(b2dcam.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            //shapeRenderer.setColor(Color.GREEN);
            //shapeRenderer.rect(0,100, 100,100 );
            //shapeRenderer.line(0,0,mybody.getPosition().x,mybody.getPosition().y);
            shapeRenderer.setProjectionMatrix(cam.combined);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(0, 0, player1.getPosition().x * PPM, player1.getPosition().y * PPM);
            //shapeRenderer.
            
            //qu kuai test 
            //ZMapRender.shaperender(shapeRenderer,qukuaimgr);
            
            
            //for(int i=0 ;i<100;i++){shapeRenderer.point(3,pathx[i],pathy[i]);}
            shapeRenderer.point(5, player1.getPosition().x, player1.getPosition().y);
            shapeRenderer.end();
        }


        try {
            player1.body.setTransform(Float.valueOf(receiveText.split("#")[0]), Float.valueOf(receiveText.split("#")[1]), 0);
        } catch (Exception e) {}

        
    }
    //在绘图前更新相机，坐标，世界
    @SuppressWarnings("unused")
    public void camupdate(boolean smooth) {
        b2dcam.zoom = I.cameraheightscale;
        cam.zoom = 1 / PPM * I.cameraheightscale;
        nx = player1.getPosition().x;
        ny = player1.getPosition().y;
        if (false/*I.camupdateUseHuanDong*/) {
            cameraupdate(b2dcam, nx, ny);
            cameraupdate(cam, nx * PPM, ny * PPM);
        } else {
            b2dcam.position.set(nx, ny, 0);
            cam.position.set(nx * PPM, ny * PPM, 0);
            b2dcam.update();
            cam.update();
        }

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
            cam.setToOrtho(false, size * PPM* PPM * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size  * PPM* PPM);
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
        //MainActivity.use.showQucikDialog("", "pause()", new Runnable(){@Override public void run(){}});
    }

    @Override
    public void resume() {
        //恢复
        //camupdate(false);
        //MainActivity.use.showQucikDialog("恭喜", "你", new Runnable(){@Override public void run(){}});
    }
    public Player getp() {
        return player1;
    }


}

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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zyx.lost.I;
import zyx.lost.MyGdxGame;
import zyx.lost.component.InformationBar;
import zyx.lost.noworldentities.MyNoWorldGame;
import zyx.lost.noworldentities.Player;
import zyx.lost.noworldentities.Zombie;
import zyx.lost.rule.QuKuaiManager;
import zyx.lost.rule.ZMapRender;
import zyx.lost.noworldentities.EntitySet;

import java.util.Random;

public class Alpha2 extends MyNoWorldGame{
    float size = 160 * 2 / PPM ;//* Info.cameraheightscale;
    float nx,ny;
    Texture testtexture = new Texture("texture/blocks/blast_furnace_top.png");
    //TEST
    ShapeRenderer shapeRenderer;
    String receiveText;
    TextureRegion bullet;
    public float time;
    boolean next = false;
    //Music music;
    QuKuaiManager qukuaimgr;
    Random seedrandom = new Random(I.mapseed);
    //技能键

    //Texture zhujue = new Texture("assets\\player\\hp.png");

    @Override
    public void create() {
        batch = new SpriteBatch();
        fontbatch = new SpriteBatch();
        font  = MyGdxGame.ass.get("font/fontc_16.fnt", BitmapFont.class);
        // b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(int i = 0;i<256;i++){
            I.random[i] = seedrandom.nextInt(256);//-128;
        }

        //地图初始化
        {
            qukuaimgr = new QuKuaiManager();
            qukuaimgr.setcjzqkall(4,I.qukuaiTest.layer_deepdate_upleft,0);
            for(int i = 0;i<5;i++){
                for(int j = 0;j<5;j++){
                    qukuaimgr.getjizhunxianglianng(4)[0][i][j] = new Vector2(I.random[I.qukuaiTest.nextint()],I.random[I.qukuaiTest.nextint()]);
                    qukuaimgr.getjizhunxianglianng(4)[1][i][j] = new Vector2(i*16,j*16);
                    //I.qukuaiTest.s+="["+I.random[(i+j)*2]+":::"+I.random[(i+j)*2+1]+"]";
                }
                //I.qukuaiTest.s+="\n";
            }
        }

        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(cam);
        stage = new Stage(viewport);

        configureCamera();
        ib = new InformationBar();
        controlStage = new DeskTopCtrlSatge();
        controlStage.init();
        Gdx.input.setInputProcessor(controlStage);
        bullet = new TextureRegion(new Texture("texture/items/bullet.png"));
        //创建玩家
        player = new Player(stage,0,0, batch, ib);
        //创建实体集
        entityset = new EntitySet(null, stage, batch, ib);
        player.attackAback();//秋意渐浓，时光清浅
        //生成多个僵尸
        for(int i = 0;i<100;i++)entityset.create(new Zombie(stage, seedrandom.nextInt(64/2), seedrandom.nextInt(64/2), batch, ib));
        //更新一次摄像机
        camupdate(false);
        configureCamera();
        controlStage.addActor(ib);
        //TEST
        shapeRenderer = new ShapeRenderer();
        
    }

    @Override
    public void render(float p) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // keypress.key();


        time += Gdx.graphics.getDeltaTime();
        I.upCharacters = String.format("%.2f",time);
        //刷新摄像机
        //放前面以防出bug
        camupdate(true);
        //刷新世界
        batch.setProjectionMatrix(cam.combined);
        ZMapRender.render(batch,qukuaimgr.cjzqks[4],testtexture);
        
        batch.setProjectionMatrix(cam.combined);
        //刷新实体
        //这里有按键的检测和使用
        player.update();
        player.move();
        player.attack();
        //ib.addInfo("sdfsdfsdf");

        //案件以及操作的阻断
        I.StdInput.update();

        I.KeyUpdate.update();///test

        entityset.update();
        entityset.move();
        //刷新Batch
        batch.begin();
        {
            //batch.draw(zhujue,px*PPM,py*PPM,zhujue.getWidth()/2,zhujue.getHeight()/2);//更新主角位置并绘制
            
        }
        {//计算绝对坐标(float) & 更新绝对方块坐标(int)
            I.position.absolute_clickx = cam.position.x/PPM + (I.touchbgX-I.ScreenWidth/2)/I.ScreenWidth*size*I.cameraheightscale;
            I.position.absolute_clicky = cam.position.y/PPM + (I.touchbgY-I.ScreenHeight/2)/I.ScreenWidth*size*I.cameraheightscale;

            I.position.update_block_position();
        }
        batch.end();
        stage.act();
        stage.draw();
        //刷新控制层
        controlStage.act();
        controlStage.draw();
        if(I.isdebugtest){
            //DEBUG
            font.getData().setScale(2);
            fontbatch.begin();
            font.setColor(Color.RED);
            //font.draw(fontbatch, player.getPosition().x + "\n" + player.getPosition().y + "\n" + I.touchbgX+ "\n" + I.touchbgY +"\n"+I.Test.x+"\n"+I.Test.y + "\n#" + receiveText + ib.getY() + "/" + ib.getHeight() + "/" +"/"+I.jumpacount+"/"+I.controlstage_isUpa+"/"+I.controlstage_isUp+"/"+"/"+I.playerName+"\n", 0, 600);
            font.draw(fontbatch, player.getPosition().x + "\n"
            + player.getPosition().y + "\n"
            +"touchbgx"+I.touchbgX+ "\n"
            +"touchbgx/16"+I.touchbgX/16+ "\n"
            +"camx"+cam.position.x+"\n"
            +"camx/16[]"+cam.position.x/16+"\n"
            +"testx"+I.position.absolute_clickx+"\n"
            +"testy"+I.position.absolute_clicky + "\n"
            +"isclick"+I.KeyUpdate.isclickscreen +"\n"
            + entityset.bullets.size + "\n#"
            + receiveText + ib.getY() + "/" + ib.getHeight() + "/" +"/"+I.jumpacount+"/"+I.controlstage_isUpa+"/"+I.controlstage_isUp+"/"+"/"+I.playerName+"\n", 0, 600);
            fontbatch.end();
            //TEST
            shapeRenderer.setProjectionMatrix(cam.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(cam.combined);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.line(player.getPosition().x*PPM, player.getPosition().y*PPM, 0, 0);
            shapeRenderer.rect(I.position.absolute_blockX*PPM, I.position.absolute_blockY*PPM, 16, 16);
            shapeRenderer.end();
        }
    }
    //在绘图前更新相机，坐标，世界
    public void camupdate(boolean smooth) {
        // b2dcam.zoom = I.cameraheightscale;
        cam.zoom = 1 / PPM * I.cameraheightscale;
        nx = player.getPosition().x;//player1.getPosition().x;
        ny = player.getPosition().y;//player1.getPosition().y;
        if (smooth/*I.camupdateUseHuanDong*/) {
            // cameraupdate(b2dcam, nx, ny);
            cameraupdate(cam, nx * PPM, ny * PPM);
        } else {
            // b2dcam.position.set(nx, ny, 0);
            cam.position.set(nx * PPM, ny * PPM, 0);
            //b2dcam.update();
            cam.update();
        }

    }
    private void cameraupdate(Camera camera, float targetX, float targetY) {
        float dx = (targetX - camera.position.x) / 8;
        float dy = (targetY - camera.position.y) / 4;
        camera.position.add(dx, dy, 0);
        camera.update();
    }

    private void configureCamera() {
        if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth()) {
            //横屏
            // b2dcam.setToOrtho(false, size, size * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
            cam.setToOrtho(false, size * PPM * PPM, size * PPM * PPM * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
        } else {
            // b2dcam.setToOrtho(false, size * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size);
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
    
}

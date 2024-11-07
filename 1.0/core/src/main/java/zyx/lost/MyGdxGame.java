package zyx.lost;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import zyx.lost.screen.HomeScreen;
import zyx.lost.screen.LoadingScreen;

public class MyGdxGame extends Game {

    Stage topstage;
    Image image;
    
    public static AssetManager ass;
    private SpriteBatch batch;
    private BitmapFont font;
    
    public static GameManager GM;
    
    //场景
    //HomeScreen h;
    LoadingScreen loadingscreen;

    //加载完了
    public void load_finish() {
        
        //setScreen(new SettingScreen(this));
        //MainActivity.use.getversion();
        //Info.deviceinfo = MainActivity.use.getDeviceinfo();
        //multipplayerbattle = new MultipPlayerBattle(this);
        //controlstage = new ControlStage();
        //controlstage.init_stage();
        //settingscreen = new SettingScreen(this);
        //GM = new GameManager();
        //gamescreen = new GameScreen(this,GM);
        //多人对战准备
        setScreen(new HomeScreen(this));
        //setScreen(gamescreen);
    }
    
    @Override
    public void create() {
        
        // Gdx.graphics.setVSync(true);
        // Gdx.graphics.setForegroundFPS(120);
        
        batch = new SpriteBatch();

        ass = new AssetManager();
        loadingscreen = new LoadingScreen(this);
        //进入加载场景
        super.setScreen(loadingscreen);
        //加载各种奇奇怪怪的东西
        ass.load("font/fontc_16.fnt", BitmapFont.class);
        
        GM = new GameManager();
        
        //初始化过渡黑场
        topstage = new Stage();
        image = new Image(new Texture("ui/tran.jpg"));
        image.setSize(topstage.getWidth(), topstage.getHeight()); 
        image.setOrigin(topstage.getWidth() / 2, topstage.getHeight() / 2); 
        image.setColor(Color.CLEAR); 
        topstage.addActor(image); 

        font = new BitmapFont(Gdx.files.internal("font/font.fnt"),Gdx.files.internal("font/font.png"),false);
		I.font = font;
        font.getData().setScale(1.5f);
        Gdx.graphics.setForegroundFPS(120);
        Gdx.graphics.setVSync(false);
    }

    int ii = 0;
    @Override
    public void render() {
        // Draws a red background
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //渲染游戏
        //Gdx.graphics.setForegroundFPS(120);

        ii++;
        if(ii==1000){
            ii=0;
            System.gc();
        }
        
        super.render();

        topstage.act();
        topstage.draw();
        
        

        batch.begin();
		
        if(Gdx.input.isKeyJustPressed(Keys.F5))
            if(I.devmode)I.devmode = false;
            else I.devmode = true;

		if(I.devmode){
			//开发者信息
			font.setColor(Color.BLACK);
			font.draw(batch,"fps:" + Gdx.graphics.getFramesPerSecond()+"\n"+((Gdx.app.getJavaHeap() / 1048576) + (Gdx.app.getNativeHeap() / 1048576)) + "MB",2,126);
			font.setColor(Color.GREEN);
			font.draw(batch,"fps:" + Gdx.graphics.getFramesPerSecond()+"\n"+((Gdx.app.getJavaHeap() / 1048576) + (Gdx.app.getNativeHeap() / 1048576)) + "MB"+I.E,0,128);
            font.draw(batch, I.E, 0, 70);
		}
        
        batch.end();
        //State.screenstate.update(this);
        
		if(LoadingScreen.isOk){
            LoadingScreen.isOk = false;
			I.fontc16 = ass.get("font/fontc_16.fnt", BitmapFont.class);
			Asst.load();
            load_finish();
		}
        if(!LoadingScreen.isOk&Asst.isOK){
			Asst.isOK = false;
            load_finish();
		}
        
        
    }
    //游戏过渡动作
    public void transition(Color c, float t) {
        image.setColor(c);
        image.addAction(Actions.color(Color.CLEAR, t));
    }

    public void transition() {
        image.setColor(Color.BLACK);
        image.addAction(Actions.color(Color.CLEAR, 0.5f));
    }
    
    //当手机分辨率变化时
    @Override
    public void resize(int width, int height)
    {
        super.resize(width, height);
    }
    //当游戏进入后台
    @Override
    public void pause()
    {
        //MainActivity.use.showQuickTip("暂停");
		super.pause();
    }
    //当游戏进程回复时
    @Override
    public void resume()
    {
        //MainActivity.use.showQuickTip("返回");
		super.resume();
	}

    @Override
    public void setScreen(Screen screen) {
        transition();
        super.setScreen(screen);
    }
}


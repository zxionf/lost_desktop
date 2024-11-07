package zyx.lost.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import zyx.lost.ControlStage;
import zyx.lost.I;
import zyx.lost.ThreeDeController;
import zyx.lost.component.InformationBar;
import zyx.lost.entities.Player;
import zyx.lost.entities.EntitySet;

public abstract class MyGame implements ApplicationListener {
    
    World world;
    public Stage stage;
    public ControlStage controlStage;
    public ThreeDeController tdStage;
    public InformationBar ib;
    TiledMap map;
    OrthogonalTiledMapRenderer ren;
    public OrthographicCamera cam,b2dcam;
    SpriteBatch batch;
    Batch fontbatch;
    BitmapFont font;
    
    public float PPM = I.PPM;
    public Player player1,player2;
    
    public EntitySet entityset;
    
    
    @Override
    public abstract void create();

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(){}
    
    public void render(float p) {
        render();
    }
    @Override
    public void resize(int p, int p1) {
    }

    @Override
    public void resume() {
    }
    
    public void setView(){
        
    }
    
}

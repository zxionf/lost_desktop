package zyx.lost.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import zyx.lost.MyGdxGame;
import zyx.lost.game.MainGame;

public class GameScreen extends AbstractScreen {
    
    
    public GameScreen(MyGdxGame game) {
        super(game);
        //常规启动
        MyGdxGame.GM.setGame(new MainGame());
        MyGdxGame.GM.getGame().create();

        //现在的启动
        // MyGdxGame.GM.setGame(new Alpha2());
        // MyGdxGame.GM.getnoworldGame().create();
    }

    float pp = 0;
    @Override
    public void render(float p) {
       // pp+= p;
       // if(pp>0.03){
           // pp = 0;
            Gdx.gl.glClearColor(0,0,0,0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            MyGdxGame.GM.update();
            MyGdxGame.GM.render(p);
        //}
        
        
    }
    
    

    @Override
    public void resize(int p, int p1) {
    }
    @Override
    public void show() {
    }
    @Override
    public void hide() {
    }
    
}

package zyx.lost.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zyx.lost.MyGdxGame;

public class LoadingScreen extends AbstractScreen {

    public LoadingScreen(MyGdxGame game) {
        super(game);
    }

    private BitmapFont font;
    private Batch batch;

    Texture processbar;
    TextureRegionDrawable zx;

    public static boolean isOk;

    private Texture R(int weight, int height) {
        Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x000000cc);
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
        font.getData().setScale(3f);
        font.setColor(Color.BLACK);

        zx = new TextureRegionDrawable(new TextureRegion(new Texture("zx_small.png")));
        //zx = new Texture(Gdx.files.internal("zx_small.png"));
        zx.setMinSize(512, 256);
        //hp = new Texture(Gdx.files.internal("res/hp.png"));
        processbar = R(1, 100);
        //MFont.load();

//        new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //Looper.prepare();
//                    //Looper.loop();
//                    
//                    
//                    
//                }
//            }).start();
        
		}

    @Override
    public void render(float delta) {
        // Draws a red background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float s = MyGdxGame.ass.getProgress();
		

        batch.begin();
        zx.draw(batch, (Gdx.graphics.getWidth() - 512) / 2, (Gdx.graphics.getHeight() - 128) / 2, 512, 256);
        batch.draw(processbar, 0, 0, Gdx.graphics.getWidth() * s, 50);
        font.getData().setScale(3);
        font.draw(batch, "Loading:" + (int)(s * 100) + "%", Gdx.graphics.getWidth() / 2 - 160, Gdx.graphics.getHeight() / 4);
        font.getData().setScale(1.5f);
        if(MyGdxGame.ass.getProgress()>0.04f){
            //String b = MyGdxGame.ass.getAssetNames().get(MyGdxGame.ass.getAssetNames().size-1);
            font.draw(batch,MyGdxGame.ass.getAssetNames().get(MyGdxGame.ass.getAssetNames().size-1), Gdx.graphics.getWidth()/ 2 - 160, 132);
        }
        batch.end();

        if (MyGdxGame.ass.update()) {
            isOk = true;
		}
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }
    
    
}

package zyx.lost.screen;

import com.badlogic.gdx.Gdx;
import zyx.lost.MyGdxGame;
import zyx.lost.scenes.Simple;
import zyx.lost.scenes.Scene;
import zyx.lost.scenes.SceneFirst;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

public class HomeScreen extends AbstractScreen {

	Image transImage;
	Stage stage;


	//scenes
	Simple scenes_simple = new Simple();
	SceneFirst scenes_first = new SceneFirst(this);

	public HomeScreen(MyGdxGame game) {
		super(game);
		// Gdx.graphics.setForegroundFPS(30);
		// Gdx.graphics.setVSync(false);
		MyGdxGame.GM.setScene(scenes_first);
		MyGdxGame.GM.getScene().create();

		stage = new Stage();
		transImage = new Image(new Texture("ui/tran.jpg"));
        transImage.setSize(stage.getWidth(), stage.getHeight()); 
        transImage.setOrigin(stage.getWidth() / 2, stage.getHeight() / 2); 
        transImage.setColor(Color.CLEAR); 
        stage.addActor(transImage); 

	}

	@Override
	public void render(float p) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		MyGdxGame.GM.sceneRender();

		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int p, int p1) {
	}

	public int getintrand(int MIN, int MAX) {
		Random rand = new Random();
		// int MAX = 100, MIN = 1;
		return rand.nextInt(MAX - MIN + 1) + MIN;
	}
	
	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	public void setSceneWtran(Scene s){
		transotion();
		MyGdxGame.GM.setScene(s);
	}
	public void transotion(){
		transImage.setColor(Color.BLACK);
        transImage.addAction(Actions.color(Color.CLEAR, 0.5f));
	}
}

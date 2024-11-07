package zyx.lost.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import zyx.lost.I;
import zyx.lost.component.RectTextue;
import zyx.lost.component.Typingline;
import zyx.lost.component.ZButton;
import zyx.lost.screen.HomeScreen;

public class SceneFirst extends Scene{
	HomeScreen hmscreen;
    ZButton.TextBtn startbtn,tuichubtn;
	Batch batch = new SpriteBatch();
	BitmapFont font;
	Label l;
	Typingline typingline;
	Image click = new Image(/*new TextureRegionDrawable(RectTextue.RGBArectangle(256, 64, 0xff0000ff))*/);

	public SceneFirst(HomeScreen screen){
		hmscreen = screen;
	}

    @Override
    public void create() {
        startbtn = new ZButton.TextBtn("开始游戏", new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				typingline.setVisible(true);
				typingline.setTyping(true);
				click.setVisible(true);
				l.setVisible(true);
				正式开始();
			}
		});
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		stage.addActor(startbtn);
		startbtn.setPosition(I.ScreenWidth / 2-startbtn.getWidth()/2, I.ScreenHeight / 2-startbtn.getHeight()/2);

		//退出按钮
		tuichubtn = new ZButton.TextBtn("退出游戏", new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(tuichubtn);
		tuichubtn.setPosition(I.ScreenWidth / 2-startbtn.getWidth()/2, I.ScreenHeight / 2-startbtn.getHeight()/2-100);


		Label.LabelStyle ls = new Label.LabelStyle();
		I.fontcy16x2.getData().markupEnabled = true;
		ls.font = I.fontcy16x2;
		ls.font.setColor(Color.WHITE);
		ls.font.getData().setScale(2);
		ls.font.getData().markupEnabled = true;
		// Gdx.input.setInputProcessor(this.stage);
		l = new Label("开始退出", ls);
		l.setPosition( 230, 40);
		stage.addActor(l);
		l.setVisible(false);

        typingline = new Typingline(ls, "寻找开始按钮进入下一关", 0.02f, 1);
		
        stage.addActor(typingline);
        // typingline.setTyping(true);
        typingline.setPosition(I.ScreenWidth/2, I.ScreenHeight/2+200);

		click.setSize(256, 64);
		click.setPosition(I.ScreenWidth/2+128, I.ScreenHeight/2+190);
		click.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y) {
				// if(x>=128&&x<=256&&y<=240&&y>=190)System.out.println("yeyee");;
				click.setVisible(false);
				typingline.getLabel().addAction(Actions.alpha(0, 0.5f));
				typingline.addAction(Actions.delay(1f,Actions.run(new Runnable() {
					@Override
					public void run() {
						typingline.setString("关卡二");
						typingline.getLabel().addAction(Actions.alpha(1));
						typingline.setTyping(true);
					}
				})));
				typingline.getLabel().addAction(Actions.delay(2f,Actions.alpha(0,0.5f)));
				typingline.getLabel().addAction(Actions.delay(2.5f,Actions.run(new Runnable(){
					@Override
					public void run() {
						hmscreen.setSceneWtran(new Simple());
					}
				})));
			}
		});
		stage.addActor(click);
		click.setVisible(false);
		// Gdx.input.setInputProcessor(this.stage);

		font = I.fontcy16x2;
    }
    @Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		batch.begin();
		batch.end();
	}

	private void 正式开始(){
		startbtn.addAction(Actions.alpha(0,0.2f));
		tuichubtn.addAction(Actions.alpha(0,0.2f));
		startbtn.addAction(Actions.delay(0.2f,Actions.run(new Runnable() {
			@Override
			public void run() {
				startbtn.remove();
				tuichubtn.remove();
			}
		})));
		
	}
}

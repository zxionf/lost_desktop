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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import zyx.lost.I;
import zyx.lost.component.Typingline;
import zyx.lost.component.ZButton;
import zyx.lost.game.MyGame;
import zyx.lost.rule.Cahrcn;

public class Simple extends Scene{

    // Stage stage = new Stage();
	ZButton.TextBtn startbtn;
	Label l;
	Batch batch = new SpriteBatch();
	BitmapFont font;
	

	// public Simple(){}

	// public Simple(HomeScreen homescreen){
	// 	this.homescreen = homescreen;
	// }

	// int start = Integer.parseInt("4e00", 16);
	// int end = Integer.parseInt("9fa5", 16);
	int i = 0;
	int end = Cahrcn.basicSentence.length();

	int ii = 0;

	public Simple(){
		create();
	}

	boolean isstartbtnusable=true;

    Typingline typingline;

    @Override
    public void create() {
        startbtn = new ZButton.TextBtn("开始游戏", new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				typingline.setTyping(true);
			}
		});
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		stage.addActor(startbtn);
		startbtn.setPosition(I.ScreenWidth / 2-75, I.ScreenHeight / 2-25);

		Label.LabelStyle ls = new Label.LabelStyle();
		I.fontcy16x2.getData().markupEnabled = true;
		ls.font = I.fontcy16x2;
		ls.font.setColor(Color.WHITE);
		ls.font.getData().setScale(2);
		ls.font.getData().markupEnabled = true;
		l = new Label("", ls);
		stage.addActor(l);
		l.setPosition(0, 0);
		l.setSize(600, 600);
		l.setScale(16);
		// Gdx.input.setInputProcessor(this.stage);

        typingline = new Typingline(ls, "sdgfdsfgdsfgdsfgsdfgddfjkhg", 0.02f, 1);
        stage.addActor(typingline);
        // typingline.setTyping(true);
        typingline.setPosition(I.ScreenWidth/2, I.ScreenHeight/2+200);

		/*
		 * FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
		 * Gdx.files.internal( "font/siyuanrouheiMonospaceRegular.ttf") );
		 * FreeTypeFontGenerator.FreeTypeFontParameter p = new
		 * FreeTypeFontGenerator.FreeTypeFontParameter();
		 * p.color = Color.BLACK;
		 * p.size = 60;
		 * font = generator.generateFont(p);
		 */

		font = I.fontcy16x2;
    }
    @Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// if(startbtn.isOver()){
		// startbtn.setStyle(Asst.TextBtnStyle.NormalOver);
		// }

		stage.act();
		stage.draw();
		batch.begin();
		// if(i<=end)i++;

		ii++;
		if (ii == 20 * 4) {
			if (i == end)
				i = 0;
			ii = 0;
			i++;
		}

		// I.fontc16.setColor(Color.GRAY);
		// I.fontc16.draw(batch,"开始游戏",600,600);
		// font.draw(batch,""+(char)i,I.ScreenWidth/3,I.ScreenHeight/2);
		l.setText("" + Cahrcn.basicSentence.charAt(i));
		// l.setText(""+(char)i);

		batch.end();
	}

	private void gotogame(){
		l.addAction(Actions.alpha(0,0.2f));
		startbtn.addAction(Actions.alpha(0,0.2f));

		Actions.delay(0.2f,Actions.run(new Runnable() {
			@Override
			public void run() {
				l.remove();
				startbtn.remove();
			}
		}));
	}
}

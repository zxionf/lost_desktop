package zyx.lost.noworldentities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import zyx.lost.I;
import zyx.lost.MyGdxGame;
import zyx.lost.component.InformationBar;
import zyx.lost.component.RectTextue;

public class Zombie extends Entity {
Label.LabelStyle ls,ils;
    public Label label;
    ProgressBar HPBar;
    TextureRegionDrawable lsbg;
    //GlyphLayout layout = new GlyphLayout();
    InformationBar ib;

    @Override
    public void create() {
        ProgressBar.ProgressBarStyle pbs = new ProgressBar.ProgressBarStyle();
        TextureRegionDrawable bg =new TextureRegionDrawable(new TextureRegion(new Texture("ui/down_60.png")));
        bg.setMinSize(1/I.PPM,12/I.PPM);
        pbs.knobAfter =bg;
        TextureRegionDrawable knobbefore =new TextureRegionDrawable(new TextureRegion(RectTextue.RGBArectangle(50,50,0xff0000bb)));
        knobbefore.setMinSize(1/I.PPM,12/I.PPM);
        pbs.knobBefore = knobbefore;

        ils = new Label.LabelStyle();
        ils.font = MyGdxGame.ass.get("font/fontc_16.fnt",BitmapFont.class);
        ils.font.getData().setScale(0.25f);
        ils.font.getData().markupEnabled = true;

        HPBar = new ProgressBar(0,200,1,false,pbs);
        HPBar.setPosition(10,5);
        HPBar.setSize(24,2);
        stage.addActor(HPBar);
    }




    public Zombie(Stage stage,int x,int y,SpriteBatch batch,InformationBar ib) {
        this.stage = stage;
        this.position = new Vector2(x,y);
        this.batch = batch;
        this.ib = ib;
        textureregion = new TextureRegion(new Texture("player/hp.png"));

        maxspeed = 4;
        height = 12;
        width = 6;
        DamageValue = 4;
        HP = 200;
        //this.body.setGravityScale(2);
        create();
    }

    public void update(){

        HPBar.setValue(HP);
        HPBar.setPosition(getPosition().x * I.PPM - 12, getPosition().y * I.PPM + 15);

        //监听状态
        if(HP <= 0){
            HP = 200;
            ib.addInfo("[YELLOW]"+I.playerName+"[WHITE]死亡");
            dispose();
        }

        batch.begin();
        batch.draw(textureregion, getPosition().x * I.PPM - width, getPosition().y * I.PPM - height, 2*width, 2*height);
        batch.end();

    }

    public void receiveAttack(Bullet b){//这里可能会出问题
        DeductHP(b.DamageValue);
    }

    public void move() {
        //move.key();
        targetX = MyGdxGame.GM.getnoworldGame().player.position.x;
        targetY = MyGdxGame.GM.getnoworldGame().player.position.y;
        position.add((targetX-position.x)/100, (targetY-position.y)/100);
    }

    public Image getTexture(){
        return new Image(textureregion);
    }
    public void DeductHP(int d){
        HP -= d;
        Label damagelabel = new Label("[RED]-"+d,ils);
        damagelabel.setFontScale(0.25f);
        damagelabel.setPosition(getPosition().x*I.PPM,getPosition().y*I.PPM);
        damagelabel.addAction(Actions.moveTo(getPosition().x*I.PPM,getPosition().y*I.PPM+6,0.5f,Interpolation.smoother));

        damagelabel.addAction(Actions.delay(0.5f,Actions.removeActor(damagelabel)));
        stage.addActor(damagelabel);
    }
    @Override
    public void dispose() {
        MyGdxGame.GM.getnoworldGame().entityset.entities.removeValue(this, false);
        ls = null;
        ils = null;
        HPBar.remove();
        HPBar = null;
        lsbg = null;
    }
}
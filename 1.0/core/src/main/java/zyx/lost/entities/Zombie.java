package zyx.lost.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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
        TextureRegionDrawable knobbefore =new TextureRegionDrawable(new TextureRegion(RectTextue.RGBArectangle(50,50,0x00ff00bb)));
        knobbefore.setMinSize(1/I.PPM,12/I.PPM);
        pbs.knobBefore = knobbefore;

        HPBar = new ProgressBar(0,200,1,false,pbs);
        HPBar.setPosition(10,5);
        HPBar.setSize(24,2);
        stage.addActor(HPBar);

        ils = new Label.LabelStyle();
        ils.font = MyGdxGame.ass.get("font/fontc_16.fnt",BitmapFont.class);
        ils.font.getData().setScale(0.25f);
        ils.font.getData().markupEnabled = true;

        ls = new Label.LabelStyle();
        ls.font = MyGdxGame.ass.get("font/fontc_16.fnt",BitmapFont.class);
        ls.font.getData().setScale(0.25f);
        ls.font.getData().markupEnabled = true;
        lsbg = new TextureRegionDrawable(RectTextue.RGBArectangle(10, 8,0x00000040));
        ls.background =lsbg;
        label = new Label("[RED]Z[BLACK]X[GREEN]I[GRAY]O[BLUE]N[GOLD]F[]",ls);
        //label.getStyle().background = new TextureRegionDrawable(RectangleBackGround.RGBArectangle((int)(8),(int)(8), RectangleBackGround.BLACK60));
        label.setPosition(body.getPosition().x*I.PPM,body.getPosition().y*I.PPM);
        stage.addActor(label);

    }




    public Zombie(World world , Stage stage ,int x,int y,SpriteBatch batch,InformationBar ib) {
        this.init();
        this.world = world;
        this.stage = stage;
        this.batch = batch;
        this.ib = ib;
        textureregion = new TextureRegion(new Texture("player/zxionf.png"));

        maxspeed = 4;
        height = 12;
        width = 6;
        DamageValue = 4;
        HP = 200;

        bdef.type = BodyDef.BodyType.DynamicBody;

        bdef.position.set(30 /I. PPM, 30 /I. PPM);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width /I. PPM, height /I. PPM);
        fdef.shape = shape;
        fdef.density = 0;

        fdef.filter.groupIndex = I.b2dValue.player;
        this.body = world.createBody(bdef);
        this.body.createFixture(fdef).setUserData("palyer");

        //创建传感器
        shape.setAsBox(width/2 /I. PPM, 1 /I. PPM,new Vector2(0,-12/I.PPM),0);
        fdef.shape = shape;
        fdef.filter.groupIndex = I.b2dValue.player;
        fdef.isSensor = true;
        this.body.createFixture(fdef).setUserData("foot");


        //this.body.setGravityScale(2);
        create();
    }

    public void update(){
        HPBar.setValue(HP);
        HPBar.setPosition((float)getPosition().x * I.PPM - 12, getPosition().y * I.PPM + 15);
        //label
        label.setFontScale(0.25f);
        label.setText(I.playerName);
        //layout.setText(ls.font,Info.playerName);
        //label.setWidth(layout.width/8);
        label.setWidth(label.getPrefWidth());
        label.setHeight(label.getPrefHeight());
        label.setPosition((getPosition().x-label.getWidth()/2/I.PPM)* I.PPM, (getPosition().y+1.4f)* I.PPM);

        //监听玩家状态
        if(HP <= 0){
            HP = 200;
            ib.addInfo("[YELLOW]"+I.playerName+"[WHITE]死亡");
        }

        batch.begin();
        batch.draw(textureregion, getPosition().x * I.PPM - width, getPosition().y * I.PPM - height, 2*width, 2*height);
        batch.end();
    }

    @Override
    public void move() {
        
    }

    public Image getTexture(){
        return new Image(textureregion);
    }

    public void DeductHP(){
        HP -= DamageValue;
        Label damagelabel = new Label("[RED]-"+DamageValue,ils);
        damagelabel.setFontScale(0.25f);
        damagelabel.setPosition(getPosition().x*I.PPM,getPosition().y*I.PPM);
        damagelabel.addAction(Actions.moveTo(getPosition().x*I.PPM,getPosition().y*I.PPM+6,0.5f,Interpolation.smoother));

        damagelabel.addAction(Actions.delay(0.5f,Actions.removeActor(damagelabel)));
        stage.addActor(damagelabel);
    }

    @Override
    protected void dispose() {
        MyGdxGame.GM.getGame().entityset.entities.removeValue(this, false);
        ls = null;
        ils = null;
        HPBar.remove();
        HPBar = null;
        lsbg = null;
        //throw new UnsupportedOperationException("Unimplemented method 'dispose'");
    }

}

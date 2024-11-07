/*
 *2024/20/22/zxionf
 */
package zyx.lost.noworldentities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

import zyx.lost.GameManager;
import zyx.lost.I;
import zyx.lost.MyGdxGame;
import zyx.lost.component.InformationBar;
import zyx.lost.component.RectTextue;
import zyx.lost.game.Keypressed;

public class Player extends Entity {

    Label.LabelStyle ls,ils;
    public Label label;
    ProgressBar HPBar;
    TextureRegionDrawable lsbg;
    //GlyphLayout layout = new GlyphLayout();
    InformationBar ib;

    Keypressed move = new Keypressed() {

        @Override
        public void key() {
                //角色移动逻辑
                if(Gdx.input.isKeyPressed(Input.Keys.W))position.y += 0.1f;
                if(Gdx.input.isKeyPressed(Input.Keys.S))position.y -= 0.1f;
                if(Gdx.input.isKeyPressed(Input.Keys.A))position.x -= 0.1f;
                if(Gdx.input.isKeyPressed(Input.Keys.D))position.x += 0.1f;
                if(Gdx.input.isKeyPressed(Input.Keys.J)){
                    I.StdInput.clickAttack_is_ZuDuan = true;
                    I.KeyUpdate.isclickscreen = true;
                }
        }
        
    };

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
        label.setPosition(getPosition().x*I.PPM,getPosition().y*I.PPM);
        stage.addActor(label);

    }




    public Player(Stage stage,int x,int y,SpriteBatch batch,InformationBar ib) {
        //this.init();
        //this.world = world;
        this.stage = stage;
        this.position = new Vector2(x,y);
        this.batch = batch;
        this.ib = ib;
        textureregion = new TextureRegion(new Texture("player/zxionf.png"));

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

            GameManager.viewState = GameManager.DEAD_VIEW;
            HP = 200;
            ib.addInfo("[YELLOW]"+I.playerName+"[WHITE]死亡");
        }

        batch.begin();
        batch.draw(textureregion, getPosition().x * I.PPM - width, getPosition().y * I.PPM - height, 2*width, 2*height);
        batch.end();

    }

    public void move() {
        move.key();
        if(MyGdxGame.GM.getnoworldGame().entityset.entities.size != 0&&I.StdInput.clickAttack_is_available&&I.KeyUpdate.isclickscreen){
            Entity e;
            e = MyGdxGame.GM.getnoworldGame().entityset.getNearestEntity(position.x, position.y);
            if(e!=null)MyGdxGame.GM.getnoworldGame().entityset.create(new Bullet(stage, batch, this.position.x, this.position.y, 10, new Vector2(e.position.x-this.position.x,e.position.y-this.position.y)));
        }
    }
    public Entity attackAback(){
        //ib.addInfo("d6d");
        if(I.KeyUpdate.isclickscreen)MyGdxGame.GM.getnoworldGame().entityset.create(new Bullet(stage, batch, 1, 1, 1, new Vector2(1,1)));
        return new Bullet(stage, batch, 1, 1, 1, angle);
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
    public void dispose() {
        
    }

}

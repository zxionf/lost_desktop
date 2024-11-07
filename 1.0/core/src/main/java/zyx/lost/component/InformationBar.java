package zyx.lost.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import zyx.lost.MyGdxGame;
import zyx.lost.I;

public class InformationBar extends Table {

    Label.LabelStyle labelstyle;
    int i = 0;
    Label l;
    public InformationBar() {
        labelstyle = new Label.LabelStyle();
        labelstyle.font = MyGdxGame.ass.get("font/fontc_16.fnt", BitmapFont.class);
        labelstyle.font.getData().setScale(2);
        labelstyle.fontColor = Color.WHITE;
        labelstyle.background = new TextureRegionDrawable(RectTextue.RGBArectangle(400, 32, 0x00000099));
        this.setSize(400,400);
        /*this.addListener(new ClickListener(){
         public void clicked(InputEvent event, float x, float y) {
         //l.setPosition(l.getX()+50,l.getY());
         GameManager.viewState = GameManager.INFO_VIEW;
         }
         });*/
        //this.debug();
        this.align(Align.bottomLeft);

    }
    public void addInfo(String s) {
        i++;
        l = new Label("",labelstyle);
        l.setWidth(400);
        l.setText(i+s);
        l.setWrap(true);

        //l.addAction(Actions.delay(2.5f,Actions.removeActor(l)));
        l.addAction(Actions.delay(2.5f,Actions.fadeOut(0.5f)));
        l.addAction(Actions.delay(3,Actions.removeActor(l)));
        /*l.addAction(Actions.addListener(new EventListener(){

         @Override
         public boolean handle(Event event) {

         return false;
         }
         }, true));*/
        I.EventRecorder += s+"\n";
        this.add(l).width(400).maxWidth(400).left().row();
        this.setPosition(40, 150);
    }

}

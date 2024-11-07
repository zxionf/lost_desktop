package zyx.lost.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import zyx.lost.I;
import zyx.lost.component.RectTextue;

public class DeskTopCtrlSatge extends Stage{
    public void init(){
        Image touchbg = new Image(new TextureRegionDrawable(RectTextue.RGBArectangle(2, 2, 0x00000000)));
        touchbg.setSize(I.ScreenWidth, I.ScreenHeight);
        touchbg.setPosition(0, 0);
        this.addActor(touchbg);
        touchbg.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.touchbgX = x;
                    I.touchbgY = y;
                    I.KeyUpdate.isclickscreen = true;
                    I.StdInput.clickAttack_is_ZuDuan = true;
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }@Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    I.touchbgX = x;
                    I.touchbgY = y;
                    //return super.touchDown(event, x, y, pointer, button);
                }
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    I.KeyUpdate.isclickscreen = false;
                }
            });
    }
    
}

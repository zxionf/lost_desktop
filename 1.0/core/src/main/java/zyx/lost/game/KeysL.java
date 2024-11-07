package zyx.lost.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zyx.lost.component.RectTextue;
import zyx.lost.I;

public class KeysL extends Stage {
    public int CtlbtnSize = 150;
    Image image_left,image_right,image_up,image_down,image_attrack_near;
    void init() {
        image_left = new Image(new TextureRegion(new Texture("ui/left.png")));
        image_right = new Image(new TextureRegion(new Texture("ui/right.png")));
        image_up = new Image(new TextureRegion(new Texture("ui/up.png")));
        image_down = new Image(new TextureRegion(new Texture("ui/down.png")));

        Image touchbg = new Image(new TextureRegionDrawable(RectTextue.RGBArectangle(2, 2, 0x00000000)));
        touchbg.setSize(I.ScreenWidth, I.ScreenHeight);
        touchbg.setPosition(0, 0);
        this.addActor(touchbg);
        touchbg.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.touchbgX = x;
                    I.touchbgY = y;
                    if (I.pixshape) {
                        I.pixshape = false;
                    } else I.pixshape = true;
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }@Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    I.touchbgX = x;
                    I.touchbgY = y;

                    //return super.touchDown(event, x, y, pointer, button);
                }
            });

        image_attrack_near = new Image(new TextureRegion(new Texture("ui/attack_near.png")));

        image_left.setSize(CtlbtnSize, CtlbtnSize);
        image_right.setSize(CtlbtnSize, CtlbtnSize);
        image_up.setSize(CtlbtnSize, CtlbtnSize);
        image_down.setSize(CtlbtnSize, CtlbtnSize);

        image_attrack_near.setSize(150 * 3, 150);
        image_attrack_near.setPosition(40, 0);

        image_left.setPosition(40, CtlbtnSize);
        image_down.setPosition(40 + CtlbtnSize * 1, CtlbtnSize);
        image_right.setPosition(40 + CtlbtnSize * 2, CtlbtnSize);
        image_up.setPosition(40 + CtlbtnSize * 1, CtlbtnSize * 2);

        
        addActor(image_left);
        addActor(image_right);
        addActor(image_up);
        addActor(image_down);
        addActor(image_attrack_near);

        
    }

    @Override
    public void act() {
        super.act();
        /*if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
         image_left.addAction(Actions.color(Color.WHITE,0.2f));
         //image_left.addAction(Actions.delay(0.1f,Actions
         }*/
       
    }
    
    // public void updatekey(){
    //     if (I.key_d) {
    //         image_right.addAction(Actions.alpha(0.1f));
    //         //image_right.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (I.key_a) {
    //         image_left.addAction(Actions.alpha(0.1f));
    //         //image_left.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (I.key_w) {
    //         image_up.addAction(Actions.alpha(0.1f));
    //         //image_up.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (I.key_s) {
    //         image_down.addAction(Actions.alpha(0.1f));
    //         //mage_down.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }/*if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
    //      image_attrack_near.addAction(Actions.alpha(0.1f, 0.1f));
    //      image_attrack_near.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //      }*/if (I.key_space) {
    //         image_attrack_near.addAction(Actions.alpha(0.1f));
    //         //image_attrack_near.addAction(Actions.delay(0.1f,Actions.alpha(1)));
    //     }
        
        
        
    //     if (!I.key_d) {
    //         image_right.addAction(Actions.alpha(1));
    //         //image_right.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (!I.key_a) {
    //         image_left.addAction(Actions.alpha(1));
    //         //image_left.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (!I.key_w) {
    //         image_up.addAction(Actions.alpha(1));
    //         //image_up.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }if (!I.key_s) {
    //         image_down.addAction(Actions.alpha(1));
    //         //mage_down.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //     }/*if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
    //      image_attrack_near.addAction(Actions.alpha(0.1f, 0.1f));
    //      image_attrack_near.addAction(Actions.delay(0.1f, Actions.alpha(1)));
    //      }*/if (!I.key_space) {
    //         image_attrack_near.addAction(Actions.alpha(1));
    //         //image_attrack_near.addAction(Actions.delay(0.1f,Actions.alpha(1)));
    //     }
    // }

}

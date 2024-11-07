package zyx.lost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import zyx.lost.component.RectTextue;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class ThreeDeController extends Stage {

    public int CtlbtnSize = 150;

    public Touchpad touchPad;
    Touchpad.TouchpadStyle touchpadstyle;
    CameraInputController camctr;
    PerspectiveCamera cam;
    //InformationBar ib = new InformationBar();

    public void init_stage(PerspectiveCamera cam) {
        this.cam = cam;
        Image image_left= new Image(new TextureRegion(new Texture("ui/left.png")));
        Image image_right = new Image(new TextureRegion(new Texture("ui/right.png")));
        Image image_up = new Image(new TextureRegion(new Texture("ui/up.png")));
        Image image_down = new Image(new TextureRegion(new Texture("ui/down.png")));

        Image image_bag = new Image(new TextureRegion(new Texture("ui/bag.png")));

        Image image_setting = new Image(new TextureRegion(new Texture("ui/bag.png")));

        Image image_attrack_near = new Image(new TextureRegion(new Texture("ui/attack_near.png")));
        
        Image image_camctr = new Image(new TextureRegionDrawable(RectTextue.RGBArectangle(10,10,0x00000000)));
        image_camctr.setSize(I.ScreenWidth,I.ScreenHeight);
        image_camctr.setPosition(0,0);
        this.addActor(image_camctr);
        camctr = new CameraInputController(cam,0);
        camctr.setSize(I.ScreenWidth,I.ScreenHeight);
        this.addActor(camctr);

        image_left.setSize(CtlbtnSize, CtlbtnSize);
        image_right.setSize(CtlbtnSize, CtlbtnSize);
        image_up.setSize(CtlbtnSize, CtlbtnSize);
        image_down.setSize(CtlbtnSize, CtlbtnSize);
        image_bag.setSize(CtlbtnSize, CtlbtnSize);

        image_setting.setSize(600, 600);
        image_setting.setPosition(I.ScreenWidth-600, I.ScreenHeight - 650);
        image_setting.setColor(1,1,1,0.3f);

        image_attrack_near.setSize(150, 150);
        image_attrack_near.setPosition(I
                                       .ScreenWidth - 300, I.ScreenHeight / 2);

        image_left.setPosition(40, 150);
        image_down.setPosition(190,0);
        image_right.setPosition(40+300,150);
        image_up.setPosition(190,300);
        image_bag.setPosition(Gdx.graphics.getWidth() - CtlbtnSize, 0);

        image_left.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isLeft = true;
                    I.controlstage_isStop = false;
                    //MyGdxGame.GM.getGame().cam.translate(-1,0);
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isStop = true;
                    I.controlstage_isLeft  = false;
                    // super.touchUp(event, x, y, pointer, button);
                }

            });

        image_right.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isRight  = true;
                    I.controlstage_isStop = false;
                    //MyGdxGame.GM.getGame().cam.translate(+1,0);
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isStop = true;
                    I.controlstage_isRight  = false;
                    //super.touchUp(event, x, y, pointer, button);
                }

            });
        image_up.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isUp = true;
                    //MyGdxGame.GM.getGame().cam.translate(0,1);
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isUp = false;
                    //super.touchUp(event, x, y, pointer, button);
                }

            });
        image_down.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isDown = true;
                    //MyGdxGame.GM.getGame().cam.translate(0,-1);
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    I.controlstage_isDown = false;
                    //super.touchUp(event, x, y, pointer, button);
                }

            });
        image_bag.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    GameManager.viewState = GameManager.PPW_VIEW;
                }

            });
        


        image_attrack_near.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                    //return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //State.screenstate.screenflag = State.screenstate.setting;
                    //GameManager.viewState = GameManager.SETTING_VIEW;
                    //MyGdxGame.GM.getGame().player2.DeductHP();
                }

            });

        touchpadstyle = new Touchpad.TouchpadStyle();
        touchpadstyle.background = new TextureRegionDrawable(new TextureRegion(R(300, 300)));
        touchpadstyle.knob = new TextureRegionDrawable(new TextureRegion(R2(100, 100)));
        touchPad = new Touchpad(0, touchpadstyle);
        touchPad.setPosition(900, 50);
        touchPad.addListener(new InputListener(){

                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    //return super.touchDown(event, x, y, pointer, button);

                    return true;
                }
                
                
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    //State.screenstate.screenflag = State.screenstate.setting;
                    //Info.bulletposition[0] = Info.player1.position.x;
                    //Info.bulletposition[1] = Info.player1.position.y;
                    //Info.bulleta[0] = touchPad.getKnobPercentX()*2;
                    //Info.bulleta[1] = touchPad.getKnobPercentY()*2;
                    //     I.bulleta[0] = touchPad.getKnobPercentX() * 1000; //x-touchPad.getX();
                    //MultipPlayerBattle.time = 0;
                }

            });
        addActor(touchPad);

        addActor(image_left);
        addActor(image_right);
        addActor(image_up);
        addActor(image_down);
        addActor(image_bag);
        addActor(image_setting);
        addActor(image_attrack_near);
//        ScrollPane.ScrollPaneStyle scrollpanestyle = new ScrollPane.ScrollPaneStyle();
//        Table table = new Table();
//        scrollpanestyle.background = new TextureRegionDrawable(RectangleBackGround.RGBArectangle(300, 50, RectangleBackGround.BLACK60));
//        final ScrollPane scrollpane = new ScrollPane(table, scrollpanestyle);
//        scrollpane.setHeight(50);
//        scrollpane.setWidth(300);
//        scrollpane.setPosition(200,300);
//        scrollpane.addListener(new ClickListener(){
//                public void clicked(InputEvent event, float x, float y) {
//                    scrollpane.setPosition(scrollpane.getX()+50,scrollpane.getY());
//                }
//            });
//        addActor(scrollpane);

        Image image_chat = new Image(new TextureRegion(new Texture("ui/bag.png")));
        image_chat.setSize(50, 50);
        image_chat.setPosition((I.ScreenWidth - 100) / 2, I.ScreenHeight - 50);
        image_chat.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    //l.setPosition(l.getX()+50,l.getY());
                    GameManager.viewState = GameManager.INFO_VIEW;
                }
            });
        this.addActor(image_chat);

        Image image_map = new Image(new TextureRegion(new Texture("ui/bag.png")));
        image_map.setSize(50, 50);
        image_map.setPosition((I.ScreenWidth) / 2, I.ScreenHeight - 50);
        image_map.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    //l.setPosition(l.getX()+50,l.getY());
                    GameManager.viewState = GameManager.MAP_VIEW;
                }
            });
        this.addActor(image_map);
        
         
    }
    
    public void update(){
        //camctr.process(
        camctr.update();
    }

    private Texture R(int weight, int height) {
        Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
//        pixmap.setColor(0x000000cc);
//        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
//        pixmap.setColor(0xffffffaa);
//        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
        pixmap.setColor(0x000000cc);
        pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2);
        //pixmap.fillRectangle(0,0,4,pixmap.getHeight());
        //pixmap.fillRectangle(0,0,pixmap.getWidth(),4);
        //pixmap.fillRectangle(pixmap.getWidth()-4,0,4,pixmap.getHeight());
        //pixmap.fillRectangle(0,pixmap.getHeight()-4,pixmap.getWidth(),4);
        //pixmap.setColor(0xff00ffcc);
        //pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    private Texture R2(int weight, int height) {
        Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
//        pixmap.setColor(0x000000cc);
//        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
//        pixmap.setColor(0xffffffaa);
//        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
        pixmap.setColor(0xff00ffcc);
        pixmap.fillCircle(pixmap.getWidth() / 2, pixmap.getHeight() / 2, pixmap.getHeight() / 2);
        //pixmap.fillRectangle(0,0,4,pixmap.getHeight());
        //pixmap.fillRectangle(0,0,pixmap.getWidth(),4);
        //pixmap.fillRectangle(pixmap.getWidth()-4,0,4,pixmap.getHeight());
        //pixmap.fillRectangle(0,pixmap.getHeight()-4,pixmap.getWidth(),4);
        //pixmap.setColor(0xff00ffcc);
        //pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
    
    
}

class CameraInputController extends Widget{// extends GestureDetector {
    public int activateKey;
    protected boolean activatePressed;
    public boolean alwaysScroll;
    public boolean autoUpdate;
    public int backwardKey;
    protected boolean backwardPressed;
    protected int button;
    public Camera camera;
    protected boolean controlsInverted;
    public int forwardButton;
    public int forwardKey;
    protected boolean forwardPressed;
    public boolean forwardTarget;
    private boolean multiTouch;
    public float pinchZoomFactor;
    public float rotateAngle;
    public int rotateButton;
    public int rotateLeftKey;
    protected boolean rotateLeftPressed;
    public int rotateRightKey;
    protected boolean rotateRightPressed;
    public float scrollFactor;
    public boolean scrollTarget;
    private float startX;
    private float startY;
    public Vector3 target;
    private final Vector3 tmpV1;
    private final Vector3 tmpV2;
    private int touched;
    public int translateButton;
    public boolean translateTarget;
    public float translateUnits;

    protected CameraInputController(final Camera camera,int i) {
        this.rotateButton = 0;
        this.rotateAngle = 360.0f;
        this.translateButton = 1;
        this.translateUnits = 10.0f;
        this.forwardButton = 2;
        this.activateKey = 0;
        this.alwaysScroll = true;
        this.scrollFactor = -0.1f;
        this.pinchZoomFactor = 10.0f;
        this.autoUpdate = true;
        this.target = new Vector3();
        this.translateTarget = true;
        this.forwardTarget = true;
        this.scrollTarget = false;
        this.forwardKey = 51;
        this.backwardKey = 47;
        this.rotateRightKey = 29;
        this.rotateLeftKey = 32;
        this.button = -1;
        this.tmpV1 = new Vector3();
        this.tmpV2 = new Vector3();
        this.camera = camera;
        
        this.addListener(new InputListener(){

                
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                    //return super.touchDown(event, x, y, pointer, button);
                    I.E ="jfjdndjdnfhfidjebeddhebebegg";
                    touchDown((int)x,(int)y,pointer,button);
                    return true;
                }

                public boolean touchDown(int i, int i2, int i3, int i4) {
                    
                    
                    touched |= 1 << i3;
                    multiTouch = !MathUtils.isPowerOfTwo(touched);
                    if (multiTouch) {
                        button = -1;
                    } else if (button < 0 && (activateKey == 0 || activatePressed)) {
                        startX = (float) i;
                        startY = (float) i2;
                        button = i4;
                    }
                    return false;//super.touchDown(i, i2, i3, i4) || activateKey == 0 || activatePressed;
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer){
                    //touchDragged((int)x,(int)y,pointer);
                    float deltaX = (x - startX) / ((float) Gdx.graphics.getWidth());
                    float deltaY = (startY - y) / ((float) Gdx.graphics.getHeight());
                    startX = (float) x;
                    startY = (float) y;
                    process(deltaX, deltaY, button);
                }

                @SuppressWarnings("unused")
                public boolean touchDragged(int i, int i2, int i3) {
                    boolean result = touchDragged(i, i2, i3);
                    if (result || button < 0) {
                        return result;
                    }
                    float deltaX = (((float) i) - startX) / ((float) Gdx.graphics.getWidth());
                    float deltaY = (startY - ((float) i2)) / ((float) Gdx.graphics.getHeight());
                    startX = (float) i;
                    startY = (float) i2;
                    return process(deltaX, deltaY, button);
                }

                public boolean zoom(float f) {
                    if (!alwaysScroll && activateKey != 0 && !activatePressed) {
                        return false;
                    }
                    camera.translate(tmpV1.set(camera.direction).scl(f));
                    if (scrollTarget) {
                        target.add(tmpV1);
                    }
                    if (autoUpdate) {
                        camera.update();
                    }
                    return true;
                }
                
                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                    //return super.touchDwn(event, x, y, pointer, button);
                    I.E ="jfjdjebeddhebebegg";
                    touchUp((int)x,(int)y,pointer,button);
                    
                }

                public boolean touchUp(int i, int i2, int i3, int i4) {
                   touched &= (1 << i3) ^ -1;
                   multiTouch = !MathUtils.isPowerOfTwo(touched);
                    if (i4 == button) {
                        button = -1;
                    }
                    return false;//super.touchUp(i, i2, i3, i4) ||activatePressed;
                }

                @SuppressWarnings("unused")
                public void setInvertedControls(boolean z) {
                    if (controlsInverted != z) {
                        rotateAngle = -rotateAngle;
                    }
                    controlsInverted = z;
                }


                @SuppressWarnings("unused")
                public boolean pinchZoom(float f) {
                    return zoom(pinchZoomFactor * f);
                }

                @SuppressWarnings("unused")
                public boolean scrolled(float f, float f2) {
                    return zoom((scrollFactor * f2) * translateUnits);
                }
            });
    }

    
    
    protected boolean process(float f, float f2, int i) {
        if (i == this.rotateButton) {
            this.tmpV1.set(this.camera.direction).crs(this.camera.up).y = 0.0f;
            this.camera.rotateAround(this.target, this.tmpV1.nor(), this.rotateAngle * f2);
            this.camera.rotateAround(this.target, Vector3.Y, (-this.rotateAngle) * f);
        } else if (i == this.translateButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).crs(this.camera.up).nor().scl((-f) * this.translateUnits));
            this.camera.translate(this.tmpV2.set(this.camera.up).scl((-f2) * this.translateUnits));
            if (this.translateTarget) {
                this.target.add(this.tmpV1).add(this.tmpV2);
            }
        } else if (i == this.forwardButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).scl(this.translateUnits * f2));
            if (this.forwardTarget) {
                this.target.add(this.tmpV1);
            }
        }
        if (this.autoUpdate) {
            this.camera.update();
        }
        return true;
    }

    public void update() {
        if (this.rotateRightPressed || this.rotateLeftPressed || this.forwardPressed || this.backwardPressed) {
            float deltaTime = Gdx.graphics.getDeltaTime();
            if (this.rotateRightPressed) {
                this.camera.rotate(this.camera.up, (-deltaTime) * this.rotateAngle);
            }
            if (this.rotateLeftPressed) {
                this.camera.rotate(this.camera.up, this.rotateAngle * deltaTime);
            }
            if (this.forwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl(this.translateUnits * deltaTime));
                if (this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if (this.backwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl((-deltaTime) * this.translateUnits));
                if (this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if (this.autoUpdate) {
                this.camera.update();
            }
        }
    }

    
}


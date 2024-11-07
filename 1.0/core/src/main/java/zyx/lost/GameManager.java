package zyx.lost;
import com.badlogic.gdx.utils.Array;
import zyx.lost.game.MyGame;
import zyx.lost.noworldentities.MyNoWorldGame;
import zyx.lost.scenes.Scene;
import zyx.lost.view.View;

public class GameManager {

    private MyGame game;
    private Scene scene;
    private MyNoWorldGame noworldgame;

    public static int viewState = -1;
    public static final int HIDEALL = -1;
    public static final int SETTING_VIEW = 0;
    public static final int PPW_VIEW = 1;
    public static final int INFO_VIEW= 2;
    public static final int DEAD_VIEW= 3;
    public static final int MAP_VIEW=4;

    Array<View> views = new Array<View>();;

    public GameManager() {
    }

    public void init() {
        /*SettingView sv = new SettingView(this);
        sv.create();
        views.add(sv);
        PropertyPanelView ppw = new PropertyPanelView(this);
        ppw.create();
        views.add(ppw);
        InformationView iv = new InformationView();
        iv.create();
        views.add(iv);
        DeathView dv = new DeathView(this);
        dv.create();
        views.add(dv);
        MapView mv = new MapView(this);
        mv.create();
        views.add(mv);*/
    }
    public void update() {}
    float pp = 0;
    public void render(float p) {
       // pp+=p;
        //if(pp>1){
           // pp= 0;
            if(game != null)game.render(p);
            if(noworldgame != null)noworldgame.render(p);
            if (viewState == -1) {} else {
                views.get(viewState).render(p);
                views.get(viewState).fadein();
            }
        //}
        
    }
    public void sceneRender(){
        if(scene!=null)scene.render();
    }
    public void setGame(MyGame game) {
        this.game = game;
    }
    public void setGame(MyNoWorldGame noworldgame){
        this.noworldgame = noworldgame;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    public MyGame getGame() {
        return game;
    }
    public MyNoWorldGame getnoworldGame() {
        return noworldgame;
    }
    public Scene getScene(){
        return scene;
    }
}

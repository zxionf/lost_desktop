package zyx.lost.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import zyx.lost.I;
import zyx.lost.MyGdxGame;
import zyx.lost.component.Typingline;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import zyx.lost.component.AnimaCtr;

public class TransAnimaScreen extends AbstractScreen {

	Array<Typingline> lines = new Array<Typingline>();
    //Array<Typingline> lineshis = new Array<Typingline>();
    Queue<Typingline> lil  = new ArrayDeque<Typingline>();
    
    Array<AnimaCtr> animarray = new Array<AnimaCtr>();

    public TransAnimaScreen(MyGdxGame game) {
        super(game); 
    }

    private BitmapFont font;
    @SuppressWarnings("unused")
    private Batch batch;

    boolean firststart = true;

    float timer = 0;
    int str_which = 0;
    String str[] = {
        "hello,[YELLOW]zxionf[]!",
        "*******Animated presentation*******",
        "[YELLOW]$[]open -internal -txt test.txt >>t | print -char t"

    };
    String strs[];
    ScrollPane sp;
    Label.LabelStyle ls;
    Table table,in;
    Stage stage;
    Typingline ty[];

    Image bg ;

    public static boolean isOk;

    @SuppressWarnings("unused")
    private Texture R(int weight, int height) {
        Pixmap pixmap = new Pixmap(weight, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(0x000000cc);
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public void show() {
        stage = new Stage();
        batch = new SpriteBatch();
        /*font = new BitmapFont(Gdx.files.internal("font/font.fnt"), Gdx.files.internal("font/font.png"), false);
         font.getData().setScale(2f);
         font.setColor(Color.WHITE);*/

        Gdx.input.setInputProcessor(stage);

		font = I.fontmonoc;


        FileHandle fh = Gdx.files.internal("animation/test.txt");
        strs = fh.readString().split("\n");

        bg = new Image(new Texture("ui/tran.jpg"));
        bg.setSize(I.ScreenWidth, I.ScreenHeight);
        bg.setPosition(0, 0);
        stage.addActor(bg);
        bg.addAction(Actions.color(Color.BLACK, 0.8f));
        ls = new Label.LabelStyle();
        ls.font = font;
        table = new Table();
        table.align(Align.topLeft);
        ScrollPane.ScrollPaneStyle ss = new ScrollPane.ScrollPaneStyle();
        sp = new ScrollPane(table, ss);
        sp.setSize(I.ScreenWidth - 20, I.ScreenHeight);
        sp.setPosition(20, 0);
     //sp.debugAll();
        stage.addActor(sp);

        table.add(new Label("game initalize", ls)).left().row();

        
        

        ty = new Typingline[str.length];
        //I.E = ""+str.length+str[0]+str[1]+str[2];
        for (int i = 0;i < str.length;i++) {
            if(str[i].contains("$")){
                ty[i] = new Typingline(ls, str[i],0 );
            }else{
                ty[i] = new Typingline(ls, str[i], false);
            }
            table.add(ty[i]).size(I.ScreenWidth - 40, 32).left().row();
        }
        ty[0].setTyping(true).setbiao(true);
        //MFont.load();

        new Thread(new Runnable() {
                @Override
                public void run() {
                    //Looper.prepare();
                    //Looper.loop();

                    WHI1:while (timer < 200) {
                        for (int i = 0;i < str.length;i++) {
                            if (ty[i].getCode() != ty[i].getPreCode()) {
                                ty[i].setPreCode(ty[i].getPreCode() + 1);
                                if (i + 1 < str.length) {
                                    ty[i + 1].setbiao(true);
                                    try {
                                        Thread.sleep(600);
                                    } catch (InterruptedException e) {}
                                    ty[i + 1].setTyping(true);

                                }
                                if (i == str.length) {
                                    ty[i].setbiao(true); 
                                }
                            }
                            if (ty[str.length - 1].getOkState()) {
                                //保留光标
                                //ty[str.length - 1].setbiao(true);

                                //I.upCharacters = "" + timer;
                                break WHI1;
                            }

                        }
                    }

                }
            }).start();
            
        //////////////////////////////////////////////////////////////////////// 
        animarray.add(new AnimaCtr(){
                public void action(){
                    for (int i = 0;i < strs.length;i++) {
                        //lines.add(new Typingline(ls, strs[i]));
                        lil.add(new Typingline(ls, strs[i]));
                    }
                }
            });
        animarray.add(new AnimaCtr(0){
                public void act(){
                    time += Gdx.graphics.getDeltaTime();
                    if(time>5){
                        setState(State.ok);
                    }
                }
            });
        animarray.add(new AnimaCtr(){
                public void action(){
                    lil.add(new Typingline(ls,"[YELLOW]$[]sleep 2000 | print -line t",0));
                }
            });
        animarray.add(new AnimaCtr(0){
                public void act(){
                    time += Gdx.graphics.getDeltaTime();
                    if(time>2){
                        setState(State.ok);
                    }
                }
            });
        animarray.add(new AnimaCtr(){
        public void action(){
            for (int i = 0;i < strs.length;i++) {
                //lines.add(new Typingline(ls, strs[i]));
                lil.add(new Typingline(ls, strs[i],false));
            }
         }
         });
        /*animarray.add(new AnimaCtr(){
                public void action(){
                    
                }
            });*/
      //////////////////////////////////////////////////////////////////////////////
    }

    @Override
    public void render(float delta) {
        // Draws a red background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!animarray.isEmpty()){
            animarray.first().update();
            if(lines.isEmpty()){
                animarray.first().act();
            }if(animarray.first().getState()==AnimaCtr.State.ok){
                animarray.removeValue(animarray.first(),false);
            }
        }
        
        
        //I.E = ""+lines.size;
        
        if(firststart){
            firststart = false;
        }
        
        if (timer > 5f ) {
            
            //table.add(lines.first()).left().height(32).width(900).row();
            //lines.first().setTyping(true).setbiao(true);
            try {
                if(!lil.isEmpty()&lines.isEmpty()){
                    //lineshis.first().setTyping(true).setbiao(true);
                    for(int i = 0;i<lil.size();i++){
                        lines.add(lil.poll());
                    }
                    lines.first().setTyping(true).setbiao(true);
                    table.add(lines.first()).left().height(32).width(900).row();

                }
                if (!lines.isEmpty() && lines.first().getOkState()) {
                    lines.removeValue(lines.first(), true);
                    lines.shrink();
                    lines.first().setTyping(true).setbiao(true);
                    table.add(lines.first()).left().height(32).width(900).row();
                }
            } catch (Exception e) {}
            
        }

        


        stage.act();
        stage.draw();
        
        if(!lines.isEmpty()){
            sp.setScrollPercentY(1);
        }
        

        timer += Gdx.graphics.getDeltaTime();
        I.E = timer + "";

        if (timer > 5f) {
            isOk = true;
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

}

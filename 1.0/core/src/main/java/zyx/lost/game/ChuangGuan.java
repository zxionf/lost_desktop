package zyx.lost.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import zyx.lost.I;

public class ChuangGuan extends MyGame {

    float timer = 0;
    Box2DDebugRenderer ren2 = new Box2DDebugRenderer();
    Cstage cstage = new Cstage();
    ShapeRenderer shaperenderer = new ShapeRenderer();

    /*int i = 61;int j= 27;
     int s = 40;*/

    //喜r= 5
    /*int i = 61;int j= 27;
     int s = 10;*/

    int i = 60;int j= 27;
    int s = 40;

    float time = 0;
    int line = 0;

    Pixmap pixmap ;

    int ori[][];
    int[][] pic = new int[i][j];
    Array<FileHandle> pictures = new Array<>();
    boolean next = true;

    @Override
    public void create() {
        batch = new SpriteBatch();
        b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(cam);
        stage = new Stage(viewport);
        world = new World(new Vector2(0, -32), true);

        cstage.init_stage();
        Gdx.input.setInputProcessor(cstage);

        FileHandle[] fhs = new FileHandle("/storage/emulated/0/AppProjects/pictest").list();
        for(FileHandle file : fhs) {
            pictures.add(file);
            System.out.println(file);
        }

    }

    @Override
    public void render() {
        timer += Gdx.graphics.getDeltaTime();

        time += Gdx.graphics.getDeltaTime();

        if(pictures.isEmpty()){
            FileHandle[] fhs = new FileHandle("/storage/emulated/0/AppProjects/pictest").list();
            for(FileHandle file : fhs) {
                pictures.add(file);
                System.out.println(file);
            }
        }

        if(!pictures.isEmpty()&next){
            next = false;
            pixmap = new Pixmap(pictures.first());
            pictures.removeValue(pictures.first(),false);
            pictures.shrink();

            ori = new int[i][j];

            if(pixmap.getWidth()/pixmap.getHeight()>1){
                int wi = pixmap.getWidth()/i;
                int hi = pixmap.getHeight()/j;

                for(int x = 0;x<i;x++){
                    for(int y = 0;y<j;y++){
                        ori[x][j-y-1] = pixmap.getPixel(x*wi,y*hi);
                    }
                }
            }else{

                int wi = pixmap.getWidth()/j;
                int hi = pixmap.getHeight()/i;

                for(int x = 0;x<i;x++){
                    for(int y = 0;y<j;y++){
                        ori[x][y] = pixmap.getPixel(y*hi,x*wi);
                    }
                }
            }


        }

        if(time>0.1f/8){
            time= 0;
            push();
        }
        cam.update();

        //shaperenderer.setProjectionMatrix(cam.combined);
        //shaperenderer.setColor(Color.RED);
        shaperenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int x = 0;x<i;x++){
            for(int y = 0;y<j;y++){
                shaperenderer.setColor(new Color(pic[x][y]));
                if(I.pixshape)shaperenderer.circle(x*s,y*s,5);
                else shaperenderer.rect(x*s,y*s,s,s);

            }
        }

        shaperenderer.setColor(Color.GREEN);
        for(int x = 0;x<i;x++){
            for(int y = 0;y<j;y++){
                if(I.touchbgX-s/2<x*s&I.touchbgX+s/2>x*s&I.touchbgY-s/2<y*s&I.touchbgY+s/2>y*s){
                    if(I.pixshape)shaperenderer.circle(x*s,y*s,10);
                    else shaperenderer.rect(x*s,y*s,s,s);
                    I.E = x+"///////"+y;
                    //I.E = ""+ pic[x][y];
                }
            }
        }
        shaperenderer.end();

        //I.E = ""+I.touchbgX+"///////"+I.touchbgY;

        batch.begin();
        I.fontcy16x2.draw(batch,"abcdefg",I.touchbgX,I.touchbgY);
        batch.end();

        cstage.act();
        cstage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    void push(){
        for(int y = 0;y<j;y++){
            pic[line][y] =ori[line][y];//pixmap.getPixel(line,y);
        }
        if(line == i-1)
        {
            line=0;
            next = true;
        }
        else line++;
    }
}

package zyx.lost.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.Random;
import zyx.lost.I;

public class MitPointLine extends MyGame {
    
    float timer = 0;
    Box2DDebugRenderer ren2 = new Box2DDebugRenderer();
    Cstage cstage = new Cstage();
    ShapeRenderer shaperenderer = new ShapeRenderer();
    
    int i =(int) 60/4;int j=(int) 27/4;
    int s = 40*4;

    float time = 0;
    int line = 0;

    Pixmap pixmap ;

    int ori[][];
    int[][] pic = new int[i][j];
    Array<Point> points1 = new Array<>();
    Array<Point> points2 = new Array<>();
    boolean next = true;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(cam);
        stage = new Stage(viewport);
        //world = new World(new Vector2(0, -32), true);

        cstage.init_stage();
        Gdx.input.setInputProcessor(cstage);

        for(int x = 0;x<i;x++){
            for(int y = 0;y<j;y++){
                points1.add(new Point(x*s+40,y*s+40));
                points2.add(new Point(x*s+40,y*s+40));
            }
        }

    }

    @Override
    public void render() {
        timer += Gdx.graphics.getDeltaTime();

        time += Gdx.graphics.getDeltaTime();

        

        if(time>0.1f/8){
            time= 0;
        }
        cam.update();
        
        

        //shaperenderer.setProjectionMatrix(cam.combined);
        shaperenderer.setColor(Color.RED);
        shaperenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int x = 0;x<i;x++){
            for(int y = 0;y<j;y++){
                if(I.pixshape)shaperenderer.circle(x*s,y*s,5);
                else shaperenderer.rect(x*s,y*s,s,s);

            }
        }
        for(Point p2 :points2){
            points2.removeValue(p2,true);
        }
        points2.shrink();
        shaperenderer.setColor(Color.YELLOW);
        for(Point p : points1){
            p.move();
            shaperenderer.circle(p.x,p.y,10);
            points2.add(p);
        }
        shaperenderer.setColor(Color.WHITE);
        for(Point p1 : points1){
            for(Point p2 : points2){
                shaperenderer.setColor(new Color(Color.rgba8888(1,1,1,0.2f)));//400/calcu(p1,p2));
                if(calcu(p1,p2)<400)shaperenderer.rectLine(p1.x,p1.y,p2.x,p2.y,1/calcu(p1,p2)*540);
                
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
        I.fontcy16x2.draw(batch,"abcdefg"+points1.size,I.touchbgX,I.touchbgY);
        batch.end();

        cstage.act();
        cstage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    float calcu(Point p1,Point p2){
        return (float)Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
    }
}

class Point {
    int x;
    int y;
    float xx;
    float yy;
    public Point(int x,int y){
        this.x = x;
        this.y = y;
        xx =getintrand(-4,4);
        yy =getintrand(-4,4);
    }
    public void move(){
        if(x<0||x>I.ScreenWidth|y<0|y>I.ScreenHeight){
            if(x<0){
                xx = -xx;
                x+=4;
            }else if(x>I.ScreenWidth){
                xx = -xx;
                x-=4;
            }
            if(y<0){
                yy = -yy;
                y+=4;
            }else if(y>I.ScreenHeight){
                yy = -yy;
                y-=4;
            }
        }else{
            x+=xx/2;
            y+=yy/2;
        }
        
    }
    public int getintrand(int MIN,int MAX) {
        Random rand = new Random();
        //int MAX = 100, MIN = 1;
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }
}

package zyx.lost.noworldentities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import zyx.lost.I;
import zyx.lost.component.RectTextue;

public class Bullet extends Entity{

    public Bullet(Stage stage,SpriteBatch batch,float x,float y,int speed,Vector2 angle){
        this.stage = stage;
        position = new Vector2();
        position.x = x;
        position.y = y;
        this.batch = batch;
        this.maxspeed = speed;
        this.speed = speed;
        this.angle = angle;
        DamageValue = 400;

        texture = RectTextue.RGBArectangle(2, 2, 0xffffffff);
        
    }

    public Bullet(Stage stage,SpriteBatch batch,float x,float y,int speed,float radangle){
        this(stage,batch,x,y,speed,new Vector2().setAngleRad(radangle));
    }

    @Override
    public void create() {
        
    }
    @Override
    public void update() {
        batch.begin();
        batch.draw(texture, position.x*I.PPM, position.y*I.PPM);
        batch.end();
    }
    @Override
    public void move() {
        position.x += angle.x/200*speed;
        position.y += angle.y/200*speed;
    }

    public void dispose(){
        
    }
}

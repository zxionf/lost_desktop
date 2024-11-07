package zyx.lost.noworldentities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import zyx.lost.entities.Bag;
import zyx.lost.entities.Skill;

public abstract class Entity {
public static final String TAG = "Entity";

    public int id;
    public String name;
    public int width;
    public int height;
    public int minheight;
    public int minwidth;
    public int maxheight;
    public int maxwidth;
    public Texture texture;
    public TextureRegion textureregion;

    float targetX,targetY;

    public int HP;
    public int DamageValue;

    public int AttackDistance;

    public int speed = 1;
    public int maxspeed = 1;

    public Vector2 position;
    public Vector2 angle;
    public float radangle;

    public Skill skill0,skill1,skill2;

    public Bag bag;

    public Stage stage;
    public SpriteBatch batch;
    // public BodyDef bdef;
    // public Body body;
    // public FixtureDef fdef;
    // public Fixture fixture;
    //sensor
    //float x,y;
    //public boolean right,stop = true,left,jump;

    public abstract void move();

    public abstract void update();

    // public void init(){
    //     bdef = new BodyDef();
    //     fdef = new FixtureDef();
    // }

    public abstract void create();

    public void receiveAttack(Bullet b){};

    public void attack(){}

    public Entity attackAback(){
        return null;
    }

    public abstract void dispose();

    public Vector2 getPosition(){
        return position;
    }
    // private void setPosition(float x,float y){
    //     body.getPosition().x = x;
    //     body.getPosition().y = y;
    // }
    public void setTransform(float x ,float y,float angle){
        setTransform(x,y,angle);
    }
    public void setTransform(float x ,float y){
        setTransform(x,y);
    }

}

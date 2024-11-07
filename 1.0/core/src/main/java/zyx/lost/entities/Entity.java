package zyx.lost.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import zyx.lost.noworldentities.Bullet;

public abstract class Entity implements Action {

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

    public int HP;
    public int DamageValue;

    public int AttackDistance;

    public int speed = 1;
    public int maxspeed = 1;

    public Vector2 position;

    public Skill skill0,skill1,skill2;

    public Bag bag;


    public World world;
    public Stage stage;
    public SpriteBatch batch;
    public BodyDef bdef;
    public Body body;
    public FixtureDef fdef;
    public Fixture fixture;
    //sensor
    //float x,y;
    public boolean right,stop = true,left,jump,up;

    public abstract void move();

    public abstract void update();

    public void init(){
        bdef = new BodyDef();
        fdef = new FixtureDef();
    }

    public abstract void create();

    public void attack(){}

    public Vector2 getPosition(){
        return position = body.getPosition();
    }
    // private void setPosition(float x,float y){
    //     body.getPosition().x = x;
    //     body.getPosition().y = y;
    // }
    public void setTransform(float x ,float y,float angle){
        body.setTransform(x,y,angle);
    }
    public void setTransform(float x ,float y){
        body.setTransform(x,y,body.getAngle());
    }

    protected abstract void dispose();

    public void receiveAttack(Bullet bullet) {
        //throw new UnsupportedOperationException("Unimplemented method 'receiveAttack'");
    }

}


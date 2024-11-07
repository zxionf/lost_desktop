package zyx.lost.entities;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import zyx.lost.GameManager;
import zyx.lost.I;
import zyx.lost.component.InformationBar;
import zyx.lost.noworldentities.Bullet;

public class EntitySet {

    GameManager gm;
    World world;
    Stage stage;
    SpriteBatch batch;
    InformationBar ib;

    public static int zombie = 1 ;

     Array<Entity> entities = new Array<Entity>();
    public Array<Bullet> bullets = new Array<Bullet>();

    public EntitySet(GameManager gm,World world,Stage stage,SpriteBatch batch,InformationBar ib){
        this.gm = gm;
        this.world = world;
        this.stage = stage;
        this.batch = batch;
        this.ib = ib;
    }

    public void create(Entity entity){
        entities.add(entity);
    }

    public void create(Bullet bullet){
        bullets.add(bullet);
    }

    /*public void create(int i){
        switch(i){
            case 1:
                Zombie zombie = new Zombie(world,stage,batch,ib);
                arr.add(zombie);
                break;
        }
    }*/

    public void update(){
        //刷新绘制
        for(Entity e :entities){
            e.update();
            if(e.HP<=0){
                e.dispose();
                e=null;
            }
        }
        entities.shrink();//在这一步很必要
        for(Bullet e :bullets){
            e.update();
        }
        //碰撞检测
        for(Entity entity :entities){//这两个循环参数写反会得到不同的效果：一枚子弹对多个实体造成伤害
            for(Bullet bullet :bullets){
                //如果子弹碰撞到实体
                if(bullet.position.x<entity.position.x+entity.width/I.PPM&&bullet.position.x>entity.position.x-entity.width/I.PPM
                &&bullet.position.y<entity.position.y+entity.height/I.PPM&&bullet.position.y>entity.position.y-entity.height/I.PPM
                ){
                    
                    entity.receiveAttack(bullet);//实体接受攻击
                    bullets.removeValue(bullet, false);//清除子弹
                    //entities.removeValue(entity, false);
                }
            }
        }
        entities.shrink();
        bullets.shrink();
    }

    public void move(){
        for(Entity e :entities){
            e.move();
        }
        for(Bullet e :bullets){
            e.move();
        }
    }
    
    public Entity getNearestEntity(float ox,float oy){
        double mindistance = -1;
        double tpdistance;
        Entity result;
        boolean first = true;
        entities.shrink();
        result = null;
        for(Entity e :entities){
            if(first){
                first = false;
                mindistance = calculateDistance(ox, oy, e.getPosition().x, e.getPosition().y);
                result = e;
            }
            
            tpdistance = calculateDistance(ox, oy, e.getPosition().x, e.getPosition().y);
            if(tpdistance<mindistance){
                mindistance = tpdistance;
                result = e;
            }
        }
        return result;
    }

    public double calculateDistance(float ox,float oy,float x,float y){
        return Math.sqrt(Math.pow(ox-x, 2)+Math.pow(oy-y, 2));
    }
}
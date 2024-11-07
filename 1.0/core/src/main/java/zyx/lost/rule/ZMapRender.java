package zyx.lost.rule;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import zyx.lost.I;

public class ZMapRender {
    
    public static void render(Batch batch,QuKuai qukuai,Texture t){
        batch.begin();
        
        for(int indexi = -1;indexi<=1;indexi++){
            for(int indexj = -1;indexj<=1;indexj++){
                for(int i = 0;i<QuKuai.x;i++){
                    for(int j = 0;j<QuKuai.y;j++){
                        batch.draw(t,indexi*64*16+i*16,indexj*64*16+j*16);
                    }
                }
            }
        }
        
        batch.end();
    }
    public static int i = 0;
    public static boolean t = true;
    public static void shaperender(ShapeRenderer shaperendere,QuKuaiManager qukuaimgr){
        i++;
        if(i==10){
            i=0;
            if(t)t=false;
            else t = true;
        }
        for(int i = 0;i<QuKuai.x;i++){
            for(int j = 0;j<QuKuai.y;j++){
                shaperendere.setColor(qukuaimgr.getblock(4,I.qukuaiTest.layer_deepdate_all,i,j)/6024f,0,0,0);
                //if(t)shaperendere.setColor(qukuaimgr.getblock(4,I.qukuaiTest.layer_deepdate_upleft,i,j)/6024f,0,0,0);
                //else shaperendere.setColor(qukuaimgr.getblock(4,I.qukuaiTest.layer_deepdate_upright,i,j)/6024f,0,0,0);
                shaperendere.rect(i*16,j*16,16,16);
            }
        }
    }
}

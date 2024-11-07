package zyx.lost.rule;
import com.badlogic.gdx.math.Vector2;

public class QuKuaiManager {
    
    QuKuai qukuai;
    public static final int quickSuoyin[] = {};
    // 1 2 3
    // 4 0 5
    // 6 7 8
    public QuKuai cjzqks[];
    //guo zao fang fa
    public QuKuaiManager(){
        cjzqks = new QuKuai[9];
        for(int n = 0;n<9;n++){
            cjzqks[n] = new QuKuai();
        }
    }

    public void setcjzqkall(int suoyin,int layer,int block){
        for(int i = 0;i<QuKuai.x;i++){
            for(int j = 0;j<QuKuai.y;j++){
                cjzqks[suoyin].blocks[layer][i][j] = block;
            }
        }
    }
    
    public void fillcjzqkuai(int suoyin,int layer,int block,int startx,int endx,int starty,int endy){
        setcjzqkall(4,0,0);
        for(int i = startx-1;i<=endx-1;i++){
            for(int j = starty-1;j<=endy-1;j++){
                cjzqks[suoyin].blocks[layer][i][j] = block;
            }
        }
    }
    public static float chazhi(float x1,float x2,float t){
        return (1-t)*x1 + t*x2;
    }
    public int getblock(int suoyin,int layer,int x,int y){
        return cjzqks[suoyin].blocks[layer][x][y];
    }
    public int[][][] getblocks(int suoyin){
        return cjzqks[suoyin].blocks;
    }
    public Vector2[][][] getjizhunxianglianng(int suoyin){
        return cjzqks[suoyin].jizhunxianglianng;
    }
    public static Vector2 indexToij(int i){
        switch(i){
            case 0:
                return new Vector2(-1,1);
            case 1:
                return new Vector2(0,1);
            case 2:
                return new Vector2(1,1);
            case 3:
                return new Vector2(-1,0); 
            case 4:
                return new Vector2(0,0);
            case 5:
                return new Vector2(1,0);
            case 6:
                return new Vector2(-1,-1);
            case 7:
                return new Vector2(0,-1);
            case 8:
                return new Vector2(1,-1);
        }
        return null;
    }
}


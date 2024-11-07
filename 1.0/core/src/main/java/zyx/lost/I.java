package zyx.lost;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class I {

    public static final String TAG = "INFO";

	public static BitmapFont font;
    public static BitmapFont fontc16;
    public static BitmapFont fontc16x2;
	public static BitmapFont fontc16x4;
	public static BitmapFont fontc16x8;

    public static BitmapFont fontmono;
    public static BitmapFont fontmonoc;
    public static BitmapFont fontcy16x2;
    
    public static String playerName = "Binxi";

	public static final int ScreenWidth = Gdx.graphics.getWidth();
    public static final int ScreenHeight = Gdx.graphics.getHeight();

	public static boolean controlstage_isLeft = false;

	public static boolean controlstage_isStop = false;

	public static boolean controlstage_isRight = false;

	public static boolean controlstage_isUp = false;

	public static boolean controlstage_isDown = false;

	public static String player1IP;

	public static String player2IP;

	public static float PPM = 16f;

	public static String E = "";

    public static float cameraheightscale = 2f;//摄像机高度
    
    public static boolean camupdateUseHuanDong = true;

	public static boolean devmode=false;

    public static boolean pixshape = true;

    public static float touchbgX = 0;
    public static float touchbgY = 0;

	
    
    public static int jumpacount = 2;
    public static boolean controlstage_isUpa = true;

    public static boolean isplayeronground = true;
    
    public static String EventRecorder = "";
    public static String upCharacters ="";

    public static boolean show_2dbox = true;
    public static boolean isdebugtest = true;
    
    public static int mapseed = 1000;
    
    public static int random[] = new int[256];

    public static class position{
        public static float absolute_clickx = 0;
        public static float absolute_clicky = 0;
        public static int absolute_blockX = 0;
        public static int absolute_blockY = 0;
        public static void update_block_position(){
            if(absolute_clickx>=0)absolute_blockX = (int) absolute_clickx;
                else absolute_blockX = (int) absolute_clickx -1;
            if(absolute_clicky>=0)absolute_blockY = (int) absolute_clicky;
                else absolute_blockY = (int) absolute_clicky - 1;

            // for(int indexi = -1;indexi<=1;indexi++){
            //     for(int indexj = -1;indexj<=1;indexj++){
            //         for(int i = 0;i<QuKuai.x;i++){
            //             for(int j = 0;j<QuKuai.y;j++){
            //                 //batch.draw(t,indexi*64*16+i*16,indexj*64*16+j*16);
                            
            //             }
            //         }
            //     }
            // }
        }
    }

    public static class StdInput{
        public static final float clickAttack_ZuDuan_Time = 0.01f;
        static float clickAttack_Now_Time = 0;
        public static boolean clickAttack_is_available = true;
        public static boolean clickAttack_is_ZuDuan = false;
        public static boolean key_w = true;
        public static boolean key_s = false;
        public static boolean key_a = false;
        public static boolean key_d = false;
        public static boolean key_space = false;
        public static void update(){
            //攻击阻断
            if(clickAttack_is_ZuDuan){
                clickAttack_is_available = false;
                clickAttack_Now_Time += Gdx.graphics.getDeltaTime();
                if(!I.KeyUpdate.isclickscreen&&clickAttack_Now_Time>clickAttack_ZuDuan_Time){
                    clickAttack_Now_Time = 0;
                    clickAttack_is_ZuDuan = false;
                }
            }else clickAttack_is_available = true;
        }
    }

    public static class KeyUpdate {
        public static boolean isclickscreen = false;
        public static void update() {
            isclickscreen = false;
            
            I.StdInput.key_w = false;
            I.StdInput.key_s = false;
            I.StdInput.key_a = false;
            I.StdInput.key_d = false;
            I.StdInput.key_space = false;
            if(Gdx.input.isKeyPressed (Input.Keys.A))I.StdInput.key_a = true;
            if(Gdx.input.isKeyPressed (Input.Keys.S))I.StdInput.key_s = true;
            if(Gdx.input.isKeyPressed (Input.Keys.W))I.StdInput.key_w = true;
            if(Gdx.input.isKeyPressed (Input.Keys.D))I.StdInput.key_d = true;
            if(Gdx.input.isKeyPressed (Input.Keys.SPACE))I.StdInput.key_space = true;
        }
        
    }
    
    public static class Test{
        public static float x;
        public static float y;
    }
    
    public static class b2dValue{
        public static final short block = 1;
        public static final short player = -1;
    }
    public static class qukuaiTest{
        public static String s = "";
        public static int layer_deepdate_upleft = 1;
        public static int layer_deepdate_upright = 2;
        public static int layer_deepdate_downleft = 3;
        public static int layer_deepdate_downright = 4;
        public static int layer_deepdate_all = 5;
        public static int layer_nulll = 0;
        public static int index = 0;
        public static int nextint(){
            return index++;
        }
        public static void resetindex(){
            index = 0;
        }
    }
}

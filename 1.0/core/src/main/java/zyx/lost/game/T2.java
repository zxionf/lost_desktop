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

public class T2 extends MyGame {

    float timer = 0;
    Box2DDebugRenderer ren2 = new Box2DDebugRenderer();
    KeysL keysl = new KeysL();
    ShapeRenderer shaperenderer = new ShapeRenderer();

    /*int i = 61;int j= 27;
     int s = 40;*/

    //喜r= 5
    /*int i = 61;int j= 27;
     int s = 10;*/

    int i = 60 * 4;int j= 27 * 4;
    int s = 10;

    float time = 0;
    int line = 0;

    Pixmap pixmap ;

    int ori[][];
    int[][] pic = new int[i][j];
    Array<FileHandle> pictures = new Array<>();
    boolean next = true;

    int n;
    String sss = "寒来暑寒来暑往远古洪荒海田沧桑陆地漂移板块碰撞山岳巍峨湖泊荡漾植被旷野岛撒汪洋冰川冻土沙漠沃壤木丰树森岩多滩广鸟飞兽走鳞潜羽翔境态和谐物种安详形分上下道合阴阳幽冥杳渺天体著彰凝气为精聚能以场缩浓而质积微显量化巨幻虚恍惚成象强固凌弱柔亦制刚终极必反存兴趋亡色空轮回动静恒常唯实众名一理万方父母爹娘没齿难忘兄弟姐妹危困助帮姑姨叔舅亲戚互访侄男闺少哺育茁壮夫妻相敬梦忆糟糠隔屋邻舍遇事谦谅伯公妪婆慈孝赡养尊朋礼友仁义君郎炎黄二帝尧舜禅让禹启世袭灭桀商汤周武伐纣侯列各邦秦皇集权汉刘楚项鼎立割据乱晋八王南北对峙腐朽隋炀贞观政要五代续唐陈桥兵变耻辱靖康耶律完颜元建宋僵钟离太祖崇祯吊丧清军入关大臣驻粉碎叛卓犁域设将台湾复归守卫边防鸦片战争英占香港戊戌维新社会改良辛亥革命孙文思想联盟抗倭国共两党定都京师人民解放诸子百家孔孟老庄扁鹊灵医鲁班巧匠罗盘硝药针灸疗伤蔡伦毕升鉴真玄奘易经论语史记达畅河图洛书算术九章西三红水聊儒瓶厢诗词曲赋戏剧说唱琵琶琴瑟锣镲铿锵笙箫呜咽卧笛悠扬筝音奔奋唢呐高亢荆浩匡庐董源潇湘米芾写意悲鸿骏昂笔墨纸砚匾楣楹榜楷隶篆刻碑帖草狂敦煌石窟城伟墙青铜甲骨缕衣纱裳虎符越剑陶马俑葬彩瓷宝瓮丝绸他乡凡尔赛宫金字塔状泰姬陵墓彼得教堂自由女神希腊塑像最后晚餐创造亚当亭榭楼阁寺庙殿廊蓬门荜户丈室绿窗府别墅画栋雕梁庭院踏步影屏幕障承尘藻井篱笆柱桩舷舵扶靠凭栏眺望悬崖峭壁峰峦叠嶂泉喷岚罩湍急瀑宕峡沟潭渊溪涧流淌池渠堰坝沼泽泥塘漩涡带波礁屿连江汹涌澎湃惊涛骇浪灾涝溢泻汛潮浮涨苍松寿柏垂柳毛杨芭蕉蒲扇斑竹篾筐槐椿榆桦杉桂榕樟斋扉紧闭栅苑濒旁坪埔莱茵菲窥坞坊蔷薇翩跹莆菏蔚茫蕴蒂荚芯蓓蕾琳琅奇花异卉艳丽荣秧兰荷菊梅四季芬芳杜鹃泣血芙蓉吉祥茉莉馥郁玫瑰刺芒瓜果蔬菜葱蒜韭姜茴椒芹葵皮芥辣酱芸苔芋笋葫芦瓢瓤番茄蘑菇乳蛋醇酿碘盐食醋脆卜甜糖珍馐旨甘肴馔膏粱葡萄美酒玉液琼浆咖啡益智茗茶顺肠桃李杏柿汁鲜味爽椰柚橙桔渴饮品尝菠萝柑橘橄榄槟榔梨枣苹楂荔栗榴棠蝌蚪摆尾蛤蟆鼓囊钓饵蚯蚓蠕虫蚂蟥鹦鹉学舌蜜蜂穿忙蝙蝠栖洞梧桐引凰蜘蛛牵补螟蛉蛀粮蜻蜓振翅鸠鹏张膀鸥莺燕雀蝴蝶鸳鸯鲤鲫鲇鲸蛙蚌螺螃蚜蛾蝉蛹龟卵翼蝗蚊蝇鼠蚁蛇蝎鳝蟒蜈蚣毒腺蟋蟀蹬闯鹿狈狐狸熊豹豺狼猿啼猴吱鸵孵獭躺雏猩攀梢雌牡匿往远古洪荒海田沧桑陆地漂移板块碰撞山岳巍峨湖泊荡漾植被旷野岛撒汪洋冰川冻土沙漠沃壤木丰树森岩多滩广鸟飞兽走鳞潜羽翔境态和谐物种安详形分上下道合阴阳幽冥杳渺天体著彰凝气为精聚能以场缩浓而质积微显量化巨幻虚恍惚成象强固凌弱柔亦制刚终极必反存兴趋亡色空轮回动静恒常唯实众名一理万方父母爹娘没齿难忘兄弟姐妹危困助帮姑姨叔舅亲戚互访侄男闺少哺育茁壮夫妻相敬梦忆糟糠隔屋邻舍遇事谦谅伯公妪婆慈孝赡养尊朋礼友仁义君郎炎黄二帝尧舜禅让禹启世袭灭桀商汤周武伐纣侯列各邦秦皇集权汉刘楚项鼎立割据乱晋八王南北对峙腐朽隋炀贞观政要五代续唐陈桥兵变耻辱靖康耶律完颜元建宋僵钟离太祖崇祯吊丧清军入关大臣驻粉碎叛卓犁域设将台湾复归守卫边防鸦片战争英占香港戊戌维新社会改良辛亥革命孙文思想联盟抗倭国共两党定都京师人民解放诸子百家孔孟老庄扁鹊灵医鲁班巧匠罗盘硝药针灸疗伤蔡伦毕升鉴真玄奘易经论语史记达畅河图洛书算术九章西三红水聊儒瓶厢诗词曲赋戏剧说唱琵琶琴瑟锣镲铿锵笙箫呜咽卧笛悠扬筝音奔奋唢呐高亢荆浩匡庐董源潇湘米芾写意悲鸿骏昂笔墨纸砚匾楣楹榜楷隶篆刻碑帖草狂敦煌石窟城伟墙青铜甲骨缕衣纱裳虎符越剑陶马俑葬彩瓷宝瓮丝绸他乡凡尔赛宫金字塔状泰姬陵墓彼得教堂自由女神希腊塑像最后晚餐创造亚当亭榭楼阁寺庙殿廊蓬门荜户丈室绿窗府别墅画栋雕梁庭院踏步影屏幕障承尘藻井篱笆柱桩舷舵扶靠凭栏眺望悬崖峭壁峰峦叠嶂泉喷岚罩湍急瀑宕峡沟潭渊溪涧流淌池渠堰坝沼泽泥塘漩涡带波礁屿连江汹涌澎湃惊涛骇浪灾涝溢泻汛潮浮涨苍松寿柏垂柳毛杨芭蕉蒲扇斑竹篾筐槐椿榆桦杉桂榕樟斋扉紧闭栅苑濒旁坪埔莱茵菲窥坞坊蔷薇翩跹莆菏蔚茫蕴蒂荚芯蓓蕾琳琅奇花异卉艳丽荣秧兰荷菊梅四季芬芳杜鹃泣血芙蓉吉祥茉莉馥郁玫瑰刺芒瓜果蔬菜葱蒜韭姜茴椒芹葵皮芥辣酱芸苔芋笋葫芦瓢瓤番茄蘑菇乳蛋醇酿碘盐食醋脆卜甜糖珍馐旨甘肴馔膏粱葡萄美酒玉液琼浆咖啡益智茗茶顺肠桃李杏柿汁鲜味爽椰柚橙桔渴饮品尝菠萝柑橘橄榄槟榔梨枣苹楂荔栗榴棠蝌蚪摆尾蛤蟆鼓囊钓饵蚯蚓蠕虫蚂蟥鹦鹉学舌蜜蜂穿忙蝙蝠栖洞梧桐引凰蜘蛛牵补螟蛉蛀粮蜻蜓振翅鸠鹏张膀鸥莺燕雀蝴蝶鸳鸯鲤鲫鲇鲸蛙蚌螺螃蚜蛾蝉蛹龟卵翼蝗蚊蝇鼠蚁蛇蝎鳝蟒蜈蚣毒腺蟋蟀蹬闯鹿狈狐狸熊豹豺狼猿啼猴吱鸵孵獭躺雏猩攀梢雌牡匿";

    @Override
    public void create() {
        batch = new SpriteBatch();
        b2dcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Viewport viewport = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.setCamera(cam);
        stage = new Stage(viewport);
        world = new World(new Vector2(0, -32), true);

        keysl.init();
        Gdx.input.setInputProcessor(keysl);

        FileHandle[] fhs = new FileHandle("/storage/emulated/0/AppProjects/pictest").list();
        for (FileHandle file : fhs) {
            pictures.add(file);
            System.out.println(file);

        }

        I.fontc16.getData().markupEnabled = true;
    }

    @Override
    public void render() {
        I.KeyUpdate.update();
        
        timer += Gdx.graphics.getDeltaTime();

        time += Gdx.graphics.getDeltaTime();

        if (pictures.isEmpty()) {
            FileHandle[] fhs = new FileHandle("/storage/emulated/0/AppProjects/pictest").list();
            for (FileHandle file : fhs) {
                pictures.add(file);
                System.out.println(file);
            }
        }

        if (!pictures.isEmpty() & next) {
            next = false;
            pixmap = new Pixmap(pictures.first());
            pictures.removeValue(pictures.first(), false);
            pictures.shrink();

            ori = new int[i][j];

            if (pixmap.getWidth() / pixmap.getHeight() > 1) {
                int wi = pixmap.getWidth() / i;
                int hi = pixmap.getHeight() / j;

                for (int x = 0;x < i;x++) {
                    for (int y = 0;y < j;y++) {
                        ori[x][j - y - 1] = pixmap.getPixel(x * wi, y * hi);
                    }
                }
            } else {

                int wi = pixmap.getWidth() / j;
                int hi = pixmap.getHeight() / i;

                for (int x = 0;x < i;x++) {
                    for (int y = 0;y < j;y++) {
                        ori[x][y] = pixmap.getPixel(y * hi, x * wi);
                    }
                }
            }


        }

        if (time > 0.1f / 8) {
            time = 0;
            push();
        }
        cam.update();

        //shaperenderer.setProjectionMatrix(cam.combined);
        //shaperenderer.setColor(Color.RED);
        shaperenderer.begin(ShapeRenderer.ShapeType.Filled);
        batch.begin();
        for (int x = 0;x < i;x++) {
            for (int y = 0;y < j;y++) {
                shaperenderer.setColor(new Color(pic[x][y]));
                if (I.pixshape) {
                    /*if (pic[x][y] <= 255) {
                     I.fontc16.draw(batch, "[]山[]", x * s, y * s);
                     } else if (255 < pic[x][y] & pic[x][y] <= 65535) {
                     I.fontc16.draw(batch, "闪", x * s, y * s);
                     } else if (65535 < pic[x][y] & pic[x][y] <= 16777215) {
                     I.fontc16.draw(batch, "区", x * s, y * s);
                     } else if (16777215 < pic[x][y]) {
                     I.fontc16.draw(batch, "口", x * s, y * s);

                     }*/
                    n = getdrawmode(pic[x][y]);
                    I.fontc16.draw(batch, sss.charAt(n) + "", x * s, y * s);
                    /*switch(n){
                     case 1:
                     I.fontc16.draw(batch, "山", x * s, y * s);
                     break;
                     case 2:
                     I.fontc16.draw(batch, "闪", x * s, y * s);
                     break;
                     case 3:
                     I.fontc16.draw(batch, "区", x * s, y * s);
                     break;
                     }*/
                } else shaperenderer.rect(x * s, y * s, s, s);

            }
        }
        batch.end();
        shaperenderer.setColor(Color.GREEN);
        for (int x = 0;x < i;x++) {
            for (int y = 0;y < j;y++) {
                if (I.touchbgX - s / 2 < x * s & I.touchbgX + s / 2 > x * s & I.touchbgY - s / 2 < y * s & I.touchbgY + s / 2 > y * s) {
                    if (I.pixshape)shaperenderer.circle(x * s, y * s, 10);
                    else shaperenderer.rect(x * s, y * s, s, s);
                    I.E = x + "///////" + y + "/////" + n;//pic[x][y];
                    //I.E = ""+ pic[x][y];
                }
            }
        }
        shaperenderer.end();

        //I.E = ""+I.touchbgX+"///////"+I.touchbgY;

        batch.begin();
        I.fontcy16x2.draw(batch, "abcdefg", I.touchbgX, I.touchbgY);
        batch.end();

        // keysl.updatekey();
        keysl.act();
        keysl.draw();
        /*if(Gdx.input.isKeyPressed (Input.Keys.A)){
         if(I.pixshape)
         I.pixshape = false;
         else I.pixshape = true;
         }*/
//        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) | Gdx.input.isKeyJustPressed(Input.Keys.A)) {
//            if (I.pixshape)
//                I.pixshape = false;
//            else I.pixshape = true;
//        }
        // if (I.key_a) {
        //     if (I.pixshape)
        //         I.pixshape = false;
        //     else I.pixshape = true;
        // }
    }

    @Override
    public void dispose() {
        super.dispose();
    }


//从 color 到 RGBA
    public int[] colorToRGBA(int color) {
        //alpha为最高位
        int alpha=color >>> 24;
        //其它按顺序次之
        int r=(color & 0xff0000) >> 16 ;
        int g=(color & 0xff00) >> 8;
        int b= color & 0xff ;
        int colors[] =new int[4];
        //... RGBA 依次赋值给colors，这里略
        //return new Color.rgba8888(r,g,b,alpha);
        colors[0] = alpha;
        colors[1] = r;
        colors[2] = g;
        colors[3] = b;
        return colors; 
    }

//从 RGBA 到 color
    public int RGBAtoColor(int r, int g, int b, int alpha) {
        //alpha 为最高位
        int color = (alpha << 24) | (r << 16) | (g << 8) | b;
        return color;
    }

    void push() {
        for (int y = 0;y < j;y++) {
            pic[line][y] = ori[line][y];//pixmap.getPixel(line,y);
        }
        if (line == i - 1) {
            line = 0;
            next = true;
        } else line++;
    }

    public int getdrawmode(int color) {
        int r=(color & 0xff0000) >> 16 ;
        int g=(color & 0xff00) >> 8;
        int b= color & 0xff ;

        /*if(r>=g&r>=b){
         n=1;
         }
         else if(g>=r&g>=b){
         n=2;
         }
         else if(b>=r&b>=g){
         n=3;
         }*/
        return (int)(r + g + b) / 8;
    }
    
}
    


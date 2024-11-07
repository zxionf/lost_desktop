package zyx.lost;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import zyx.lost.component.RectTextue;

public class Asst {

    public static final String TAG = "Asst";
	public static boolean isOK = false;
	
	public static class TextBtnStyle {
		public static TextButton.TextButtonStyle Normal;
        public static TextButton.TextButtonStyle NormalOver;
		public static TextButton.TextButtonStyle getNormal() {
			return Normal;
		}
        public static TextButton.TextButtonStyle getNormalOver(){
            return NormalOver;
        }
	}

    static Array<String> array = new Array<String>();

    static FileHandle fh = Gdx.files.internal("texture/blocks");

    public static void load() {
        //MyGdxGame.ass.load("texture/structure_void.png",Texture.class);
        //filepath_test();
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/HYZhengYuan-65W.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 32;
        parameter.mono = true;
        // parameter.color=Color.RED;
        parameter.incremental=true;
        I.fontcy16x2 = generator.generateFont(parameter);
        I.fontcy16x2.getData().markupEnabled = true;
        
        FreeTypeFontGenerator generato = new FreeTypeFontGenerator(Gdx.files.internal("font/HYZhengYuan-65W.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter paramete = new FreeTypeFontGenerator.FreeTypeFontParameter();
        paramete.size = 32;
        I.fontmono = generato.generateFont(paramete);
        
        I.fontmonoc = new BitmapFont(Gdx.files.internal("font/fontmonoc.fnt"));
        I.fontmonoc.getData().markupEnabled = true;
        
        MyGdxGame.ass.load("font/fontc_16.fnt", BitmapFont.class);
		I.fontc16x2 = new BitmapFont(Gdx.files.internal("font/fontc_16.fnt"));
		I.fontc16x2.getData().setScale(2);
		I.fontc16x4 = new BitmapFont(Gdx.files.internal("font/fontc_16.fnt"));
		I.fontc16x4.getData().setScale(4);
		I.fontc16x8 = new BitmapFont(Gdx.files.internal("font/fontc_16.fnt"));
		I.fontc16x8.getData().setScale(8);
		//text btn normal
		TextureRegionDrawable up = new TextureRegionDrawable(RectTextue.RGBArectangle(150, 50, RectTextue.BLACK60));
        TextureRegionDrawable upOver = new TextureRegionDrawable(RectTextue.RectangularFrame(154,54,0xffffffff,RectTextue.WHITE60,2));
		TextureRegionDrawable down =new TextureRegionDrawable(RectTextue.RGBArectangle(150, 50, RectTextue.WHITE60));
		TextBtnStyle.Normal = new TextButton.TextButtonStyle();
		TextBtnStyle.Normal.up = up;
		TextBtnStyle.Normal.down = down;
		TextBtnStyle.Normal.font = I.fontc16x4;
		TextBtnStyle.Normal.fontColor = Color.YELLOW;

        TextBtnStyle.NormalOver = new TextButton.TextButtonStyle();
		TextBtnStyle.NormalOver.up = upOver;
		TextBtnStyle.NormalOver.down = down;
		TextBtnStyle.NormalOver.font = I.fontc16x4;
		TextBtnStyle.NormalOver.fontColor = Color.YELLOW;
		
		isOK = true;
    }

    // private static void filepath_test() {
    //     for (FileHandle f : fh.list()) {  
    //         if (!f.isDirectory())       
    //             MyGdxGame.ass.load(f.path(), Texture.class);
            
    //     }                                    
    // }
}

package zyx.lost.component;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import zyx.lost.Asst;

public class ZButton {


    public static class TextBtn extends TextButton {
        public TextBtn(String text, ClickListener c) {
            super(text, Asst.TextBtnStyle.getNormal());
            this.addListener(c);
            
		}
        @Override
        public void draw(Batch batch, float parentAlpha) {
            if(isOver())setStyle(Asst.TextBtnStyle.getNormalOver());
            else setStyle(Asst.TextBtnStyle.getNormal());
            super.draw(batch, parentAlpha);
        }
	}

}

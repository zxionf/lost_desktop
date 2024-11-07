package zyx.lost.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class Typingline extends Widget{

    Label.LabelStyle ls;
    Label l;
    String s,subs = "";
    float timer = 0;
    int currentPos = 0;
    boolean istyping = false;
    Image biao = new Image(new Texture("ui/tran.jpg"));
    boolean biao_display = false;
    int code = 0 ,precode = 0;
    boolean isok = false;
    float time = 0.002f;
    int speedscale = 1;
    
    int met = 1;
    
    boolean hsanimation = true;

    public Typingline(Label.LabelStyle ls, String s) {
        this.ls = ls;
        this.s = s+" ";
        biao.setColor(Color.WHITE);
        biao.setSize(16, 32);
        l = new Label(subs, ls);
        //setSize(Info.ScreenWidth,64);
        //setBounds(0,0,Info.ScreenWidth,64);
    }
    public Typingline(Label.LabelStyle ls,String s ,int i) {
        this.ls = ls;
        this.s = s+" ";
        this.time = 0.06f;
        met = i;
        biao.setColor(Color.WHITE);
        biao.setSize(16, 32);
        subs = s.substring(0,s.indexOf("$")+3);
        currentPos = s.indexOf("$")+3;
        l = new Label(subs, ls);
        
    }
    public Typingline(Label.LabelStyle ls,String s ,float t,int speed) {
        this.ls = ls;
        this.s = s+" ";
        this.time = t;
        this.speedscale = speed;
        biao.setColor(Color.WHITE);
        biao.setSize(16, 32);
        l = new Label(subs, ls);
    }
    public Typingline(Label.LabelStyle ls,String s ,boolean is) {
        this.ls = ls;
        this.s = s+" ";
        this.hsanimation = is;
        biao.setColor(Color.WHITE);
        biao.setSize(16, 32);
        l = new Label(subs, ls);
    }
    public Typingline(Label.LabelStyle ls) {
        setHeight(32);
        this.ls = ls;
        biao.setColor(Color.WHITE);
        biao.setSize(16, 32);
        l = new Label(subs, ls);
    }

    public void setString(String s) {
        this.s = s+" ";
    }
    public Typingline setTyping(boolean b) {
        istyping = b;
        return this;
    }
    public int getCode() {
        return code;   
    }
    public int getPreCode() {
        return precode;   
    }
    public void setPreCode(int i) {
        precode = i;   
    }
    public void setbiao(boolean b) {
        biao_display = b;  
    }
    public boolean getOkState() {
        return isok;
    }
    public Label getLabel(){
        return l;
    }
    @Override
    public void act(float current) {
        super.act(current);
        //debug();
        timer += Gdx.graphics.getDeltaTime();
        if (istyping) {
            if(hsanimation){
                if (timer >= time) {//控制打字速率
                    timer = 0;
                    //currentPos++;
                    try{
                        subs = s.substring(0, (currentPos++)*speedscale);//刷新文本显示内容 
                    }catch(Exception e){
                        istyping = false;
                        isok = true;
                        subs = s;
                    }

                    if (currentPos*speedscale >= s.length()) {
                        istyping = false;
                        isok = true;

                        currentPos = 0;
                        code++;
                        biao_display = false;
                        //GameState.dialog_n++;
                        //GameState.leveprogress++;
                    }
                }
            }else{
                istyping = false;
                isok = true;
                code++;
                biao_display = false;
                subs = s;
            }
        }
        l.setText(subs);
        l.act(current);
        //biao.setPosition(getX() + l.getPrefWidth(), getY());
        biao.act(current);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        l.draw(batch, parentAlpha);
        if(met == 1){
            l.setPosition(this.getX(),this.getY()+16);
        }
        else l.setPosition(this.getX(),this.getY()-4);
        
        //biao.setPosition(getX(), getY());
        biao.setPosition(getX() + l.getPrefWidth(), getY());
        if (biao_display) {
            biao.draw(batch, parentAlpha);
        }
    }
    /*@Override
    public void layout() {
        super.layout();
     l.setPosition(this.getX(),this.getY() + 16);
     biao.setPosition(getX(), getY());
    }*/
    /*
     public void invalidate () {
     super.invalidate();
     }*/
}

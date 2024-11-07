package zyx.lost.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import zyx.lost.MyGdxGame;
import zyx.lost.I;

public class OnlineBattlePrepareScreen extends AbstractScreen {

    public OnlineBattlePrepareScreen(MyGdxGame game) {
        super(game);
    }

    Stage stage = new Stage();

    private BitmapFont font;
    private Batch batch;

    public static final int TEXT_FIELD_WIDTH = 64 * 8;
    public static final int TEXT_FIELD_HEIGHT = 64;
    private Texture bgTexture;
    private Texture cursorTexture;
    TextField edittext;
    Label label1,label2;

    TextureRegionDrawable up,down;
    int button_width = 150;
    int button_height = 50;
    TextButton btn1,btn2;

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = MyGdxGame.ass.get("font/fontc_16.fnt", BitmapFont.class);
        font.getData().setScale(3f);
        font.setColor(Color.BLACK);
        create();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        // Draws a red background
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        batch.begin();

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void hide() {
    }

    private void create() {

        Label.LabelStyle ls = new Label.LabelStyle();
        ls.font = font;
        ls.font.getData().markupEnabled = true;
        //label = new Label("[RED]Z[BLACK]X[GREEN]I[GRAY]O[BLUE]N[GOLD]F[]",ls);
        label1 = new Label("[BLACK]你的IP:[RED]" + getIpAddress() + "[]", ls);
        label1.setPosition(100, 500);
        stage.addActor(label1);

        label2 = new Label("[BLACK]对方IP:[]", ls);
        label2.setPosition(100, label1.getY() - label1.getHeight() - 20);
        stage.addActor(label2);

        bgTexture = createBackgroundTexture();
        cursorTexture = createCursorTexture();

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        // 设置背景纹理区域
        style.background = new TextureRegionDrawable(new TextureRegion(bgTexture));
        // 设置光标纹理区域
        style.cursor = new TextureRegionDrawable(new TextureRegion(cursorTexture));
        style.font = font;
        //style.font.setColor(Color.BLACK);
        style.fontColor = Color.BLACK;
        edittext = new TextField("192.168.0.", style);
        // 设置文本框的宽高
        edittext.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        // 设置文本框的位置
        edittext.setPosition(label2.getWidth() + 100, 426);

        edittext.setMessageText("000");

        stage.addActor(edittext);


        up = new TextureRegionDrawable(new TextureRegion(new Texture("ui/down_60.png")));
        up.setMinSize(button_width, button_height);
        down = new TextureRegionDrawable(new TextureRegion(new Texture("ui/up_20.png")));
        down.setMinSize(button_width, button_height);

        TextButton.TextButtonStyle normal = new TextButton.TextButtonStyle();
        normal.up = up;
        normal.down = down;
        //font.getData().setScale(2);
        normal.font = font;
        normal.fontColor = Color.YELLOW;

        btn1 = new TextButton("TEST", normal);
        btn1.setPosition(Gdx.graphics.getWidth() - button_width, Gdx.graphics.getHeight() / 2);
        btn1.addListener(new ClickListener(){

                public void clicked(InputEvent event, float x, float y) {
                    //game.setScreen(new MultipPlayerBattle(game));
                    I.player1IP = getIpAddress();
                    I.player2IP = edittext.getText();
                    //game.setScreen(MyGdxGame.multipplayerbattle);
                   // App.request_permissions();
                }
            });
        stage.addActor(btn1);
    }

    //文本框背景
    private Texture createBackgroundTexture() {
        Pixmap pixmap = new Pixmap(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT, Pixmap.Format.RGBA8888);
        //pixmap.setColor(1, 0, 0, 1);
        //pixmap.drawRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
        pixmap.setColor(0x000000cc);
        pixmap.fillRectangle(0, 0, 4, pixmap.getHeight());
        pixmap.fillRectangle(0, 0, pixmap.getWidth(), 4);
        pixmap.fillRectangle(pixmap.getWidth() - 4, 0, 4, pixmap.getHeight());
        pixmap.fillRectangle(0, pixmap.getHeight() - 4, pixmap.getWidth(), 4);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    /**
     * 创建文本框中的光标纹理
     */
    private Texture createCursorTexture() {
        Pixmap pixmap = new Pixmap(8, TEXT_FIELD_HEIGHT - 4, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        //pixmap.fill();
        pixmap.fillRectangle(4, TEXT_FIELD_HEIGHT - 4, 4, 0);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    public static String getIpAddress() {
        try {
         Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
         InetAddress ip = null;
         while(allNetInterfaces.hasMoreElements()) {
         NetworkInterface netInterface = allNetInterfaces.nextElement();
         if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
         continue;
         } else {
         Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
         while (addresses.hasMoreElements()) {
         ip = addresses.nextElement();
         if (ip != null && ip instanceof Inet4Address) {
         return ip.getHostAddress();

         }
         }
         }

         }

         } catch (Exception e) {
         //System.err.println("IP地址获取失败" + e.toString());
         return "获取失败";
         }
         
        /*try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();
            I.E = addr.getHostName();
            //System.out.println("Local HostAddress: " + addr.getHostAddress());
            return addr.getHostAddress();
            
            //System.out.println("Local host name: " + hostname);
        } catch (UnknownHostException e) {}*/
        return "";
    }
}

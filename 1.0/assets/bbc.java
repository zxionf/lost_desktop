// package com.zyx.x;



// import com.badlogic.gdx.ApplicationListener;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.Pixmap;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.scenes.scene2d.Actor;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Label;
// import com.badlogic.gdx.scenes.scene2d.ui.List;
// import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
// import com.badlogic.gdx.scenes.scene2d.ui.Table;
// import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;

// public class MyGdxGame implements ApplicationListener {


//     SpriteBatch batch;
//     //CheckBox checkBox;
//     ScrollPane scrollPane;

//     Stage stage;
//     //Skin skin;

//     @Override
//     public void create() {

//         batch = new SpriteBatch();
//         stage = new Stage();
//         Gdx.input.setInputProcessor(stage);

//         /*skin = new Skin(Gdx.files.internal("uiskin.json"));

//          checkBox = new CheckBox("CheckBox", skin);
//          checkBox.addListener(new ChangeListener() {

//          @Override
//          public void changed(ChangeEvent event, Actor actor) {
//          if (checkBox.isChecked()) {
//          Gdx.app.log("TAG", "box is checked");
//          } else {
//          Gdx.app.log("TAG", "box is unchecked");
//          }
//          }
//          });
//          */
//         List.ListStyle skin = new List.ListStyle();
//         skin.font = mFont.mainbitmapFont;
//         skin.background = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("stone2.png"))));
//         skin.fontColorSelected = Color.RED;
//         skin.selection=new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("stone2.png"))));

//         //final List<String> list2 = new List<String>(skin);
//         /*List<Object> list2 = new List<Object>(skin);
//          String[] items = {"item5", "item6", "item7", "item8"};
//          Object[] sting = {new STING().get(3),new STING().get(4)};
//          list2.setItems(sting);
//          list2.pack();*/
//         Label.LabelStyle l = new Label.LabelStyle();
//         l.font = mFont.mainbitmapFont;
//         l.font.setScale(4);
//         Table table = new Table();

//         for(int i = 0 ; i < 100 ; ++i){
//             table.row();
//             //table.add(new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("stone2.png"))))), l));
//             //table.add();
//         }
//         //mEt u = new mEt();
//         //u.create();
//         ScrollPane.ScrollPaneStyle skin2 = new ScrollPane.ScrollPaneStyle();
//         //skin2.backgrou
//         Image i = new Image(R(300,900));
//         i.setPosition(400,300);
//         scrollPane = new ScrollPane(i,skin2);
//         scrollPane.setHeight(500);
//         scrollPane.setWidth(500);
//         scrollPane.setPosition(0, 40);
//         //scrollPane.get

//         scrollPane.addListener(new ChangeListener() {

//                 @Override
//                 public void changed(ChangeEvent event, Actor actor) {
//                     // 其实是内部的Actor来处理的响应函数
//                     //System.out.println(""+list2.getSelected());

//                 }

//             });

//         //stage.addActor(checkBox);
//         stage.addActor(scrollPane);
//     }

//     private Texture R(int weight,int height) {
//         Pixmap pixmap = new Pixmap(weight,height, Pixmap.Format.RGBA8888);
// //        pixmap.setColor(0x000000cc);
// //        pixmap.fillRectangle(0,0,pixmap.getWidth(),pixmap.getHeight());
// //        pixmap.setColor(0xffffffaa);
// //        pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         pixmap.setColor(0x000000cc);
//         //pixmap.fillCircle(pixmap.getWidth()/2,pixmap.getHeight()/2,pixmap.getHeight()/2);
//         pixmap.fillRectangle(0,0,4,pixmap.getHeight());
//         pixmap.fillRectangle(0,0,pixmap.getWidth(),4);
//         pixmap.fillRectangle(pixmap.getWidth()-4,0,4,pixmap.getHeight());
//         pixmap.fillRectangle(0,pixmap.getHeight()-4,pixmap.getWidth(),4);
//         //pixmap.setColor(0xff00ffcc);
//         pixmap.fillRectangle(4,4,pixmap.getWidth()-8,pixmap.getHeight()-8);
//         Texture texture = new Texture(pixmap);
//         pixmap.dispose();
//         return texture;
//     }

//     @Override
//     public void render() {
//         Gdx.gl.glClearColor(0.39f, 0.58f, 0.92f, 1.0f);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//         batch.begin();
//         mFont.mainbitmapFont.draw(batch,""+scrollPane.getVisualScrollY(),0,300);
//         batch.end();


//         stage.act();
//         stage.draw();
//     }

//     @Override
//     public void dispose() {
//         stage.dispose();
//         //skin.dispose();
//     }
//     @Override
//     public void resize(int p1, int p2) {
//     }

//     @Override
//     public void pause() {
//     }

//     @Override
//     public void resume() {
//     }
// }

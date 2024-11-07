// package com.zyx.x;

// import com.badlogic.gdx.Game;
// import com.badlogic.gdx.Gdx;
// import com.badlogic.gdx.graphics.GL20;
// import com.badlogic.gdx.graphics.OrthographicCamera;
// import com.badlogic.gdx.maps.MapLayer;
// import com.badlogic.gdx.maps.MapLayers;
// import com.badlogic.gdx.maps.MapObjects;
// import com.badlogic.gdx.maps.objects.RectangleMapObject;
// import com.badlogic.gdx.maps.tiled.TiledMap;
// import com.badlogic.gdx.maps.tiled.TmxMapLoader;
// import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
// import com.badlogic.gdx.math.Rectangle;
// import com.badlogic.gdx.math.Vector2;
// import com.badlogic.gdx.physics.box2d.BodyDef;
// import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
// import com.badlogic.gdx.physics.box2d.FixtureDef;
// import com.badlogic.gdx.physics.box2d.PolygonShape;
// import com.badlogic.gdx.physics.box2d.World;
// import com.badlogic.gdx.utils.Array;
// import com.badlogic.gdx.physics.box2d.Body;
// import com.badlogic.gdx.graphics.Camera;
// import com.badlogic.gdx.scenes.scene2d.Stage;
// import com.badlogic.gdx.scenes.scene2d.ui.Image;
// import com.badlogic.gdx.graphics.g2d.TextureRegion;
// import com.badlogic.gdx.graphics.Texture;
// import com.badlogic.gdx.scenes.scene2d.InputListener;
// import com.badlogic.gdx.scenes.scene2d.InputEvent;
// import com.badlogic.gdx.graphics.g2d.Batch;
// import com.badlogic.gdx.graphics.g2d.SpriteBatch;
// import com.badlogic.gdx.graphics.g2d.BitmapFont;
// import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


// public class MyGdxGame extends Game {
    
//     int PPM;
    
//     float size = 160*2;
//     OrthographicCamera camera;
    
//     TiledMap map;
//     OrthogonalTiledMapRenderer ren;
    
//     SpriteBatch batch;
//     Batch fontbatch;
    
//     World world;
//     Box2DDebugRenderer ren2;
//     Body mybody;
//     TextureRegion me;
    
//     Stage stage;
    
//     boolean right,stop,left,jump;
//     float nx,ny;
    
//     BitmapFont font;

//     public static int speed = 80;//用于控制图片的移动

//     //技能键

//     @Override
//     public void create() {
//         batch = new SpriteBatch();
//         fontbatch = new SpriteBatch();
//         font  = new BitmapFont(Gdx.files.internal("font/font-cn.fnt"),Gdx.files.internal("font/font-cn.png"),false);
//         camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//         map = new TmxMapLoader().load("Mp02.tmx");
//         ren = new OrthogonalTiledMapRenderer(map);
//         camera.position.x = Gdx.graphics.getWidth()/2;
//         camera.position.y = Gdx.graphics.getHeight()/2;
        
//         world = new World(new Vector2(0,-32),true);
//         ren2 = new Box2DDebugRenderer();
        
//         BodyDef bdef = new BodyDef();
//         bdef.type = BodyDef.BodyType.StaticBody;
//         FixtureDef fdef = new FixtureDef();
        
//         MapLayers layers = map.getLayers();
//         MapLayer layer = layers.get("Collision");
//         MapObjects mapobjs = layer.getObjects();
//         Array<RectangleMapObject> rmos = mapobjs.getByType(RectangleMapObject.class);
//         for(RectangleMapObject rmo: rmos){
//             /*Rectangle rect = rmo.getRectangle();
//             PolygonShape shape = new PolygonShape();
//             shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            
//             fdef.shape = shape;
//             bdef.position.set(rect.getX()+rect.getWidth()/2,rect.getY()+rect.getHeight()/2);
//             world.createBody(bdef).createFixture(fdef);*/
            
//             Rectangle rect = rmo.getRectangle();
//             //if (!mo.getProperties().get("name").equals("im")) {
//             Body body;
//             bdef.type = BodyDef.BodyType.StaticBody;
//             bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
//             body = world.createBody(bdef);
//             PolygonShape r = new PolygonShape();
            
//             r.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
//             fdef.shape = r;
            
// 			body.createFixture(fdef);
//         }
        
//         bdef.type = BodyDef.BodyType.DynamicBody;
//         bdef.position.set(300,300);
//         PolygonShape shape = new PolygonShape();
//         shape.setAsBox(8,12);
//         fdef.shape = shape;
//         fdef.restitution = 0.2f;
//         //fdef.friction = 0.1f;
//         mybody = world.createBody(bdef);
//         mybody.createFixture(fdef);
        
//         stage = new Stage();
//         init_stage();
//         Gdx.input.setInputProcessor(stage);
        
//         me = new TextureRegion(new Texture("tile.png"));
//         font.setColor(Color.RED);
//     }

//     @Override
//     public void render() {        
//         Gdx.gl.glClearColor(1, 1, 1, 1);
//         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        
//         world.step(Gdx.graphics.getDeltaTime(),200,200);
//         ren.setView(camera);
//         ren.render();
        
//         batch.setProjectionMatrix(camera.combined);
//         batch.begin();
//         batch.draw(me,mybody.getPosition().x -8,mybody.getPosition().y -12,16,24);
//         batch.end();
        
//         ren2.render(world,camera.combined);
        
//         update();
//         move();
        
//         stage.act();
//         stage.draw();
        
        
//         font.getData().setScale(2);
//         fontbatch.begin();
//         font.draw(fontbatch,""+mybody.getPosition().x+"\n"+mybody.getPosition().y+"\n"+nx+"\n"+ny,0,200);
//         fontbatch.end();
//     }
//     public void move()
//     {
//         if (!stop)
//         {
//             if (right)
//             {
//                 if (mybody.getLinearVelocity().x < speed)
//                 {
//                     //mybody.applyForceToCenter(new Vector2(speed, 0), true);
//                     mybody.applyLinearImpulse(new Vector2(speed,0),new Vector2(speed,0),true);
//                 }
//             }
//             else
//             {
//                 if (mybody.getLinearVelocity().x > -speed)
//                 {
//                     //mybody.applyForceToCenter(new Vector2(-speed, 0), true);
//                     mybody.applyLinearImpulse(new Vector2(-speed,0),new Vector2(-speed,0),true);
//                 }
//             }
//         }else{
//             //mybody.setLinearVelocity(0,mybody.getLinearVelocity().y);
//             //mybody.setLinearDamping(7);
//         }
//         if (jump)
//         {
//             if (mybody.getLinearVelocity().y < 0.01f && mybody.getLinearVelocity().y > -0.01f)
//             {
//                 mybody.applyForceToCenter(new Vector2(0, 4000f), true);
//             }
//         }
//     }
//     //在绘图前更新相机，坐标，世界
//     public void update() {
//         cameraupdate(camera, mybody.getPosition().x, mybody.getPosition().y);

//         //world.step(1 / 60f, 3, 2);

//         nx = mybody.getPosition().x - (52) / 2;
//         ny = mybody.getPosition().y - (52) / 2;

// 	}
// public static void cameraupdate(Camera camera,float targetX,float targetY){
//         float dx = (targetX - camera.position.x) / 32;
//         float dy = (targetY - camera.position.y) / 16;
//         camera.position.add(dx, dy, 0);
// 		camera.update();
//     }
//     private void configureCamera() {
//         if (Gdx.graphics.getHeight() < Gdx.graphics.getWidth())
//             camera.setToOrtho(false, size, size * Gdx.graphics.getHeight() / Gdx.graphics.getWidth());
//         else
//             camera.setToOrtho(false, size * Gdx.graphics.getWidth() / Gdx.graphics.getHeight(), size);
    
//     }
//     @Override
//     public void dispose() {
//         //shapeRenderer.dispose();
//     }

//     @Override
//     public void resize(int width, int height) {
//         configureCamera();
//     }

//     @Override
//     public void pause() {
//     }

//     @Override
//     public void resume() {
//     }
    
//     public void init_stage(){
//         Image image_left= new Image(new TextureRegion(new Texture("tile.png")));
//         Image image_right = new Image(new TextureRegion(new Texture("tile.png")));
//         Image image_up = new Image(new TextureRegion(new Texture("tile.png")));
//         Image image_down = new Image(new TextureRegion(new Texture("tile.png")));
        
//         image_left.setSize(150,150);
//         image_right.setSize(150,150);
//         image_up.setSize(150,150);
//         image_down.setSize(150,150);
        
//         image_left.setPosition(0,0);
//         image_down.setPosition(image_left.getWidth(),0);
//         image_right.setPosition(image_left.getWidth()*2,0);
//         image_up.setPosition(image_left.getWidth(),image_left.getWidth());
        
//         image_left.addListener(new InputListener(){

//                 @Override
//                 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     right = false;
//                     stop = false;
//                     return true;
//                     //return super.touchDown(event, x, y, pointer, button);
//                 }

//                 @Override
//                 public void touchUp(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     stop = true;
//                     // super.touchUp(event, x, y, pointer, button);
//                 }

//             });

//         image_right.addListener(new InputListener(){

//                 @Override
//                 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     right  = true;
//                     stop = false;
//                     return true;
//                     //return super.touchDown(event, x, y, pointer, button);
//                 }

//                 @Override
//                 public void touchUp(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     stop = true;
//                     //super.touchUp(event, x, y, pointer, button);
//                 }

//             });
//         image_up.addListener(new InputListener(){

//                 @Override
//                 public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     jump = true;
//                     return true;
//                     //return super.touchDown(event, x, y, pointer, button);
//                 }

//                 @Override
//                 public void touchUp(InputEvent event, float x, float y, int pointer, int button)
//                 {
//                     jump = false;
//                     //super.touchUp(event, x, y, pointer, button);
//                 }

//             });
//         stage.addActor(image_left);
//         stage.addActor(image_right);
//         stage.addActor(image_up);
//         stage.addActor(image_down);
        
//     }
    
// }

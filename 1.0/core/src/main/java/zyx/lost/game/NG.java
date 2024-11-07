package zyx.lost.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import zyx.lost.ThreeDeController;
import zyx.lost.I;

public class NG extends MyGame {

    CameraInputController camController;
    Environment environment;
    public PerspectiveCamera cam;
    public ModelBatch modelBatch;
    public Model model,tablebox;
    public ModelInstance instance,tableins;
    
    DirectionalShadowLight shadowLight;
    ModelBatch shadowBatch;
    
    float x= 0,y= 0,z = 5;
    
    float timer = 0;
    
    @SuppressWarnings("deprecation")
    @Override
    public void create() {

        Gdx.graphics.setVSync(false);
        Gdx.graphics.setForegroundFPS(-1);

        shadowBatch = new ModelBatch(new DepthShaderProvider());
        shadowLight = new DirectionalShadowLight(1024, 1024, 10f, 10f, 1f, 20f);
        shadowLight.set(0.8f, 0.8f, 0.8f, -.4f, -.4f, -.4f);
        
        
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        //environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
        environment.add(shadowLight);
        environment.shadowMap = shadowLight;
        
        modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(60, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        /*model = modelBuilder.createBox(5f, 5f, 5f,
                                       new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                                       Usage.Position | Usage.Normal);
        new Material(ColorAttribute.createDiffuse(Color.YELLOW));*/
        modelBuilder.begin();
        modelBuilder.node().id = "box";
        modelBuilder.part("box", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal,
                          new Material(ColorAttribute.createDiffuse(Color.GREEN)))
            .box(0f, 0f, 5f, 5f, 5f, 5f);
        model = modelBuilder.end();
        instance = new ModelInstance(model);
        
        modelBuilder.begin();
        modelBuilder.node().id = "top";
        modelBuilder.part("top", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal,
                     new Material(ColorAttribute.createDiffuse(new Color(0x63750A))))
            .box(0f, 0f, -0.5f, 25f, 25f, 1f);
        tablebox = modelBuilder.end();
        tableins = new ModelInstance(tablebox);
        
        camController = new CameraInputController(cam);
        //Gdx.input.setInputProcessor(camController);
        
        tdStage = new ThreeDeController();
        tdStage.init_stage(cam);
        Gdx.input.setInputProcessor(tdStage);
    }

    @Override
    public void render() {
        
        timer += Gdx.graphics.getDeltaTime();
        //instance.transform.translate(0,0,timer/1000);
        
        boxmove();
        
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        
        shadowLight.begin(Vector3.Zero, Vector3.Zero);
        shadowBatch.begin(shadowLight.getCamera());
        shadowBatch.render(instance);
        shadowBatch.end();
        shadowLight.end();
        
        modelBatch.begin(cam);
        modelBatch.render(instance,environment);
        modelBatch.render(tableins,environment);
        modelBatch.end();
        
        tdStage.act();
        tdStage.draw();
        tdStage.update();
        //Gdx.input.setInputProcessor(tdStage);
    }

    @Override
    public void dispose() {
        super.dispose();
        modelBatch.dispose();
        model.dispose();
    }
    
    public void boxmove(){
        if(I.controlstage_isUp){
            instance.transform.translate(x++/1000,y,z);
        }
    }
    
}

package zyx.lost.view;

public interface View {

    public abstract void create();

    public abstract void render(float p);

    public abstract void pause() ;

    public abstract void dispose();

    public abstract void resize(int p, int p1) ;

    public abstract void resume() ;

	public abstract void show();

    public abstract void hide();

    public abstract void fadein();
}

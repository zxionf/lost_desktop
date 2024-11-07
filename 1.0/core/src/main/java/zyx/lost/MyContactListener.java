package zyx.lost;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {

    boolean a = true;

    @Override
    public void beginContact(Contact contact) {
        //MyGdxGame.GM.getGame().player1.DeductHP();
        //MyGdxGame.GM.getGame().ib.addInfo("[RED][碰撞检测器][]");
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if((fa.getUserData() != null && fa.getUserData().equals("foot"))
           |(fb.getUserData() != null && fb.getUserData().equals("foot"))){
            I.isplayeronground = true;
            //MyGdxGame.GM.getGame().ib.addInfo("[RED][碰撞检测器][]on");
            //MyGdxGame.si.addInfo("idndndndnndndnenen");
            I.jumpacount = 2;
        }
    }

    @Override
    public void endContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if((fa.getUserData() != null && fa.getUserData().equals("foot"))
           |(fb.getUserData() != null && fb.getUserData().equals("foot"))){
            I.isplayeronground = false;
            //MyGdxGame.GM.getGame().ib.addInfo("[RED][碰撞检测器][]no");
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {
    }

}

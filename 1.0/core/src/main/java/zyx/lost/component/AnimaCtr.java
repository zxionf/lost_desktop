package zyx.lost.component;



public abstract class AnimaCtr {

    State state;
    Mode mode = Mode.nor;
    public float time ;
    boolean sleepfirst = false;

    public enum State { 
        pre, act, ok; 
    }
    
    public enum Mode{
        nor,sleep;
    }

    public AnimaCtr() {
        state = State.pre;
    }

    public AnimaCtr(State state) {
        this.state = state;
    }
    public AnimaCtr(float t) {
        time = t;
        mode = Mode.sleep;
    }
    public void action() {}

    public void act() {
        if(mode == Mode.nor){
            action();
            state = State.ok;
        }
        //I.E += "\n"+time;
    }

    public void update() {

    }

    public void setState(State s) {
        state = s;
    }
    public State getState() {
        return state;
    }

}

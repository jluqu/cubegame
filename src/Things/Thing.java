package Things;

public class Thing {
    protected float x = 0.0f;
    protected float y = 0.0f;
    boolean visible = true;
    
    public Thing(float x_in, float y_in) {
        x = x_in;
        y = y_in;
    }
    
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x_in) {
        x = x_in;
    }
    public void setY(float y_in) {
        x = y_in;
    }
    public void move(float dx, float dy) {
        x += dx;
        y += dy;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible_in) {
        visible = visible_in;
    }
    public void draw() {
        System.out.println("Thing::draw called... TODO: this should probably be an exception");
    }
}

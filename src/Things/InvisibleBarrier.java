package Things;


public class InvisibleBarrier extends RectangularThing {

    // Constructor
    public InvisibleBarrier(float x_in, float y_in, float z_in, float width_in, float height_in, float depth_in) {
        super(x_in, y_in, z_in, width_in, height_in, depth_in);
    }

    public void draw() {
        // Do nothing... its invisible!
    }
}

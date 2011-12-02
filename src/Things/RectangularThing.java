package Things;
import javax.vecmath.Vector3f;

import CustomExceptions.NegativeSizeException;

import com.bulletphysics.collision.shapes.BoxShape;

public abstract class RectangularThing extends RigidThing {
    public float width = 1.0f;
    public float height = 1.0f;
    public float depth = 1.0f;
    
    public RectangularThing(float x_in, float y_in, float z_in, 
                            float width_in, float height_in, float depth_in) throws NegativeSizeException {
        super(x_in, y_in, z_in);
        setSize(width_in, height_in, depth_in);
    }
    
    public void setSize(float width_in, float height_in, float depth_in) throws NegativeSizeException {
        width = width_in;
        height = height_in;
        depth = depth_in;
        if (width_in < 0f) {
            throw new NegativeSizeException("Rectangular thing width was < 0");
        }
        if (height_in < 0f) {
            throw new NegativeSizeException("Rectangular thing height was < 0");
        }
        if (depth_in < 0f) {
            throw new NegativeSizeException("Rectangular thing depth was < 0");
        }
        // TODO: should check negative sizes and throw negative size exception
        shape = new BoxShape(new Vector3f(width, height, depth));    
    }
    
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getDepth() { return depth; }
    
    public abstract void draw();
}

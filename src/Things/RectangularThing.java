package Things;
import CustomExceptions.NegativeSizeException;

public class RectangularThing extends Thing {
    protected float width = 1.0f;
    protected float height = 1.0f;
    protected float depth = 1.0f;
    
    public RectangularThing(float x_in, float y_in, float height_in, float width_in) {
        super(x_in, y_in);
        try {
            setHeight(height_in);
            setWidth(width_in);
        } catch (NegativeSizeException e) {
            System.out.println("Negative sizes aren't allowed!");
            System.exit(1);
        }
    }
    
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
    public float getDepth() {
        return depth;
    }
    public void setHeight(float height_in) throws NegativeSizeException {
        if (height_in < 0.0f) {
            throw new NegativeSizeException("RectangularThing height must be >= 0");
        }
        height = height_in;
    }
    public void setWidth(float width_in) throws NegativeSizeException {
        if (width_in < 0.0f) {
            throw new NegativeSizeException("RectangularThing width must be >= 0");
        }
        width = width_in;
    }
    public void setDepth(float depth_in) throws NegativeSizeException {
        if (depth_in < 0.0f) {
            throw new NegativeSizeException("RectangularThing width must be >= 0");
        }
        depth = depth_in;
    }
}

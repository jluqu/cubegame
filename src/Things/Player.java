package Things;

import javax.vecmath.Vector3f;
import CustomExceptions.NegativeSizeException;
import com.bulletphysics.collision.shapes.BoxShape;

// this is gonna change later.... a lot
public class Player extends Block {
	
    public Player(float x_in, float y_in) {
        super(x_in, y_in, 0f, 4f);
        init();
    }
    
    public Player() {
        super(0f, 0f, 0f, 4f);
        init();
    }
    
    public void init() {
        shape = new BoxShape(new Vector3f(2f, 2f, 2f));

        setColor(0f, 1f, 0f);
        setStatic(false);
        try {
        	setMass(1f);
        } catch (NegativeSizeException e) {
        	System.out.println("huh?... " + e.getMessage());
        }
        //clobberInertia();
        initRigidBody();
        getRigidBody().setFriction(0.3f);
        getRigidBody().setAngularFactor(0f);
    }
}

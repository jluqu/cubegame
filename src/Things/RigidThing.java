package Things;

import javax.vecmath.Vector3f;

import CustomExceptions.NegativeSizeException;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;

public abstract class RigidThing {

    public boolean isVisible = true;
    public boolean isStatic = true;
    public float x, y, z;
    
    protected RigidBody body = null;
    protected float mass = 0f;
    protected DefaultMotionState motionState = null;
    protected CollisionShape shape = null;
    protected Vector3f inertia = null;

    public RigidThing(float x_in, float y_in, float z_in) {
        moveTo(x_in, y_in, z_in);
        inertia = new Vector3f(0f, 0f, 0f);
        mass = 0f;
    }

    // call this after shape has been created
    public void initRigidBody() {
        if (motionState != null && shape != null) {
            if (inertia == null) {
                body = new RigidBody(mass, motionState, shape);                
            } else {
                body = new RigidBody(mass, motionState, shape, inertia);
            }
        } else {
            // TODO: make this an exception
            System.out.println("Ow! Something was null! TODO: make this an exception");
        }
    }
    
    public CollisionShape getShape() {
    	return shape;
    }
    public void setShape(CollisionShape shape_in) {
    	shape = shape_in;
    }
    
    public void moveTo(float x_in, float y_in, float z_in) {
        x = x_in;
        y = y_in;
        z = z_in;
        Transform t = new Transform();
        t.setIdentity();
        t.origin.set(x, y, z);
        setMotionState(new DefaultMotionState(t));
    }
    
    public RigidBody getRigidBody() {
        return body;
    }
    
    public void setMotionState(DefaultMotionState motionState_in) {
        motionState = motionState_in;
    }
    
    public void setMass(float mass_in) throws NegativeSizeException {
        if (mass_in < 0.0f) {
            throw new NegativeSizeException("mass must be >= 0");
        }
        mass = mass_in;
        if (mass > 0) {
            isStatic = false;
            CollisionShape shape = getShape();
            if (shape != null) {
                shape.calculateLocalInertia(mass, inertia);
            }
        }
    }
    public void clobberInertia() {
    	inertia = null;
    }
    
    public void setStatic(boolean isStatic_in) {
        isStatic = isStatic_in;
        if (isStatic) {
            mass = 0f;
        } else {
            mass = 1f;
        }
    }
    
    public abstract void draw();
}

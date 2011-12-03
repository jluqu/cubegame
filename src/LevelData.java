import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Vector3f;

import CustomExceptions.NegativeSizeException;
import Things.Block;
import Things.RigidThing;

import com.bulletphysics.dynamics.DynamicsWorld;


public class LevelData {
    private ArrayList<RigidThing> things;
    //private ArrayList<Thing> npcs;
    
    private final int HEIGHT = 13;
    private final int WIDTH = 21;
    
    private final float scale = 5.0f;

    private int data[][] = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    
    public void init(DynamicsWorld world) {
        things = new ArrayList<RigidThing>();
        
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (data[i][j] == 1) {
                    Block b;
                    b = new Block((float)j*scale, (float)(HEIGHT-i)*scale, 0f, scale*0.9f);
                    b.setStatic(true); // don't let it go anywhere
                    things.add(b);
                    
                    // physics crap
                    b.initRigidBody();
                    world.addRigidBody(b.getRigidBody());
                }
            }
        }
        
        // test cube we're gonna send a'tumblin
        Block b2 = new Block(18f*scale, 10f*scale, 0f, scale*0.9f);
        b2.setStatic(false);
        try {
            b2.setMass(1f);
        } catch (NegativeSizeException e) {
            System.out.println("huh?... " + e.getMessage());
        }
        b2.initRigidBody();

        b2.getRigidBody().setAngularVelocity(new Vector3f(0f, 0f, 1f));
        b2.getRigidBody().setLinearVelocity(new Vector3f(-1f, 0f, 0f));

        b2.getRigidBody().setCcdMotionThreshold(1f);
        b2.getRigidBody().setCcdSweptSphereRadius(0.2f);
        
        world.addRigidBody(b2.getRigidBody());
        things.add(b2);
    }
    
    public void draw() {
        Iterator<RigidThing> it = things.iterator();
        while (it.hasNext()) {
            RigidThing b = it.next();
            if (b.isVisible) {
                b.draw();
            }
        }
    }
    
}

import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Vector3f;

import CustomExceptions.NegativeSizeException;
import Things.Block;
import Things.InvisibleBarrier;
import Things.RigidThing;

import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;


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
        
        float blockWidth = scale*0.95f;
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (data[i][j] == 1) {
                    Block b;
                    b = new Block((float)j*scale, (float)(HEIGHT-i)*scale, 0f, blockWidth);
                    b.setStatic(true); // don't let it go anywhere
                    things.add(b);
                    
                    // physics crap
                    b.initRigidBody();
                    b.getRigidBody().setFriction(.5f);
                    world.addRigidBody(b.getRigidBody());
                }
            }
        }
        
        // test cubes we're gonna send a'tumblin
        int numCubes = 50;
        float cubeWidth = scale * .5f;
        for (int i = 0; i < numCubes; i++) {
            float x0 = 2f*scale + (float)(i % 10)*(cubeWidth+1f);
            float y0 = 5f*scale + (float)(i / 10)*(cubeWidth+1f);
            Block b2 = new Block(x0, y0, 0f, cubeWidth);
            b2.setColor(1f, .2f, .2f);
            b2.setStatic(false);
            try {
                b2.setMass(.2f);
            } catch (NegativeSizeException e) {
                System.out.println("huh?... " + e.getMessage());
            }
            b2.initRigidBody();
            RigidBody rb = b2.getRigidBody();
            rb.setFriction(.2f);
            if (rb.isActive()) {
                System.out.println("Block is active");
            }
    
            //b2.getRigidBody().setLinearVelocity(new Vector3f(-10f, 20f, 0f));
            rb.applyForce(new Vector3f(200f + x0*20, (float)(20*i), 0f), new Vector3f(0f, 1f, 0f));
            
            //b2.getRigidBody().applyTorque(new Vector3f(0f, 0f, 500f));
            
            //b2.getRigidBody().setCcdMotionThreshold(1f);
            //b2.getRigidBody().setCcdSweptSphereRadius(0.2f);
            
            world.addRigidBody(b2.getRigidBody());
            things.add(b2);
        }        
        
        // Add invisible barriers in front of and behind the level so the crap inside can't fall out
        float barrierWidth = 1f;
        InvisibleBarrier ib = new InvisibleBarrier(0f, 0f, blockWidth/2f + barrierWidth/2f,
                                                   scale*WIDTH*2, scale*HEIGHT*2, barrierWidth);
        ib.initRigidBody();
        ib.getRigidBody().setFriction(0.01f);
        ib.setStatic(true);
        world.addRigidBody(ib.getRigidBody());
        
        InvisibleBarrier ib2 = new InvisibleBarrier(0f, 0f, -blockWidth/2f - barrierWidth/2f,
                                                    scale*WIDTH*2, scale*HEIGHT*2, barrierWidth);
        ib2.initRigidBody();
        ib2.getRigidBody().setFriction(0.01f);
        ib2.setStatic(true);
        world.addRigidBody(ib2.getRigidBody());
        
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

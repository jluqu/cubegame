import java.util.ArrayList;
import java.util.Iterator;

import CustomExceptions.NegativeSizeException;
import Things.Block;
import Things.RigidThing;

import com.bulletphysics.dynamics.DynamicsWorld;


public class LevelData {
    private ArrayList<RigidThing> staticThings;
    //private ArrayList<MoveableThing> movableThings;
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
        staticThings = new ArrayList<RigidThing>();
        
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (data[i][j] == 1) {
                    Block b;
                    try {
                        b = new Block((float)j*scale, (float)(HEIGHT-i)*scale, 0f, scale*0.9f);
                        b.setStatic(true); // don't let it go anywhere
                        staticThings.add(b);
                        
                        // physics crap
                        b.initRigidBody();
                        world.addRigidBody(b.getRigidBody());
                    } catch (NegativeSizeException e) {
                        System.out.println("huh?... " + e.getMessage());
                    }
                }
            }
        }
    }
    
    public void draw() {
        Iterator<RigidThing> it = staticThings.iterator();
        while (it.hasNext()) {
            RigidThing b = it.next();
            if (b.isVisible) {
                b.draw();
            }
        }
    }
}

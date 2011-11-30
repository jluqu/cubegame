import java.util.ArrayList;
import java.util.Iterator;

import CustomExceptions.NegativeSizeException;
import Things.Block;
import Things.Thing;


public class LevelData {
    private ArrayList<Thing> staticThings;
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
    
    public void init() {
        staticThings = new ArrayList<Thing>();
        
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (data[i][j] == 1) {
                    Block b;
                    try {
                        b = new Block((float)j*scale, 
                                      (float)(HEIGHT-i)*scale,
                                      scale*0.9f,
                                      scale*0.9f);
                        b.setWidth(scale*0.9f);
                        b.setHeight(scale*0.9f);
                        b.setDepth(scale*0.9f);
                        
                        staticThings.add(b);
                    } catch (NegativeSizeException e) {
                        System.out.println("huh?... " + e.getMessage());
                    }
                }
            }
        }
    }
    
    public void draw() {
        Iterator<Thing> it = staticThings.iterator();
        while (it.hasNext()) {
            Thing b = it.next();
            b.draw();
        }
    }
}

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;

public class Level {
    private float cameraX;
    private float cameraY;
    
    private LevelData levelData;

    // physics crap
    private DynamicsWorld world = null;
    private DefaultCollisionConfiguration cConfig;
    private CollisionDispatcher dispatcher;
    private BroadphaseInterface broadphase;
    private ConstraintSolver solver;
        
    public Level() {
        init();
    }
    
    public void init() {
        // physics crap
        cConfig = new DefaultCollisionConfiguration();
        dispatcher = new CollisionDispatcher(cConfig);
        broadphase = new DbvtBroadphase();
        solver = new SequentialImpulseConstraintSolver();
        world = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, cConfig);

        levelData = new LevelData();
        levelData.init(world);
        
//        blockList = new ArrayList<RectangularThing>();
//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                if (data[i][j] == 1) {
//                    Block b;
//                    try {
//                        b = new Block((float)j*scale, 
//                                      (float)(HEIGHT-i)*scale,
//                                      scale*0.9f,
//                                      scale*0.9f);
//                        //b.setWidth(scale*0.9f);
//                        //b.setHeight(scale*0.9f);
//                        b.setDepth(scale*0.9f);
//                        
//                        blockList.add(b);
//                    } catch (NegativeSizeException e) {
//                        System.out.println("huh?... " + e.getMessage());
//                    }
//                }
//            }
//        }
                
    }
    
    public void draw() {
        setCamera(50f, 34f);

        glPushMatrix();
        
        glTranslatef(-cameraX, -cameraY, -300.0f);
        glRotatef(-10.0f, -1.0f, 0.3f, 0.0f);
        
        levelData.draw();

        //player.updatePosition(levelData);
        //player.draw();
        
        glPopMatrix();
    }
    
    public void setCamera(float x, float y) {
        cameraX = x;
        cameraY = y;
    }
    public void moveCamera(float x, float y) {
        cameraX += x;
        cameraY += y;
    }
   

}

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import javax.vecmath.Vector3f;

import Things.Player;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.bulletphysics.linearmath.Clock;


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
    private Clock clock;
    private Player player;
        
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
        world.setGravity(new Vector3f(0f, -80f, 0f));
        clock = new Clock();
        
        levelData = new LevelData();
        levelData.init(world, player);
    }
    public void update() {
        // simple dynamics world doesn't handle fixed-time-stepping
        float ms = clock.getTimeMicroseconds();
        clock.reset();
        
        // step the simulation
        if (world != null) {
            world.stepSimulation(ms / 1000000f);
        }
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
    
    public void jumpPlayer(float force) {
    	levelData.jumpPlayer(force);
    }
    public void accelPlayer(float ax, float ay) {
    	levelData.accelPlayer(ax, ay);
    }
}

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_LIGHT0;
import static org.lwjgl.opengl.GL11.GL_LIGHTING;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_NORMALIZE;
import static org.lwjgl.opengl.GL11.GL_POSITION;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrustum;
import static org.lwjgl.opengl.GL11.glLight;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
    
    private Level level;
    private final int WIN_HEIGHT = 700;
    private final int WIN_WIDTH = 1000;
    
    public static void main(String[] args) {
        new Game().execute();
        System.exit(0);
    }
    
    private void execute() {
        try {
            init();
        } catch (LWJGLException e) {
            System.out.println("Failed to initialize");
            e.printStackTrace();
            return;
        }
        loop();
        destroy();
    }

    // cleanup
    private void destroy() {
        Display.destroy();
    }

    // main loop
    private void loop() {
        
        float fpsLimit = 60;
        long loopDuration = (long)(1.0f/fpsLimit * 1000.0f); 
        
        //Keyboard.enableRepeatEvents(true);
        int leftKey = Keyboard.KEY_LEFT;
        int rightKey = Keyboard.KEY_RIGHT;
        int jumpKey = Keyboard.KEY_SPACE;
        int escapeKey = Keyboard.KEY_ESCAPE;
        boolean done = false;
        
        while (!Display.isCloseRequested() && !done) {
            long startTime = System.currentTimeMillis();
            
            if (Keyboard.isKeyDown(leftKey)) {
                level.accelPlayer(-40f, 0.0f);
            }
            if (Keyboard.isKeyDown(rightKey)) {
                level.accelPlayer(40f, 0.0f);
            }
            if (Keyboard.isKeyDown(jumpKey)) {
                level.jumpPlayer(10.0f);
            }
            if (Keyboard.isKeyDown(escapeKey)) {
                done = true;
            }
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            level.draw();
            level.update();
            Display.update();
            
            long elapsed = System.currentTimeMillis() - startTime; 
            if (elapsed < loopDuration) {
                try {
                    Thread.sleep(loopDuration - elapsed);
                } catch (InterruptedException e) {
                    System.out.println("This thread was interupted!");
                }
                
            } else {
              // TODO: come up with a clever way to print an under fpsLimit warning, but just once in a while!                
            }

        }
    }


    private void init() throws LWJGLException {
        Display.setLocation((Display.getDisplayMode().getWidth() - WIN_HEIGHT) / 2,
                            (Display.getDisplayMode().getHeight() - WIN_WIDTH) / 2);
        Display.setDisplayMode(new DisplayMode(WIN_WIDTH, WIN_HEIGHT));
        Display.setTitle("Game Test Framework");
        Display.create();

        FloatBuffer pos = BufferUtils.createFloatBuffer(4).put(new float[] { 60.0f, 100.0f, 100.0f, 0.0f});
        pos.flip();
        glLight(GL_LIGHT0, GL_POSITION, pos);
        
        glEnable(GL_CULL_FACE);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_NORMALIZE);

        glMatrixMode(GL_PROJECTION);

        float h = (float) WIN_HEIGHT / (float) WIN_WIDTH;
        glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 500.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        
        level = new Level();
    }
}

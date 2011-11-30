import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Game {
    
    private Level level;
    private final int WIN_HEIGHT = 520;
    private final int WIN_WIDTH = 840;
    
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
        
//        //Keyboard.enableRepeatEvents(true);
//        int leftKey = Keyboard.KEY_LEFT;
//        int rightKey = Keyboard.KEY_RIGHT;
//        int jumpKey = Keyboard.KEY_SPACE;
        
        while (!Display.isCloseRequested()) {
            long startTime = System.currentTimeMillis();
            
//            if (Keyboard.isKeyDown(leftKey)) {
//                level.accelPlayer(-0.2f, 0.0f);
//            }
//            if (Keyboard.isKeyDown(rightKey)) {
//                level.accelPlayer(0.2f, 0.0f);
//            }
//            if (Keyboard.isKeyDown(jumpKey) && level.playerIsGrounded()) {
//                level.accelPlayer(0.0f, 1.0f);
//            }
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            level.draw();
            
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
            
            
//           if (startTime > System.currentTimeMillis()) {
//               fps++;
//           } else {
//               long timeUsed = 5000 + (startTime - System.currentTimeMillis());
//               startTime = System.currentTimeMillis() + 5000;
//               System.out.println(fps + " frames in " + timeUsed / 1000f + " seconds = "
//                       + (fps / (timeUsed / 1000f)));
//               fps = 0;
//           }
            
        }
    }


    private void init() throws LWJGLException {
        Display.setLocation((Display.getDisplayMode().getWidth() - WIN_HEIGHT) / 2,
                            (Display.getDisplayMode().getHeight() - WIN_WIDTH) / 2);
        Display.setDisplayMode(new DisplayMode(WIN_WIDTH, WIN_HEIGHT));
        Display.setTitle("Game Test Framework");
        Display.create();

        FloatBuffer pos = BufferUtils.createFloatBuffer(4).put(new float[] { 50.0f, 100.0f, -100.0f, 0.0f});
        pos.flip();
        glLight(GL_LIGHT0, GL_POSITION, pos);
        
        glEnable(GL_CULL_FACE);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_NORMALIZE);

        glMatrixMode(GL_PROJECTION);

//        System.err.println("LWJGL: " + Sys.getVersion() + " / " + LWJGLUtil.getPlatformName());
//        System.err.println("GL_VENDOR: " + glGetString(GL_VENDOR));
//        System.err.println("GL_RENDERER: " + glGetString(GL_RENDERER));
//        System.err.println("GL_VERSION: " + glGetString(GL_VERSION));
//        System.err.println();
//        System.err.println("glLoadTransposeMatrixfARB() supported: " + GLContext.getCapabilities().GL_ARB_transpose_matrix);

        float h = (float) WIN_HEIGHT / (float) WIN_WIDTH;
        glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 400.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        
        level = new Level();
        level.init();
    }
}

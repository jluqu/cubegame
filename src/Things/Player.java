package Things;
import static org.lwjgl.opengl.GL11.GL_AMBIENT_AND_DIFFUSE;
import static org.lwjgl.opengl.GL11.GL_FLAT;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glMaterial;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glShadeModel;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import CustomExceptions.NegativeSizeException;


public class Player extends MoveableThing {
   
    // TEMP- just to get this working
    FloatBuffer red;
    
    public Player(float x_in, float y_in) {
        super(x_in, y_in, 4f, 2f);
        try {
            setDepth(2.0f);
        } catch (NegativeSizeException e) {
            System.out.println("huh?... " + e.getMessage());
        }
        
        red = BufferUtils.createFloatBuffer(4).put(new float[] { 1.0f, 0.0f, 0.0f, 1.0f});
        red.flip();
    }
    

    public void draw() {
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, red);
        glShadeModel(GL_FLAT);
        glPushMatrix();
        glTranslatef(x, y, 0.0f);
        
        glBegin(GL_QUADS);
        // left
        glNormal3f(-1.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, height, depth);
        glVertex3f(0.0f, height, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, depth);
        // right
        glNormal3f(1.0f, 0.0f, 0.0f);
        glVertex3f(width, 0.0f, depth);
        glVertex3f(width, 0.0f, 0.0f);
        glVertex3f(width, height, 0.0f);
        glVertex3f(width, height, depth);
        // top
        glNormal3f(0.0f, 1.0f, 0.0f);
        glVertex3f(0.0f, height, depth);
        glVertex3f(width, height, depth);
        glVertex3f(width, height, 0.0f);
        glVertex3f(0.0f, height, 0.0f);
        // bottom
        glNormal3f(0.0f, -1.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        glVertex3f(width, 0.0f, 0.0f);
        glVertex3f(width, 0.0f, depth);
        glVertex3f(0.0f, 0.0f, depth);
        // back
        glNormal3f(0.0f, 0.0f, 1.0f);
        glVertex3f(0.0f, height, 0.0f);
        glVertex3f(width, height, 0.0f);
        glVertex3f(width, 0.0f, 0.0f);
        glVertex3f(0.0f, 0.0f, 0.0f);
        // front
        glNormal3f(0.0f, 0.0f, -1.0f);
        glVertex3f(0.0f, height, depth);
        glVertex3f(0.0f, 0.0f, depth);
        glVertex3f(width, 0.0f, depth);
        glVertex3f(width, height, depth);
        glEnd();
        
        glPopMatrix();
    }
}

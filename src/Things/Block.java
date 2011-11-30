package Things;
import static org.lwjgl.opengl.GL11.*;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;


public class Block extends RectangularThing {
   
    // TEMP- just to get this working
    FloatBuffer blue;
    
    // Constructor
    public Block(float x_in, float y_in, float width_in, float height_in) {
        super(x_in, y_in, width_in, height_in);
        
        blue = BufferUtils.createFloatBuffer(4).put(new float[] { 0.2f, 0.2f, 1.0f, 1.0f});
        blue.flip();
    }

    public void draw() {
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue);
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

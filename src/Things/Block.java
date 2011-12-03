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
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import com.bulletphysics.linearmath.Transform;

// Defines a cube-shaped block at a specified position
public class Block extends RectangularThing {
    // TEMP- just to get this working
    FloatBuffer blue;
    private static FloatBuffer floatBuf = BufferUtils.createFloatBuffer(16);
    
    // Constructor
    public Block(float x_in, float y_in, float z_in, float size) {
        super(x_in, y_in, z_in, size, size, size);
        
        blue = BufferUtils.createFloatBuffer(4).put(new float[] {0.2f, 0.2f, 1.0f, 1.0f});
        blue.flip();
    }

    public void draw() {
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, blue);
        glShadeModel(GL_FLAT);
        
        glPushMatrix();
        
        Transform tr = new Transform();
        tr.set(motionState.graphicsWorldTrans);
        
        float[] glMat = new float[16];
        tr.getOpenGLMatrix(glMat);
        floatBuf.clear();
        floatBuf.put(glMat).flip();
        GL11.glMultMatrix(floatBuf);

       //glTranslatef(x, y, 0.0f);
        
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

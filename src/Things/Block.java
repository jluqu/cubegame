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
    FloatBuffer color;
    private static FloatBuffer floatBuf = BufferUtils.createFloatBuffer(16);
    
    // Constructor
    public Block(float x_in, float y_in, float z_in, float size) {
        super(x_in, y_in, z_in, size, size, size);
    
        // TODO: (PERFORMANCE!) use a single float buffer for each color
        color = BufferUtils.createFloatBuffer(4);
        setColor(.2f, .2f, 1f);
    }

    public void setColor(float r, float g, float b) {
        color.put(new float[] {r, g, b, 1.0f});
        color.flip();
    }
    
    public void draw() {
        glMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE, color);
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

        float halfWidth = width/2f;
        float halfHeight = height/2f;
        float halfDepth = depth/2f;
        
        //TODO: (PERFORMANCE!) This can probably be optimized once I figure out what I'm doing
        //TODO: One of the faces is getting drawn darker than the other ones, not sure why
        glBegin(GL_QUADS);

        // left
        glNormal3f(-1f, 0f, 0f);
        glVertex3f(-halfWidth, halfHeight, halfDepth);
        glVertex3f(-halfWidth, halfHeight, -halfDepth);
        glVertex3f(-halfWidth, -halfHeight, -halfDepth);
        glVertex3f(-halfWidth, -halfHeight, halfDepth);
        // right
        glNormal3f(1f, 0f, 0f);
        glVertex3f(halfWidth, -halfHeight, halfDepth);
        glVertex3f(halfWidth, -halfHeight, -halfDepth);
        glVertex3f(halfWidth, halfHeight, -halfDepth);
        glVertex3f(halfWidth, halfHeight, halfDepth);
        // top
        glNormal3f(0f, 1f, 0f);
        glVertex3f(-halfWidth, halfHeight, halfDepth);
        glVertex3f(halfWidth, halfHeight, halfDepth);
        glVertex3f(halfWidth, halfHeight, -halfDepth);
        glVertex3f(-halfWidth, halfHeight, -halfDepth);
        // bottom
        glNormal3f(0f, -1f, 0f);
        glVertex3f(-halfWidth, -halfHeight, -halfDepth);
        glVertex3f(halfWidth, -halfHeight, -halfDepth);
        glVertex3f(halfWidth, -halfHeight, halfDepth);
        glVertex3f(-halfWidth, -halfHeight, halfDepth);
        // back
        glNormal3f(0f, 0f, -1f);
        glVertex3f(-halfWidth, halfHeight, -halfDepth);
        glVertex3f(halfWidth, halfHeight, -halfDepth);
        glVertex3f(halfWidth, -halfHeight, -halfDepth);
        glVertex3f(-halfWidth, -halfHeight, -halfDepth);
        // front
        glNormal3f(0f, 0f, 1f);
        glVertex3f(-halfWidth, halfHeight, halfDepth);
        glVertex3f(-halfWidth, -halfHeight, halfDepth);
        glVertex3f(halfWidth, -halfHeight, halfDepth);
        glVertex3f(halfWidth, halfHeight, halfDepth);
        glEnd();
        
        glPopMatrix();
    }
}

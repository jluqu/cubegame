package Things;

import java.util.ArrayList;

public class MoveableThing extends RectangularThing {
    
    public MoveableThing(float x_in, float y_in, float height_in, float width_in) {
        super(x_in, y_in, height_in, width_in);
    }

    protected float dx = 0.0f;
    protected float dy = 0.0f;
    public boolean grounded = false;
    
    public float getDx() {
        return dx;
    }
    public float getDy() {
        return dy;
    }
    public void setDx(float dx_in) {
        dx = dx_in;
        if (dx < 0.05f && dx > -0.05f) {
            dx = 0.0f;
        }
    }
    public void setDy(float dy_in) {
        dy = dy_in;
        if (dy < 0.05f && dy > -0.05f) {
            dy = 0.0f;
        }        
    }
    public void accelerate(float dx_in, float dy_in) {
        dx += dx_in;
        dy += dy_in;
        if (dx < 0.05f && dx > -0.05f) {
            dx = 0.0f;
        }
        if (dy < 0.05f && dy > -0.05f) {
            dy = 0.0f;
        }        
    }
    
    public void updatePosition(ArrayList<RectangularThing> blocklist) {
        x += dx;
        y += dy;
        
        //checkForCollisions(blocklist);
    }
    
/*    public void checkForCollisions(ArrayList<RectangularThing> blocklist) {
        Iterator<RectangularThing> it = blocklist.iterator();
        while (it.hasNext()) {
            RectangularThing b = it.next();
            
            

        }
//        Iterator<RectangularThing> it = blocklist.iterator();
//        while (it.hasNext()) {
//            RectangularThing b = it.next();
//            bx = b.x;
//            by = b.y;
//            bx2 = b.x + b.getWidth();
//            by2 = by + b.getHeight();
//            
//            // old way... todo: create Edges
//            if (y + height > b.getY() && y < b.getY() + b.getHeight()) {
//                // bump on the right
//                if (px2 > bx && px < bx2) {
//                    return true;
//                }
//                // bump on the left
//                if (px < bx2 && px2 > bx) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    
//        //vertical
//        float px, px2, py, py2, bx, by, bx2, by2; 
//        px = player.getX() + dx;
//        px2 = player.getX2() + dx;
//        py = player.getY() + dy;
//        py2 = player.getY2() + dy;
//        
//        Iterator<Block> it = blockList.iterator();
//        while (it.hasNext()) {
//            Block b = it.next();
//            bx = b.getX();
//            by = b.getY();
//            bx2 = bx + b.getSize();
//            by2 = by + b.getSize();
//            
//            if (px2 > bx && px < bx2) {
//                // hit your head
//                if (py2 > by && py < by2) {
//                    return true;
//                }
//                // hit the ground
//                if (py < by2 && py2 > by) {
//                    return true;
//                }
//            }
//        }
//        return false;
//        
        
    }*/
}

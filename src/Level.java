import Things.Player;
import static org.lwjgl.opengl.GL11.*;

public class Level {
    private float cameraX;
    private float cameraY;
    private Player player;
    private final float scale = 5.0f;
    
    private LevelData levelData;
    
    
    public void init() {
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
        
        levelData = new LevelData();
        levelData.init();
        player = new Player(scale*10f, scale*5f);
        
    }
    
    public void draw() {
        setCamera(player.getX(), player.getY()+9.0f);

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
    public void movePlayer(float dx) {
        //float newX = player.getX() + dx;
        //if (newX > scale*1.0f && newX < scale*(WIDTH-1.0f)) {
        //    player.move(dx, 0.0f);
        //}
        
        
        
        player.move(dx, 0.0f);
    }
    public void accelPlayer(float ax, float ay) {
        player.setDx(player.getDx() + ax);
        player.setDy(player.getDy() + ay);
    }
    public void updatePlayerPosition() {
        player.setDx(player.getDx() * 0.6f);

    }
    

    

}

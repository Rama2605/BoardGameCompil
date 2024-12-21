package TheMain;

import javax.swing.*;
import java.awt.*;

public class PacChar {
    protected int x, y, dx, dy;
    private final Image imageOpen;
    private final Image imageClose;
    private boolean isOpen = true;

    public PacChar(int x, int y, String imageOpenPath, String imageClosePath) {
        this.x = x;
        this.y = y;
        this.imageOpen = new ImageIcon(getClass().getResource(imageOpenPath)).getImage();
        this.imageClose = imageClosePath != null ? new ImageIcon(getClass().getResource(imageClosePath)).getImage() : null;
    }

    public void setDirection(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        x += dx;
        y += dy;
        isOpen = !isOpen;
    }

    public void draw(Graphics g) {
        g.drawImage(isOpen && imageClose != null ? imageOpen : imageClose, x, y, 30, 30, null);
    }
}

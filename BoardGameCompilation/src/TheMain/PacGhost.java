package TheMain;

import java.util.Random;

public class PacGhost extends PacChar {
    private final Random random = new Random();

    public PacGhost(int x, int y, String imagePath) {
        super(x, y, imagePath, null);
    }

    public void moveRandomly(int width, int height) {
        x = Math.max(0, Math.min(width - 30, x + random.nextInt(7) - 3));
        y = Math.max(0, Math.min(height - 30, y + random.nextInt(7) - 3));
    }
}

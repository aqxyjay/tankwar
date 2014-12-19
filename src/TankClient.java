import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;
    private static final long serialVersionUID = 6254741544943336854L;
    Image offScreenImage = null;
    Tank myTank = null;

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.launchFrame();
    }

    public void paint(Graphics g) {
        myTank.draw(g);
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.LIGHT_GRAY);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void launchFrame() {
        this.setLocation(400, 300);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setBackground(Color.LIGHT_GRAY);
        this.addMyTank();
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    private void addMyTank() {
        myTank = new Tank(50, 50, Color.RED);
        this.addKeyListener(new KeyMonitor());
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    repaint();
                    Thread.sleep(80);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }
    }
}

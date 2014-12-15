import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankClient extends Frame {

    private static final long serialVersionUID = 6254741544943336854L;

    int x = 50, y = 50;

    // Ë«»º´æ±³¾°Í¼Æ¬
    Image offScreenImage = null;

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);

        y += 5;
    }

    @Override
    public void update(Graphics g) {
        super.update(g);
    }

    public void launchFrame() {
        this.setLocation(400, 300);
        this.setSize(800, 600);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setBackground(Color.LIGHT_GRAY);
        setVisible(true);

        new Thread(new PaintThread()).start();
    }

    public static void main(String[] args) {
        TankClient tc = new TankClient();
        tc.launchFrame();
    }

    private class PaintThread implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    repaint();
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

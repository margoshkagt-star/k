import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class root2 extends JFrame {

    public Image p;   // персонаж
    public Image r;   // цветок
    public Image fon; // фон

    private boolean flowerVisible = true;

    private int flowerX = 800;
    private int flowerY = 250;

    private int flowerWidth = 120;
    private int flowerHeight = 120;

    private BufferedImage buffer;
    public String _w;
    public root2(String s, String w) {
        setTitle("GAME");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        _w=w;

        p = Toolkit.getDefaultToolkit().getImage("src/U (2).png");
        r = Toolkit.getDefaultToolkit().getImage(s);
        fon = Toolkit.getDefaultToolkit().getImage("src/noname.png");

        addMouseListener(mouseListener);

        setVisible(true);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void paint(Graphics g) {
        if (buffer == null || buffer.getWidth() != getWidth()
                || buffer.getHeight() != getHeight()) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        }

        Graphics2D g2 = buffer.createGraphics();

        if (fon != null)
            g2.drawImage(fon, 0, 0, getWidth(), getHeight(), this);

        if (p != null)
            g2.drawImage(p, 200, 300, this);

        if (flowerVisible && r != null)
            g2.drawImage(r, flowerX, flowerY,
                    flowerWidth, flowerHeight, this);

        if (_w!=null) {
            g2.drawImage(Toolkit.getDefaultToolkit().getImage(_w), 1080, 100, 300, 200, this);
        }
        g.drawImage(buffer, 0, 0, this);
        g2.dispose();
    }

    private final MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {

            if (!flowerVisible) return;

            int mx = e.getX();
            int my = e.getY();

            Rectangle flowerRect =
                    new Rectangle(flowerX, flowerY,
                            flowerWidth, flowerHeight);

            if (flowerRect.contains(mx, my)) {
                flowerVisible = false;
                repaint();
dispose();

            }
        }
    };


}
/*import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Root extends JFrame  {
    public Image image;
    public Image r;
    public Image U;
    int x= 0;
    int y= 0;
root p;


    BufferedImage  bi;

    Root() {
// Set the title of the JFrame
        setTitle("GAME");
p=new root();

        image = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Start.png");
        //image_quit=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/quit.png");
        //image_start=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/start.png");
        //check=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/bro.png");
        r= Toolkit.getDefaultToolkit().getImage("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Start.png");



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height ); //1920,1080);
        setVisible(true);
        addMouseListener(ml);

        bi = new BufferedImage(getWidth(), getHeight(), 2);
        my_timer.start();
    }

    @Override
    public void paint(Graphics g) {
        // Call the superclass method to ensure proper rendering
        if (bi == null) {

            bi = new BufferedImage(getWidth(), getHeight(), 2);
        }

        Graphics2D test = bi.createGraphics();

        // Draw the image on the JFrame using drawImage()
        test.drawImage(image, 0, 0, this);



        g.drawImage(bi,0,0,this);
    }


    KeyListener KL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {


            int key = e.getKeyCode();

            if (key == KeyEvent.VK_A) {


                if (x - 6 > 0) x = x - 6; //глобальная координата смещается на шаг персонажа
                else x = x;
            }

            if (key == KeyEvent.VK_D) {

                if (x + 6 < 1920) x = x + 6;
                else x = x;
            }

            if (key == KeyEvent.VK_W) {


                if (y - 6 > 0) y = y - 6;

                else y = y;
            }

            if (key == KeyEvent.VK_S) {


                if (y + 6 < 1080) y = y + 6;
                else y = y;

            }

            if (key == KeyEvent.VK_R) {

                Rectangle rect1 = new Rectangle(rx, ry, rw1, rw2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
                Rectangle rect2 = new Rectangle(x - 20, y - 20, w1 + 40, w2 + 40);

                if (rect1.intersects(rect2) == true) {
                    pickable = 0;


                }
            }
            repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {


        }
    } ;

    public MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e)  {
            int x =e.getX();
            int y= e.getY();
            System.out.println(x+ " "+y);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {




            repaint();
        }

};
    Timer my_timer = new Timer(100, al);
class root {
    int pickable = 1;
    int use = 1;
    int type = 1;  // 1-medicine, 2-axe
    int inv = -1;

    Image image;
    int x, y;
    int w1, w2;

    root(String s, int _x, int _y, int _w1, int _w2) {
        x = _x - _w1 / 2;
        y = _y - _w2 / 2;
        w1 = _w1;
        w2 = _w2;
        image = Toolkit.getDefaultToolkit().createImage(s);
    }

    int d_pick = 0;
    int w1_pick = 50 + d_pick;
    int w2_pick = d_pick + 50;


} }

*/

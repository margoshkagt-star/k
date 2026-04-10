import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class level_choose extends JFrame {
    public Image image;
boolean set= false;
    public Image sett;
    public Image gal;
    int dif=4;

    BufferedImage bi;


    ActionListener al= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    };
    Timer my_timer = new Timer(100, al);

    level_choose() {
// Set the title of the JFrameaa
        setTitle("GAME");


        image = Toolkit.getDefaultToolkit().getImage("src/pic/Выбор уровня в лесу.png");
        //image_quit=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/quit.png");
        //image_start=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/start.png");
        //check=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/bro.png");
sett = Toolkit.getDefaultToolkit().getImage("pic/DifficultChoose.png");
        gal = Toolkit.getDefaultToolkit().getImage("pic/zelenaja-galochka-64x52.png");


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width, GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height); //1920,1080);
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

        if (set) {
            test.drawImage(sett, 0, 0,getWidth() , getHeight(),this );
        }

else {test.drawImage(image, 0, 0, this);}
        // Draw the image on the JFrame using drawImage()

        g.drawImage(bi, 0, 0, this);
    }

    public MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();


            if (!set) {
                if ((x > 250) && (x < 503) && (y > 400) && (y < 565)) {
                    try { new level_game(new File("src/Таблица (2).xlsx"), dif); } catch (Exception h) {}
                    dispose();
                }
                if ((x > 635) && (x < 895) && (y > 400) && (y < 565)) {
                    try {new level_game(new File("src/Таблица (4).xlsx"), dif);} catch (Exception h) {}
                    dispose();
                }
                if ((x > 1025) && (x < 1285) && (y > 400) && (y < 565)) {
                    try {new level_game(new File("src/Таблица (5).xlsx"), dif);} catch (Exception h) {}
                    dispose();
                }
                if ((x > 435) && (x < 700) && (y > 610) && (y < 785)) {
                    try {new level_game(new File("src/Map_01.xlsx"), dif);} catch (Exception h) {}
                    dispose();
                }
                if ((x > 835) && (x < 1095) && (y > 610) && (y < 785)) {
                    try {new level_game(new File("src/Map_01.xlsx"), dif);} catch (Exception h) {}
                    dispose();
                }
                if ((x > 42) && (x < 143) && (y > 37) && (y < 137)) {
                    set = true;
                }
            }

            if (set) {
                if ((x > 1103) && (x < 1191) && (y > 358) && (y < 443)) {
                  dif=1;
                }
                if ((x > 1104) && (x < 1190) && (y > 562) && (y < 646)) {
                    dif=2;
                }
                if ((x > 1105) && (x < 1191) && (y > 764) && (y < 815)) {
                    dif=3;
                }
                if ((x > 75) && (x < 162) && (y > 45) && (y < 132)) {
                    set= false;
                }
            }


            System.out.println(x + " " + y);
            System.out.println(set);
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
}




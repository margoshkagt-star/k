import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class choose extends JFrame  {
    public Image image;
int xscr= getWidth();
int yscr= getHeight();

    BufferedImage  bi;

    choose() {
// Set the title of the JFrameaa
        setTitle("GAME");


        image = Toolkit.getDefaultToolkit().getImage("src/pic/Выбор режима в магическом лесу.png");
        //image_quit=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/quit.png");
        //image_start=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/start.png");
        //check=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/bro.png");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height ); //1920,1080);
        setVisible(true);
        addMouseListener(ml);

        bi = new BufferedImage(xscr, yscr, 2);
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

    public MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e)  {

            int x =e.getX();
            int y= e.getY();
            if ((x>getWidth()*0.113)&&(x<getWidth()*0.437)&&(y>getHeight()*0.573)&&(y<getHeight()*0.913)){
                try { new game();}
                catch (Exception h) {}
dispose();
            }
            if ((x>getWidth()*0.546)&&(x<getWidth()*0.883)&&(y>getHeight()*0.573)&&(y<getHeight()*0.913)){

                try{  new level_choose();   } catch (Exception h) {    }
                dispose();
            }
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



}
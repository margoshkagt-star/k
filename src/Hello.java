import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hello extends JFrame  {
    public Image image;


    BufferedImage  bi;

    Hello() {
// Set the title of the JFrameaa
        setTitle("GAME");


        image = Toolkit.getDefaultToolkit().getImage("src/Start.png");
        //image_quit=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/quit.png");
        //image_start=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/start.png");
        //check=Toolkit.getDefaultToolkit().getImage("C:/Users/user/Downloads/bro.png");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height ); //1920,1080);
        setVisible(true);
        addMouseListener(ml);

        bi = new BufferedImage(getWidth(), getHeight(), 2);
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

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT)
        new Hello();
    }

    public MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e)  {

            int x =e.getX();
            int y= e.getY();
            if ((x>609)&&(x<914)&&(y>330)&&(y<398)){
                System.exit(0);
            }
            if ((x>609)&&(x<914)&&(y>126)&&(y<198)){
                dispose();
                try{  new game();   } catch (Exception h) {    }
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



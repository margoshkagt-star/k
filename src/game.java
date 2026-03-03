
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class game extends JFrame {
Toolkit toolkit= Toolkit.getDefaultToolkit();
Dimension screenSize = toolkit.getScreenSize();

int potin =0;
int story=0;

    public int xscr=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width; //определяем текуще разрешение экрана
    public int yscr=GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height;
    public int xmax=3*xscr; //вводим координаты длину и ширину поля
    public int ymax=3*yscr;
    public int xp=xmax/2; //вводим глобальныее координаты и ставим персонажа
    public int yp=ymax/2;

    public int xpr= xscr*20/100;
    public int ypr= yscr*20/100;

    public boolean shield=false;

    Random random = new Random();

    inventory abc = new inventory();
    int kolbush=1000;
int kolwolf=30;
int kol= kolbush+kolwolf; //общее количество всего

    public enemy [] snakes = new enemy[kolwolf];
    public obj_pick [] snake=new obj_pick[kolwolf];
    public boolean [] dead =new boolean[kolwolf];

    public obj [] bushes = new obj [kolbush];
    int [][] basa = new int [kol][12];
    public obj_pick [] sup =new obj_pick[24+kolwolf+2+4+1];

    int [] inv = new int[abc.k];


    public child [] children = new child[4];


int abl1=0;
    int ax=1319;
    int bx=1216;
    int mx=1113;
    int gx= 1010;
    int use=10;

    int finish = 3;

boolean fin=true;
boolean dea =true;

boolean first=true;



ActionListener sh = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (shield ==true) {
            shield=false;
            shi.stop();
        }
    }
};

Timer shi = new Timer(10000,sh);

    ActionListener hunge = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            me.hung--;
            if (me.hung==-10) {
                new Death();
            }
            repaint();
        }


};
    public Timer hunger =new Timer(1500,hunge);


    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x,y;
            x=e.getX();
            y=e.getY();
           System.out.println(x+ " "+y);
           if (((x>ax-100&&ax+100>x)&&(117-100<y&&117+100>y))&&use>0) {
               abl1=1;

           }
    //            else if (((1319-100<x&&1419>x)&&(117-100<y&&117+100>y))&&abl1==1) {
    //                abl1=0;
    //            }
            if (((x>bx-100&&bx+100>x)&&(117-100<y&&117+100>y))) {
                abl1=2;
            }
            if (((x>mx-100&&mx+100>x)&&(117-100<y&&117+100>y))) {
                abl1=3;
            }
            if (((x>gx-100&&gx+100>x)&&(117-100<y&&117+100>y))) {
                abl1=4;
            }
            else {}
System.out.println("X:"+x+"; Y:"+y);



          if (abc.onoff==true) {
              abc.clean();

              int r = abc.choose(x, y);



              if (r != -1) {
                  abc.inv[3][r]=1;
                  System.out.println("Присвоил");
              }
          }



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

    /*public void hunt (enemy kakashka, character me, int xp, int yp) {
        int dx = (kakashka.x - xp - me.x);
        int dy = (kakashka.y - yp- me.y);
        double distance= Math.sqrt(dx*dx+dy*dy);


        if (distance<=400) {
            kakashka.x -= 5 * dx / distance;
            kakashka.y -= 5 * dy / distance;

            if (collision(me, kakashka,xp,yp) == true) {
                me.health--;
                kakashka.x += (int) (100 * (dx / distance));
            }
        }*/

    ActionListener al = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (shield==true) {
                shi.start();
            }


            if(story<4||finish<4) {
                wolf1.hunt(wolf1, me, xp, yp, shield);
            }

    for (int i = 0; i < kolwolf; i++) {

        if (basa[i + kolbush][5] <= 0&&dead[i]==false) continue;
        if (basa[i + kolbush][5] <= 0&&dead[i]==true) {
            sup[24+i].pickable=1;

            dead[i]=false;
        }
        else {
            snakes[i].hunt(snakes[i], me, xp, yp,shield);
            sup[24+i].x=snakes[i].x;
           sup[24+i].y=snakes[i].y;
        }

}



if (finish==4&&fin==true&&story==0) {
    dispose();
    new Win("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Win.png");
    fin=false;
}

if (me.health==0&&dea==true) {
    dispose();
    new Death();
    dea=false;
}

if (potin==4&&first==true) {
    sup[30 + kolwolf].x=me.x+xp;
    sup[30 + kolwolf].y=me.y +yp;

    sup[30 + kolwolf].pickable=1;

    first=false;
}
            repaint();
        }


    };
    Timer my_timer = new Timer(100, al);
    KeyListener KL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {


            int key = e.getKeyCode();

            int acol=0; //счетчик соударений об кусты
            int dcol=0;
            int wcol=0;
            int scol=0;

            if ((key == KeyEvent.VK_E)) {

               // for (int i=0; i<kolbush;i++) {

              //  }


                for (int i=0; i<kolbush;i++) {

                    if (basa[i][5] > 0) {
                        Rectangle rect1 = new Rectangle(bushes[i].x - xp, bushes[i].y - yp, bushes[i].w1, bushes[i].w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
                        Rectangle rect2 = new Rectangle(me.x - 20, me.y - 20, me.w1 + 40, me.w2 + 40);

                        if (rect1.intersects(rect2) == true && abl1 == 1 && use > 0) {
                            basa[i][5] = basa[i][5] - 5;
                            use--;
                        } else if (rect1.intersects(rect2) == true && abl1 == 2 && basa[i][4] == 1) {
                            basa[i][5]--;
                        } else if (rect1.intersects(rect2) == true && abl1 == 3 && basa[i][4] == 2) {
                            basa[i][5]--;
                        } else if (rect1.intersects(rect2) == true && abl1 == 4 && basa[i][4] == 0) {
                            basa[i][5]--;
                        } else {
                        }
                    }
                }
                    for (int i = 0; i < kolwolf; i++) {
                        if (basa[i+kolbush][5] > 0) {
                            Rectangle rect1 = new Rectangle(snakes[i].x - xp, snakes[i].y - yp, snakes[i].w1, snakes[i].w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
                            Rectangle rect2 = new Rectangle(me.x - 40, me.y - 40, me.w1 + 60, me.w2 + 60);

                            if (rect1.intersects(rect2) == true && abl1 == 1 && use > 0) {
                                basa[i+kolbush][5] = basa[i+kolbush][5] - 5;
                                use--;
                            }
                        }
                    }



            }

                if (key == KeyEvent.VK_A) {

                    for(int i=0; i<kolbush; i++) {
                        if (basa[i][5]<=0) continue;
                        else if (me.anticollision(bushes[i], me, -6, 0, xp, yp) == true) acol++; //цикл для подсчета всех ударов обо все кусты
                        else continue;

                    }

                    if (acol==0) {
                        if (me.x - 6 < xpr) xp = xp - 6; //глобальная координата смещается на шаг персонажа
                        else me.x -= 6;
                    }
                    else me.x = me.x;
                }

                if (key == KeyEvent.VK_D) {

                    for(int i=0; i<kolbush; i++) {
                        if (basa[i][5]<=0) continue;
                        else if (me.anticollision(bushes[i], me, 6, 0, xp, yp) == true) dcol++;
                        else continue;
                    }

                    if (dcol==0) {
                        if (me.x + 6 > (xscr - xpr-me.w1)) xp = xp + 6;
                        else me.x += 6;
                    }
                     else me.x = me.x;
                }

                if (key == KeyEvent.VK_W) {

                    for(int i=0; i<kolbush; i++) {
                        if (basa[i][5]<=0) continue;
                        else if (me.anticollision(bushes[i], me, 0, -6, xp, yp) == true) wcol++;
                        else continue;
                    }

                        if(wcol==0) {
                            if (me.y - 6 < ypr) yp = yp - 6;
                            else me.y -= 6;
                        }
                     else me.y = me.y;
                }

                if (key == KeyEvent.VK_S) {

                    for(int i=0; i<kolbush; i++) {
                        if (basa[i][5]<=0) continue;
                        else if (me.anticollision(bushes[i], me, 0, 6, xp, yp) == true) scol++;
                        else continue;
                    }

                         if (scol==0){
                            if (me.y + 6 > yscr - ypr -me.w2) yp = yp + 6;
                            else me.y += 6;
                        }
                         else me.y = me.y;

                }

            if (key == KeyEvent.VK_Q) {
             if (abc.onoff==false) abc.onoff=true;
             else abc.onoff=false;
            }



            if (key == KeyEvent.VK_R) {

                for (int i=0; i<abc.k; i++) {
                    if (abc.inv[2][i]==1 && sup[inv[i]].use==0) {
                        abc.inv[2][i]=0;
                    }
                }




                for (int i=0; i<sup.length; i++) {
                    Rectangle rect1 = new Rectangle(sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
                    Rectangle rect2 = new Rectangle(me.x - 20, me.y - 20, me.w1 + 40, me.w2 + 40);

                    if (rect1.intersects(rect2) == true) {

                        int inv1 = abc.pick(sup[i]);
                        if (inv1!=-1) {


                        abc.inv[2][inv1] = 1;
                        inv[inv1] = i;
                    }
                    }
                }




            }



            if (key == KeyEvent.VK_T) {


                for (int i=0; i<abc.k; i++) {

                    if (inv[i] != -1) {
                        if (abc.inv[3][i] == 1 && sup[inv[i]].use != 0) {
                            sup[inv[i]].use_med(me);
                            use = sup[inv[i]].use_axe(use);
                            finish = sup[inv[i]].use_kid(finish);
                            sup[inv[i]].use_food(me);
                            shield=sup[inv[i]].use_shield(shield);

                            potin=sup[inv[i]].use_wrt1(potin,story);
                            sup[inv[i]].use_wrt2(story);
                            sup[inv[i]].use_wr3(story);
                            potin=sup[inv[i]].use_wr4(potin,story);

                            System.out.println("Использовал");
                            System.out.println(finish);

                            if(sup[inv[i]].type==5||sup[inv[i]].type==6||sup[inv[i]].type==7||sup[inv[i]].type==8) {
                                story=sup[inv[i]].use_st(story);
                            }

                            if (inv[i]==30+kolwolf&&story==4&&finish==4) {
                                Rectangle rect1 = new Rectangle(wolf1.x-xp, wolf1.y - yp, wolf1.w1, wolf1.w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
                                Rectangle rect2 = new Rectangle(me.x - 40, me.y - 40, me.w1 + 60, me.w2 + 60);

                                if (rect1.intersects(rect2) == true) {
                                    new Win("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Winwolf.png");
                                }
                            }

                        }
                    }
                }








            }



                repaint();

        }

        @Override
        public void keyReleased(KeyEvent e) {


        }
    } ;

    enemy wolf1 = new enemy("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wolf0.png", 2000, 1500,344*2/5,766*2/5);
    character me = new character("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\U (2).png", xscr/2, yscr/2, 150, 150);

obj_pick pic = new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Без названия.png",2500, 1500, 100,100 );

    BufferedImage  bi;
    BufferedImage f;
    BufferedImage hp;
    BufferedImage hu;
BufferedImage axe;

    BufferedImage bush;
    BufferedImage mushroom;
    BufferedImage grass;

    float k;
    public game() throws Exception
    {
//        for (int i=0; i<kolwolf; i++) {
//
//            snake[i]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\1771348523058.png",snakes[i].x,snakes[i].y,50,50);
//            snake[i].pickable=0;
//            dead[i]=false;
//        }

        try{  bush = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\bush.png"));    } catch (IOException e) {    }
        try{  mushroom = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\mushroom.png"));    } catch (IOException e) {    }
        try{  grass = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\grass1.png"));  } catch (IOException e) {    }


        try{  hu = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\1771348523058.png"));    } catch (IOException e) {    }
        try{  f = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\noname.png"));    } catch (IOException e) {    }
        try{  hp = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\hp.png"));    } catch (IOException e) {    }
        try{  axe = ImageIO.read(new File("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\34fabfc492ea8cd1a482dae1679e6754-Photoroom.png"));  } catch (IOException e) {    }

        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width,GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().height ); //1920,1080);
        setVisible(true);
        addKeyListener(KL);
        addMouseListener(ml);

for(int i=0; i<inv.length;i++) {
    inv[i]=-1;
}

        bi = new BufferedImage(getWidth(), getHeight(), 2);
        my_timer.start();
        hunger.start();

        FileInputStream fis = new FileInputStream("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Map_01.xlsx"); // } catch (IOException e) {    }

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("DataSheet.xlsx file opened successfully");

        XSSFSheet sheet = wb.getSheet("Лист1");

        for (int i=0; i<20; i++) {
int type = random.nextInt(5);
if (type==0) {
    type=1;
}
if (type==1) {
    sup[i] = new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\Без названия.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
    System.out.println("Сщздал");
}
else if (type==2) {
    sup[i] = new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\34fabfc492ea8cd1a482dae1679e6754-Photoroom.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
    sup [i].type=type;
}
else if (type==3) {
    sup[i] = new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\1771348523058.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
    sup [i].type=type;
}
else {
    sup[i] = new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\vd4yrq7ofue51.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
    sup [i].type=type;
}

        }

        sup[20]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-214-Photoroom.png", random.nextInt(xmax),random.nextInt(ymax), 70, 189 );
        sup[21]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-2141-Photoroom.png", random.nextInt(xmax),random.nextInt(ymax), 70, 238 );
        sup[22]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-2142-Photoroom.png", random.nextInt(xmax),random.nextInt(ymax), 70, 182 );
        sup[23]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-2143-Photoroom.png", random.nextInt(xmax),random.nextInt(ymax), 70, 217 );

        sup[20].type=10;
        sup[21].type=10;
        sup[22].type=10;
        sup[23].type=10;

        sup [24+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-214-Photoroom.png", random.nextInt(xmax),random.nextInt(ymax), 70, 189 );
        sup[25+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\70019-ODEI3S-214-Photoroom.png", xp, yp, 70, 189 );

        sup[24+kolwolf].type=4;
        sup[25+kolwolf].type=4;//random.nextInt((int)(xmax/2))   random.nextInt((int)(ymax/2))

        sup[26+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr1.png", xmax/2,ymax/2,50,50);
        sup[27+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr2.png", xmax/2,ymax/2,50,50);
        sup[28+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr3.png", xmax/2,ymax/2,50,50);
        sup[29+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr4.png", xmax/2,ymax/2,50,50);
        sup[30+kolwolf]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\potion.png", xmax/2,ymax/2,50,50);

        sup[26+kolwolf].type=5;
        sup[27+kolwolf].type=6;
        sup[28+kolwolf].type=7;
        sup[29+kolwolf].type=8;
        sup[30+kolwolf].type=9;

        sup[30+kolwolf].pickable=0;

        int count=0;
        double k;
            for (int a=2;a<28;a++)
            {
              for(int j=4;j<50;j++)
                {
               if (sheet.getRow(a).getCell(j).getCellType() == CellType.BLANK)
                 {
                       k=0;
                   }
                 else
                   {

                        k = sheet.getRow(a).getCell(j).getNumericCellValue();

                       System.out.print(k+" ");

                        basa[count][2] = 100;
                        basa[count][3] = basa[count][2];
                        basa[count][0] = (int) ((j-4)*basa[count][2]+basa[count][2]/2); //использую рандом для создания координат и размеров кустоов
                        basa[count][1] = (int) ((a-2)*basa[count][3]+basa[count][3]/2);
                        basa[count][4] = (int) (k);
                        basa[count][5] = 5;

                      switch (basa[count][4])
                        {
                      case (1):
                        bushes[count] = new obj("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\grass1.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                        break;
                      case (2):
                        bushes[count] = new obj("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\bush.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                        break;
                      case (3):
                        bushes[count] = new obj("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\mushroom.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                        break;
                        default:
                        bushes[count] = new obj("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\bush.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                        break;
                        }
                       count++; System.out.print(k); System.out.print(count);
                    }

                }

            }



             //общее количество всего
       for(int i=0; i<kolwolf; i++) {

            basa[i+kolbush][0] = (int) (Math.random() * xmax); //использую рандом для создания координат и размеров кустоов
            basa[i+kolbush][1] = (int) (Math.random() * ymax);
           basa[i+kolbush][2] = 100; //(int) (Math.random() * 150);
           basa[i+kolbush][3] = basa[i][2] ;
           basa[i+kolbush][4] = 1;
           basa[i+kolbush][5] = 4;
          snakes[i] = new enemy("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\photo_2025-12-16_19-55-46-Photoroom.png",basa[i+kolbush][0], basa[i+kolbush][1], basa[i+kolbush][2], basa[i+kolbush][3] );
           sup[24+i]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\1771348523058.png",snakes[i].x,snakes[i].y,50,50);
           sup[24+i].pickable=0;
           sup[24+i].type=3;
           dead[i]=true;
     }




    }


    public void paint(Graphics g)
    {
        if (bi == null) {

            bi = new BufferedImage(getWidth(), getHeight(), 2);
        }

        Graphics2D test = bi.createGraphics();
        //f= f.getScaledInstance((int)(f.getWidth()*k), (int)(f.getHeight()*k), Image.SCALE_SMOOTH );

        test.drawImage(f,0,0,this );





//        int dx = (wolf1.x+wolf1.w1/2 - me.x-me.w1/2); // расстояние по х нормированное по серединам картинок
//        int dy = (wolf1.y+wolf1.w2/2 - me.y-me.w2/2);
//        double distance= Math.sqrt(dx*dx+dy*dy);
        AffineTransform at = new AffineTransform();
        double angle = wolf1.rotate1(wolf1, me, xp, yp); // Поворот на 45 градусов
        int x = wolf1.x + wolf1.w1 / 2 - xp;// Центр по X
        int y = wolf1.y + wolf1.w2 / 2 - yp;// Центр по Y
        at.rotate(angle, x, y);
            test.setTransform(at);  //Создаю преобразование

                //Поворачиваю вокруг x,y
                 // "Добавляю" (применяю) поворот вокруг x,y


        test.drawImage(wolf1.image, wolf1.x-xp, wolf1.y-yp, wolf1.w1, wolf1.w2,  this); //Рисую волка с примененным поворотом
test.setTransform(new AffineTransform()); //Создаю и сразу применяю пустую прансформацию (т.е. она ничего не крутит)

        test.drawImage(me.image,me.x,me.y,me.w1, me.w2,this);


        for(int i=0; i<kolbush; i++) { //с помощью цикла отрисовываю все кусты
            if(basa[i][5]<=0) continue;
            else test.drawImage(bushes[i].image, bushes[i].x - xp, bushes[i].y - yp, bushes[i].w1, bushes[i].w2, this);
        }




        for(int i=0; i<kolwolf; i++) { //с помощью цикла отрисовываю все кусты



            AffineTransform at1 = new AffineTransform(); //Создаю преобразование
            double angle1 = snakes[i].rotate1(snakes[i], me, xp,yp); // Поворот на 45 градусов
            int x1 = snakes[i].x+snakes[i].w1/2 - xp;// Центр по X
            int y1 =snakes[i].y+snakes[i].w2/2 - yp; // Центр по Y
            at1.rotate(angle1, x1, y1); //Поворачиваю вокруг x,y
             // "Добавляю" (применяю) поворот вокруг x,y

            if (basa[i+kolbush][5]<=0)
            {
                continue;
            }
            else
            {
                test.setTransform(at1);
                test.drawImage(snakes[i].image, snakes[i].x - xp, snakes[i].y - yp, snakes[i].w1, snakes[i].w2, this);
            }

            test.setTransform(new AffineTransform());

        }

        for (int i=0; i< sup.length; i++) {
            if (sup [i].pickable == 1) {
                test.drawImage(sup[i].image, sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2, this);
            }
        }

        for (int i=0; i< sup.length; i++) {
           if (sup [i].pickable == 1) {
               test.drawImage(sup[i].image, sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2, this);
            }
        }


        if (me.health>=1){
            test.drawImage(hp,xscr/10-100,yscr-150,this );
        }
        if (me.health>=2){
            test.drawImage(hp,xscr*2/10-100,yscr-150,this );
        }
        if (me.health>=3){
            test.drawImage(hp,xscr*3/10-100,yscr-150,this );
        }

        int h=40;

if (me.hung>=10){
    test.drawImage(hu,56,623, h,h,this );
}
if (me.hung>=20){
    test.drawImage(hu,56+h,623, h,h,this );
}
if (me.hung>=30){
    test.drawImage(hu,56+h*2,623, h,h,this );
}
        if (me.hung>=40){
            test.drawImage(hu,56+h*3,623, h,h,this );
        }
        if (me.hung>=50){
            test.drawImage(hu,56+h*4,623, h,h,this );
        }
        if (me.hung>=60){
            test.drawImage(hu,56+h*5,623, h,h,this );
        }
        if (me.hung>=70){
            test.drawImage(hu,56+h*6,623, h,h,this );
        }
        if (me.hung>=80){
            test.drawImage(hu,56+h*7,623, h,h,this );
        }
        if (me.hung>=90){
            test.drawImage(hu,56+h*8,623, h,h,this );
        }

        test.drawImage(axe,1319,117,100,100,this );




        test.drawImage(bush,1216,117,100,100,this );
        test.drawImage(mushroom,1113,117,100,100,this );
        test.drawImage(grass,1010,117,100,100,this );

        if(abl1==1&&use>0) {
            test.fillRect(ax-10, 117-10, 20,20);
            test.setColor(Color.RED);
            test.setFont(new Font("Arial",Font.BOLD, 24));
            test.drawString(String.valueOf(use),ax-15,117+10);
        }



        if(abl1==2) test.fillRect(bx-10, 117-10, 20,20);
        if(abl1==3) test.fillRect(mx-10, 117-10, 20,20);
        if(abl1==4) test.fillRect(gx-10, 117-10, 20,20);


if (shield==true) {
    test.drawImage(sup[24+kolwolf].image,me.x,me.y,40,40,this);
}

        if (abc.onoff==true) {
            for (int i=0; i<abc.k; i++) {
                if (abc.inv[3][i]==1) {
                    test.drawImage(abc.choosable, abc.inv[0][i],abc.inv[1][i] , abc.a+3, abc.b+3, this);

                }
                if (abc.inv[3][i]==0) test.drawImage(abc.in, abc.inv[0][i],abc.inv[1][i] , abc.a, abc.b, this);

if (inv[i]!=-1) {
    if (abc.inv[2][i] == 1 && sup[inv[i]].use != 0) {
        test.drawImage(sup[inv[i]].image, abc.inv[0][i], abc.inv[1][i], abc.a + 3, abc.b + 3, this);
    }
}
            }
        }


//        for (int i=0; i< sup.length; i++) {
//            if (sup [i].pickable == 1) {
//               test.drawImage(sup[i].image, sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2, this);
//            }
//        }




//        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
//        test.setComposite(alphaComposite);


        g.drawImage(bi,0,0,this); //отрисовываю холст


    }



//    public static void main(String[] args) throws Exception
//    {
//
//
//
//
//        new game();
//
//
//    }


}

class character
{
    Image image;
    int x,y, health, hung;
int w1, w2;

    boolean anticollision(obj c1,character c2,int  dx,int dy, int xp, int yp) {

        Rectangle rect1 = new Rectangle(c1.x-xp , c1.y -yp, c1.w1, c1.w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
        Rectangle rect2 = new Rectangle(c2.x+dx, c2.y+dy, c2.w1, c2.w2);

        return rect1.intersects(rect2);
    }

    character(String s, int _x, int _y, int _w1, int _w2 )
    {
        x= _x-_w1/2;
        y= _y-_w2/2;
        image = Toolkit.getDefaultToolkit().createImage(s);
        health = 3;
        hung=100;
        w1= _w1;
        w2=_w2;
    }

}

class enemy
{

    Image image;
    int x,y, health;
    int w1, w2;

    enemy(String s, int _x, int _y, int _w1, int _w2 )
    {
        x= _x-_w1/2;
        y= _y-_w2/2;
        image = Toolkit.getDefaultToolkit().createImage(s);
        health = 3;
        w1= _w1;
        w2=_w2;
    }

    public void hunt (enemy kakashka, character me, int xp, int yp, boolean shield) {
        int dx = (kakashka.x - xp - me.x);
        int dy = (kakashka.y - yp- me.y);
        double distance= Math.sqrt(dx*dx+dy*dy);


        if (distance<=400) {
            kakashka.x -= 5 * dx / distance;
            kakashka.y -= 5 * dy / distance;

            if (collision(me, kakashka,xp,yp) == true) {
                if (shield==false) {
                    me.health--;
                }
                kakashka.x += (int) (100 * (dx / distance));
            }
        }

    }

  public double rotate1 (enemy e1, character me, int xp, int yp) {
      int dx = (e1.x +e1.w1/2- xp- me.x-me.w1/2);
      int dy = (e1.y +e1.w2/2- yp - me.y-me.w2/2);
      double ygol;
      double distance= Math.sqrt(dx*dx+dy*dy);
     if (dy>0) {
         ygol =  -Math.asin(dx/ distance);

      }
  else ygol =  3.14+ Math.asin(dx/ distance);

      return ygol;
  }


    boolean collision(character c1, enemy c2, int xp, int yp) {
        // Используем класс Rectangle для простого обнаружения пересечений
        Rectangle rect1 = new Rectangle(c1.x, c1.y, 100, 100);
        Rectangle rect2 = new Rectangle(c2.x-xp, c2.y-yp,c2.w1 , c2.w2);

        return rect1.intersects(rect2);
    }

}

class obj
{
    Image image;
    int x,y;
int w1, w2;


    obj(String s, int _x, int _y, int _w1, int _w2)
    {
        x= _x-_w1/2;
        y= _y-_w2/2;
        w1= _w1;
        w2=_w2;
        image = Toolkit.getDefaultToolkit().createImage(s);
    }



}

class obj_pick {
    int pickable=1;
    int use=1;
    int type =1;  // 1-medicine, 2-axe
int inv=-1;

    Image image;
    int x,y;
    int w1, w2;
    obj_pick (String s, int _x, int _y, int _w1, int _w2) {
        x= _x-_w1/2;
        y= _y-_w2/2;
        w1= _w1;
        w2=_w2;
        image = Toolkit.getDefaultToolkit().createImage(s);
    }

    int d_pick=0;
    int w1_pick= 50 +d_pick;
    int w2_pick =d_pick +50;

    public void use_med (character me){
        if (type ==1) {
            if (me.health<3) {
                use--;
                me.health++;
            }
        }
    }

     public int use_axe (int us) {
    if (type==2) {
    use --;
    us= us+10;

    }
    return us;
    }

    public int use_kid (int fin) {
        if (type ==10) {
            fin= fin +1;
            use--;
        }
        return fin;
    }

public void use_food(character me){
        if (type==3) {
            me.hung=me.hung+8;
            use--;

        }
}

public boolean use_shield (boolean shield) {
        shield=false;
        if (type==4) {
            shield=true;
            use--;

        }
        return shield;
}

public int use_wrt1 (int potin, int story) {
        if (type==5) {
use--;
new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\root.png", "C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr1.png");
new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\mox.png", "C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr1.png");
new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wat.png", "C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr1.png");
potin=potin+3;
story++;
        }
        System.out.println("Wrt1");
    System.out.println(type);
        return potin;
    }
    public void use_pot (){

    }
    public void use_wrt2 (int story){
if (type==6){
    new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr2.png", null);
    use--;
    story++;
}
        System.out.println("Wrt2");
    }

    public void use_wr3 ( int story) {
        if (type==7){
            new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr3.png", null);
            use--;
            story++;
        }
        System.out.println("Wrt3");
    }


    public int use_wr4 (int potin, int story) {
        if (type==8){
            new root2("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\fl.png","C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\wr4.png" );
            use--;
            potin++;
            story++;
        }
        System.out.println("Wrt4");
return potin;
    }

    public int use_st (int story){
story++;
return story;

    }
}

class child {
    Image image;
    int x,y, health;
    int w1, w2;
    int pickable=1;

    boolean anticollision(obj c1,character c2,int  dx,int dy, int xp, int yp) {

        Rectangle rect1 = new Rectangle(c1.x-xp , c1.y -yp, c1.w1, c1.w2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм
        Rectangle rect2 = new Rectangle(c2.x+dx, c2.y+dy, c2.w1, c2.w2);

        return rect1.intersects(rect2);
    }


    child (String s, int _x, int _y, int _w1, int _w2 )
    {
        x= _x-_w1/2;
        y= _y-_w2/2;
        image = Toolkit.getDefaultToolkit().createImage(s);

        w1= _w1;
        w2=_w2;
    }


        

}

class inventory {


    Image in =Toolkit.getDefaultToolkit().createImage("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\IMG_20260120_214856.jpg") ;
    Image choosable = Toolkit.getDefaultToolkit().createImage("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\IMG_20260127_215309.png") ;

    int x=400; //координата первого квадрата
    int y=600;

    int a=50; //длина и ширина квадрата (ячейки) инвентаря
    int b=a;

    int n=5; //количество ячеек в ряду инвентаря
    int k=20; //количество ячеек в инвентаре
    int [][] inv = new int[4][k]; // массив чисел для инвентаря 1-х; 2-y; 3- занята/не занята

    boolean onoff =false;



    inventory () {
        int c=0; //колво столбцов
        for (int i=0; i<k; i++) {
            if (i - c * n > 5) {
                c++;
                System.out.println("прибавил " + c);
            } // (коор.нач.ин.+(ном.яч.-колво.ряд*колво.в.ряд)

            inv[0][i] = x + i * a - c * n * a; //х координата ячейки инвентаря
            inv[1][i] = y + b * c;


            System.out.println(inv[0][i] + " " + inv[1][i]);

            inv[2][i] = 0;
            inv[3][i] = 0;
        }

        inv[0][0] = inv[0][n] ;
        inv[1][0]= inv[1][k-1];
    }


    public int pick (obj_pick d) {
        boolean nash=false;
        int cell=-1;
 if (d.pickable !=0) {

     for (int i = 1; i < k; i++) {


         if (inv[2][i] == 0) {
             inv[2][i] = 1;
             //nash=true;
             cell = i;
             d.pickable = 0;
             break;
         } else continue;
     }
     if (cell == -1) {
         inv[2][0] = 1;
     }
 }
        return cell;
    }

     public int choose (int x, int y) {
    int r=-1;
         Rectangle pick = new Rectangle(x, y, 2, 2); //тнимаю глобальные координаты персонажа, чтобы хитбоксы куста не убегалм

         for (int i=0; i<k; i++) {

       Rectangle inc = new Rectangle(inv[0][i]+2,inv[1][i]+2 , a-4, b-4);

              if  ( pick.intersects(inc)==true) {
              r=i;
                  System.out.println("Нашел");
              break;

              }

     }
     return r;
    }

 public void clean (){
    for (int i=0; i<k; i++) {
        inv[3][i]=0;
        System.out.println("0");

    }
    }


}


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


public class level_game extends JFrame {
    Toolkit toolkit= Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    int dif;

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
    public obj_pick [] sup =new obj_pick[24+kolwolf];

    int [] inv = new int[abc.k];


    public child [] children = new child[4];


    int abl1=0;
    int ax=1319;
    int bx=1216;
    int mx=1113;
    int gx= 1010;
    int use=10;

    int finish = 0;

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
            int x, y;
            x = e.getX();
            y = e.getY();
            System.out.println(x + " " + y);

            if (dif!=3&&dif!=4) {

                if (((x > ax - 100 && ax + 100 > x) && (117 - 100 < y && 117 + 100 > y)) && use > 0) {
                    abl1 = 1;

                }
                //            else if (((1319-100<x&&1419>x)&&(117-100<y&&117+100>y))&&abl1==1) {
                //                abl1=0;
                //            }

                if (dif != 2) {

                    if (((x > bx - 100 && bx + 100 > x) && (117 - 100 < y && 117 + 100 > y))) {
                        abl1 = 2;
                    }
                    if (((x > mx - 100 && mx + 100 > x) && (117 - 100 < y && 117 + 100 > y))) {
                        abl1 = 3;
                    }
                    if (((x > gx - 100 && gx + 100 > x) && (117 - 100 < y && 117 + 100 > y))) {
                        abl1 = 4;
                    }
                } else {
                }
                System.out.println("X:" + x + "; Y:" + y);

if (dif!=3&&dif!=4){
                if (abc.onoff == true) {
                    abc.clean();

                    int r = abc.choose(x, y);


                    if (r != -1) {
                        abc.inv[3][r] = 1;
                        System.out.println("Присвоил");
                    }
                }
            }

        }//

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

            if (shield==true) {
                shi.start();
            }



if (dif!=4) {
    for (int i = 0; i < kolwolf; i++) {

        if (basa[i + kolbush][5] <= 0 && dead[i] == false) continue;
        if (basa[i + kolbush][5] <= 0 && dead[i] == true) {
            sup[24 + i].pickable = 1;

            dead[i] = false;
        } else {
            snakes[i].hunt(snakes[i], me, xp, yp, shield);
            sup[24 + i].x = snakes[i].x;
            sup[24 + i].y = snakes[i].y;
        }

    }
}


            if (finish==4&&fin==true) {
                dispose();
                new Win("src/pic/WinNoWolf.png");
                fin=false;
            }

            if (me.health==0&&dea==true) {
                dispose();
                new Death();
                dea=false;
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

            if ((key == KeyEvent.VK_E)&&dif!=3&&dif!=4) {

if (dif==1) {
    for (int i = 0; i < kolbush; i++) {

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


            if ((key == KeyEvent.VK_Q)&&dif!=3&&dif!=4) {
                if (abc.onoff==false) abc.onoff=true;
                else abc.onoff=false;
            }



            if ((key == KeyEvent.VK_R)&&dif!=3&&dif!=4) {

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



            if ((key == KeyEvent.VK_T)&&dif!=3&&dif!=4) {


                for (int i=0; i<abc.k; i++) {

                    if (inv[i] != -1) {
                        if (abc.inv[3][i] == 1 && sup[inv[i]].use != 0) {
                            sup[inv[i]].use_med(me);
                            use = sup[inv[i]].use_axe(use);
                            finish = sup[inv[i]].use_kid(finish);
                            sup[inv[i]].use_food(me);

                            if(dif!=2) shield=sup[inv[i]].use_shield(shield);

                            potin=sup[inv[i]].use_wrt1(potin,story);
                            sup[inv[i]].use_wrt2(story);
                            sup[inv[i]].use_wr3(story);
                            potin=sup[inv[i]].use_wr4(potin,story);

                            System.out.println("Использовал");
                            System.out.println(finish);

                            if(sup[inv[i]].type==5||sup[inv[i]].type==6||sup[inv[i]].type==7||sup[inv[i]].type==8) {
                                story=sup[inv[i]].use_st(story);
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


    character me = new character("src/pic/FiremanW.png", xscr/2, yscr/2, 150, 150);

    BufferedImage  bi;
    BufferedImage f;
    BufferedImage hp;
    BufferedImage hu;
    BufferedImage axe;

    BufferedImage bush;
    BufferedImage mushroom;
    BufferedImage grass;

    float k;
    public level_game(File fil, int dif_) throws Exception
    {
//        for (int i=0; i<kolwolf; i++) {
//
//            snake[i]=new obj_pick("C:\\Users\\Tikho\\IdeaProjects\\untitled6 — копия 2 — копия (2)\\src\\1771348523058.png",snakes[i].x,snakes[i].y,50,50);
//            snake[i].pickable=0;
//            dead[i]=false;
//        }
dif=dif_;

        try{  bush = ImageIO.read(new File("src/pic/Bush.png"));    } catch (IOException e) {    }
        try{  mushroom = ImageIO.read(new File("src/pic/Wood.png"));    } catch (IOException e) {    }
        try{  grass = ImageIO.read(new File("src/pic/Yama.png"));  } catch (IOException e) {    }


        try{  hu = ImageIO.read(new File("src/pic/Chicken.png"));    } catch (IOException e) {    }
        try{  f = ImageIO.read(new File("src/pic/noname.png"));    } catch (IOException e) {    }
        try{  hp = ImageIO.read(new File("src/pic/Hp.png"));    } catch (IOException e) {    }
        try{  axe = ImageIO.read(new File("src/pic/FireAxe.png"));  } catch (IOException e) {    }

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

        FileInputStream fis = new FileInputStream(fil); // } catch (IOException e) {    }

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        System.out.println("DataSheet.xlsx file opened successfully");

        XSSFSheet sheet = wb.getSheet("Лист1");

        for (int i=0; i<20; i++) {
            int type = random.nextInt(5);
            if (type==0) {
                type=1;
            }
            if (type==1) {
                sup[i] = new obj_pick("src/pic/Bandage.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                System.out.println("Сщздал");
            }
            else if (type==2) {
                sup[i] = new obj_pick("src/pic/FireAxe.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                sup [i].type=type;
            }
            else if (type==3) {
                sup[i] = new obj_pick("src/pic/Chicken.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                sup [i].type=type;
            }
            else {
                sup[i] = new obj_pick("src/pic/Shield.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                sup [i].type=type;
            }

            if (dif==2) {
                while (type==2) type=random.nextInt(5);
                if (type==0) {
                    type=1;
                }
                if (type==1) {
                    sup[i] = new obj_pick("src/pic/Bandage.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                    System.out.println("Сщздал");
                }
                else if (type==2) {
                    sup[i] = new obj_pick("src/pic/FireAxe.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                    sup [i].type=type;
                }
                else if (type==3) {
                    sup[i] = new obj_pick("src/pic/Chicken.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                    sup [i].type=type;
                }
                else {
                    sup[i] = new obj_pick("src/pic/Shield.png", random.nextInt(xmax), random.nextInt(ymax), 100, 100);
                    sup [i].type=type;
                }
            }

        }

        sup[20]=new obj_pick("src/pic/Child1.png", random.nextInt(xmax),random.nextInt(ymax), 70, 189 );
        sup[21]=new obj_pick("src/pic/Child2.png", random.nextInt(xmax),random.nextInt(ymax), 70, 238 );
        sup[22]=new obj_pick("src/pic/Child3.png", random.nextInt(xmax),random.nextInt(ymax), 70, 182 );
        sup[23]=new obj_pick("src/pic/Child4.png", random.nextInt(xmax),random.nextInt(ymax), 70, 217 );

        sup[20].type=10;
        sup[21].type=10;
        sup[22].type=10;
        sup[23].type=10;

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
                            bushes[count] = new obj("src/pic/Yama.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                            break;
                        case (2):
                            bushes[count] = new obj("src/pic/Bush.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                            break;
                        case (3):
                            bushes[count] = new obj("src/pic/Wood.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
                            break;
                        default:
                            bushes[count] = new obj("src/pic/Bush.png", basa[count][0], basa[count][1], basa[count][2], basa[count][3]);
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
            snakes[i] = new enemy("src/pic/Snake.png",basa[i+kolbush][0], basa[i+kolbush][1], basa[i+kolbush][2], basa[i+kolbush][3] );
            sup[24+i]=new obj_pick("src/pic/Chicken.png",snakes[i].x,snakes[i].y,50,50);
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
        test.drawImage(me.image,me.x,me.y,me.w1, me.w2,this);

        for(int i=0; i<kolbush; i++) { //с помощью цикла отрисовываю все кусты
            if(basa[i][5]<=0) continue;
            else test.drawImage(bushes[i].image, bushes[i].x - xp, bushes[i].y - yp, bushes[i].w1, bushes[i].w2, this);
        }



if (dif!=4) {
    for (int i = 0; i < kolwolf; i++) { //с помощью цикла отрисовываю все кусты


        AffineTransform at1 = new AffineTransform(); //Создаю преобразование
        double angle1 = snakes[i].rotate1(snakes[i], me, xp, yp); // Поворот на 45 градусов
        int x1 = snakes[i].x + snakes[i].w1 / 2 - xp;// Центр по X
        int y1 = snakes[i].y + snakes[i].w2 / 2 - yp; // Центр по Y
        at1.rotate(angle1, x1, y1); //Поворачиваю вокруг x,y
        // "Добавляю" (применяю) поворот вокруг x,y

        if (basa[i + kolbush][5] <= 0) {
            continue;
        } else {
            test.setTransform(at1);
            test.drawImage(snakes[i].image, snakes[i].x - xp, snakes[i].y - yp, snakes[i].w1, snakes[i].w2, this);
        }

        test.setTransform(new AffineTransform());

    }
}

if (dif!=3&&dif!=4){
        for (int i=0; i< sup.length; i++) {
            if(dif==1){
            if (sup [i].pickable == 1) {
                test.drawImage(sup[i].image, sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2, this);
            }}
        }}

        if (dif!=3&&dif!=4){
            for (int i=0; i< sup.length; i++) {
                if(dif==1){
                    if (sup [i].pickable == 1) {
                        test.drawImage(sup[i].image, sup[i].x - xp, sup[i].y - yp, sup[i].w1, sup[i].w2, this);
                    }}
            }}


        if (me.health>=1){
            test.drawImage(hp,(int)(xscr*0.023),(int)(yscr*0.829),100,100,this );
        }
        if (me.health>=2){
            test.drawImage(hp,(int)(xscr*0.023)+110,(int)(yscr*0.829),100,100,this );
        }
        if (me.health>=3){
            test.drawImage(hp,(int)(xscr*0.023)+220,(int)(yscr*0.829),100,100,this );
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

        if(dif!=3&&dif!=4) test.drawImage(axe,1319,117,100,100,this );

        if (dif==1) {
            test.drawImage(bush, 1216, 117, 100, 100, this);
            test.drawImage(mushroom, 1113, 117, 100, 100, this);
            test.drawImage(grass, 1010, 117, 100, 100, this);
        }


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

        g.drawImage(bi,0,0,this); //отрисовываю холст


    }


}
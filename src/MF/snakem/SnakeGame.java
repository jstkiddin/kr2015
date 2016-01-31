package MF.snakem;

import MF.snake.objects.Food;
import MF.snake.objects.Snake;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener{

    public static final long serialVersinUID = -4201295877166777528L;
    public static final int scale = 32;
    public static final int wid = 20;
    public static final int hei = 20;
    public static final int sp = 10;

    public static Synthesizer synth = null;

    Food a = new Food((int)(Math.random()*wid), (int) (Math.random()*hei));
    static Snake s = new Snake(10,10,9,10); // обьявление змейки(тела)
    Timer time = new Timer(1000/sp, this);

    public SnakeGame(){
        time.start();
        addKeyListener(new Keyboard()); //pressing
        setFocusable(true);
    }
    public void paint(Graphics g) {  //покраска фона

        g.setColor(color(5,54,100)); //параметры RGB
        g.fillRect(0,0,wid*scale,hei*scale );
        g.setColor(color(246,205,47));

        for(int xx = 0; xx<=wid*scale;xx+=scale ){
           g.drawLine(xx, 0,xx,hei*scale);
        }

        for (int yy = 0; yy<=hei*scale; yy+=scale){
            g.drawLine(0,yy,wid*scale,yy);
        }

        for(int d =0; d<s.length; d++){ //отображение змейки
            g.setColor(color(5,125,181));
            g.fillRect(s.snakeX[d]*scale+1,s.snakeY[d]*scale+1,scale-1,scale-1);
        }

        g.setColor(color(246,205,47)); //рисуем еду
        g.fillRect(a.posX*scale+1,a.posY*scale+1, scale-1, scale-1);

    }

    public Color color(int red, int green, int blue){
        return new Color(red,green,blue); //генерация
    }

    public static void main (String [] args) throws MidiUnavailableException {
        JFrame fr = new JFrame(); //создаем форму
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(wid*scale+7,hei*scale+30); //задаем размер окошка
        fr.setLocationRelativeTo(null);
        fr.add(new SnakeGame());
        fr.setVisible(true);
        synth = MidiSystem.getSynthesizer();
        synth.open();
    }

    public void actionPerformed (ActionEvent arg0){ //движение змейки
        try {
            s.move();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if((s.snakeX[0]==a.posX) & (s.snakeY[0]==a.posY)) { //random possition of food
            a.setRandomPosition();
            s.length++;
            MidiChannel[] channels  = synth.getChannels(); //открытие канналов для звука
            channels[2].noteOn(67,100); //нота  и громкость
            channels[2].noteOff(67);
            for(int k =1; k < s.length; k++) { //places food in random position
                if ((s.snakeX[k] == a.posX) & (s.snakeY[k] == a.posY)) {
                    a.setRandomPosition();
                }
            }
        }
        repaint(); //updates the panel
    }

    public class Keyboard extends KeyAdapter {
        public void keyPressed(KeyEvent kEvt){
            int key = kEvt.getKeyCode(); // получаем код соотв. клавиши
            //используем arrows
            if ((key == KeyEvent.VK_RIGHT)& (s.direction != 2)) {s.direction = 0;} //right
            if ((key == KeyEvent.VK_DOWN) & (s.direction != 3)) s.direction = 1; //down
            if ((key == KeyEvent.VK_LEFT)& (s.direction != 0)) s.direction = 2;//left
            if ((key == KeyEvent.VK_UP)& (s.direction != 1)) s.direction = 3;//up

       /*     if ((key == KeyEvent.VK_SPACE) & (count==0)) {
                count = 1;
                suspend();
            }

            if ((key == KeyEvent.VK_SPACE) & (count==1)) {
                count = 0;
                resume();
            }*/

        }
    }

    /*static void resume(){
        s.myresume();
        //RESET GAME
    }

    static void suspend(){
        s.mysuspend();
       //Draw scene PAUSE
    }*/

}

package MF.snake.objects;

import MF.snakem.SnakeGame;

public class Snake {

    SnakeGame main;
    private boolean flag;  //счетччки "паузы"
    public int direction;
    public int length = 3; //начальная длинна змейки - 2 клетки

    public int snakeX[] = new int [main.wid*main.hei]; //макс. число эл.
    public int snakeY[] = new int [main.wid*main.hei];

    public Snake (int x0, int x1,int y0,int y1){ //принимает стартовые позиции змейки
        flag = false;
        snakeX[0] = x0;
        snakeY[0] = y0;
        snakeX[1] = x1;
        snakeY[1] = y1;
    }

    public void move() throws InterruptedException { //


            for (int d = length; d > 0; d--) {
            snakeX[d] = snakeX[d-1];
            snakeY[d] = snakeY[d-1];
        }
                                              //^
        //работаем только с 0 эл., тк благодаря | массив сдвигается на ед.

        if (direction == 0 ) snakeX[0]++; //right
        if (direction == 1 ) snakeY[0]++; //down
        if (direction == 2 ) snakeX[0]--; //left
        if (direction == 3 ) snakeY[0]--; //up


        for (int  d = length-1; d >0; d--) {
            if((snakeX[0] == snakeX[d]) &(snakeY[0]==snakeY[d])) length = d;
        }

        if(snakeX[0]> main.wid) snakeX[0] = 0; //переход от одного конца поля к другому по х
        if(snakeX[0] < 0) snakeX[0] = main.wid-1;

        if(snakeY[0]> main.hei-1) snakeY[0] = 0; //переход от одного конца поля к другому по У
        if(snakeY[0] < 0) snakeY[0] = main.hei-1;

        if(length <2 ) length =2;

        synchronized(this){
            while(flag) {
                wait(); //
            }
        }
    }

    public void mysuspend(){
        flag=true;
    } //пауза

    synchronized public void myresume(){ //продолжить вычисления
        flag=false;
        notify(); //
    }
}

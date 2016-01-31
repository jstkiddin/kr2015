package MF.snake.objects;

import MF.snakem.SnakeGame;

public class Food {

    SnakeGame main;

     public int posX;
     public int posY;

    public Food (int startX, int startY){
        posX = startX;
        posY = startY;
    }

    @SuppressWarnings("static accesss")
    public void setRandomPosition(){
        posX = (int)(Math.random()*main.wid);
        posY = (int)(Math.random()*main.wid);
    }
}

/*package MF.snakem;

import MF.snakem.*;
import javafx.scene.Scene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;


    public class GameOver extends Scene {

        GameOver(IGame iGame) {
            super(iGame);
        }

        @Override
        public void update(long passedTime) {
            for (KeyEvent event : iGame.getAction().getPressed()) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    iGame.setScene(new MainScene(iGame));
                }
            }
        }

        @Override
        public void draw(Graphics2D g) {
            // TODO Auto-generated method stub
            g.setColor(Color.black);
            g.fillRect(0, 0, iGame.getScreenSize().width, iGame.getScreenSize().height);

            g.setFont(new Font("Default", Font.BOLD, 16));
            g.setColor(Color.white);

            String message = "Press <Enter> to start new game";
            Rectangle2D messageBounds = g.getFontMetrics().getStringBounds(message, g);
            int messageWidth = (int) (messageBounds.getWidth());
            int messageHeight = (int) (messageBounds.getHeight());

            g.drawString(message,
                    iGame.getScreenSize().width / 2 - messageWidth / 2,
                    iGame.getScreenSize().height / 2 - messageHeight / 2
            );

        }
    }*/
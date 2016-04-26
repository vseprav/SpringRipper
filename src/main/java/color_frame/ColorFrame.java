package color_frame;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by andrii.peliak on 4/22/2016.
 */
public abstract class ColorFrame extends JFrame {

    public ColorFrame() throws HeadlessException {
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showOnRandomPlace(){
        Random random = new Random();
        setLocation(random.nextInt(1200),random.nextInt(600));
        getContentPane().setBackground(getColor());
        repaint();
    }

     protected abstract Color getColor();
}

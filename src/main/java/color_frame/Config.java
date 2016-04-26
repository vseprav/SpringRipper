package color_frame;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by andrii.peliak on 4/22/2016.
 */
@Configuration
@ComponentScan(basePackages = "color_frame")
public class Config {

    @Bean
    @Scope("periodical")
    public Color color(){
        Random random = new Random();
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
    @Bean
    public ColorFrame frame(){
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true){
            context.getBean(ColorFrame.class).showOnRandomPlace();
            Thread.sleep(200);
        }

    }

}

package quoters;

/**
 * Created by andrii.peliak on 4/18/2016.
 */
public class T1000 extends TerminalQuoter implements Quotes {
    @Override
    @PostProxy
    public void sayQuoter() {
        System.out.println("I am Liquid");
    }
}

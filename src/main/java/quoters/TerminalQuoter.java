package quoters;

/**
 * Created by andrii.peliak on 4/14/2016.
 */
@DeprecatedClass(newImpl = T1000.class)
@Profiling
public class TerminalQuoter implements Quotes {
    @InjectRandomInt(min=2, max=7)
    private  int repeat;

    private String message;

    public TerminalQuoter() {
    }

    public void  init(){
        System.out.println(repeat);
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @PostProxy
    public void sayQuoter() {
        for(int i = 0; i < repeat; i++){
            System.out.println("Say " + message);
        }
    }
}

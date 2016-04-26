package color_frame;

import com.sun.jmx.snmp.Timestamp;
import javafx.util.Pair;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by andrii.peliak on 4/22/2016.
 */
public class PeriodicalScope implements Scope {

    private Map<String, Pair<Timestamp, Object>> map = new HashMap();

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {

        Timestamp timestamp = new Timestamp();
        if(map.containsKey(name)){
            Pair<Timestamp, Object> pair = map.get(name);
            Timestamp objectTimestamp = pair.getKey();
            long diff = timestamp.getDateTime() - objectTimestamp.getDateTime();
            if((diff) > 5000){
                map.put(name, new Pair<>(new Timestamp(), objectFactory.getObject()));
            }
        }else{
            map.put(name, new Pair<>(new Timestamp(), objectFactory.getObject()));
        }

        return map.get(name).getValue();
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}

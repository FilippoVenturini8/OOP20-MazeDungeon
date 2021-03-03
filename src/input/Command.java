package input;

import java.awt.event.KeyEvent;
import java.util.Set;


public interface Command {

    void setKey(KeyEvent key, boolean b);
    
    boolean checkStopVertical();
    boolean checkStopHorizontal();
   
    Set<Integer> getPermittedKeys();
    void execute();
    void setMenuClosed();
    
}

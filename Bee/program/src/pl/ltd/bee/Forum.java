/*
 * Forum.java
 *
 * Created on 9 marzec 2005, 17:27
 */

package pl.ltd.bee;

import java.util.*;

/**
 *
 * @author pawelb
 */
public class Forum {
    
    private String Nazwa;
    private long ID;
    
    /** Creates a new instance of Forum */
    public Forum(String Forum_ID, String Nazwa) {
        this.ID=Long.decode(Forum_ID).longValue();
        this.Nazwa=Nazwa;
    }
    
    public long getID() {
        return ID;
    }
}

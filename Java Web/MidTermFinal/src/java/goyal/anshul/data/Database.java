/*
ANSHUL
MID TERM JAVA EXAM
17TH JULY

*/
package goyal.anshul.data;

/**
 *
 * @author Admin
 */

import static goyal.anshul.data.UpdatedRecord.Lover;


public class Database {
   
    String love;
    String lover;

    public Database(String love,String lover) {
       this.love=love;
       this.lover=lover;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

      public String getLover() {
        return Lover;
    }

 
    public void setLover(String lover) {
        this.lover = lover;
    }
    
}

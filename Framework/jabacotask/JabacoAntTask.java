import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import java.util.Vector;
import java.util.Iterator;

public class JabacoAntTask extends Task {

    /** The message to print. As attribute. */
    String message;
    public void setMessage(String msg) {
        message = msg;
    }

    /** Should the build fail? Defaults to false. As attribute. */
    boolean fail = false;
    public void setFail(boolean b) {
        fail = b;
    }

    /** Support for nested text. */
    public void addText(String text) {
        message = text;
    }

    /** Do the work. */
    public void execute() {
        // handle attribute 'fail'
        if (fail) throw new BuildException("Fail requested.");

        // handle attribute 'message' and nested text
        if (message!=null) log(message);

        // handle nested elements
        for (Iterator it=messages.iterator(); it.hasNext(); ) {
            Message msg = (Message)it.next();
            log(msg.getMsg());
        }
    }


    /** Store nested 'message's. */
    Vector messages = new Vector();

    /** Factory method for creating nested 'message's. */
    public Message createMessage() {
        Message msg = new Message();
        messages.add(msg);
        return msg;
    }

}

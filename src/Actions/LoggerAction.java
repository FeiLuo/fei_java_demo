package Actions;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LoggerAction extends ActionBase {

    public LoggerAction() {
        super("LoggerAction");
    }

    @Override
    public void action() {
        try {
            LogTest();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void LogTest() throws SecurityException, IOException {
        Logger log = Logger.getLogger("testpoint.Actions.log");
        log.setLevel(Level.ALL);
        FileHandler filehandler = new FileHandler("bin/LoggerAction.log");
        filehandler.setLevel(Level.ALL);
        filehandler.setFormatter(new LogFroamtter());  
        log.addHandler(filehandler);  
        log.info("This is test java util log.");
    }
    
}

class LogFroamtter extends Formatter {

    @Override
    public String format(LogRecord record) {
        Date date = new Date();
        String sDate = date.toString();
        return "[" + sDate + "]" + record.getLevel() + "]" 
                + record.getClass() + record.getMessage() + "\n"; 
    }
    
}
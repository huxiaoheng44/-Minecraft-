package peace.minecraftserver.utils;

import java.util.logging.Logger;

public class MLogger {
    private static final Logger log = Logger.getLogger("MLogger");
    public static boolean islogger = true;

    public static void info(String msg){
        if(islogger) {
            log.info(msg);
        }
    }
}

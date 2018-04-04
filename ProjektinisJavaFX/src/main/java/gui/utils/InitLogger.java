package gui.utils;

import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

public class InitLogger {
    private  static final Logger LOGGER = Logger.getLogger("myLogger");

    public static void initLogger() throws IOException {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        FileHandler fileHandler = new FileHandler("logs.log",true);
        consoleHandler.setFormatter(new CustomFormat());
        fileHandler.setFormatter(new CustomFormat());
        LOGGER.setUseParentHandlers(false);
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        LOGGER.info("hi");
    }
    static class CustomFormat extends Formatter{

        @Override
        public String format(LogRecord record) {
            return new Date() + " "+ record.getMessage() +"\n";
        }
    }
}

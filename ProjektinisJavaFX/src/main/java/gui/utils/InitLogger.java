package gui.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.*;

import static gui.utils.FormatedDate.EXPRESS_DATE_FORMAT;
import static gui.utils.FormatedDate.SIMPLE_DATE_FORMAT;

public class InitLogger {
    private static final Logger LOGGER = Logger.getLogger("myLogger");

    public static void initLogger(String file, String msg) throws IOException {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        String fileDir = "C:\\Users\\ajankauskas\\Desktop\\Android\\ProjektinisJavaFX\\logs\\" + SIMPLE_DATE_FORMAT.format(new Date());
        new File(fileDir).mkdir();
        String fileLocation = fileDir + "/system.log";
        System.out.println(fileLocation);
        FileHandler fileHandler = new FileHandler(fileLocation, true);
        consoleHandler.setFormatter(new CustomFormat());
        fileHandler.setFormatter(new CustomFormat());
        LOGGER.setUseParentHandlers(false);
//        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        LOGGER.info(file + " ; " + msg);
    }

    static class CustomFormat extends Formatter {

        @Override
        public String format(LogRecord record) {
            return EXPRESS_DATE_FORMAT.format(new Date()) + " - " + record.getMessage() + "\n";
        }
    }
}

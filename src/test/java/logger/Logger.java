package logger;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;

public class Logger {
    private static final ThreadLocal<org.apache.logging.log4j.Logger> LOGGER = ThreadLocal.withInitial(() -> LogManager.getLogger(Logger.class));

    private Logger() {}

    public static void info(String message){
        Allure.step(message);
        LOGGER.get().info(message);
    }

    public static void debug(String message){
        LOGGER.get().debug(message);
    }

    public static void warn(String message){
        Allure.step(message);
        LOGGER.get().warn(message);
    }

    public static void warn(String message, Throwable error){
        Allure.step(message);
        LOGGER.get().warn(message, error);
    }

    public static void error(String message) {
        String s = "  *****  ";
        LOGGER.get().error(s + message + s);
        Allure.step(message);
    }

    public static void error(String message, Throwable error){
        String s = "  *****  ";
        LOGGER.get().error(message, error);
        Allure.step(message);
    }

    public static void trace(String message){
        System.out.println();
        String s = "  ==========  ";
        Allure.step(message);
        LOGGER.get().trace(s + message + s);
    }

}

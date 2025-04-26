package src.java.org.projet.interfaces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger implements AutoCloseable {
    private final String className;
    private final String logFileName;
    private final SimpleDateFormat dateFormat;
    private PrintWriter writer;
    private final String logFolder = "log/";
    private boolean consoleOutput;

    /**
     * Constructeur avec une classe
     * @param clazz la classe pour laquelle on veut logger
     */
    public MyLogger(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    /**
     * Constructeur avec le nom de la classe
     * @param className le nom de la classe pour laquelle on veut logger
     */
    public MyLogger(String className) {
        this.className = className;
        this.logFileName = logFolder + className + ".log";
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.consoleOutput = true;

        // Initialiser le fichier de log
        try {
            File logFile = new File(logFileName);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            writer = new PrintWriter(new FileWriter(logFile, false), true);
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier de log: " + e.getMessage());
        }
    }

    /**
     * Active ou désactive l'affichage des logs dans la console
     * @param enabled true pour activer, false pour désactiver
     */
    public void setConsoleOutput(boolean enabled) {
        this.consoleOutput = enabled;
    }

    /**
     * Écrit un message de niveau INFO dans le log
     * @param message le message à logger
     */
    public void info(String message) {
        log("INFO", message);
    }

    public void severe(String message) {
        log("SEVERE", message);
    }

    /**
     * Écrit un message de niveau WARNING dans le log
     * @param message le message à logger
     */
    public void warning(String message) {
        log("WARNING", message);
    }

    /**
     * Écrit un message de niveau ERROR dans le log
     * @param message le message à logger
     */
    public void error(String message) {
        log("ERROR", message);
    }

    /**
     * Écrit un message de niveau DEBUG dans le log
     * @param message le message à logger
     */
    public void debug(String message) {
        log("DEBUG", message);
    }

    /**
     * Méthode interne pour formater et écrire le message de log
     * @param level le niveau de log (INFO, WARNING, etc.)
     * @param message le message à logger
     */

    private void log(String level, String message) {
        String timestamp = dateFormat.format(new Date());

        // Récupérer le nom de la méthode appelante
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName = stackTrace[3].getMethodName(); // [0]=getStackTrace, [1]=log, [2]=info/debug/etc., [3]=méthode appelante

        String logMessage = String.format("[%s] %s - %s.%s - %s",
                timestamp, level, className, methodName, message);

        // Écrire dans le fichier
        if (writer != null) {
            writer.println(logMessage);
        }

        // Écrire dans la console si activé
        if (consoleOutput) {
            System.out.println(logMessage);
        }
    }
    private void log2(String level, String message) {
        String timestamp = dateFormat.format(new Date());
        String logMessage = String.format("[%s] %s - %s - %s",
                timestamp, level, className, message);

        // Écrire dans le fichier
        if (writer != null) {
            writer.println(logMessage);
        }

        // Écrire dans la console si activé
        if (consoleOutput) {
            System.out.println(logMessage);
        }
    }

    /**
     * Ferme le fichier de log
     */
    @Override
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}

class TestApplication {
    private static final MyLogger logger = new MyLogger(TestApplication.class);

    public static void main(String[] args) {
        // Optionnel: afficher les logs aussi dans la console
        logger.setConsoleOutput(true);

        logger.info("Démarrage de l'application");
        logger.debug("Ceci est un message de debug");

        try {
            // Code métier...
            logger.warning("Attention: opération critique");
        } catch (Exception e) {
            logger.error("Erreur dans l'application: " + e.getMessage());
        }

        logger.info("Fin de l'application");
        logger.close();
    }
}
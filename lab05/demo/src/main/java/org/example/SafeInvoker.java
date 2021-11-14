package org.example;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class SafeInvoker {

    Logger logger = Logger.getLogger(SafeInvoker.class.getName());
    FileHandler fileHandler;

    private static final Integer time = 3; //time until next repeat
    private static final Integer maxRepeat = 3; //maximum repeats


    private int retryCounter;

    public SafeInvoker() throws IOException {
        fileHandler = new FileHandler("app.log", true);
        logger.addHandler(fileHandler);
        retryCounter = 0;
    }

    public boolean invoke(Runnable task) throws InterruptedException {
        try {
            task.run();
            return true;
        } catch (RetryException e) {
            TimeUnit.SECONDS.sleep(time);
            retry(task);
        }
        return false;
    }

    void retry(Runnable task) throws InterruptedException {
        while (retryCounter <= maxRepeat) { //Maximum amount of retries for Operation
            try {
                task.run();
                return;
            } catch (Exception e) {
                retryCounter++;
                if (retryCounter <= maxRepeat) {
                    logger.info("Retry nr: " + retryCounter + " will attempt in " + time + " seconds");
                    TimeUnit.SECONDS.sleep(time);
                } else {
                    logger.info("Maximum retries reached");
                }
            }
        }
    }


}

import java.util.HashMap;
import java.util.Map;


class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();

        System.out.println(logger.shouldPrintMessage(1, "foo"));  // true
        System.out.println(logger.shouldPrintMessage(2, "bar"));  // true
        System.out.println(logger.shouldPrintMessage(3, "foo"));  // false
        System.out.println(logger.shouldPrintMessage(8, "bar"));  // false
        System.out.println(logger.shouldPrintMessage(10, "foo")); // false
        System.out.println(logger.shouldPrintMessage(11, "foo")); // true

        System.out.println("Logger size: " + logger.loggerSize()); // 2
        System.out.println("Clean at timestamp 11: " + logger.clean(11)); // false
        System.out.println("Clean at timestamp 12: " + logger.clean(12)); // true
    }
}


class Logger {
    private final Map<String, Integer> messageDict;

    public Logger() {
        messageDict = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        clean(timestamp);
        if (!messageDict.containsKey(message) || timestamp >= messageDict.get(message)) {
            int maxSize = 100;
            if (messageDict.size() >= maxSize) {
                clean(timestamp);
            }
            messageDict.put(message, timestamp + 10);
            return true;
        }
        return false;
    }

    public boolean clean(int timestamp) {
        boolean removed = false;
        if (timestamp >= 0) {
            int initialSize = messageDict.size();
            messageDict.entrySet().removeIf(entry -> entry.getValue() <= timestamp);
            if (messageDict.size() < initialSize) {
                removed = true;
            }
        }
        return removed;
    }

    public int loggerSize() {
        return messageDict.size();
    }


}


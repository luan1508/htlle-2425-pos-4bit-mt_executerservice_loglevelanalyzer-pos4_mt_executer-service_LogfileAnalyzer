import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.of;

public class LogAnalyzerSequential implements LogAnalyzer {


    @Override
    public void analyze(File[] files) throws FileNotFoundException {
        long start = System.currentTimeMillis();

        for(File file: files) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            for(String logLevel: List.of("TRACE", "DEBUG", "INFO", "WARN", "ERROR")) {
                map.put(logLevel, 0);
            }
            System.out.println(file.getName()+": ");
            LogHelper.countLogs(file, map);
        }

        long end = System.currentTimeMillis();
        System.out.println("Analyse abgeschlossen in " + (end - start) + "ms.");

    }
}

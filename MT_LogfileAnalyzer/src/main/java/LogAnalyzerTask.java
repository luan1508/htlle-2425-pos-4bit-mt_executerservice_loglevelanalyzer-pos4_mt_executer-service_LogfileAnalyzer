import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LogAnalyzerTask implements Callable<Map<String, Integer>> {

    private final File logfile;

    public LogAnalyzerTask(File logfile) {
        this.logfile = logfile;
    }

    @Override
    public Map<String, Integer> call() throws Exception {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String logLevel: List.of("TRACE", "DEBUG", "INFO", "WARN", "ERROR")) {
            map.put(logLevel, 0);
        }

        LogHelper.countLogs(logfile, map);

        return map;
    }
}
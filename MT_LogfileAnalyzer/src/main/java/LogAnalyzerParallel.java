import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogAnalyzerParallel implements LogAnalyzer {

    @Override
    public void analyze(File[] files) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<Map<String, Integer>>> futures = new ArrayList<>();
        for (File file : files) {
            futures.add(executor.submit(new LogAnalyzerTask(file)));
        }

        Map<String, Integer> finalResult = new HashMap<>();

        for (Future<Map<String, Integer>> future : futures) {
            try {
                Map<String, Integer> result = future.get();

                for (Map.Entry<String, Integer> entry : result.entrySet()) {
                    finalResult.merge(entry.getKey(), entry.getValue(), Integer::sum);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("Analyse abgeschlossen in " + (end - start) + "ms.");
        System.out.println("Ergebnis: " + finalResult);
    }
}

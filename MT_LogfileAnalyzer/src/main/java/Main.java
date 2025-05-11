import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.startsWith("app-") && name.endsWith(".log"));

        System.out.println();
        System.out.println("Sequential Analyzer");
        System.out.println("-----------------------------");
        new LogAnalyzerSequential().analyze(files);
        System.out.println("-----------------------------");

        System.out.println();
        System.out.println("Parallel Analyzer");
        System.out.println("-----------------------------");
        System.out.println();
        new LogAnalyzerParallel().analyze(files);
        System.out.println("-----------------------------");

    }
}

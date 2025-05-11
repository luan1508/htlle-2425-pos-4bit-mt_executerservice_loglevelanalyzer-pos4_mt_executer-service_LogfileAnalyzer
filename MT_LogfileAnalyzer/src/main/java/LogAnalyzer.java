import java.io.File;
import java.io.FileNotFoundException;

public interface LogAnalyzer {
    void analyze(File[] files) throws FileNotFoundException;
}

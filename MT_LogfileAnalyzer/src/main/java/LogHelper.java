import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class LogHelper {

    public static void countLogs(File file, Map<String, Integer> map) throws FileNotFoundException {
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            try(Scanner scanner = new Scanner(file)) {
                while(scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if(line.contains(entry.getKey())) {
                        map.put(entry.getKey(), entry.getValue() + 1);
                    }
                }
                System.out.println(entry.getKey() + ": " + entry.getValue());
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

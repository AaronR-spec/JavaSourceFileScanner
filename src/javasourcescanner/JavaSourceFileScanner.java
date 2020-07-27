
package javasourcescanner;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Java scanner that returns the identifiers of the file
 * @author Aaron Reihill/D00222467
 */
public class JavaSourceFileScanner
{

    public static void main(String[] args)
    {
        JavaSourceFileScanner.start("JavaSource.txt");
    }
    /**
     * takes in file path and displays identifier and line numbers
     * @param file
     */
    public static void start(String file)
    {
        int countLine = 0;
        // used set as it doesnt allow duplicates so no need to check later on
        // debated using a set for string as i have a check for duplicates but for a key string is better
        Map<String, Set<Integer>> map = new HashMap<>();

        try (Scanner sc = new Scanner(new File(file)))
        {
            Scanner line;
            sc.useDelimiter("[^A-Za-z0-9_]+");
            while (sc.hasNext())
            {
                line = new Scanner(sc.nextLine());
                while (line.hasNext())
                {
                    String symbol = line.next();
                    if (!map.containsKey(symbol))
                    {
                        map.put(symbol, new HashSet<>());
                        map.get(symbol).add(countLine);
                    }
                    else
                    {
                        map.get(symbol).add(countLine);
                    }
                }
                countLine++;
            }
        }
        catch (IOException e)
        {
            System.err.println("File Not Found. " + e.getLocalizedMessage());
        }
        print(map);
    }

    private static void print(Map<String, Set<Integer>> map)
    {
        System.out.println("\nIdentifier [count] line Numbers\n");
        Set<Integer> lines;
        for (String s : map.keySet())
        {
            System.out.print(" " + s);
            lines = map.get(s);
            System.out.print(" [" + lines.size() + "] ");
            printLines(lines);
        }

    }

    private static void printLines(Set<Integer> m)
    {
        for (int num : m)
        {
            System.out.print(num + ",");
        }
        System.out.println("");

    }
}

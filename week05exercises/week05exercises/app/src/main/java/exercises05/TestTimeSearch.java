package exercises05;
// jst@itu.dk * 2022-09-06

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntToDoubleFunction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class TestTimeSearch {
  public static void main(String[] args) { new TestTimeSearch(); }

  public TestTimeSearch() {
    final String filename = "app/src/main/resources/long-text-file.txt";
    final String target= "ipsum";

    final LongCounter lc= new LongCounter();
    String[] lineArray= readWords(filename);  // return a list of sentences, some of them is empty

    System.out.println("Array Size: "+ lineArray.length);
    System.out.println("# Occurences of "+target+ " :"+search(target, lineArray, 0, lineArray.length, lc));

    // Mark 7
    Mark7("Test Time Search", i -> search(target, lineArray, 0, lineArray.length, lc));

    // N threads
    countParallelN(target, lineArray, 10, lc);
  }

  static long search(String x, String[] lineArray, int from, int to, LongCounter lc){
    // return the number of x(terget) in a list of sentences.
    //Search each line of file
    for (int i=from; i<to; i++ ) lc.add(linearSearch(x, lineArray[i]));
    //System.out.println("Found: "+lc.get());
    return lc.get();
  }

  static long linearSearch(String x, String line) {
    //Search for occurences of c in line
    String[] arr= line.split(" ");
    long count= 0;
    for (int i=0; i<arr.length; i++ ) if ( (arr[i].equals(x)) ) count++;                   
    return count;
  }

  public static String[] readWords(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        return reader.lines().toArray(String[]::new);   //will be explained in Week07;
    } catch (IOException exn) { return null;}
  }

  public static double Mark7(String msg, IntToDoubleFunction f) {
    int n = 10, count = 1, totalCount = 0;
    double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
    do { 
      count *= 2;
      st = sst = 0.0;
      for (int j=0; j<n; j++) {
        Timer t = new Timer();
        for (int i=0; i<count; i++) 
          dummy += f.applyAsDouble(i);
        runningTime = t.check();
        double time = runningTime * 1e9 / count; // nanoseconds
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f ns %10.2f %10d%n", msg, mean, sdev, count);
    return dummy / totalCount;
  }

  public static void SystemInfo() {
    System.out.printf("# OS:   %s; %s; %s%n", 
                      System.getProperty("os.name"), 
                      System.getProperty("os.version"), 
                      System.getProperty("os.arch"));
    System.out.printf("# JVM:  %s; %s%n", 
                      System.getProperty("java.vendor"), 
                      System.getProperty("java.version"));
    // The processor identifier works only on MS Windows:
    System.out.printf("# CPU:  %s; %d \"cores\"%n", 
                      System.getenv("PROCESSOR_IDENTIFIER"),
                      Runtime.getRuntime().availableProcessors());
    java.util.Date now = new java.util.Date();
    System.out.printf("# Date: %s%n", 
    new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(now));
  }


  private static long countParallelN(String target, String[] lineArray, int N, LongCounter lc) {
        // uses N threads to search lineArray
        //devide the lineArray into N parts, for each part use a thread to search it 
        int slot = lineArray.length / N;  //the max length of each part
        List<Thread> list = new ArrayList<Thread>();  
        int startPos = 0;
        for(int i =0; i< N; i++){
            Thread t = new Thread(() -> {
                Lock lock = new ReentrantLock();
                System.out.println(startPos);
            });
            t.start();

            list.add(t);
        }

        try  
        {  
            for(Thread thread : list)  
            {  
                thread.join();  
            }  
        }  
        catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  

        return lc.get();
}
}
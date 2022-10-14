package exercises05;
import java.util.function.IntToDoubleFunction;

public class TestVolatile {
    private volatile int vCtr;
    private int ctr;

    public static void main(String[] args) {
        new TestVolatile();
    }

    public TestVolatile() {
        SystemInfo();
        Mark7("test normal int", i -> {inc(); return this.ctr;});
        Mark7("test volatile int", i -> {vInc(); return this.vCtr;});
    }

    public void vInc () {
        vCtr++;
    }

    public void inc () {
        ctr++;
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
            double time = runningTime * 1e9 / count;
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
}
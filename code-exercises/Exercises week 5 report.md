## Exercises week 5 report

### Exercise 5.1

1. Use Benchmark.java to run the Mark1 through Mark6 measurements.

   - Mark1

     ![image-20221013223506656](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013223506656.png)

   - Mark2

     ![image-20221013223557509](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013223557509.png)

   - Mark3

     ![Mark3](/var/folders/8j/yjhn3lrx7y79zf_5cr9wb4380000gn/T/com.apple.Preview/com.apple.Preview.PasteboardItems/Mark3.png)

   - Mark4

     ![Mark4](/var/folders/8j/yjhn3lrx7y79zf_5cr9wb4380000gn/T/com.apple.Preview/com.apple.Preview.PasteboardItems/Mark4.png)

   - Mark5

     ![image-20221013223632052](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013223632052.png)

   - Mark6

     ![image-20221013223642006](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013223642006.png)

     All the results above are plausible. The actual running time may vary from the  *Microbenchmarks note*,  however they're reasonable.

     The running time in Mark1 is significantly small, this may because in JIT, the loop isn't processed actually. Once we return *dummy*, the result in Mark2 is more reasonale. And in Mark3 we can see all the iterations take nearly the same amount of time. 

     There're some exception in Mark5 when there are sudden increase for iteration count 128/1024/16384, but we also see that the standard deviations are very large in those cases, which tells us we can have no confidence in those results.

2. Use Mark7 to measure the execution time for the mathematical functions pow, exp, and so on, as in *Microbenchmarks note* Section 4.2. Record the results in a text file along with appropriate system identifi- cation. Preferably do this on at least two different platforms, eg. your own computer and a fellow studen- t/friends computer.

   - MacOS

     ![image-20221013230550813](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013230550813.png)

   - Windows

   some analysis here...

### Exercise 5.2

1. First compile and run the thread timing code as is, using Mark6, to get a feeling for the variation and robustness of the results. Do not hand in the results but discuss any strangenesses, such as large variation in the time measurements for each cases.

   There're always some exceptions in different cases. Such as for *Thread create start join*, when iteration count is  512, there's significant increase of the mean time. This outlier measurement may be caused by the garbage collector accidentally performing some work at that time, or the just-in-time compiler, or some other external disturbance.

2. Now change all the measurements to use Mark7, which reports only the final result. Record the results in a text file along with appropriate system identification.

   ![image-20221013231914418](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013231914418.png)

​		Result is plausible, we can see that the creation of simple object cost just 2.7ns, but the creation of thread takes more than 800ns. And the start of 		a thread is even more, it takes almost 70000ns, even after creating those threads.

### Exercise 5.3

1. Measure the performance of the primecounting example on your own hardware, as a function of the number of threads used to determine whether a given number is a prime. Record system information as well as the measurement results for 1. . . 32 threads in a text file. If the measurements take excessively long time on your computer, you may measure just for 1. . . 16 threads instead.

2. Reflect and comment on the results; are they plausible? Is there any reasonable relation between the number of threads that gave best performance, and the number of cores in the computer you ran the benchmarks on? Any surprises

   ![image-20221013235341960](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221013235341960.png)

   Based on the results, when the number of threads is 2, it gives the best performance, it has the lowest mean running time. This may because that my computer has 2 cores(not as the system info, but the infomation on my computer). And we can also see that when the number of threads is 4, 6 and 8, they have a better performance. Maybe because they can be devided by 2- the number of cores. But when the number of threads are bigger than 8, we can't see a significant difference.

3. Now instead of the LongCounterclass, use the java.util.concurrent.atomic. AtomicLong class for the counts. Perform the measurements again as indicated above. Discuss the results: is the performance of AtomicLong better or worse than that of LongCounter? Should one in general use adequate built-in classes and methods when they exist?

   ![image-20221014002557827](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221014002557827.png)

When use AtomicLong class, the mean run time is longer.

### Exercise 5.4

1. Use Mark7 (from Bendchmark.java) to compare the performance of incrementing a volatile int and a normal int. Include the results in your hand-in and comment on them: Are they plausible? Any surprises?

![image-20221014004910624](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221014004910624.png)

​	The code is within *TestVolatile.java*, the result is shown as above. From the result we can see that the performance of incrementing a volatile int is worse than a normal int. 

​	Volatile int always read the value from the main memory, this may result in some error, when some threads changed the value in the register but hasn't write the value into the main memory. The performance of the normal int is better may because that normal int read the value from the local register for each thread, which will cost less time than reading from the main memory.

### Exercise 5.5

1. Extend LongCounter with these two methods in such a way that the counter can still be shared safely by several threads.

   The implementation of this part can be seen in *LongCounter.java*, use *ReentrantLock*.

2. How many occurencies of "ipsum" is there in long-text-file.txt. Record the number in your solution.

   ![image-20221014012206745](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221014012206745.png)

   The size of the array is 5697, and occurences of ipsum is 1430.

3. Use Mark7 to benchmark the search function. Record the result in your solution.

   Test search function           12340448.0 ns  585142.47         32

   ![image-20221014012603286](/Users/zq2lii/Library/Application Support/typora-user-images/image-20221014012603286.png)

4. Extend the code in TestTimeSearch with a new method

   

   

   
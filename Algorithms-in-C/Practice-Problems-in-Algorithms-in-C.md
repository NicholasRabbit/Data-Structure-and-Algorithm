## Practice Problems

### 1.10

Estimate the minimum amount of time (in days) that would be required for quick find (Program 1.1) to solve a problem with $10^6$ objects and $10^9$ input pairs, on a computer capable of executing $10^9$  instructions per second. Assume that each iteration of the while loop requires at least 10 instructions.  

**A solution:** 

If a while loop consists of at least 10 instructions, we consider that $N$ represents the objects of an array and $M$ represents the number of pairs then we conclude that it needs $T=MN$ while loops. Since there are at least 10 instructions in each while loop, the total number of instructions is $10\times MN$, namely $10^6\times10^9\times10=10^{16}$. Consequently, the time consumption is $10^{16}\div 10^9=10^7$ seconds. If it were measured in days, the result would be approximately $10^7\div(24\times60\times60)\approx 115.74$ which is extremely long time for a programme.
## Chapter 1 Introduction

### 1.2 A Sample Problem: Connectivity

Here is the elaboration of the first problem-connectivity example.

If the inputs are `3-4`  and they can not be directly connected by the previous inputs, for example users have already input `3-1, 1-4` previously and `3-4` are extraneous pairs so that programme don't output anything. 

> When the program inputs a pair p-q, it should output the pair only if the pairs it has seen to that point
> do not imply that p is connected to q.  

N.B. "imply" to suggest that something is true without saying so directly.

```txt
inputs	outputs 
//The first pairs of inputs: They are the first inputs so it is self-evident that they are not connected directly to each other when search for the stored table.
3-4 	3-4   
4-9		4-9   //The second pairs are still not directly connected by the previous inputs
8-0		8-0   //The are the same.
2-3		2-3
5-6		5-6
2-9 		 //The programme doesn't output because 2-9 can be connected by '2-3,3-4,4-9'
....
5-6 		 //No output. Because '5-6' has been input and connected each other. They are 'implied' by the previous inputs.
```

Apparently, the goal of the programme that we are going to devise, which is evident, is to find the "connection" in the "previous inputs" efficiently. Therefore an algorithm should be considered.

### 1.3 Union-Find Algorithms

##### 1) Below is the original code of union-find algorithms.

```c
#include <stdio.h>
#define N 10
int main(void) 
{
	int i, p, q, t, id[N];	
	for (i = 0; i < N; i++)
		id[i] = i;

    while (scanf("%d %d", &p, &q) == 2) {
		//If the two numbers were connected, the programme would continue.
		if (id[p] == id[q]) 
			continue;
        // This 'for loop' is the core of the algorithm
		for (t = id[p], i = 0; i < N; i++)  
			if (id[i] == t)
				id[i] = id[q];
            printf("%d %d\n", p, q);
            printf("after adding nodes\n");
            for (i = 0; i < N; i++)
                printf("%d ", id[i]);
            printf("\n");
	}
	return 0;
}
```

Analyses: 

The indices of the array `id[N]` represent the numbers inputted. For instance, 

After inputting 3,4

```
0,1,2,4,4,5,6,7,8,9 
```

After inputting 3,9, since `t=id[3]=4`, the element with index of `4` also equals `4` so it will be assigned the value of `4` simultaneously in the `for` loop. 

```txt
0,1,2,9,9,5,6,7,8,9  
```

##### 2) Property I.2 

Property I.2 (Page 15) Fro M>N, the *quick-union algorithm* could take more than MN/2 instructions to slove a connectivity problem with M pairs of N objects.

Why does it at least take $MN\backslash 2$ instructions?

The worst scenario of *quick-union* is the tree is linear; it at least takes 0 and at most $N-1$ steps to find object N. 

Elaboration of the equation:

$(0+1+2...(N-1))/ N=(N-1)/2$ 

Since the number of operands in $(0+1+2...(N-1))$ is N in total, we can get $[0+(N-1)]\times N/2$.
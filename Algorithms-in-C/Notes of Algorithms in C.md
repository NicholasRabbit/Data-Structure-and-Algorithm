### 1.2 A Sample Problem: Connectivity

Here is the elaboration of the first problem-connectivity example.

If the inputs are `3-4`  and they can not be directly connected by the previous inputs, for example users have already input `3-1, 1-4` before and `3-4` are extraneous pairs so that programme don's output. 

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

Apparently, the goal of the programme that we are going to devise, which is evident, is to find the "connection" in the "previous inputs" efficiently. An algorithm should be considered.


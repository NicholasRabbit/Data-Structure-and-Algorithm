#include <stdio.h>

#define N 10


int main(void) 
{

	int i, p, q, t, id[N];	

	for (i = 0; i < N; i++)
		id[i] = i;

	//The '\n' in the original "scanf("%d %d\n")" was ommitted since it was difficult to input it on CLI.
	while (scanf("%d %d", &p, &q) == 2) {
		//If the two numbers were connected, the programme will continue executing.
		if (id[p] == id[q]) 
			continue;
		/*
		 * 
		 * */
		for (t = id[p], i = 0; i < N; i++) 
			if (id[i] == t)
				id[i] = id[q];
		
		printf("%d %d\n", p, q);
	
		printf("After adding nodes:\n");
		for (i = 0; i < N; i++)
			printf("%d ", id[i]);
		printf("\n");

	}

	return 0;
}

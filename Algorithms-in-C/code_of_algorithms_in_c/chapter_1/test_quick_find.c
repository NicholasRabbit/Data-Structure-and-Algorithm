#include<stdio.h>

#define N 10

void iteration(int id[])
{
	int i;
	for(i = 0; i < N; i++)
		printf("%d ", id[i]);
	printf("\n");
}


int main (void)
{
	
	int p, q, id[N], i, temp;
	
	for(i = 0; i < N; i++)
		id[i] = i;

	iteration(id);

	while(scanf("%d %d", &p, &q) == 2) {
		temp = id[q];
		for (i = 0; i < N; i++)
			if (id[i] == id[p])
				id[i] = temp;

		iteration(id);
	}

	return 0;

}

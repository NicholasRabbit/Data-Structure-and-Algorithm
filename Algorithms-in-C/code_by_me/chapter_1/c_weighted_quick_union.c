#include <stdio.h>
#define N 10

int iterate_array(int id[]);

int main(int argc, char *argv[])
{
	int i, j, p, q, id[N], sz[N];

	for (i = 0; i < N; i++) {
		id[i] = i;
		sz[i] = 1;	
	}

	while (scanf("%d %d", &p, &q) == 2) {

		for (i = p; id[i] != i; i = id[i]);
		for (j = q; id[j] != j; j = id[j]);

		if (i == j) continue;

		if (sz[i] < sz[j]) {
			// Connect the tree with shorter path to the root of the longer one.
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}

		printf(" %d %d\n", p, q);
		
		iterate_array(id);
		
	}


	return 0;
}


int iterate_array(int id[])
{

	printf("After inputing nodes:\n");
	int i;
	for (i = 0; i < N; i++)
		printf("%d ", id[i]);
	printf("\n");
	return 0;
}



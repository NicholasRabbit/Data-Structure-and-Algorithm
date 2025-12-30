#include <stdio.h>

#define N 10

/*
 * Program 1.4 Path Compression by Halving
 *
 * */

void iterate_array(int id[]);

int main(int argc, char *argv[])
{

	int i, j, p, q, id[N], sz[N];

	for (i = 0; i < N; i++) {
		id[i] = i;
		sz[i] = 1;
	}


	while (scanf("%d %d", &p, &q) == 2) {
		for (i = p; i != id[i]; i = id[i])
			id[i] = id[id[i]];

		for (j = q; j != id[j]; j = id[j])
			id[j] = id[id[j]];

		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}

		iterate_array(id);
	}

	return 0;
}

void iterate_array(int id[])
{

	printf("After inputing nodes:\n");
	int i;
	for (i = 0; i < N; i++)
		printf("%d ", id[i]);
	printf("\n");
}



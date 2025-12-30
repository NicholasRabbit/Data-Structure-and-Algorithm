#include <stdio.h>

#define N 10

int iterate_array(int id[]);

int main(int argc, char *argv[])
{
	int i, count, p, q, id[N];

	for (i = 0; i < N; i++)
		id[i] = i;

	while (scanf("%d %d", &p, &q) == 2) {

		if (id[p] == id[q]) 
			continue;

		int t = id[p];
		count = 0;
		for (i = 0; i < N; i++) {
			count++;
			if (id[i] == t) {
				id[i] = id[q];
				count++;
			}
		}
	
		iterate_array(id);
		printf("Access: %d times.\n", count);
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





#include <stdio.h>

#define N 10

int main(void) 
{
	
	int i, j, p, q, id[N];	
	// initialisation
	for (i = 0; i < N; i++)
		id[i] = i;


	/*
	 * This is improvement to 'quick-find' algorightm because it does not
	 * need to scan all the elements in the array. It only changes the value
	 * of an element in the index of 'i' to that in the index of 'j'. If "id[j]"
	 * has been changed, then "id[i]" will be assigned the changed value.
	 * For instance, after inputing the fourth pair "2 3" "id[2]" has been altered 
	 * to "9" as that in "id[3]".
	 *
	 * */ 
	while (scanf("%d %d", &p, &q) == 2) {
		for (i = p; i != id[i]; i = id[i]);
		for (j = q; j != id[j]; j = id[j]);
		if (i == j) continue;
		id[i] = j;
		printf(" %d %d\n", p, q);

		printf("After inputing nodes:\n");
		for (i = 0; i < N; i++)
			printf("%d ", id[i]);
		printf("\n");

	}

	return 0;
}



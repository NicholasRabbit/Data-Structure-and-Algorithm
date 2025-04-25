#include <stdio.h>

#define N 10

int iterate_array(int *id);

int main(void) 
{
	
	int i, j, p, q, id[N];	
	// initialisation
	for (i = 0; i < N; i++)
		id[i] = i;


	/*
	 * This is improvement to 'quick-find' algorightm because it does not
	 * need to scan all the elements in an array. It only changes the value
	 * of an element in the index of 'i' to that in the index of 'j'. If "id[j]"
	 * has been changed, then "id[i]" will be assigned the changed value.
	 * For instance, after inputing the fourth pair "2 3" "id[2]" has been altered 
	 * to "9" as that in "id[3]".
	 *
	 * */ 
	while (scanf("%d %d", &p, &q) == 2) {
		for (i = p; i != id[i]; i = id[i]) {
			printf("i = %d\n", i);
		}
		for (j = q; j != id[j]; j = id[j]) {
			printf("j = %d\n", j);
		}
    // That indicates that he two elements point to the same root
		if (i == j) continue;
		id[i] = j;
		printf(" %d %d\n", p, q);

		iterate_array(id);

	}

	return 0;
}


int iterate_array(int *id)
{

	printf("After inputing nodes:\n");
	int i;
	for (i = 0; i < N; i++)
		printf("%d ", id[i]);
	printf("\n");
	return 0;
}



#include <stdio.h>

#define N 10

/*
 * Review the quick union algorithm.
 * */
int quick_union(int id[]);
void list_array(int arr[]);



int main(int argc, char *argv[])
{
	int id[N];
	quick_union(id);
	return 0;
}

int quick_union(int id[])
{
	int i, j, p, q;

	int k;
	for(k = 0; k < N; k++)
		id[k] = k;

	list_array(id);

	while(scanf("%d %d", &p, &q) == 2){
		
		for(i = p; id[i] != i; i = id[i]);
		for(j = q; id[j] != j; j = id[j]);

		if (i == j) continue;

		id[i] = j;
		printf("%d %d\n", p, q);
		list_array(id);
	
	}


	

	return 0;
}

void list_array(int arr[])
{
	int i;
	for(i = 0; i < N; i++)
		printf("%d ", arr[i]);

	printf("\n");
}

#include <stdio.h>
#include <stdlib.h>
#include <iostream>

void compare(int i, int n, int * cols) {
	if(cols[i] > cols[i+1]) {
		int diff = cols[i] - cols[i+1];
		cols[i] -= diff;
		cols[i+1] += diff;

		if (i+1 < n-1) {
			compare(i+1, n, cols);
		}
	}
	if (i != 0) {
		compare(i-1, n, cols);
	}
	return;
}

int main() {
	int n;
	std::cin >> n;
	int cols[n];
	for (int i = 0; i < n; i++) {
		std::cin >> cols[i];
	}
	if (n > 1) {
		compare(n-2, n, cols);

	}

	// print cols
	for (int j = 0; j < n; j++) {
		printf("%d ", cols[j]);
	}
	//printf("\n");
	return 0;

}
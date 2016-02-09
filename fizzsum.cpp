// Author: brian hodge (bph443)

#include <iostream>
#include <math.h>

// Returns the result of FizzBuzz applied to parameter x
int fizzBuzz(int x) {
	if (x%3 == 0 && x%5 == 0) {
		return pow(x, 2);
	}
	if (x%3 == 0) {
		return x+3;
	}
	if (x%5 == 0) {
		return x+5;
	}
	return 0;
}

// input: (p1,p2) is a range of integers
int sumFizz(int p1, int p2) {
	int sum = 0;
	for (int i = p1; i <= p2; i++) {
		sum += fizzBuzz(i);
	}
	return sum;
}

int main() {
	int p1; 
	int p2;
	while (std::cin >> p1 >> p2) {
		std::cout<<sumFizz(p1,p2)<<std::endl;
	}	
	return 0;
}
#include "stdafx.h"
#include "TestHeader.h"
#include <time.h>

int main()
{
	clock_t t1, t2;
	t1 = clock();

	int sum = 0, previous_sum = 1;

	for (long i = 0; i < 10; ++i) {
		sum += previous_sum;

		if (sum < 1) {
			previous_sum = sum;
		}

		else {
			previous_sum = sum - previous_sum;
		}
		cout << sum << endl;
	}

	t2 = clock();
	float diff = ((float)t2 - (float)t1);
	cout << "The time it took to run this whole program is " << diff << "s" << endl;
	getchar();
    return 0;
}
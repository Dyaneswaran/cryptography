#include <iostream>
#include <cmath>
using namespace std;


int main(){
	char s[] = "hello";

	long m = 0;
	int n = 5;
	for(int i = 0; s[i]; ++i){
		m += s[i] * pow(256, i);
		cout << s[i] << endl;
	}

	long temp = m;
	while(temp){
		int rem = temp % 256;
		cout << rem << endl;
		temp /= 256;
	}



	return 0;
}

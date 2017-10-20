#include <iostream>
#include <functional>
#include <string>
#include <windows.h>
#include <wincrypt.h>
#include <bcrypt.h>

void main() {
	using namespace std;

	char nts1[] = "Test";
	char nts2[] = "Test2";
	string str1(nts1);
	string str2(nts2);

	HCRYPTPROV hCryptProv = 0;
	const DWORD dwlength = 16;
	BYTE pbData[dwlength] = {};
	hash<char*> ptr_hash;
	hash<string> str_hash;

	cout << "same hashes:\n" << boolalpha;
	cout << "nts1 and nts2: " << (ptr_hash(nts1) == ptr_hash(nts2)) << '\n';
	cout << "str1 and str2: " << (str_hash(str1) == str_hash(str2)) << '\n';
	cout << "String 1: " << str_hash(str1) << " String 2: " << str_hash(str2) << '\n';

	if (CryptAcquireContextW(&hCryptProv, 0, 0, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT | CRYPT_SILENT)) {

	}

	if (CryptGenRandom(hCryptProv, dwlength, pbData))
	{
		CryptReleaseContext(hCryptProv, 0);
		for (DWORD i = 0; i < dwlength; i++) {
			cout << static_cast<unsigned int>(pbData[i]);
		}
		cout << endl;
		printf("Random sequence generated. \n");
	}
	else
	{
		printf("Error during CryptGenRandom.\n");
	}
	getchar();
}
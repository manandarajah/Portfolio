#include <iostream>
#include <functional>
#include <string>
#include <windows.h>
#include <wincrypt.h>
#include <bcrypt.h>

void main() {
	using namespace std;

	char message[] = "test";

	printf("Enter a message to hash: ");
	scanf_s("%s", &message);
	HCRYPTPROV hCryptProv = 0;
	const DWORD dwlength = 5;
	BYTE pbData[dwlength] = {};
	unsigned int hashed[sizeof(message) + dwlength];

	if (CryptAcquireContextW(&hCryptProv, 0, 0, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT | CRYPT_SILENT)) {

	}

	if (CryptGenRandom(hCryptProv, dwlength, pbData))
	{
		CryptReleaseContext(hCryptProv, 0);
		for (DWORD i = 0; i < dwlength; i++) {
			hashed[i] = static_cast<unsigned int>(pbData[i]);
		}
		for (int i = dwlength + 1; i < sizeof(hashed); i++) {
			hashed[i] = static_cast<unsigned int>(message[i]);
		}

		for (int i = 0; i < sizeof(hashed); i++) {
			cout << hashed[i];
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
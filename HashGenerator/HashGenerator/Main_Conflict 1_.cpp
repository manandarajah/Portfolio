#include <iostream>
#include <functional>
#include <string>
#include <windows.h>
#include <wincrypt.h>
#include <bcrypt.h>

void main() {
	using namespace std;

	string message;

	printf("Enter a message to hash: ");
	cin >> message;

	char messageByte[1024];

	for (int i = 0; i < message.length(); i++) {
		messageByte[i] = message[i];
	}
	HCRYPTPROV hCryptProv = 0;
	const DWORD dwlength = 5;
	BYTE pbData[dwlength] = {};
	hash<char*> ptr_hash;
	unsigned int hashed[sizeof(messageByte)+dwlength];
	//ptr_hash(messageByte);

	if (CryptAcquireContextW(&hCryptProv, 0, 0, PROV_RSA_FULL, CRYPT_VERIFYCONTEXT | CRYPT_SILENT)) {

	}

	if (CryptGenRandom(hCryptProv, dwlength, pbData))
	{
		CryptReleaseContext(hCryptProv, 0);
		for (DWORD i = 0; i < dwlength; i++) {
			hashed[i] = static_cast<unsigned int>(pbData[i]);
		}
		int count = 0;

		for (int i = 0; i < message.length(); i++) {
			cout << messageByte[i];
		}

		for (int i = dwlength; i < sizeof(hashed); i++) {
			hashed[i] = static_cast<unsigned int>(messageByte[count]);
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
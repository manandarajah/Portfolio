#include <iostream>
#include <functional>
#include <string>
#include <windows.h>
#include <wincrypt.h>
#include <bcrypt.h>

#include "cppconn/driver.h"
#include "cppconn/exception.h"
#include "cppconn/resultset.h"
#include "cppconn/statement.h"

#pragma comment(lib, "mysqlcppconn.lib")

using namespace std;
using namespace sql;

string server = "localhost";
string username = "root";
string password = "root";

void main() {

	char message[] = "Text";
	int decision = 0;

	//Choose an option to login or create account using numbers as input
	printf("1. Login account\n2. Create account\n");
	scanf_s("%d", decision);

	//Checks if input is valid
	while (decision != 1 && decision != 2) {
		printf("Invalid entry, try again\n");
		printf("1. Login account\n2. Create account\n");
		scanf_s("%d", decision);
	}

	//Switches based on the user's decision
	switch (decision) {
		case 1:
			char password[] = "test";
			int id = 0;

			printf("ID Number: ");
			scanf_s("%d", id);
			printf("Password: ");
			scanf_s("%s", &password);
			passwordLookup(password, id);
			break;
		case 2:
			string table = "";
			int accountType = 0;
			printf("Which account do you want to create?\n1. Customer\n2. Employee\n");
			scanf_s("%d", accountType);

			while (accountType != 1 && accountType != 2) {
				printf("Invalid entry, try again\n");
				printf("Which account do you want to create?\n1. Customer\n2. Employee\n");
				scanf_s("%d", accountType);
			}

			if (accountType == 1) {
				createAccount("customer");
			}

			else {
				createAccount("employee");
			}
			break;
	}
	getchar();
}

//Looks up hashed password within the database
bool passwordLookup(char password[], int id) {
	string customer = "select custno,password from customer where custno = " + id;
	string employee = "select empno,password from employee where empno = " + id;
	string hashedPass = "";

	if (hashWithSalt(password, &hashedPass)) {
		Driver *driver;
		Connection *connection;
		Statement *statement;
		ResultSet *resultSet;

		try {
			driver = get_driver_instance();
			connection = driver->connect(server, username, password);
			statement = connection->createStatement();
			statement->execute("use ctajax_db");
			resultSet = statement->executeQuery(customer);
			if (id == resultSet->getInt("custno") && hashedPass == resultSet->getString("password")) {
				cout << "Successfully logged in as customer" << endl;
				return true;
			}
			resultSet = statement->executeQuery(employee);
			if (id == resultSet->getInt("empno") && hashedPass == resultSet->getString("password")) {
				cout << "Successfully logged in as employee" << endl;
				return true;
			}
		}

		catch (SQLException e) {
			cout << "Error: " << e.what() << endl;
		}
		return true;
	}
	return false;
	// connect to database, extract password, and compare to input hash. If there's a match, password verification successfull
}

//Creates a new entry
void createAccount(string table) {
	// connect to database, compare id, and insert into database
	string query = "insert into " + table;
	string value = "";
	string firstName = "", lastName = "", unitNo = "", address = "", postal = "", areaCode = "", phone = "", email = "";
	string hashedPass = "";
	char password[] = "test";

	printf("First Name: ");
	scanf_s("%S", &firstName);
	printf("Last Name: ");
	scanf_s("%S", &lastName);
	printf("Password: ");
	scanf_s("%S", &password);
	printf("Unit No: ");
	scanf_s("%S", &unitNo);
	printf("Address: ");
	scanf_s("%S", &address);
	printf("Postal Code: ");
	scanf_s("%S", &postal);
	printf("Area Code: ");
	scanf_s("%S", &areaCode);
	printf("Phone: ");
	scanf_s("%S", &phone);
	printf("Email: ");
	scanf_s("%S", &email);

	if (hashWithSalt(password,&hashedPass)) {

		query += " (first_name,last_name,password,address_1,address_2,postal_code,areacode,number,email_address";
		value += " values ("+firstName + "," + lastName + "," + hashedPass + "," + unitNo + "," + address + "," + postal + "," + areaCode + "," + phone + "," + email;

		Driver *driver;
		Connection *connection;
		Statement *statement;
		ResultSet *resultSet;

		if (table == "employee") {
			string position = "", paystring = "";
			double payrate = 0;

			printf("Position: ");
			scanf_s("%s", &position);
			printf("Pay Rate: ");
			scanf_s("%f", payrate);
			paystring = payrate;
			query += ",position,payrate,datehired";
			value += "," + position + "," + paystring;
		}
		query += ")";
		value += ")";
		query += value;

		try {
			driver = get_driver_instance();
			connection = driver->connect(server, username, password);
			statement = connection->createStatement();
			statement->execute("use ctajax_db");
			statement->execute(query);
		}

		catch (SQLException e) {
			cout << "Error: " << e.what() << endl;
		}
	}
}

//SALT function
bool hashWithSalt(char password[], string *hashedPass) {

	HCRYPTPROV hCryptProv = 0;
	const DWORD dwlength = 5;
	BYTE pbData[dwlength] = {};
	hash<char*> ptr_hash;
	unsigned int passHash[dwlength + sizeof(password)];
	int count = 0;

	ptr_hash(password);

	if (CryptGenRandom(hCryptProv, dwlength, pbData)) {
		CryptReleaseContext(hCryptProv, 0);
		for (DWORD i = 0; i < dwlength; i++) {
			passHash[i] = static_cast<unsigned int>(pbData[i]);
		}
		for (int i = dwlength + 1; i < sizeof(passHash); i++) {
			passHash[i] = static_cast<unsigned int>(password[count]);
			count++;
		}
		for (int i = 0; i < sizeof(passHash); i++) {
			hashedPass += passHash[i];
		}
		return true;
	}
	return false;
}
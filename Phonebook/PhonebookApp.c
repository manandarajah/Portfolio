#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <conio.h>

#define TRUE 1
#define FALSE 0 

typedef struct TreeNode { 
   struct TreeNode *leftPtr;
	double phone;
	char firstName[25];
	char lastName[25];  
	struct TreeNode *rightPtr;
}TreeNode, *TreeNodePtr;                 

void insertNode( TreeNodePtr *treePtr, double phone, char *firstName, char *lastName);
void sortAscending( TreeNodePtr treePtr );
void deleteNode( TreeNodePtr *treePtr, double input );
void searchNode ( TreeNodePtr *treePtr, double input, TreeNodePtr *parentPtr, TreeNodePtr *tempNodePtr, int *found );
void openFile(FILE *fp, char mod);
void closeFile(FILE *fp);
void saveList(FILE *fp, TreeNodePtr treePtr);
void loadList(FILE *fp, TreeNodePtr *treePtr);
void instructions();
int isNumber(char *string);
void setNull(char *string);

int main(void) {

    TreeNodePtr rootPtr = NULL;
	FILE *fp;

	int choice;
	double phone;
	char firstName[25];
	char lastName[25];
	double input;
	
	printf("Enter a choice\n");
	instructions();
	scanf("%d", &choice);
	
	while(choice != 8) {
		switch(choice) {
			case 1:
				printf("\nEnter your phone number: ");
				scanf("%lf", &phone);
				printf("Enter your first name: ");
				scanf("%s", firstName);
				printf("Enter your last name: ");
				scanf("%s", lastName);

				int found ;    

				TreeNodePtr parentPtr, tempNodePtr;

				parentPtr = NULL ;
				tempNodePtr = NULL;

				/* find the node to be deleted */

				searchNode ( &rootPtr, phone, &parentPtr, &tempNodePtr, &found ) ;

				/* if the node is not found */
				if ( found == TRUE )
				{
					printf("Phone number %0.f already exists\n\n", phone);
				} else {
					insertNode( &rootPtr, phone, firstName, lastName);
				}
			
			break;
			case 2:
				printf("\nEnter a phone number to delete: ");
				scanf("%lf", &input);
				deleteNode(&rootPtr, input);
			break;
			case 3:
				printf("\nSort phone numbers ascending\n");
				sortAscending( rootPtr );
 			break;
			case 4:
				printf("\nPhone number search:\n");
				printf("Enter a phone#: ");
				scanf("%lf", &phone);
				searchNode ( &rootPtr, phone, &parentPtr, &tempNodePtr, &found ) ;

				/* if the node is not found */
				if ( found != TRUE )
				{
					printf("\nContact phone number: %0.f doesnt exist\n\n", phone);
				} else {
					insertNode( &rootPtr, phone, firstName, lastName);
				}

 			break;
			case 5:
				fp = fopen("phonebook.txt","w");
				saveList(fp, rootPtr);
				fclose(fp);
				break;
			case 6:
				fp = fopen("phonebook.txt","r");
				loadList(fp, &rootPtr);
				fclose(fp);
				break;
			case 7:
			instructions();
			break;

			default:
				printf("Invalid choice.\n\n");
				instructions();
				break;
		}
		printf("Enter a choice. Type 7 to show menu again\n");
		scanf("%d", &choice);
	}
	
	return 0;
}

void instructions() {
	printf("1. Insert contact\n2. Remove contact\n3. Sort phone numbers ascending\n4. Phone number search\n5. Save \n6. Load \n7. Menu \n8. Exit application\n");
}

//insert into tree
void insertNode( TreeNodePtr *treePtr, double phone, char *firstName, char *lastName)
{ 
   //determine where to insert based on phone#
   if ( !*treePtr ) { 

      *treePtr = malloc( sizeof( TreeNode ) );      

      if ( *treePtr ) { 
         ( *treePtr )->phone = phone;
         strcpy( ( *treePtr )->firstName, firstName );
         strcpy( ( *treePtr )->lastName, lastName );
         ( *treePtr )->leftPtr = NULL;
         ( *treePtr )->rightPtr = NULL;
      } 
      else {
         printf( "Contact not inserted. No memory available.\n" );
      } 
      return; 
   } 
   else { 

      if ( phone < ( *treePtr )->phone ) {
         insertNode( &( ( *treePtr )->leftPtr ), phone, firstName, lastName);
      } 
      else if (phone > ( *treePtr )->phone ){ 
		  
         insertNode( &( ( *treePtr )->rightPtr ), phone, firstName, lastName);
      } 
      else { 
        //printf("-duplicate");  
      }   

   }
   
} 


void sortAscending( TreeNodePtr treePtr )
{ 

   if ( treePtr != NULL) { 
      sortAscending( treePtr->leftPtr );
      printf( "Phone: %0.f\nFirst name: %s\tLast name: %s\n\n", treePtr->phone, treePtr->firstName, treePtr->lastName);
      sortAscending( treePtr->rightPtr );
   } 

}



//delete tree node
void deleteNode ( TreeNodePtr *treePtr, double input )
{
  	int found ;
    TreeNodePtr parentPtr, tempNodePtr, replacementNodePtr ;
    
    if ( *treePtr == NULL )
    {
        printf ( "\nThere are no contacts with that phone number\n" ) ;
        return ;
    }

    parentPtr = NULL ;
    tempNodePtr = NULL;

    searchNode ( treePtr, input, &parentPtr, &tempNodePtr, &found ) ;//find phone number

    if ( found == FALSE )
    {
        printf ( "\n contact with the phone number: %0.f not found\n", input ) ;
        return ;
    }

    if ( tempNodePtr -> leftPtr != NULL && tempNodePtr -> rightPtr != NULL )
    {
		
        parentPtr = tempNodePtr ;
        replacementNodePtr = tempNodePtr -> rightPtr ;

        while ( replacementNodePtr -> leftPtr != NULL )
        {
            parentPtr = replacementNodePtr ;
            replacementNodePtr = replacementNodePtr -> leftPtr ;
        }

        tempNodePtr->phone = replacementNodePtr -> phone;
        strcpy( tempNodePtr->firstName, replacementNodePtr->firstName );
		strcpy( tempNodePtr->lastName, replacementNodePtr->lastName);
        tempNodePtr = replacementNodePtr ;
    }

    else if ( tempNodePtr -> leftPtr == NULL && tempNodePtr -> rightPtr == NULL )
    {
		if (parentPtr != NULL) {
	        if ( parentPtr -> rightPtr == tempNodePtr )
	            parentPtr -> rightPtr = NULL ;
	        else
	            parentPtr -> leftPtr = NULL ;
		}

        free ( tempNodePtr ) ;
        return ;
    }

   else if ( tempNodePtr -> leftPtr == NULL && tempNodePtr -> rightPtr != NULL )
    {
        if ( parentPtr -> leftPtr == tempNodePtr )
            parentPtr -> leftPtr = tempNodePtr -> rightPtr ;
        else
            parentPtr -> rightPtr = tempNodePtr -> rightPtr ;

        free ( tempNodePtr ) ;
        return ;
    }

    else if ( tempNodePtr -> leftPtr != NULL && tempNodePtr -> rightPtr == NULL )
    {
        if ( parentPtr -> leftPtr == tempNodePtr )
            parentPtr -> leftPtr = tempNodePtr -> leftPtr ;
        else
            parentPtr -> rightPtr = tempNodePtr -> leftPtr ;

        free ( tempNodePtr ) ;
        return ;
    }
}

//search tree for matching phone
void searchNode ( TreeNodePtr *treePtr, double input, TreeNodePtr *parentPtr, TreeNodePtr *tempNodePtr, int *found )
{
    TreeNodePtr tempPtr ;
    tempPtr = *treePtr ;
    *found = FALSE ;
    *parentPtr = NULL ;

    while ( tempPtr != NULL )
    {
     if ( tempPtr -> phone == input )
     {
       *found = TRUE ;
       *tempNodePtr = tempPtr ;
			 printf( "\nPhone: %0.f\nFirst name: %s\tLast name: %s\n\n", tempPtr->phone, tempPtr->firstName, tempPtr->lastName);

       return ;
     }

       *parentPtr = tempPtr ;
        if ( tempPtr -> phone > input )
            tempPtr = tempPtr -> leftPtr ;
        else
            tempPtr = tempPtr -> rightPtr ;
    }
}

void saveList(FILE *fp, TreeNodePtr treePtr) {
	
	if (fp != NULL && treePtr != NULL) {
		saveList(fp, treePtr->leftPtr);
		printf("Writing to file: %s\t%s\t%0.f\n", treePtr->firstName, treePtr->lastName, treePtr->phone);
		fprintf(fp, "%s\t%s\t%0.f\n", treePtr->firstName, treePtr->lastName, treePtr->phone);
		saveList(fp, treePtr->rightPtr);
	}
}

void loadList(FILE *fp, TreeNodePtr *treePtr) {
	char numberString[25], firstName[25], lastName[25], input[25], c;
	double phone = 0;
	int count = 0;

	setNull(numberString);
	setNull(firstName);
	setNull(lastName);
	setNull(input);
	
	if (fp != NULL) {
		do {
			c = getc(fp);

			if (c != '\t' && c != '\n') {
				input[count] = c;
				count++;
			}

			else {
				if (firstName[0] == '\0') {
					strcpy(firstName, input);
				}

				else if (lastName[0] == '\0') {
					strcpy(lastName, input);
				}

				else if (isNumber(input) == TRUE) {
					phone = atof(input);
					insertNode( treePtr, phone, firstName, lastName);
				}
				setNull(input);
				count = 0;
			}
		} while (c != EOF);
	}
}

int isNumber(char *string) {
	int result = TRUE;

	for (int i = 0; i < 25; i++) {
		if (isdigit(string[i]) == 0 && string[i] != '.' && string[i] != '\0') {
			result = FALSE;
		}
	}
	return result;
}

void setNull(char *string) {
	for (int i = 0; i < 25; i++) {
		string[i] = '\0';
	}
}

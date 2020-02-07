// Name:
// USC NetID:
// CSCI 455 PA5
// Fall 2019


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.


//Helper function to find the Node,
//If the Node does not exist, return NULL
ListType findNode(ListType list, string key);

//Helper function to drop the Node,
//If the Node is not in the table, return false
bool dropNode(ListType & list, string key);

//Helper function to add Node,
//If the Node already exists, return false
bool addNode(string key, int val, ListType &list);
 
//Helper function to return the number of Nodes
int numNodes(ListType list);

//Helper function to display the Nodes in the bucket
void print(ListType list);

//Helper function to update an element, 
//If the element is not in the list, return false
bool update(const string &theKey, int theValue, ListType list);
	

// keep the following line at the end of the file
#endif

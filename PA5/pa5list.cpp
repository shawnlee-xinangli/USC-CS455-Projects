// Name:
// USC NetID:
// CS 455 PA5
// Fall 2019

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {

   Node test = Node("head",10);
   ListType p = &test;
   
   cout<< "Expected Value 0:" << findNode(p,"t") <<endl;
 
   addNode("a",1,p);
   addNode("b",2,p);
   addNode("c",3,p);
   
   cout <<"Number of Nodes: "<<numNodes(p)<<endl;
   print(p);
   dropNode(p,"b");
   dropNode(p,"head");
   dropNode(p,"a");
   
   print(p);


	return 0;

}

// Name:
// USC NetID:
// CSCI 455 PA5
// Fall 2019

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
	hashSize = HASH_SIZE;
  	table = new ListType[hashSize]();
  	
}


Table::Table(unsigned int hSize) {
	hashSize = hSize;
	table = new ListType[hashSize]();
}


int* Table::lookup(const string &key) {
   	ListType head = table[hashCode(key)];
   	ListType target = findNode(list,key);
   	if (target == NULL) {
   		return NULL;
   	}
   	return &(target->value);  
}

bool Table::remove(const string &key) {
   ListType head = table[hashCode(key)];
   return dropNode(head,key);  
}

bool Table::insert(const string &key, int value) {
    ListType head = table[hashCode(key)];
  	return addNode(key, value, head);  
}

int Table::numEntries() const {
    int sum = 0;
  	for(int i = 0; i < hashSize; i++){
    	sum += numNodes(table[i]);
  	}
  	return sum;  
}


void Table::printAll() const {
	for(int i = 0; i < hashSize; i++){
    	sum += print(table[i]);
  	}
}

void Table::hashStats(ostream &out) const {
    out << "number of buckets: "<< hashSize << endl;
    out << "number of entries: " << numEntries() << endl;
    
    int longest = 0;
    int nonEmpty = 0;
    for (int i = 0; i<hashSize; i++) {
        int size = numNodes(table[i]);
        if (size != 0) {
            nonEmpty++;
        }
        if (size > longest) {
            longest = size;
        }
    }
    out << "number of non-empty buckets: "<< nonEmpty << endl;
    out << "longest chain: "<< longest << endl;
}


// add definitions for your private methods here

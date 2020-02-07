// Name:
// USC NetID:
// CSCI 455 PA5
// Fall 2019


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below



ListType findNode(ListType list, string key){
	ListType p = list;
	while ( p != NULL && p->key != theKey) {
		p = p->next;
	}
	return p;
}


bool dropNode(ListType & list, string key){

	ListType target = findNode(list, key);
	if (target == NULL) {
		return false;
	}
	if (target == list) {
		list = list->next;
		return true;

	}
	
	ListType prev = list;
	while (prev->next->key != key) {
		prev = prev->next;
	}
	prev->next = prev->next->next;
	delete target;
	
	return true;
}


bool addNode(string key, int val, ListType &list){
	if (findNode(list, key) != NULL) {
		return false;
	}

	ListType p = list;
	while (p->next != NULL) {
		p = p->next;
	}
	
	p->next = new Node(key,val);
	return true;	
}

int numNodes(ListType list) {
	
	int count = 0;
	ListType head = list;
	while (head != NULL) {
		count ++;
		head = head->next;
	}

	return count;
}

void print(ListType list) {
	if (list == NULL) {
		return;
	}
	ListType head = list;
	while (head != NULL) {
		cout << head->key <<" "<< head->value << endl;
		head = head->next;
	}

}


bool update(const string &theKey, int theValue, ListType list){
	return false;
}
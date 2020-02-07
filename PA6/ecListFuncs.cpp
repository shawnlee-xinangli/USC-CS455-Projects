/*  Name:
 *  USC NetID:
 *  CS 455 Fall 2019
 *
 *  See ecListFuncs.h for specification of each function.
 */


// for NULL
#include <cstdlib>

// in case you want to use assert statements
#include <cassert>

#include "ecListFuncs.h"

using namespace std;



void longestRun(ListType list, int & maxRunVal, int & maxRunLen) {
   
   ListType p = list;
   int prev = p->data;
   maxRunVal = p->data;
   int count = 1;
   maxRunLen = count;
   while (p -> next != NULL) {
      p = p -> next;
      if (p -> data == prev) {
         count ++;
      }else {
         count = 1;
         prev = p -> data;
      }
      maxRunVal = (maxRunLen < count) ? p -> data : maxRunVal;
      maxRunLen = (maxRunLen < count) ? count : maxRunLen;
   }

}


void removeMultiplesOf3(ListType & list) {
	
	if (list == NULL) {
		return;
	}
	if (list->data % 3 == 0) {
		list = list->next;
		removeMultiplesOf3(list);
	}
	else {
		removeMultiplesOf3(list->next);
	}
}

void insertAtPos(ListType & list, int val, int pos) {
   if (pos == 0) {
      list = new Node(val,list);
   }else {
      insertAtPos(list->next,val,pos-1);
   }

}

void insertMiddle(ListType & list, int midVal) {
   ListType p = list;
   int len = 0;
   while (p != NULL) {
      len ++;
      p = p -> next;
   }
   
   insertAtPos(list,midVal,len/2);

}


ListType merge(ListType list1, ListType list2) {
   if (list1 == NULL) {
   		return list2;
   }
   if (list2 == NULL) {
   		return list1;
   }
   ListType p = NULL;
   if (list1->data < list2->data) {
   		p = new Node(list1->data,merge(list1->next,list2));
   }
   else if (list1->data = list2->data) {
   		p = new Node(list1->data,merge(list1->next,list2->next));
   }
   else {
   		p = new Node(list2->data,merge(list1,list2->next));
   }
   return p;
}




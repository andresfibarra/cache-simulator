# Cache Simulator

## COSC50, Spring 2022, Homework 7

@author andresfibarra

### Overview

Simulate 4 types of caches: directly mapped, 2-way set associative, 4-way set associative, and fully associative 

Given a text file with the address being searched for

Info on the cache being simulated;
* 32 bit address
* 16 byte block
* 64 lines total in the cache

Organization
* have a cacheline object that holds metadata
* have a cache constructor that takes in a parameter for 
* direct mapped, 2 way, 4 way, fully associative
* verbose option (boolean)

to simulate, don't keep track of the value of each byte
instead keep track of the metadata
* 	valid bit
* 	tag
* 	when it was last touched

Organizational stats for mode:
* direct associative
* 	one line per set--> 64 sets
* 	22 tag bits
* 	6 set bits
* 	4 block bits

* 2 way set-associative
* 	2 lines per set, 32 sets
* 	23 tag bits
* 	5 set bits
* 	4 block bits
* 
* 4 way set-associative
* 	4 lines per set, 16 sets
* 	24 tag bits
* 	4 set bits
* 	4 block bits
* 
* fully associative
* 	a lot of tag bits, no set bits, some block offset bits --> 1 set, 64 lines
* 	28 tag bits
* 	0 set bits
* 	4 block bits
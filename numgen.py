from sys import argv
import random

if(len(argv) != 3):
	print 'Usage: python numgen.py <filename> <amountOfNumbers>'
	exit()

fh = open(argv[1], "w")

for i in range(0, int(argv[2])):
    fh.write(str(random.randint(1,10000)) + "\n")

print 'Done'

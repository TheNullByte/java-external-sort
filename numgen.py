from sys import argv
import random

fh = open(argv[1], "a")

for i in range(0, int(argv[2])):
    fh.write(str(random.randint(1,10000)) + "\n")

print 'Done'
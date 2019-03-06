from runner import Runner
from datetime import date
from os.path import isfile
from datetime import datetime

'''
File output is such:
[
	[Day, Write Average, Append Average, Large Write, Read Test],
	[...],
	...
]
'''

def write(d, w, a, l, r):
	f = open("readme.txt", "w")
	f.write(str(d) + "\n" + str(w) + "\n" + str(a) + "\n" + str(l) + "\n" + str(r))
	f.close()

def main():
	# Run the test
	runner = Runner()
	runner.go()

	wR = runner.writeRand
	wS = runner.writeSameChar
	aR = runner.appendRand
	aS = runner.appendSameChar
	rT = runner.readTest

	write_avg  = (wR + wS) / 2.
	append_avg = (aR + aS) / 2.
	
	write(write_avg, append_avg, rT)

	print ("Done.")

if __name__ == '__main__':
	main()

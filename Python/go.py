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

def calc_days():
	f = open("Python/start.txt", 'r')
	lst = f.readlines()
	f.close()
	arr = [int(x.strip()) for x in lst]
	d0 = date(arr[0], arr[1], arr[2])
	curr_year = datetime.now().year
	curr_mon  = datetime.now().month
	curr_day  = datetime.now().day
	d1 = date(curr_year, curr_mon, curr_day)

	delta = d1 - d0
	return delta.days


def main():
	# Run the test
	runner = Runner()
	runner.go()
	# make a csv file

	wR = runner.writeRand
	wS = runner.writeSameChar
	aR = runner.appendRand
	aS = runner.appendSameChar
	lW = runner.largeWrite
	rT = runner.readTest

	write_avg  = (wR + wS) / 2.
	append_avg = (aR + aS) / 2.

	DAYS = calc_days() # TODO (ALL): CHANGE START FILE TO DATE THAT YOU START
	
	write(DAYS, write_avg, append_avg, lW, rT)

	print ("Done.")

if __name__ == '__main__':
	main()

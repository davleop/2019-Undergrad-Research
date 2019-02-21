from out import write
from runner import Runner
from datetime import date
from os.path import isfile
from datetime import datetime

'''
CSV file output is such:
[
	[Day, SSD, HDD, Flashdrive, Chromebook],
	[...],
	...
]
'''

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

DAYS = calc_days() # TODO (ALL): CHANGE START FILE TO DATE THAT YOU START

def main():
	# Run the test
	# make a csv file

if __name__ == '__main__':
	main()

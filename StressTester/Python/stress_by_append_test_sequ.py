import time
import random
import string
from os import stat
from write import write
from remove_file import remove

def append(filepath=open("./filepath.txt", 'r').readline().strip(), seconds=600):
	'''
	Returns a tuple of (elapsed time, passes)
	This will go on for 10 minutes or 600 seconds
	It writes 256 Mb at a time.
	'''

	global cat

	cat = filepath + "stress_by_append.txt"

	j = 0

	start = end = time.time()
	choice = "A"
	while end - start < seconds:
		try:
			f = open(cat, 'a')
			f.write(choice * ((2 ** 20) * 256)) # 256 Mb
			f.close()
		except:
			remove(cat)
		end = time.time()
		j += 1

	total_time = end - start

	return(total_time, j * stat(cat).st_size / total_time)

def main():
	t, j = append()
	remove(cat)
	write("d", j)


if __name__ == '__main__':
	main()
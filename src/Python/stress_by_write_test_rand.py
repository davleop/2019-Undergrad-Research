import time
import random
import string
from write import write
from remove_file import remove

def writer(filepath=open("./filepath.txt", 'r').readline().strip(), seconds=600):
	'''
	Returns a tuple of (elapsed time, passes)
	This will go on for 10 minutes or 600 seconds
	It writes 256 Mb at a time.
	'''

	global cat

	cat = filepath + "stress_by_write.txt"

	j = 0

	start = end = time.time()

	while end - start < seconds:
		choice = random.choice(string.printable)
		try:
			f = open(cat, 'w')
			f.write(choice * ((2 ** 20) * 256)) # 256 Mb
			f.close()
		except:
			remove(cat)
		end = time.time()
		j += 1

	total_time = end - start

	return(total_time, humanize.naturalsize((j * stat(cat).st_size) / total_time) + "/s")

def main():
	t, j = writer()
	remove(cat)
	write("a", j)

if __name__ == '__main__':
	main()
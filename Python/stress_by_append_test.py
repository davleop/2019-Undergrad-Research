import time
import random
import string
from remove_file import remove

def append(choice=random.choice(string.printable), filepath="./", seconds=900):
	'''
	Returns a tuple of (elapsed time, passes)
	This will go on for 15 minutes or 900 seconds
	It writes 100 Mb at a time.
	'''
	start = time.time()
	end   = time.time()

	cat = filepath + "stress_by_append.txt"

	j = 0
	while end - start < seconds:
		f = open(cat, 'a')
		try:
			f.write(choice * ((2 ** 20) * 100)) # 100 Mb
			f.close()
		except:
			f.close()
			if f.closed:
				remove(cat)
			else:
				raise IOError("Something went wrong :: Write Test")
		end = time.time()
		j += 1

	if f.closed:
		remove(cat)
	else:
		raise IOError("Something went wrong :: Write Test")

	return(end - start, j)

def main():
	append()

if __name__ == '__main__':
	main()
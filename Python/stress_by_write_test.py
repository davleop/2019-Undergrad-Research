import time
import random
import string
from remove_file import remove

def writer(choice=random.choice(string.printable), filepath="./", seconds=900):
	'''
	Returns a tuple of (elapsed time, passes)
	This will go on for 15 minutes or 900 seconds
	It writes 1 Gb at a time.
	'''
	start = time.time()
	end   = time.time()

	cat = filepath + "txt.txt"

	j = -1
	while end - start < seconds:
		f = open(cat, 'w')
		try:
			f.write(choice * ((2 ** 20) * 1024)) # 1 Gb
			f.close()
		except:
			f.close()
			if f.closed:
				remove(cat)
			else:
				raise IOError("Something went wrong :: Write Test")
		end = time.time()
		j += 1

	return(end - start, j)

def main():
	writer()

if __name__ == '__main__':
	main()
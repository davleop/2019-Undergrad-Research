import time
import random
import string
from remove_file import remove

def writer(choice=random.choice(string.printable), filepath="./", seconds=600):
	'''
	Returns a tuple of (elapsed time, passes)
	This will go on for 10 minutes or 600 seconds
	It writes 256 Mb at a time.
	'''

	cat = filepath + "stress_by_write.txt"

	j = 0

	start = time.time()
	end   = time.time()
	while end - start < seconds:
		f = open(cat, 'w')
		f.write(choice * ((2 ** 20) * 256)) # 256 Mb
		f.close()
		end = time.time()
		j += 1

	remove(cat)

	return(end - start, j)

def main():
	#print ("Write:")
	t, j = writer(filepath="e:\\", seconds=5)
	print ("e:\\")
	print (t)
	print (j)

	#t, j = writer(filepath="f:\\", seconds=5)
	#print ("f:\\")
	#print (t)
	#print (j)

	#t, j = writer(filepath="g:\\", seconds=5)
	#print ("g:\\")
	#print (t)
	#print (j)

if __name__ == '__main__':
	main()
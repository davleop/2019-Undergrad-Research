import time
import string
import random

def large_write(filepath="./", seconds=600):
	start  = time.time()
	end    = time.time()

	cat = filepath + "largeByte.txt"

	j = 0
	while end - start < seconds:
		choice = random.choice(string.printable)
		try:
			f = open(cat, "a")
			f.write(choice * ((2 ** 20) * 256)) # 256 Mb
			f.close();
			j += 1
		except:
			pass

		end = time.time()

	return (end - start, j)

def main():
	print ("Large:")
	t, j = large_write("e:\\", 5)

	print (t)
	print (j)

if __name__ == '__main__':
	main()
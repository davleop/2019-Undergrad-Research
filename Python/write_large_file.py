import time
import struct
import random

def large_write(filepath="./", seconds=600):
	start  = time.time()
	end    = time.time()

	cat = filepath + "largeByte.txt"

	j = 0
	while end - start < seconds:

		f = open(cat, "ab")

		try:
			for i in range(512):
				choice = random.choice(range(-0x80,0x80))
				try:
					f.write(struct.pack("b", choice))
				except:
					raise Exception("Oh no!")
		except:
			break

		j += 1
		end = time.time()

	f.close()

	return (end - start, j)

def main():
	t, j = large_write("e:\\", 5)

	print (t)
	print (j)

if __name__ == '__main__':
	main()
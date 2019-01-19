import time
import random
import string

def writer():
	start = time.time()
	end   = time.time()

	j = 1
	while end - start < 90:
		f = open("txt.txt", 'w')
		f.write(random.choice(string.printable) * ((2 ** 20) * 100)) # 100 Mb
		f.close()
		end = time.time()
		j += 1

	print (end - start)
	print (j)

def main():
	writer()

if __name__ == '__main__':
	main()
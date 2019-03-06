import string
import random

def large_write(filepath="./"):

	cat = filepath + "largeByte.txt"

	j = 0
	choice = random.choice(string.printable)
	f = open(cat, "a")
	f.write(choice * ((2 ** 20) * 2000)) # 2 GB
	f.close();

def main():
	print ("Large:")
	t, j = large_write("e:\\", 5)

	print (t)
	print (j)

if __name__ == '__main__':
	main()
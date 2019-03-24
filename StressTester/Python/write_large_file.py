import string
import random

def large_write(filepath=open("./filepath.txt", 'r').readline().strip()):

	cat = filepath + "largeByte.txt"

	j = 0
	choice = random.choice(string.printable)
	f = open(cat, "a")
	f.write(choice * ((2 ** 20) * 1000)) # 3 GB == 1000 *******
	f.write(choice * ((2 ** 20) * 1000)) # 3 GB == 1000 *******
	f.write(choice * ((2 ** 20) * 1000)) # 3 GB == 1000 *******
	f.close();

def main():
	large_write()

if __name__ == '__main__':
	main()
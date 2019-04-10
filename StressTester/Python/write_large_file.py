import string
import random

def large_write(filepath=open("./filepath.txt", 'r').readline().strip()):

	cat = filepath + "largeByte.txt"

	f = open(cat, "a")

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 1000)) # 1 GB == 1000 *******
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 1000)) # 1 GB == 1000 *******
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 1000)) # 1 GB == 1000 *******
	
	f.close()

def main():
	large_write()

if __name__ == '__main__':
	main()
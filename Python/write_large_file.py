import string
import random

def large_write(filepath=open("./filepath.txt", 'r').readline().strip()):

	cat = filepath + "largeByte.txt"

	j = 0
	choice = random.choice(string.printable)
	f = open(cat, "w")
	f.write(choice * ((2 ** 20) * 256)) # 3 GB == 3000 *******
	f.close();

def main():
	large_write("e:\\")

if __name__ == '__main__':
	main()
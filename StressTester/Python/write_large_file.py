import string
import random

def large_write(filepath=open("./filepath.txt", 'r').readline().strip()):

	cat = filepath + "largeByte.txt"


	# 1st Gig

	f = open(cat, "a")

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))

	f.close()

	# 2nd Gig

	f = open(cat, "a")

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))

	f.close()

	# 3rd Gig

	f = open(cat, "a")

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))

	f.close()

def main():
	large_write()

if __name__ == '__main__':
	main()
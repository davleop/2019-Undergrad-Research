import string
import random

def large_write(filepath=open("./filepath.txt", 'r').readline().strip()):

	cat = filepath + "largeByte.txt"

	f = open(cat, "a")

	# 1st Gig

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))

	# 2nd Gig

	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))
	
	choice = random.choice(string.printable)
	f.write(choice * ((2 ** 20) * 256))

	# 3rd Gig

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
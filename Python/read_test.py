import time
from write import *
from remove_file import remove

def read_test(filepath=open("./filepath.txt", 'r').readline().strip(), seconds=600):
	cat = filepath + "largeByte.txt"
	j   = 0

	start = end = time.time()

	while end - start < seconds:
		something = 0
		f = open(cat, "rb")
		blob = f.read()
		for byte in blob:
			something ^= byte
		end = time.time()
		j += 1
		f.close()

	remove(cat)

	return (end - start, j)

def main():
	t, j = read_test()
	write("e", j)
	wrapUp()

if __name__ == '__main__':
	main()
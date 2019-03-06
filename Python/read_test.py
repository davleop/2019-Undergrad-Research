import time
from remove_file import remove

def read_test(filename="./", seconds=600):
	cat = filename + "largeByte.txt"
	j   = 0

	start = time.time()
	end   = time.time()


	while end - start < seconds:
		something = 0
		f = open(cat, "r")
		blob = f.read()
		for byte in blob:
			something ^= byte
		end = time.time()
		j += 1
		f.close()

	remove(cat)

	return (end - start, j)

def main():
	t, j = read_test("e:\\", 5)

	print (t)
	print (j)

if __name__ == '__main__':
	main()
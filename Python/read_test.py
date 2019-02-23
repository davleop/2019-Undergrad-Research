import time
from remove_file import remove

def read_test(filename="./", seconds=600):
	cat = filename + "largeByte.txt"
	j   = 0

	start = time.time()
	end   = time.time()

	while end - start < seconds:
		lst = []
		f = open(cat, "rb")
		blob = bytearray(f.read())
		for byte in blob:
			lst.append(byte)
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
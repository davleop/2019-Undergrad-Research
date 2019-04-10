import time
from write import *
from os import stat
from remove_file import remove

def read_test(filepath=open("./filepath.txt", 'r').readline().strip(), seconds=600):
	global cat
	cat = filepath + "largeByte.txt"
	j   = 0

	start = end = time.time()

	while end - start < seconds:
		something = 0
		f = open(cat, "rb")
		for chunk in iter(lambda: f.read(128), ""):
			chunk += b"."
		end = time.time()
		j += 1
		f.close()

	total_time = end - start

	return (total_time, (j * stat(cat).st_size) / total_time)

def main():
	t, j = read_test()
	remove(cat)
	write("e", j)
	wrapUp()

if __name__ == '__main__':
	main()
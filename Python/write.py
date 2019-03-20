from cleanup import clean_files

def write(filename, data):
	f = open(filename, 'w')
	f.write(str(data))
	f.close()

def wrapUp():
	q = int(open("a", 'r').readline().strip())
	w = int(open("b", 'r').readline().strip())
	e = int(open("c", 'r').readline().strip())
	r = int(open("d", 'r').readline().strip())
	t = int(open("e", 'r').readline().strip())

	write_avg  = (q + w) / 2.0
	append_avg = (e + r) / 2.0
	read       = t

	f = open("readme.txt", "w")
	f.write(str(write_avg) + "\n" + str(append_avg) + "\n" + str(read))
	f.close()

	clean_files()
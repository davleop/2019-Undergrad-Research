from cleanup import clean_files

def write(filename, data):
	f = open(filename, 'w')
	f.write(str(data))
	f.close()

def wrapUp(): # TODO(David): Revise this method
	q = float(open("a", 'r').readline().strip()[:-5])
	w = float(open("b", 'r').readline().strip()[:-5])
	e = float(open("c", 'r').readline().strip()[:-5])
	r = float(open("d", 'r').readline().strip()[:-5])
	t = float(open("e", 'r').readline().strip()[:-5])

	write_avg  = (q + w) / 2.0
	append_avg = (e + r) / 2.0
	read       = t

	f = open("readme.txt", "w")
	f.write(str(write_avg) + "\n" + str(append_avg) + "\n" + str(read))
	f.close()

	clean_files()
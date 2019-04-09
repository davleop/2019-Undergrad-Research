import humanize
from cleanup import clean_files

def write(filename, data):
	f = open(filename, 'w')
	f.write(str(data))
	f.close()

def wrapUp(): # TODO(David): Revise this method
	q = float(open("a", 'r').readline().strip())
	w = float(open("b", 'r').readline().strip())
	e = float(open("c", 'r').readline().strip())
	r = float(open("d", 'r').readline().strip())
	t = float(open("e", 'r').readline().strip())

	# TODO (David): make this the place it converts bytes per second to human readable
	#				everywhere else, make it just calculate bytes per second. Then 
	#				remove the import statements in the other files and just keep it here.

	write_avg  = (q + w) / 2.0
	append_avg = (e + r) / 2.0
	read       = t

	f = open("readme.txt", "w")
	f.write(humanize.naturalsize(write_avg) + "/s\n" + humanize.naturalsize(append_avg) + "/s\n" + humanize.naturalsize(read) + "/s")
	f.close()

	clean_files()
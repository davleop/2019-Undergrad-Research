import os

def clean_files(filepath=open("./filepath.txt", 'r').readline().strip()):
	write_file  = filepath + "stress_by_write.txt"
	append_file = filepath + "stress_by_append.txt"
	large_file  = filepath + "largeByte.txt"

	try:
		os.remove(write_file)
	except:
		pass
	try:
		os.remove(append_file)
	except:
		pass
	try:
		os.remove(large_file)
	except:
		pass

def main():
	clean_files()

if __name__ == '__main__':
	main()
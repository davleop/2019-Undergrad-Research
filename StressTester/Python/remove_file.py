import os

def remove(filename): # prefered to get full file path
	try:
		os.remove(filename)
	except:
		pass

def main():
	remove("testfile.txt")

if __name__ == '__main__':
	main()
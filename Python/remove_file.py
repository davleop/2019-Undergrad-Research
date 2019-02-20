import os

def remove(filename): # prefered to get full file path
	os.remove(filename)

def main():
	remove()

if __name__ == '__main__':
	main()
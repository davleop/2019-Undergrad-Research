from stress_by_write_test import writer
from stress_by_append_test import append
from write_large_file import large_write
from read_test import read_test

class Runner():
	'''
	Runner is a class in order to execute the stress testing scripts.
	This file will be called by "go.py" which will the be file called on by 
	"StressTester.java" that is running the GUI.
	'''
	def __init__(self, filepath=open("./filepath.txt", 'r').readline().strip()):
		self.__filepath             = filepath
		self.__writePassesRandom    = 0
		self.__writePassesSameChar  = 0 
		self.__appendPassesRandom   = 0
		self.__appendPassesSameChar = 0
		self.__writeLargeFile       = 0
		self.__read_test            = 0

	@property
	def filepath(self):
		return self.__filepath

	@property
	def writeRand(self):
		return self.__writePassesRandom
	
	@property
	def writeSameChar(self):
		return self.__writePassesRandom

	@property
	def appendRand(self):
		return self.__writePassesRandom

	@property
	def appendSameChar(self):
		return self.__writePassesRandom

	@property
	def largeWrite(self):
		return self.__writeLargeFile

	@property
	def readTest(self):
		return self.__read_test

	def __writeRandom(self, seconds=600):
		try:
			tmp, self.__writePassesRandom = writer(filepath=self.__filepath, seconds=seconds)
		except Exception:
			print ("Something went wrong in Write Stress Test...")

	def __writeSameChar(self, seconds=600, char="A"):
		try:
			tmp, self.__writePassesSameChar = writer(char, self.__filepath, seconds)
		except Exception:
			print ("Something went wrong in Write Stress Test...")

	def __appendRandom(self, seconds=600):
		try:
			tmp, self.__appendPassesRandom = append(filepath=self.__filepath, seconds=seconds)
		except Exception:
			print ("Something went wrong in Append Stress Test...")
	
	def __appendSameChar(self, seconds=600, char="A"):
		try:
			tmp, self.__appendPassesSameChar = append(char, self.__filepath, seconds)
		except Exception:
			print ("Something went wrong in Append Stress Test...")

	def __largeWrite(self, seconds=600):
		try:
			tmp, self.__writeLargeFile = large_write(self.__filepath, seconds)
		except Exception:
			print ("Something went wrong in Append Stress Test...")

	def __readTest(self, seconds=600):
		try:
			tmp, self.__read_test = read_test(self.__filepath, seconds)
		except Exception:
			print ("Something went wrong in Append Stress Test...")

	def go(self, seconds=600):
		self.__writeRandom(seconds)
		self.__writeSameChar(seconds)
		self.__appendRandom(seconds)
		self.__appendSameChar(seconds)
		self.__largeWrite(seconds)
		self.__readTest(seconds)

	def __str__(self):
		string =  "Write passes with random character : " + str(self.__writePassesRandom)    + "\n"
		string += "Append passes with random character: " + str(self.__appendPassesRandom)   + "\n"
		string += "\n"
		string += "Write passes with same character   : " + str(self.__writePassesSameChar)  + "\n"
		string += "Append passes with same character  : " + str(self.__appendPassesSameChar) + "\n"
		string += "\n"
		string += "Large write passes                 : " + str(self.__writeLargeFile)       + "\n"
		string += "Read test passes                   : " + str(self.__read_test)            + "\n"
		return string

def main():
	runner = Runner()
	runner.go(5)
	print (runner)

if __name__ == '__main__':
	main()
from stress_by_write_test import writer
from stress_by_append_test import append

class Runner():
	def __init__(self, filepath=open("./filepath.txt", 'r').readline().strip()):
		self.__filepath             = filepath
		self.__writePassesRandom    = 0
		self.__writePassesSameChar  = 0 
		self.__appendPassesRandom   = 0
		self.__appendPassesSameChar = 0
		self.__downloadPasses       = 0

	def writeRandom():
		try:
			tmp, self.__writePassesRandom = writer(filepath=self.__filepath)
		except IOError:
			print ("Something went wrong in Write Stress Test...")

	def writeSameChar():
		try:
			tmp, self.__writePassesSameChar = writer("A", self.__filepath)
		except IOError:
			print ("Something went wrong in Write Stress Test...")

	def appendRandom():
		try:
			tmp, self.__appendPassesRandom = append(filepath=self.__filepath)
		except IOError:
			print ("Something went wrong in Append Stress Test...")
	
	def appendSameChar():
		try:
			tmp, self.__appendPassesSameChar = append("A", self.__filepath)
		except IOError:
			print ("Something went wrong in Append Stress Test...")

	def go(self):
		pass

def main():
	runner = Runner()
	runner.go()

if __name__ == '__main__':
	main()
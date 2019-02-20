import csv

def write(in_data, file="data.csv"):
	f = open(file, 'w')
	writer = csv.writer(f)
	writer.writerows(in_data)
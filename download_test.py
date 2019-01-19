import uuid
import requests

FILE_URL = "ENTER SOME URL HERE" # does not download videos

def download():
	r = requests.get(FILE_URL, stream=True)

	with open(str(uuid.uuid4()), 'wb') as file:
		for chunk in r.iter_content(chunk_size=1024):
			if chunk:
				file.write(chunk)

def main():
	try:
		download()
	except:
		print ("Did you input a URL for the file to download?")
		print ("Did you check the URL for correct content and not an HTML file?")

if __name__ == '__main__':
	main()
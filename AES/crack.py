#!/usr/bin/env python3

def count_repetitions(ciphertext, block_size):
	chunks = [ciphertext[i:i+block_size] for i in range(0, len(ciphertext), block_size)]
	number_of_repetitions = len(chunks) - len(set(chunks))
	result = {
		'ciphertext': ciphertext,
		'repetitions': number_of_repetitions
	}
	return result

def detect_ecb_mode(ciphertext):
	BLOCK_SIZE = 16
	repetitions = [count_repetitions(cipher, BLOCK_SIZE) for cipher in ciphertext]

	most_repetitions = sorted(repetitions, key=lambda x: x['repetitions'], reverse=True)[0]

	print ("Ciphertext: {}".format(most_repetitions['ciphertext']))
	print ("Repeating Blocks: {}". format(most_repetitions['repetitions']))

	if most_repetitions['repetitions'] >= 3:
		return True
	return False

def detect_cbc_mode(plaintext, ciphertext):
	pass

def detect_ofb_ctr_modes(plaintext, ciphertext):
	pass # read more on the methods used...

def detect_eax_gcm_modes(ciphertext):
	pass # this ueses MAC. Just modify the file and try to decrypt...

def main():
	ciphertext = [bytes.fromhex(line.strip()) for line in open("8.txt")]
	print(detect_ecb_mode(ciphertext))

if __name__ == '__main__':
	main()
import sys
import getopt
import requests

def send_msg(message, numbers):
	url = "https://www.fast2sms.com/dev/bulk"
	payload = "sender_id=FSTSMS&message="+str(message)+"&language=english&route=p&numbers="+str(numbers)
	print(payload)
	headers = {
	'authorization': "JOdf8UW057jeRcypXuINZl9sBGazYMCqEHKVQwrnSD6FbT2L3gfGDqh0E5FyzU3HjtbCenA6J8r27msB",
	'Content-Type': "application/x-www-form-urlencoded",
	'Cache-Control': "no-cache",
	}
	response = requests.request("POST", url, data=payload, headers=headers)
	print(response.text)

def main(argv):
	message = ''
	numbers = ''

	try:
		opts, args = getopt.getopt(argv,"m:n:h",["message=","numbers="])
	except getopt.GetoptError:
		print ('Incorrect input')
		sys.exit(2)
	for opt, arg in opts:
		if opt in ("-m", "--message"):
			message = arg
		elif opt in ("-n", "--numbers"):
			numbers = arg
		else:
			print("check your args")
	print ('message is ', message)
	print ('numbers is ', numbers)
	send_msg(message, numbers);

if __name__ == "__main__":
	main(sys.argv[1:])


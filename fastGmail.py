
import sys
import getopt
import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def send_email(user, pwd, recipient, subject, body):
	gmail_user = user
	gmail_pwd = pwd
	FROM = user
	TO = recipient if type(recipient) is list else [recipient]
	SUBJECT = subject
	TEXT = body
	
	for i in range(len(TO)):
		try: 
			msg = MIMEMultipart()
			msg['From'] = FROM
			msg['To'] = TO[i]
			msg['Subject'] = SUBJECT
			 
			body = TEXT
			msg.attach(MIMEText(body, 'plain'))
			 
			server = smtplib.SMTP('smtp.gmail.com', 587)
			server.starttls()
			server.login(gmail_user, gmail_pwd)
			text = msg.as_string()
			server.sendmail(FROM, TO[i], text)
			server.quit()
			
			print ('successfully sent the mail')
		except:
				print ("failed to send mail")
	
def main(argv):
	user = 'cdivyanambiar@gmail.com'
	pwd = '2007mvpmvp'
	recipient = ''
	subject = ''
	body = ''

	try:
		opts, args = getopt.getopt(argv,"t:b:s:h",["to=","subject=","body="])
	except getopt.GetoptError:
		print ('Incorrect input')
		sys.exit(2)
	for opt, arg in opts:
		if opt in ("-t", "--to"):
			recipient = arg
		elif opt in ("-s", "--subject"):
			subject = arg
		elif opt in ("-b", "--body"):
			body = arg
		else:
			print("check your args")

	recipient = recipient.split(",")
	print ('user is ', user)
	print ('password is ', pwd)
	print ('receipient is ', recipient)
	print ('subject is ', subject)
	print ('body is ', body)
	send_email(user,pwd,recipient,subject,body);

if __name__ == "__main__":
	main(sys.argv[1:])
 
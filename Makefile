#CC="mvn"
CC="mave.sh"

cc:
	cd Client && $(CC) compile

cs:
	cd control && $(CC) compile


rs:
	cd control && mvn exec:java -Dexec.mainClass=dk.kea.App

rc:
	cd Client && mvn exec:java -Dexec.mainClass=dk.kea.App

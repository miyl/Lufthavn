#CC="mvn"
CC="mave.sh"

ccl:
	cd Client && $(CC) compile

cco:
	cd Control && $(CC) compile


rcl:
	cd Client && mvn exec:java -Dexec.mainClass=dk.kea.App

rco:
	cd Control && mvn exec:java -Dexec.mainClass=dk.kea.Airport

# 1st letter: c: compile, l: launch, t: test
# cl: client, co: control

CC="mvn"


ccl:
	cd Client && $(CC) compile

rcl:
	cd Client && mvn exec:java -Dexec.mainClass=dk.kea.App

tcl: 
	cd Client && mvn test

rco:
	cd Control && mvn exec:java -Dexec.mainClass=dk.kea.Airport

cco:
	cd Control && $(CC) compile

tco: 
	cd Control && mvn test

db:
	mysql --host="167.71.38.68" --port="3306" --user="fly" --password="flysystem" --database="lufthavn"

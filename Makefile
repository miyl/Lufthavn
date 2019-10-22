#CC="mvn"
CC="mave.sh"

ccl:
	cd clean && $(CC) compile

cco:
	cd control && $(CC) compile

cf:
	cd fuel && $(CC) compile

cl:
	cd luggage && $(CC) compile

ct:
	cd taxi && $(CC) compile


rcl:
	cd clean && mvn exec:java -Dexec.mainClass=dk.kea.App

rco:
	cd control && mvn exec:java -Dexec.mainClass=dk.kea.App

rf:
	cd fuel && mvn exec:java -Dexec.mainClass=dk.kea.App

rl:
	cd luggage && mvn exec:java -Dexec.mainClass=dk.kea.App

rt:
	cd taxi && mvn exec:java -Dexec.mainClass=dk.kea.App

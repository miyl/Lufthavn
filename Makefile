ccl:
	cd clean && mave.sh --compile

cco:
	cd control && mave.sh --compile

cf:
	cd fuel && mave.sh --compile

cl:
	cd luggage && mave.sh --compile

ct:
	cd taxi && mave.sh --compile


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

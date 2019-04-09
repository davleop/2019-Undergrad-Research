all:
			clear
			javac -Xdiags:verbose StressTester.java
			-java StressTester
c:
			clear
			javac -Xdiags:verbose StressTester.java
go:
			clear
			python Python/go.py
go3:
			clear
			python3 Python/go.py
py:
			clear
			python Python/runner.py
py3:
			clear
			python3 Python/runner.py
run_write3:
			clear
			python3 stress_by_write_test.py
run_append3:
			clear
			python3 stress_by_append_test.py
run_append:
			clear
			python stress_by_append_test.py
run_write:
			clear
			python stress_by_write_test
jar: *.class
			clear
			rm -f *.jar
			jar cvfm StressTester.jar mani.mf .
			cp StressTester.jar ../StressTester/
			rm -rf ../StressTester/Python/
			cp -avr Python ../StressTester/Python
run_jar:
			clear
			java -jar ../StressTester/StressTester.jar
clean:
			rm -rf *.class
			rm -f filepath.txt
			rm -rf Python/__pycache__
			rm -f a b c d e

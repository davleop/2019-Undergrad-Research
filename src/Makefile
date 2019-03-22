all:
			cls
			javac -Xdiags:verbose StressTester.java
			java StressTester
c:
			cls
			javac -Xdiags:verbose StressTester.java
go:
			cls
			python Python/go.py
go3:
			cls
			python3 Python/go.py
py:
			cls
			python Python/runner.py
py3:
			cls
			python3 Python/runner.py
run_write3:
			cls
			python3 stress_by_write_test.py
run_append3:
			cls
			python3 stress_by_append_test.py
run_append:
			cls
			python stress_by_append_test.py
run_write:
			cls
			python stress_by_write_test
jar: *.class
			cls
			rm -f *.jar
			jar cvfm StressTester.jar mani.mf .
			cp StressTester.jar ../StressTester/
run_jar:
			cls
			java -jar ../StressTester/StressTester.jar
clean:
			rm -rf *.class
			rm -f filepath.txt
			rm -rf Python/__pycache__
			rm -f a b c d e
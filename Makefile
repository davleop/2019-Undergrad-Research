all:
			cls
			javac -Xdiags:verbose StressTester.java
			java StressTester
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
clean:
			rm -rf *.class
			rm filepath.txt
all:
			cls
			javac gui.java
			java gui
run_write:
			cls
			python3 stress_by_append_test.py
run_append:
			cls
			python3 stress_by_write_test.py
clean:
			rm -rf gui.class
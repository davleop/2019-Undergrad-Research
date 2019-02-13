all:
			cls
			javac gui.java
			java gui
clean:
			rm -rf gui.class
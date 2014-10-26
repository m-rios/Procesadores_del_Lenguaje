all: scanner parser
	javac -cp 

scanner: lex
	javac -cp /Library/Java/java_cup_v10k:. UpdComp.lex.java

lex: UpdComp.lex
	java -cp /Library/Java JLex.Main UpdComp.lex

parser: cup
	javac -cp /Library/Java/java_cup_v10k:. parser.java

cup: parser.cup
	java -cp /Library/Java/java_cup_v10k java_cup.Main parser.cup

clean:
	rm -f sym.java
	rm -f parser.java
	rm -f UpdComp.lex.java
	rm -f *.class
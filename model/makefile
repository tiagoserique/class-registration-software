CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
BINDIR = ../bin/model

all: $(BINDIR)/CsvParser.class\
	$(BINDIR)/LeCsv.class

$(BINDIR)/%.class: ./%.java
	$(CC) $^

CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
BINDIR = ../bin/materia

all: $(BINDIR)/Materia.class\
	$(BINDIR)/Lista.class\
	$(BINDIR)/CsvParser.class\
	$(BINDIR)/LeCsv.class\
	$(BINDIR)/MateriaHistorico.class

$(BINDIR)/%.class: ./%.java
	$(CC) $^

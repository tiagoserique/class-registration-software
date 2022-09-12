CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
BINDIR = ../bin/model

all: $(BINDIR)/CsvReader.class\
	$(BINDIR)/HistoricoParser.class\
	$(BINDIR)/MateriaParser.class\
	$(BINDIR)/Teste.class

$(BINDIR)/%.class: ./%.java
	$(CC) $^

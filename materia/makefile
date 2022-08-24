CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
BINDIR = ../bin/materia

all: $(BINDIR)/Materia.class $(BINDIR)/Lista.class

$(BINDIR)/%.class: ./%.java
	$(CC) $^

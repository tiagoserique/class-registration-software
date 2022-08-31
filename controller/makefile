CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
BINDIR = ../bin/controller

all: $(BINDIR)/Controller.class\
	$(BINDIR)/TelaEstadoController.class


$(BINDIR)/%.class: ./%.java
	$(CC) $^

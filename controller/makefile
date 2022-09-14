CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
MAIN = TelaInicialController
BINDIR = ../bin/controller

all: $(BINDIR)/Controller.class\
	$(BINDIR)/TelaEstadoController.class\
	$(BINDIR)/TelaSolicitarController.class\
	$(BINDIR)/TelaInicialController.class

run: all
	@ $(RUN) controller.$(MAIN)

$(BINDIR)/%.class: ./%.java
	$(CC) $^

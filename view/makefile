CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
MAIN = TelaInicial
BINDIR = ../bin/view

all: $(BINDIR)/$(MAIN).class

run: $(BINDIR)/$(MAIN).class
	@ $(RUN) view.$(MAIN)

$(BINDIR)/$(MAIN).class: $(MAIN).java $(BINDIR)/TelaEstado.class $(BINDIR)/TelaSolicitar.class
	$(CC) $(MAIN).java

$(BINDIR)/%.class: ./%.java
	$(CC) $^

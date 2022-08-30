CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
MAIN = TelaInicial
BINDIR = ../bin/view
SRCVIEW = $(MAIN).java TelaEstado.java TelaSolicitar.java
GUI = guiElements
GUIDIR = $(BINDIR)/$(GUI)
BINGUI = $(GUIDIR)/Botao.class
SRCGUI = $(GUI)/Botao.java

all: $(BINDIR)/$(MAIN).class

run: $(BINDIR)/$(MAIN).class
	@ $(RUN) view.$(MAIN)

$(BINDIR)/$(MAIN).class: $(BINGUI) $(SRCVIEW)
	$(CC) $(SRCVIEW)

$(BINGUI): $(SRCGUI)
	$(CC) $(SRCGUI)

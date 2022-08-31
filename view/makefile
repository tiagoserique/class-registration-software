CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
MAIN = Test
BINDIR = ../bin/view
SRCVIEW = TelaInicial.java TelaEstado.java TelaSolicitar.java
GUI = guiElements
GUIDIR = $(BINDIR)/$(GUI)
BINGUI = $(GUIDIR)/Botao.class
SRCGUI = $(GUI)/Botao.java

all: $(BINDIR)/$(MAIN).class
	$(CC) $(MAIN).java

run: all
	@ $(RUN) view.$(MAIN)

$(BINDIR)/$(MAIN).class: $(BINGUI) $(SRCVIEW)
	$(CC) $(SRCVIEW)

$(BINGUI): $(SRCGUI)
	$(CC) $(SRCGUI)

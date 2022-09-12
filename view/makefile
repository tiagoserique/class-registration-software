CC = javac -cp ../bin -d ../bin
RUN = java -cp ../bin
MAIN = Test
BINDIR = ../bin/view

SUBSCRIBE = Tela.java TelaSub.java
SUBSCRIBEBIN = $(BINDIR)/Tela.class\
							 $(BINDIR)/TelaSub.class

SRCVIEW = TelaInicial.java TelaEstado.java TelaSolicitar.java

GUI = guiElements
GUIDIR = $(BINDIR)/$(GUI)
BINGUI = $(GUIDIR)/Botao.class\
				 $(GUIDIR)/Tabela.class
SRCGUI = $(GUI)/Botao.java\
				 $(GUI)/Tabela.java

all: $(BINDIR)/$(MAIN).class
	$(CC) $(MAIN).java

run: all
	@ $(RUN) view.$(MAIN)

$(SUBSCRIBEBIN): $(SUBSCRIBE)
	$(CC) $(SUBSCRIBE)

$(BINDIR)/$(MAIN).class: $(SUBSCRIBEBIN) $(BINGUI) $(SRCVIEW)
	$(CC) $(SRCVIEW)

$(BINGUI): $(SRCGUI)
	$(CC) $(SRCGUI)

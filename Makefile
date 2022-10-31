## Voici un Makefile
## Pour plus d'information sut un Makefile voir
## https://makefiletutorial.com/

INSTALLDIR=out/production/TP2
MAINCLASS=Main
JARFILE=Spell

JDKBIN=$(shell dirname `which java`)
#JDKBIN=/usr/bin
#JDKBIN=/usr/local/opt/openjdk/bin
#ECLIPSE_LOC=/Applications/Eclipse\ Java.app
#JDKBIN=$(ECLIPSE_LOC)/Contents/Eclipse/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.macosx.x86_64_16.0.2.v20210721-1149/jre/bin
ECLIPSE_LOC=/opt/eclipse-jee-2021-03
#JDKBIN=$(ECLIPSE_LOC)/plugins/org.eclipse.justj.openjdk.hotspot.jre.full.linux.x86_64_15.0.2.v20210201-0955/jre/bin
JAVAC=$(JDKBIN)/javac
JAR=$(JDKBIN)/jar
JAVA=$(JDKBIN)/java

all: compile jar exec

# Cible (target, en anglais)  pour compiler
compile: clean
	cd src ; make JAVAC="$(JAVAC)" INSTALLDIR="$(INSTALLDIR)" MAINCLASS="$(MAINCLASS)" compile

install:
	cd src ; make install

jar: compile
	cd $(INSTALLDIR); \
	echo Main-Class: $(subst /,.,$(MAINCLASS)) > manifest.txt ; \
	$(JAR) cvfm $(JARFILE).jar manifest.txt ./
	mv $(INSTALLDIR)/$(JARFILE).jar ./

clean:
	- cd src ; make -k clean ; make INSTALLDIR="$(INSTALLDIR)" cleanInstall
	- rm *.zip *.jar manifest.*


# Cible pour executer 

exec: $(JARFILE).jar
	$(JAVA) -jar $(JARFILE).jar

version:
	$(JAVA) --version

# Demarre automatiquement une demonstration de votre programme
# Il faut que cette demo soit convaincante
demo: $(JARFILE).jar
	$(JAVA) -jar $(JARFILE).jar resources/minidico.txt resources/fautes.txt 
	$(JAVA) -jar $(JARFILE).jar resources/idico.txt resources/fautes.txt 

# Executer automatiquent les test
# On s'attend (d'habitude) que pour claque classe MaClasse il y ait une
# classe TestMaClasse qui vorifie le bon comportment de chaque methode de la classe
# sur au moins une entrée
# A vous de completer
tests:

# Cible pour créer son rendu de tp 
zip:
	moi=$$(whoami) ; zip -r $${moi}_renduTP2.zip *


# Cible pour vérifier le contenu de son rendu de tp 
zipVerify:
	moi=$$(whoami) ; unzip -l $${moi}_renduTP2.zip

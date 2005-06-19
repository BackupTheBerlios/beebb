LATEX=latex
DVI2PDF=dvipdf

all: opis_srodowiska TODO konkurencja spotkanie_17.02.2005 strukturaCVS dokumentacja

opis_srodowiska: opis_srodowiska.pdf

TODO: TODO.pdf

konkurencja: konkurencja.pdf

spotkanie_17.02.2005: spotkanie_17.02.2005.pdf

strukturaCVS: strukturaCVS.pdf

dokumentacja: dokumentacja.pdf

opis_srodowiska.dvi: opis_srodowiska.tex
	$(LATEX) $<
	$(LATEX) $<

dokumentacja.dvi: dokumentacja.tex
	$(LATEX) $<
	$(LATEX) $<

strukturaCVS.dvi: strukturaCVS.tex
	$(LATEX) $<
	$(LATEX) $<

%.dvi: %.tex
	$(LATEX) $<

%.pdf: %.dvi
	$(DVI2PDF) $< $@
	

clean:
	rm -f *.aux
	rm -f *.log
	rm -f *.dvi
	rm -f *.pdf
	rm -f *.toc
	rm -f *.*~
	
	

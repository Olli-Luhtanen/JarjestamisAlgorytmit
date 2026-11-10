# Tietorakenteet ja algorytmit 1. Harjoitustyö
## Järjestämisalgorytmit, Vertailu ja ajoitus

Raportti sisältää jokaisen algorytmin aika tulokset Satunnaisen, käänteisen ja nousevan datan järjestämisestä. Satunnaisessa käytetty tiettyä seed että tulokset olisivat vertailukelpoisia.

Vertailu tehtiin 10 000 ja 100 000 alkion koolla 30 kertaa ottaen mediaanin ajan mittauksesta.
Lisäksi tarkistaen, että data on järjestyksessä (ei ajanmittauksessa mukana)

Lisäksi jokaisen tuloksia on verrattu teoreettisiin suoritusaikoihin.

## Insertion Sort
10 000
Random data sorting took: 6577489 nanoseconds
Ascending data sorting took 9678 nanoseconds
Descending data sorting took 13196958 nanoseconds 

100 000
Random data sorting took: 653112320 nanoseconds  
Ascending data sorting took 95901 nanoseconds  
Descending data sorting took 1303031377 nanoseconds  

## Bubble Sort
10 000
Random data sorting took: 74956416 nanoseconds
Ascending data sorting took 3066 nanoseconds
Descending data sorting took 64569108 nanoseconds 

100 000
Random data sorting took: 11554124081 nanoseconds  
Ascending data sorting took 35627 nanoseconds  
Descending data sorting took 6450656311 nanoseconds  

## Array Sort
10 000
Random data sorting took: 715180 nanoseconds
Ascending data sorting took 22512 nanoseconds
Descending data sorting took 34365 nanoseconds

100 000
Random data sorting took: 1025303 nanoseconds  
Ascending data sorting took 70182 nanoseconds  
Descending data sorting took 201931 nanoseconds  

## Shell Sort
10 000
Random data sorting took: 695813 nanoseconds
Ascending data sorting took 86674 nanoseconds
Descending data sorting took 144342 nanoseconds 

100 000
Random data sorting took: 9056167 nanoseconds  
Ascending data sorting took 1154046 nanoseconds  
Descending data sorting took 1799653 nanoseconds 

# Quick Sort
Annettuun pohjaan tehty muutama optimaatio mitä löysin.
1. Pivotin valinta kolmen alkion mediaanilla.
2. Kun päästään pieniin taulukon kokoihin kuten alle 24 vaihdetaan yksinkertaisempaan algorytmiin kuten InsertionSort
3. Käytetään recursiota pienempään taulukon osaan ja silmukkaa suurempaan

### Alkuperäiset mittaukset
10 000
Random data sorting took: 543014 nanoseconds
Ascending data sorting took 89929 nanoseconds
Descending data sorting took 92555 nanoseconds 

100 000
Random data sorting took: 6645531 nanoseconds  
Ascending data sorting took 1117527 nanoseconds  
Descending data sorting took 1140982 nanoseconds  

### Optimoinnin jälkeen
10 000
Random data sorting took: 793457 nanoseconds
Ascending data sorting took 239121 nanoseconds
Descending data sorting took 56276 nanoseconds 

100 000
Random data sorting took: 4748234 nanoseconds  
Ascending data sorting took 689380 nanoseconds  
Descending data sorting took 684961 nanoseconds  

# Merge Sort
Annettuun pohjaan tehty muutama optimaatio mitä löysin.
1. Taas pienille osille käytetään insertSorttia
2. Aputaulukkona temp[] jolla vältytään kahden uuden taulun luontia joka merge kutsussa.
3. Valmiiksi järjestyksessä tarkistetaan ettei tule turhia merge kutsuja
4. Vain vasen puoli taulukosta kopioidaan ei oikeaa.

### Alkuperäiset mittaukset
10 000
Random data sorting took: 726190 nanoseconds
Ascending data sorting took 301830 nanoseconds
Descending data sorting took 313862 nanoseconds

100 000
Random data sorting took: 9993836 nanoseconds  
Ascending data sorting took 3542330 nanoseconds  
Descending data sorting took 3637660 nanoseconds   

### Optimoinnin jälkeen
10 000
Random data sorting took: 895139 nanoseconds
Ascending data sorting took 13405 nanoseconds
Descending data sorting took 189578 nanoseconds

100 000
Random data sorting took: 7751577 nanoseconds  
Ascending data sorting took 145825 nanoseconds  
Descending data sorting took 2432036 nanoseconds  
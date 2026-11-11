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

### Teoria
| Tapaus | Mitä tapahtuu | Operaatioita | Aikavaativuus |
|--------|---------------|----------------|----------------|
| Paras (nouseva) | Jokainen alkio on jo paikallaan, yksi vertailu per alkio | n | O(n) |
| Keskiverto (satunnainen) | Alkio siirtyy keskimäärin puoleen väliin alkuosaa: i/2 siirtoa | n²/4 | O(n²) |
| Huonoin (käänteinen) | Alkio siirtyy koko alkuosan läpi: i siirtoa | n²/2 | O(n²) |

1. Kun n kasvaa 10×, nouseva kasvaa noin 10× ja kaksi muuta noin 100×.
2. Käänteinen on aina noin 2× hitaampi kuin satunnainen. n²/2 vs n²/4

### Tulokset
| Tapaus | 10 000 | 100 000 | Kasvukerroin | Odotettu |
|--------|--------|---------|--------------|----------|
| Nouseva | 9,7 µs | 95,9 µs | 9,9× | 10× |
| Satunnainen | 6,58 ms | 653 ms | 99× | 100× |
| Käänteinen | 13,2 ms | 1303 ms | 98,7× | 100× |

Tulokset olivat teorian mukaisia.

## Bubble Sort
10 000  
Random data sorting took: 74956416 nanoseconds  
Ascending data sorting took 3066 nanoseconds  
Descending data sorting took 64569108 nanoseconds  

100 000  
Random data sorting took: 11554124081 nanoseconds  
Ascending data sorting took 35627 nanoseconds  
Descending data sorting took 6450656311 nanoseconds 

### Teoria
| Tapaus | Mitä tapahtuu | Vertailuja | Aikavaativuus |
|--------|---------------|-------------|----------------|
| Paras (nouseva) | Ensimmäisellä läpikäynnillä ei tule yhtään vaihtoa, lippu lopettaa heti | n | O(n) |
| Keskiverto (satunnainen) | Tarvitaan lähes kaikki n kierrosta, vaihtoja noin puolessa vertailuista | ≈ n²/2 (vaihtoja n²/4) | O(n²) |
| Huonoin (käänteinen) | Kaikki kierrokset tarvitaan, jokainen vertailu johtaa vaihtoon | n²/2 (vaihtoja n²/2) | O(n²) |

1. Kun n kasvaa 10×, nouseva kasvaa noin 10× ja kaksi muuta noin 100×.
2. Käänteisessä tehdään kaksi kertaa enemmän vaihtoja kuin satunnaisessa, joten teorian pohjalta odottaisi sen olevan hitaampi tai korkeintaan yhtä hidas.

### Tulokset
| Tapaus | 10 000 | 100 000 | Kasvukerroin | Odotettu |
|--------|--------|---------|--------------|----------|
| Nouseva | 3,1 µs | 35,6 µs | 11,6× | 10× |
| Satunnainen | 75,0 ms | 11 554 ms | 154× | 100× |
| Käänteinen | 64,6 ms | 6451 ms | 99,9× | 100× |

Tulokset teorian mukaisia?
- Nouseva ja käänteinen kyllä
- Satunnainen ei täysin. Kerroin 154× on enemmän kuin 100×, mutta O(n log n) olisi ollut noin 12×, joten algoritmi on selvästi O(n²). Mittaukset pitäisi olla tarkempia, mutta tulokset ovat riittävän lähellä teorian mukaisia.

## Array Sort
10 000  
Random data sorting took: 715180 nanoseconds  
Ascending data sorting took 22512 nanoseconds  
Descending data sorting took 34365 nanoseconds  

100 000  
Random data sorting took: 1025303 nanoseconds   
Ascending data sorting took 70182 nanoseconds  
Descending data sorting took 201931 nanoseconds  

### Teoria
| Tapaus | Mitä tapahtuu | Operaatioita (n = 10 000 / 100 000) | Aikavaativuus |
|--------|---------------|---------------------------------------|----------------|
| Paras (nouseva, käänteinen) | Koko taulukko tunnistetaan yhdeksi jaksoksi. Käänteinen vain käännetään. | 10⁴ / 10⁵ | O(n) |
| Keskiverto (satunnainen) | Dual-pivot quicksort | 1,33·10⁵ / 1,66·10⁶ | O(n log n) |
| Huonoin | Puhtaassa quicksortissa O(n²). Uudemmissa Java-versioissa heap sort -varamekanismi pitää sen luokassa O(n log n). | 1,33·10⁵ / 1,66·10⁶ | O(n log n) (versiosta riippuen) |

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
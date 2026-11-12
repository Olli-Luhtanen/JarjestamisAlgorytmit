# Tietorakenteet ja algorytmit 1. Harjoitustyö
## Järjestämisalgorytmit, Vertailu ja ajoitus

Raportti sisältää jokaisen algorytmin aika tulokset Satunnaisen, käänteisen ja nousevan datan järjestämisestä. Satunnaisessa käytetty tiettyä seed että tulokset olisivat vertailukelpoisia.

Vertailu tehtiin 10 000 ja 100 000 alkion koolla hitaimmille ja 100 000 ja 1 000 000 alkion koolla muille.   
30 kertaa ottaen mediaanin ajan mittauksesta lisäksi tarkistaen, että data on järjestyksessä (ei ajanmittauksessa mukana)  

1 000 000 alkion sort olisi kestänyt liian kauan hitailla sorteilla

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
100 000  
Random data sorting took: 8824400 nanoseconds  
Ascending data sorting took 39700 nanoseconds  
Descending data sorting took 95800 nanoseconds  

1 000 000  
Random data sorting took: 75480100 nanoseconds  
Ascending data sorting took 240500 nanoseconds  
Descending data sorting took 635900 nanoseconds  

### Teoria
| Tapaus | Mitä tapahtuu | Operaatioita (n = 100 000 / 1 000 000) | Aikavaativuus |
|--------|---------------|-----------------------------------------|----------------|
| Paras (nouseva, käänteinen) | Koko taulukko tunnistetaan yhdeksi jaksoksi. Käänteinen vain käännetään. | 10⁵ / 10⁶ | O(n) |
| Keskiverto (satunnainen) | Dual-pivot quicksort | 1,66·10⁶ / 1,99·10⁷ | O(n log n) |
| Huonoin | Puhtaassa quicksortissa O(n²). Vahvasti optimoitu joka pitää sen O(n log n). | 1,66·10⁶ / 1,99·10⁷ | O(n log n) |

1. Kun n kasvaa 10×, nouseva ja käänteinen kasvavat noin 10× ja satunnainen noin 12×.
2. Järjestyksen pitäisi olla: nouseva nopein, käänteinen seuraava, satunnainen hitain.

### Tulokset
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu |
|--------|---------|-----------|--------------|----------|
| Nouseva | 39,7 µs | 240,5 µs | 6,1× | 10× |
| Satunnainen | 8,82 ms | 75,5 ms | 8,6× | 12,0× |
| Käänteinen | 95,8 µs | 635,9 µs | 6,6× | 10× |

Tulokset teorian mukaisia?
- Järjestys on teorian mukainen.
- Kasvu ei ylitä odotettua missään tapauksessa. Satunnaisen 8,6× on selvästi O(n log n) -luokkaa (O(n²) olisi 100×).
- Nousevan ja käänteisen kasvu on alle 10×, mahdollisesti mittaus ei optimaalinen tai jokin muu tekijä mistä en osaa sanoa.

## Shell Sort
100 000  
Random data sorting took: 9768800 nanoseconds  
Ascending data sorting took 1169200 nanoseconds  
Descending data sorting took 1759400 nanoseconds   

1 000 000  
Random data sorting took: 121723800 nanoseconds  
Ascending data sorting took 14735300 nanoseconds  
Descending data sorting took 18484000 nanoseconds   

### Teoria
(Alla olevassa Shell sort matikassa ja taulukossa hyödynnetty tekoälyä)
| Tapaus | Mitä tapahtuu | Operaatioita (n = 100 000 / 1 000 000) | Aikavaativuus |
|--------|---------------|-----------------------------------------|----------------|
| Paras (nouseva) | Jokaisella aukolla yksi läpikäynti ilman siirtoja | 1,16·10⁶ / 1,42·10⁷ | ≈ aukkojen määrä · n |
| Keskiverto (satunnainen) | Riippuu aukkosarjasta | n log n ... n^1,25 | n log n ... n^1,5 |
| Huonoin | Riippuu aukkosarjasta | ≤ n² | yläraja O(n²) |

1. Nousevan odotettu kasvukerroin on 1,42·10⁷ / 1,16·10⁶ ≈ 12,3×.
2. Satunnaisen odotus on 12,0× (n log n) – 17,8× (n^1,25), ja selvästi alle 100×.

### Tulokset
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu |
|--------|---------|-----------|--------------|----------|
| Nouseva | 1,17 ms | 14,7 ms | 12,6× | 12,3× |
| Satunnainen | 9,77 ms | 121,7 ms | 12,5× | 12,0–17,8× |
| Käänteinen | 1,76 ms | 18,5 ms | 10,5× | ≈ 12,3× (vähintään) |

Tulokset teorian mukaisia? Kyllä.
- Kaikki osuu lähelle odotuksia.



# Quick Sort
Annettuun pohjaan tehty muutama optimaatio mitä löysin.
1. Pivotin valinta kolmen alkion mediaanilla.
2. Kun päästään pieniin taulukon kokoihin kuten alle 24 vaihdetaan yksinkertaisempaan algorytmiin kuten InsertionSort
3. Käytetään recursiota pienempään taulukon osaan ja silmukkaa suurempaan

### Alkuperäiset mittaukset
100 000  
Random data sorting took: 7415400 nanoseconds  
Ascending data sorting took 1411800 nanoseconds  
Descending data sorting took 1468900 nanoseconds   

1 000 000  
Random data sorting took: 88335600 nanoseconds  
Ascending data sorting took 16253000 nanoseconds  
Descending data sorting took 16951400 nanoseconds 

### Optimoinnin jälkeen
100 000  
Random data sorting took: 5045700 nanoseconds  
Ascending data sorting took 657800 nanoseconds  
Descending data sorting took 707900 nanoseconds 

1 000 000  
Random data sorting took: 64447500 nanoseconds  
Ascending data sorting took 9303400 nanoseconds  
Descending data sorting took 9151400 nanoseconds 

### Teoria
| Tapaus | Mitä tapahtuu | Operaatioita (n = 100 000 / 1 000 000) | Aikavaativuus |
|--------|---------------|-----------------------------------------|----------------|
| Paras | Pivot jakaa taulukon tasan | 1,66·10⁶ / 1,99·10⁷ | O(n log n) |
| Keskiverto (satunnainen) | Jaot ovat keskimäärin riittävän tasaisia | 1,66·10⁶ / 1,99·10⁷ | O(n log n) |
| Huonoin | Pivot on toistuvasti ääriarvo | 10¹⁰ / 10¹² | O(n²) |

1. Keskimmäinen alkio (ja mediaani-of-3) on järjestetyllä datalla aina mediaani, joten kahdessa testitapauksessa odotetaan O(n log n), eli noin 12×.
2. O(n²) näkyisi kasvuna 100×.
3. Optimointien (insertion sort, mediaani-of-3, rekursio pienempään osaan) pitäisi pienentää vakiokerrointa, ei kasvuluokkaa.

### Tulokset, alkuperäinen
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu |
|--------|---------|-----------|--------------|----------|
| Satunnainen | 7,42 ms | 88,3 ms | 11,9× | 12,0× |
| Nouseva | 1,41 ms | 16,3 ms | 11,5× | 12,0× |
| Käänteinen | 1,47 ms | 17,0 ms | 11,5× | 12,0× |

### Tulokset, optimoitu
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu | Nopeutus alkuperäiseen (100 000 / 1 000 000) |
|--------|---------|-----------|--------------|----------|------|
| Satunnainen | 5,05 ms | 64,4 ms | 12,8× | 12,0× | 1,5× / 1,4× |
| Nouseva | 0,66 ms | 9,30 ms | 14,1× | 12,0× | 2,1× / 1,7× |
| Käänteinen | 0,71 ms | 9,15 ms | 12,9× | 12,0× | 2,1× / 1,9× |

Tulokset teorian mukaisia? Kyllä.
- Alkuperäinen osuu odotukseen täsmälleen ja aika per n·log₂n pysyy samana satunnaisella datalla (4,46 → 4,43 ns). Pahin O(n²) ei toteudu, koska pivot on aina lähellä mediaania.
- Optimoitu kasvaa myös n log n -luokassa (12,8–14,1×). Nousevan 14,1× on hieman yli odotetun. Varmaan mahdollista mittausvirhettä tai muuta tekijää
- Optimointi nopeuttaa kaikissa tapauksissa 1,4–2,1×, mutta ei muuta kasvuluokkaa, kuten teoria ennustaa.


# Merge Sort
Annettuun pohjaan tehty muutama optimaatio mitä löysin.
1. Taas pienille osille käytetään insertSorttia
2. Aputaulukkona temp[] jolla vältytään kahden uuden taulun luontia joka merge kutsussa.
3. Valmiiksi järjestyksessä tarkistetaan ettei tule turhia merge kutsuja
4. Vain vasen puoli taulukosta kopioidaan ei oikeaa.

### Alkuperäiset mittaukset
100 000  
Random data sorting took: 10676000 nanoseconds  
Ascending data sorting took 3939400 nanoseconds
Descending data sorting took 4063200 nanoseconds 

1 000 000  
Random data sorting took: 129385700 nanoseconds  
Ascending data sorting took 47687700 nanoseconds  
Descending data sorting took 49153400 nanoseconds  

### Optimoinnin jälkeen
100 000  
Random data sorting took: 7995400 nanoseconds  
Ascending data sorting took 144600 nanoseconds  
Descending data sorting took 2561400 nanoseconds

1 000 000  
Random data sorting took: 103890600 nanoseconds  
Ascending data sorting took 2284000 nanoseconds  
Descending data sorting took 29019700 nanoseconds  

### Teoria
| Tapaus | Mitä tapahtuu | Operaatioita (n = 100 000 / 1 000 000) | Aikavaativuus |
|--------|---------------|-----------------------------------------|----------------|
| Alkuperäinen, kaikki tapaukset | Jokainen taso yhdistää kaikki alkiot | 1,66·10⁶ / 1,99·10⁷ | Θ(n log n) |
| Optimoitu, nouseva | `a[m] <= a[m+1]` ohittaa kaikki yhdistämiset, jäljellä vain pienten lohkojen insertion sort | 10⁵ / 10⁶ | O(n) |
| Optimoitu, satunnainen ja käänteinen | Yhdistämiset tehdään normaalisti | 1,66·10⁶ / 1,99·10⁷ | Θ(n log n) |

1. Alkuperäisen kaikkien tapausten odotettu kasvu on noin 12×.
2. Optimoidun nousevan odotettu kasvu on noin 10×.
3. Käänteisellä datalla insertion sort on pahimmillaan, mutta lohkon koko on vakio (alle 24), joten kasvuluokka pysyy Θ(n log n).

### Tulokset, alkuperäinen
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu |
|--------|---------|-----------|--------------|----------|
| Satunnainen | 10,7 ms | 129,4 ms | 12,1× | 12,0× |
| Nouseva | 3,94 ms | 47,7 ms | 12,1× | 12,0× |
| Käänteinen | 4,06 ms | 49,2 ms | 12,1× | 12,0× |

### Tulokset, optimoitu
| Tapaus | 100 000 | 1 000 000 | Kasvukerroin | Odotettu | Nopeutus alkuperäiseen (100 000 / 1 000 000) |
|--------|---------|-----------|--------------|----------|------|
| Satunnainen | 8,00 ms | 103,9 ms | 13,0× | 12,0× | 1,3× / 1,2× |
| Nouseva | 145 µs | 2,28 ms | 15,8× | 10× | 27× / 21× |
| Käänteinen | 2,56 ms | 29,0 ms | 11,3× | 12,0× | 1,6× / 1,7× |

Tulokset teorian mukaisia?
- Alkuperäinen: kyllä, täsmälleen. Kaikki kolme tapausta kasvavat 12,1×, ja aika per n·log₂n pysyy samana.
- Optimoitu satunnainen ja käänteinen: kyllä (13,0× ja 11,3×).
- Optimoitu nouseva: nopeutus on selvä (21–27×), mutta kasvu 15,8× on enemmän kuin odotetut 10× eikä aineisto yksin todista O(n):ää. Syy saattaa olla mittausvirhettä tai muuta häiriötä tai ehkä jotain muistin tekemisiä. En osaa tarkalleen sanoa.


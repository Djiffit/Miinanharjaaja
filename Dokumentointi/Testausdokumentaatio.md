<b>Testausdokumentaatio</b>

Pelin logiikka on lähes täysin testattu sillä sen toiminta on hyvin pitkälti tyyppiä avaa tyhjän ruudun ja ei avaa lukittua ruutua, joten pelin logiikka on täysin automaattisesti testattavaa. 

Muu osa ohjelmasta jäi sitten lähes täysin manuaalisen testin varaan, etenkin hiirenpainalluksen tarkkuuden testaaminen vei hyvin paljon aikaa, ja jopa täydellisesti sopivilla ruudukoilla se ei ole 100% tarkkaa. Muutenkin käyttöliittymäkomponenttien sijoittelu ja skaalaus resoluution mukaan vei paljon aikaa.

Myös pistelistan testaus jäi manuaalisiin töihin, sillä sen testaaminen automaattisesti ei tuntunut järkevältä.

<b> Mahdollisia bugeja </b>

Pistelistan täytyy olla samassa kansiossa kuin .jar tiedoston tai se ei toimi.

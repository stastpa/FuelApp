# Tankuj Levně

Firma Tankuj Levně vyvinula aplikaci, pomocí které se snaží lidem v této nelehké době ušetřit peníze tím, že jim ukáže **čerpací stanice** v okolí. Kromě *adresy* a *státu*, kde se daná *čerpací stanice* nachází a *firmy*, která tuto stanici vlastní, lidi zajímá především cena daného **Paliva**. **Záznamy** o ceně paliva přidávají samotní **uživatelé**, kteří ovšem mohou záměrně zadávat nepravdivé informace. Z tohoto důvodu mají uživatelé možnost přidat *hodnocení* (like a dislike) na dané záznamy. Hodnocení všech záznamů daného uživatele tvoří hodnocení uživatele. Dále je potřeba znát _datum_ přidání záznamu, aby lidé nejeli na danou stanici zbytečně.

# Business operace
přidávání liků/disliků na záznamy, případně pokud by nějaký uživatel měl až moc dislikes, dostane nějaké omezení na přidávání záznamů (s tím se tedy pojí i kontrola, zda má v danou dobu povolení přidávat záznamy).

# Dotaz nad rámec CRUD
Vyfiltrování nejlevnějších čerpacích stanic, v daném období na dané lokaci.

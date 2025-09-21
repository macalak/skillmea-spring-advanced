- Deklaratívne
    + Ľahko sa používa tým že oanotujeme príslušnú metódu ktorá implementuje biznisovú logiku úlohy
    + Ideálne pre opakujúce sa úlohy statického charakteru
    + Spring Boot nakonfiguruje všetko potrebné za nás. Nemusíme využívať API Spring komponentov.
    - Menej flexibilná varianta.
    - Ťažšia modifikácia za runtime.
    - Nemáme kntrolu nad thread managementom, čomu sa ale väčšinou chceme vyhnúť.

- Programové
    + Flexibilita
    + Vieme volaním prerušiť vykonávanie úlohy - pokiaľ nie je blokovaná
    + Scheduling na základe meniacich sa podmienok
    - Potreba pochopenia ako veci fungujú interne vo frameworku
    - Vyššia komplexita implementácie
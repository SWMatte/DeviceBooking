# DeviceBooking

# installation 

Il progetto è stato creato in JAVA v.17 inizializato utilizzando  Spring Inizializr,   utilizzando queste dipendenze: Spring web, MYSQL DRIVER, Spring Data JPA with framework Hibernate.

<kbd> Setting </kbd>
- è importante andare ad impostare nell'application properties le dipendeze relative ad hibernate ed SQL :

  > spring.datasource.url=jdbc:mysql://localhost:3306/ **name database**

  > spring.datasource.username=root

  > spring.datasource.password= **insert your password**

  > spring.jpa.hibernate.ddl-auto=update

  > spring.jpa.show-sql=true


-  è possibile copiare il repository da > git clone https://github.com/DeviceBooking.git

- è importante installare una versione di POSTMAN per poter effettuare le chiamate REST 

## Scopo dell'applicazione?

L'applicazione, a scopo di esercitazione, è stata creata con lo scopo di poter monitorare quali abbonamenti le aziende logistiche comprano e a quali veicoli vengono associati.
 Ecco un approfondimento sulle classi dell'applicazione:

 -  ClientSub : la classe si occupa di monitorare  quali abbonamenti sono associati alle aziende e se quest'ultimi siano stati associati o meno a dei veicoli, in più tiene traccia dello stato di attivazione dell'abbonamento.
 -  Device : la classe registra un device specifico, il quale dovra' essere associato o meno ad un abbonamento.
 -  LogistiClient : tiene traccia delle compagnie logistiche.
 -  Subscription : questa classe rappresenta gli abbonamenti associati ad un device, tiene conto della data di attivazione e di termine una volta che viene associata ad un cliente.
 -  Vehicle : la classe tiene conto dei veicoli associati alle aziende con i rispettivi device.

> approfondimento sulle classi:
  - alcune di esse lavorano accoppiate, nel senso che se aggiungo un abbonamento devo associarlo ad un device.
  - aggiungendo un cliente associo ad esso degli abbonamenti che permetteranno di aggiornare la tabella subscription con la data di attivazione e di termine.
  - è possibile inoltre andare ad aggiornare tutti gli abbonamenti scaduti aggiornandoli con " EXPIRATED " 


# futuri miglioramenti

1. Migliorare le query con la possibilita' di mostrare tutti gli abbonamenti associati ad un cliente , senza quelli scaduti. 
2. Mostrare al momento dell'acquisto, il costo relativo agli abbonamenti.
3. Impostare una classe assicurazione che mi permetta di abilitare o meno i veicoli.
   Qualsiasi nuova implementazione è ben accetta.

       

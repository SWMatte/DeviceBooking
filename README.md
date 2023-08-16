# DeviceBooking
<kbd> If you download the collection of postman the sequence of calls are:
 1.  add device
 2.  add subscription
 3.  add client
 4.  add vehicle
 </kbd>
# installation 

The project was created in JAVA v.17 and initialized using Spring Initializr, with the following dependencies:

> Spring web

>  MYSQL DRIVER

> Spring Data JPA with framework Hibernate.

<kbd> Setting </kbd>

- It is important to configure the Hibernate and SQL dependencies in the application properties:
  
  > spring.datasource.url=jdbc:mysql://localhost:3306/ **name database**

  > spring.datasource.username=root

  > spring.datasource.password= **insert your password**

  > spring.jpa.hibernate.ddl-auto=update

  > spring.jpa.show-sql=true


-  You can clone the repository from > git clone  https://github.com/DeviceBooking.git

- It is necessary to install a version of POSTMAN to make REST calls.

## Purpose of the application?

The application, created for training purposes, aims to monitor the subscriptions that logistic companies purchase and associate with vehicles. Here's an overview of the application's classes:

-   ClientSub: This class monitors which subscriptions are associated with companies and whether they have been linked to vehicles. Additionally, it tracks the activation status of the subscription.
-  Device: This class records a specific device, which may or may not be associated with a subscription.
-  LogisticClient: This class keeps track of logistic companies.
-  Subscription: Represents subscriptions associated with a device. It keeps track of the activation and expiration dates once associated with a client.
-  Vehicle: This class keeps track of vehicles associated with companies, along with their respective devices.

> In-depth about the classes:

-  Some of them work in pairs, meaning that when adding a subscription, it needs to be associated with a device.
-  When adding a client, subscriptions can be linked to update the subscription table with activation and expiration dates.
-  It is also possible to update all expired subscriptions by marking them as "EXPIRED".


# Future Improvements

1. Enhance queries to allow displaying all subscriptions associated with a client, excluding expired ones.
2. Show the cost of subscriptions at the time of purchase.
3. Implement an insurance class to enable or disable vehicles.
Any new implementations are welcome.

       

# transactions-task

##Task description:

1. Add new entity to the application - UserAccount, it should store the amount of prepaid money user has in the system, which should be used during booking procedure. Add methods for refilling the account to the BookingFacade class. Add DAO and service objects for new entity. Add ticketPrice field to Event entity.

2. Update ticket booking methods to check and withdraw money from user account according to the ticketPrice for particular event. 

3. Configure appropriate PlatformTransactionManager implementation in Spring application context. 

4. Make ticket booking methods transactional using Spring declarative transactions management (either xml or annotation based config).
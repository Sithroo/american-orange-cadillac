# American Orange Cadillac
#### A blueprint of Event-Driven Microservices

American Orange Cadillac is a simple accounting system, which exposes an API for opening "current account" of already existing customers.
The API accepts information like customerId and initialCreadit
Once the API is called, a new account will be opened connected to the customerId
Also, a new transaction is sent to the new account if the initialCreadit is not 0
Another API is exposed to get account information with its transactions.
We assume American Orange Cadillac has a large customer base and is still growing with new accounts and customers. And the system continuously requires to support all type of concurrent transactions for their accounts. Considering the fact that requirement for reliable, and scalable transactions handling system with high availability, the following microservices architecture is proposed.

### Architecture Overview
The system is divided into two main services, AccountService and TransactionService. 
![Alt text](docs/aoc.jpg?raw=true "Title")

The mains architecture reasonings behind the separation are;
AccountService and TransactionService have unique scalability requirement. That is, the system should allow onboarding new accounts, and should not be affected by the performance of transaction handling.
TransactionService could be used by other parts of the system, which are only communicated over the events. 
AccountService publishes transaction event, which eventually recorded in the TranscationServices.

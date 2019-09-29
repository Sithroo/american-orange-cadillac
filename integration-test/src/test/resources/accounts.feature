Feature: Existing users can create accounts in the system. When a user
  creates an account with an initial amount, the system makes
  a separate transaction for that. The initial amount will eventually
  become the account balance. The users can also create accounts
  with no initial amount.

  Scenario: The customer create an account with an initial amount
    When the cusotmer sunil_perera creates an account with initial amount 1000.98
    Then sunil_perera gets created account with one transaction amount 1000.98

  Scenario: The customer create an account with no initial amount
    When the cusotmer don_lal creates an account with no initial amount
    Then don_lal gets created account with no transactions
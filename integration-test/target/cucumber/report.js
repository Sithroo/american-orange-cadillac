$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/accounts.feature");
formatter.feature({
  "line": 1,
  "name": "Existing users can create accounts in the system. When a user",
  "description": "creates an account with an initial amount, the system makes\na separate transaction for that. The initial amount will eventually\nbecome the account balance. The users can also create accounts\nwith no initial amount.",
  "id": "existing-users-can-create-accounts-in-the-system.-when-a-user",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 7,
  "name": "The customer create an account with an initial amount",
  "description": "",
  "id": "existing-users-can-create-accounts-in-the-system.-when-a-user;the-customer-create-an-account-with-an-initial-amount",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "the cusotmer sunil_perera creates an account with initial amount 1000.98",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "sunil_perera gets created account with one transaction amount 1000.98",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "sunil_perera",
      "offset": 13
    },
    {
      "val": "1000.98",
      "offset": 65
    }
  ],
  "location": "AccountFeatureSteps.the_customer_create_an_account(String,Double)"
});
formatter.result({
  "duration": 1015774422,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "sunil_perera",
      "offset": 0
    },
    {
      "val": "1000.98",
      "offset": 62
    }
  ],
  "location": "AccountFeatureSteps.the_customer_gets_created_account_and_transaction(String,Double)"
});
formatter.result({
  "duration": 328754702,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "The customer create an account with no initial amount",
  "description": "",
  "id": "existing-users-can-create-accounts-in-the-system.-when-a-user;the-customer-create-an-account-with-no-initial-amount",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "the cusotmer don_lal creates an account with no initial amount",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "don_lal gets created account with no transactions",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "don_lal",
      "offset": 13
    }
  ],
  "location": "AccountFeatureSteps.the_customer_create_an_account_no_amount(String)"
});
formatter.result({
  "duration": 26095968,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "don_lal",
      "offset": 0
    }
  ],
  "location": "AccountFeatureSteps.the_customer_gets_created_account_and_no_transaction(String)"
});
formatter.result({
  "duration": 13756471,
  "status": "passed"
});
});
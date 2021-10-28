# AccountService
The BeardTrust microservice for handling bank accounts.

#Endpoints:

"/accounts"
  -the primary mapping from which all others stem
  -GET this endpoint requests the user's id as a parameter and returns a list of all accounts attached to said user
  -POST this endpoint expects a NewAccountRequestModel and is used for creating an account from the AccountRegistration page.
  -DELETE this endpoint expects a body of id and parameter of userId, and sets the account to 'deactivated'
  -PUT this endpoint expects an UpdateAccountRequest, primarily used for processing account transactions

".../new"
  -GET has no expectations and returns a new, essentially empty account

".../all"
  -GET expects standard Page parameters, and returns a Pageable list of all accounts in the database

".../{id}"
  -GET expects a pathvariable of id and returns that specific account
  -PUT expects a pathvariable of id and a body of TransferEntity to change the amount of money in the account
  -DELETE expects a path variable of id and parameter of userId, removes the account entirely.

".../recovery/{id}"
  -PUT expects a path variable of id, a body of AccountEntity, and a parameter of userId, changes the recovery status of the account

".../transactions/{id}"
  -GET expects path variable of userId, parameter of a search, and a Pageable object. returns a list of account transactions
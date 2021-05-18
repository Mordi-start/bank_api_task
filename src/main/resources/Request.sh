curl --header 'Content-Type: application/json' --request POST --data '{"gettingAccountId": "2", "sendAccountId": "1", "income": "99.36"}' http://localhost:8080/doTransaction
#curl --header 'Content-Type: application/json' --request POST --data '{"type": "DEB", "paymentSystem": "MIR", "accountId": "3"}' http://localhost:8080/createCardForAccount

#curl --header 'Content-Type: application/json' --request POST --data '{"balance": "900000.36", "id": "1"}' http://localhost:8080/updateBalance

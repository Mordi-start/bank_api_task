curl --header 'Content-Type: application/json' --request POST --data '{"sendCardId": "1", "gettingCardId": "8", "value": "23335.36"}' http://localhost:8080/client/doTransaction
#curl --header 'Content-Type: application/json' --request POST --data '{"accountId": "6"}' http://localhost:8080/client/createCardForAccount

#curl --header 'Content-Type: application/json' --request POST --data '{"value": "9999", "cardId": "2"}' http://localhost:8080/client/addMoney
#curl --header 'Content-Type: application/json' --request POST --data '{"value": "99999", "cardId": "2"}' http://localhost:8080/client/withdrawMoney
#curl --header 'Content-Type: application/json' --request POST --data '{"name": "NewUser", "surname": "surnameUs", "enabled": "true"}' http://localhost:8080/admin/addNewUser

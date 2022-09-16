# POSTMAN TESTING ROUTES
___________________________________ 



### USERS ROUTES

SIGNUP
 - POST: http://localhost:8080/northern/users/signup

```bash

 {
    "username" : "osman8080",
    "password1" : "osman8080",
     "password2" : "osman8080",
    "email": "osman8080@gmail.com",
    "given_name": "Mahmoud",
    "surname" : "Osman"

    
}
 
```

  GET ALL USERS
 - GET: http://localhost:8080/northern/users




### USER ROLES ROUTE

ADD NEW USER ROLE

- POST: http://localhost:8080/northern/users/roles


```bash

 {
    "role_id": "305",
    "role": "MARKETING"
}
 
```


GET ALL USERS ROLES

 - GET: http://localhost:8080/northern/users/roles

DELETE USER ROLE 
- DELETE:  http://localhost:8080/northern/users/roles

GET USER ROLE BY ID

- GET: http://localhost:8080/northern/users/roles



### REIMBURSEMENT TYPES ROUTE
 
ADD NEW REIMBURSEMENT TYPE

- POST: http://localhost:8080/northern/types


```bash

 {
    "type_id" : "100",
    "type": "CELLPHONE PAYMENT"
}


```


GET LIST OF REIMBURSEMENT TYPES 
- GET: http://localhost:8080/northern/types



DELETE REIMBURSEMENT TYPE BY ID

- DELETE: http://localhost:8080/northern/types


UPDATE REIMBURSEMENT TYPE
- UPDATE: http://localhost:8080/northern/types



### REIMBURSEMENT STATUS ROUTES

ADD NEW REIMBURSEMENT STATUS

- POST: http://localhost:8080/northern/types/status

```bash

{
"amount": "80.65",
"description": "CELLPHONE  PAYMENT",
"receipt": "PAID",
"payment_id":"400",
"author_id": "6652d73b-c01a-4213-82e1-b1ac579e3682",
"resolver_id": "6652d73b-c01a-4213-82e1-b1ac579e3682",
"status_id": "300",
"type_id":"202"

}


```



GET ALL REIMBURSEMENTS

- GET: http://localhost:8080/northern/types/status












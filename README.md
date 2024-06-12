# What It Does
This project's aim is to deliver a test framework for the PetStore API - https://petstore3.swagger.io/. It covers a range of endpoints including all CRUD operations, tests also cover happy and sad paths. Please refer to our project board below for further information: 

[![Link](https://github.com/users/ConnerHumphrey-SpartaGlobal/projects/1)]

# Setup Notes
Clone this repo to your IDE and install dependencies listed in pom.xml.

Note: a `config.properties` file is excluded from the resources folder for security reasons. Please contact the contributors to fetch a copy.

Here are the fields that should be in your config.properties:

api_url

api_token

pet_path

petByStat_path

petByTag_path

petById_path

petIMG

storeInv_path

storeOrder_path

storeByOrderId_path

user_path

userWithList_path 

userLogin_path 

userLogout_path 

userByUsername 

# Enpoints tested

Overall we tested 13/16 of the endpoints that are available with the API, these endpoints have been deemed the most important to bussiness operations and thus must be completed in the first sprint. The endpoints that have been tested are listed bellow with their functions:

/user - Create User

/user/login - Login User

/user/logout - Logout User

/user/{username} - updating User

/user/{username} - Deleting User

/store/order - Placing an order for a pet

/store/order/{orderId} - Find purchase order by id

/pet - Updating an existing pet

/pet - Add a new pet to store

/pet/{petId} - Find pet by id

/pet/{petId} - Deleting a pet

/pet/{petId} - Updating a pet in the store with form data

/pet/findByStatus - Finding pets by status

# Credits
Designed by Room 2 - Sparta Global Java Test Automation 2024:
* [Tomasz Wolak](https://github.com/BykuTom)
* [Connor Humphreys](https://github.com/ConnerHumphrey-SpartaGlobal)
* [Logan Marshall](https://github.com/RazorWinds)
* [Steven Diep](https://github.com/stevrnd)

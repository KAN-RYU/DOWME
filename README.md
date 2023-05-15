# DOWME
UNIST 2023S1 CSE36401 Software Engineering Group 10

20171260 JunGu Han 한준구
20181014 YoungJin Kwon 권영진

***

Milestone 2 Done

***

## SSH Authorization before running "run.sh"
In accessing to Github URL, we are using SSH URL (git@github.com:KAN-RYU/DOWME.git).
So when you start a Docker image created from the Dockerfile and run.sh, you will need a SSH key to get access to Git.

Before running 'sh run.sh', please create and authorize the SSH Key of the Docker image, by doing the following.

```
root@<containerID>:~/project# ssh-keygen -t rsa
root@<containerID>:~/project# vi ~/.ssh/id_rsa.pub
```

Then, copy the content of vim editor, as SSH Key. Upload the SSH Key in your 'Setting' in github account.
After SSH Key is uploaded in your personal account, our github repository will be accessable.

***

## Bus Information

Get All Bus Ids.

`curl -X GET http://localhost:8080/bus/`

Get Time table of given bus id

`curl -X GET http://localhost:8080/bus/times/304(Yul-li)`

Get 10 Bus IDs depart soon.

`curl -X GET http://localhost:8080/bus/depart/1600`

Add duplicate bus time ignored.

`curl -X POST http://localhost:8080/bus -H 'Content-type:application/json' -d '{"busId": "304(Yul-li)", "time": "0850"}'`

Add new Bus time

`curl -X POST http://localhost:8080/bus -H 'Content-type:application/json' -d '{"busId": "304(Yul-li)", "time": "2100"}'`

Add new Bus time

`curl -X POST http://localhost:8080/bus -H 'Content-type:application/json' -d '{"busId": "777(Lucky)", "time": "0700"}'`

Add new bus time with wrong time retrieve error

`curl -X POST http://localhost:8080/bus -H 'Content-type:application/json' -d '{"busId": "777(Lucky)", "time": "0788"}'`

Delete Bus time data

`curl -X DELETE http://localhost:8080/bus/304(Yul-li)/0850`

***
## Meal Information

Get Meal data from date

`curl -X GET http://localhost:8080/meal/230501`

Get Meal data from date and time

`curl -X GET http://localhost:8080/meal/230501/Lunch`

Get Meal data from date and time and restaurant

`curl -X GET http://localhost:8080/meal/230501/Lunch/Dormitory`

Get Meal data with specific menu

`curl -X GET http://localhost:8080/meal/search/Iced+Tea`

Can Update or Add Menu

`curl -X PUT http://localhost:8080/meal -H 'Content-type:application/json' -d '{"date": "230512", "time": "Lunch", "category": "Korean", "restaurant": "Dormitory", "menu": "Corn rice / Cream soup / Chicken Cheese Cutlet & Sauce / Spaghetti Salad / COKE / Kimchi"}'`

***

## Attendance Notification User

Add New Users

`curl -X POST http://localhost:8080/attendance -H 'Content-type:application/json' -d '{"guid": "facdfe", "userId": "1"}'`

`curl -X POST http://localhost:8080/attendance -H 'Content-type:application/json' -d '{"guid": "abefff", "userId": "2"}'`

Add Lecture to user

`curl -X POST http://localhost:8080/attendance/2/4`

`curl -X POST http://localhost:8080/attendance/2/5`

Get user data

`curl -X GET http://localhost:8080/attendance/2`
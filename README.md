How this app works:
The app runs with in-build memory db h2, everytime app shuts down, the db would be cleaned up.
The csv data is loaded once off when app starts with InitializingBean.

#1, endpoint to return all the food turcks as json format: http://localhost:8080/foodtrucks/api/list
#2, get one specific truck: http://localhost:8080/foodtrucks/api/get/{locationid}
#3, find nearby food trucks for source address: http://localhost:8080/foodtrucks/api/address/{address}, adress corresponding to same column of the data file.
#4, the command line to get all truck names could be composed with exsiting java file.


what could be better:
1, db should be commercial one than the memory db for regular run.
2, when searching for nearby trucks, the current drawback is exact match for the source address with user's input from url, but few could remember the full address,
	ideally is providing a UI with smart-suggestion for user to select from cadidate source addresses containing leading letters of user's input.

3, additional feature may be searching by food, could be done with full text search by elasticsearch in food item column.
	

note:
Please use jdk 8 rather than 20 when you run it. there is jdk version conflicts.

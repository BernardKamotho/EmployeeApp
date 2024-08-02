from flask import *
from flask_restful import Api, Resource

# Database Connection function
import pymysql
import pymysql.cursors

def db_connection():
    return pymysql.connect(host='localhost', user='root', password='', database='employees_api_db')

# Start
app = Flask(__name__)
api = Api(app)

# 1. UserRegister
class UserRegister(Resource):
    def post(self):
        # step1: DB Connection and Cursor()
        connection = db_connection()
        cursor = connection.cursor()

        # Step2: Request Data
        data = request.json
        user_email = data['user_email']
        user_name = data['user_name']
        user_phone = data['user_phone']
        user_password = data['user_password']

        # Step3: Write SQL and Pass data
        sql = "insert into users(user_email, user_name, user_phone, user_password) values (%s, %s, %s, %s)"
        data = (user_email, user_name, user_phone, user_password)

        # Step4: Execute Cursor and Return Response(json)
        cursor.execute(sql, data)
        connection.commit()
        return jsonify({'response': 'User Registred Successfully'})

class UserLogin(Resource):
    def get(self):
        # step1: DB Connection and Cursor()
        connection = db_connection()
        cursor = connection.cursor(pymysql.cursors.DictCursor)

        # Step2: Request Data
        data = request.json
        user_email = data['user_email']
        user_password = data['user_password']

        # Step3: SQL(Verify Email and Password)
        sql = "select * from users where user_email = %s and user_password = %s"
        data = (user_email, user_password)

        # Step4: Execute SQL
        cursor.execute(sql, data)

        # Verification
        count = cursor.rowcount
        if count == 0:
            return jsonify({'response': 'Invalid Credentials'})
        else:
            user = cursor.fetchone()
            return jsonify({
                'response': 'Successful',
                'user': user
            })


# Adding an employee
class AddEmployee(Resource):
    def post(self):
        # step1: DB Connection and Cursor()
        connection = db_connection()
        cursor = connection.cursor()

        # Step2: Request Json Data
        data =request.json
        id_number = data["id_number"]
        username = data["username"]
        others = data["others"]
        salary = data["salary"]
        department = data["department"]

        # Step3: Write SQL and Pass data
        sql = "INSERT INTO `employees` (`id_number`, `username`, `others`, `salary`, `department`) VALUES (%s, %s, %s, %s, %s)"
        
        # use the try and exept method

        data = (id_number, username, others, salary, department)
        cursor.execute(sql, data)
        connection.commit()
        return jsonify({"message": "Employee registered succefully"})
    
# retrieve all the employees
class AllEmployees(Resource):
    def get(self):
         # step1: DB Connection and Cursor()
        connection = db_connection()
        cursor = connection.cursor(pymysql.cursors.DictCursor)

        # structure the sql to retrieve the employees
        sql = "SELECT * from employees"

        # execute the sql by use of the cursor
        cursor.execute(sql)

        # check whether there exist any records
        if cursor.rowcount == 0:
            return jsonify({"Message" : "No records found"})
        else:
            employees = cursor.fetchall()
            return jsonify(employees)
        

# update one column of the employee (salary)
class UpdateEmployee(Resource):
    def put(self):
        # step1: DB Connection and Cursor()
        connection = db_connection()
        cursor = connection.cursor()

        #step 2: Structure the update query
        sql = "UPDATE `employees` SET `salary`=%s WHERE `id_number` = %s "

        # pick the data from json
        data = request.json
        salary = data["salary"]
        id_number = data["id_number"]

        data = (salary, id_number)

        # use the cursor to execute the sql
        cursor.execute(sql, data)
        connection.commit()
        return jsonify({"Message" : "Salary has been updated successfully"})

# Deleting an employee
class DeleteEmployee(Resource):
    def delete(self):
        connection = db_connection()
        cursor = connection.cursor()

        # pick the employee id number from json data
        data = request.json
        id_number = data["id_number"]

        # structure the sql query for delete
        sql = "DELETE FROM `employees` WHERE id_number = %s"
        cursor.execute(sql, id_number)
        connection.commit()
        return jsonify({"Message" : "Employee deleted successfully"})

# Endpoints
api.add_resource(UserRegister, '/user_register')
api.add_resource(UserLogin, '/user_login')
api.add_resource(AddEmployee, '/add_employee')
api.add_resource(AllEmployees, '/all_employees')
api.add_resource(UpdateEmployee, '/update_employee')
api.add_resource(DeleteEmployee, '/delete_employee')



app.run(debug=True)
# Stops
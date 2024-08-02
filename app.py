# To Create we normally use any backend technology 
# E,g Flask, Django, Expressjs, Springbboot(java), laravel(php)

# Step1: Create a Database
# Step2: Write the Business Logic
# Implement the API methods.
# flask_restful
# Insomnia, Postman: Testing APIs

from flask import *
from flask_restful import Api, Resource

# starts
app = Flask(__name__)
api = Api(app)

# NB: The Application will not render_templates
# Rather Return JSON DATA is responses(jsonify())
# The Data returned can be used y Different clients
# The json data has a format that can be understood by any client
# The API can implement four different methods

# POST: Send data(json)
# GET: Receive Data(json)
# PUT: Modify/Update
# DELETE: Remove an existing data

# Endpoint: Its like a route to specify the path folled to access a resource e,g /post_resource -> ReourcePost

# Sample Resources
class ResourcePost(Resource):
    def post(self):
        return jsonify({'response': 'Resource Post is Triggered'})
    

class ResourceGet(Resource):
    def get(self):
        return jsonify({'response': 'Resource Get is Triggered'})
    
    
class ResourcePut(Resource):
    def put(self):
        return jsonify({'response': 'Resource Put is Triggered'})
    
    
class ResourceDelete(Resource):
    def delete(self):
        return jsonify({'response': 'Resource Delete is Triggered'})


# Adding Endpoints for every Resource
api.add_resource(ResourcePost, '/post_resource')
api.add_resource(ResourceGet, '/get_resource')
api.add_resource(ResourcePut, '/put_resource')
api.add_resource(ResourceDelete, '/delete_resource')

app.run(debug=True)
# Stops
# BASE_URL: The actual URL to Access the Endpoints

# STATUS CODES:
# 1. 200 : OK(Success)
# 5. 201: Created
# 2. 404 : Client Side(The Resource is Not Found)
# 3. 405 : Client Side(Method is Not Alowed)
# 4. 500 : Sever Side (Error in the Serve)


# Valkyrie
Valkyrie is a programming language designed for writing client-server applications without having to coordinate the client and the server.

In every client/server application you have to build an API for the client and the server to communicate with each other. This is becoming more true in reactive applications. 

In the good old days, if you wanted to have an HTML page with some stuff from your database, you could just write an HTML page and open PHP brackets <? and write code to retrieve whatever you want and render it with the HTML page ?>. Since then the difficulty has increased substantially. To do that now you have to 
1. Write an HTML page
2. Choose a way for the client to communicate to the server ( GraphQL? REST? )
3. Design an API 
4. Implement it 
5. Test it
6. Debug it until it works
7. Hope that stuff doesn't change too much

Valkyrie allows you to describe the connection between the client and server via language constructs. For example, you can define "server functions" and invoke them from the client. The compiler will generate a server and a client that know how to talk to each other.

## Demo
Source repl: https://repl.it/@noapi/valkyrie#.replit

Description:

## Documentation
### Server Functions
Server functions can be defined using the ```defs``` special form. They are functions that run on the server but can be invoked from the client. They are the equivalent of a REST API call.

##### Valkyrie Example:
```clojure
(defs server-function []
  "This function run in the server")
 
(defn app []
  (server-function #((.-log js/console) (:body %)))
  "HELLO WORLD")
```

In this example the client will render a react component that renders HELLO WORLD. The react component will asynchronouslly call a server function called "server-function" which will run on the server and return "This function run in the server" to the client. The client will in turn console log "This function run in the server".
___

### Daemon Functions
Daemon functions that run on the server can be setup and defined using the ```startd``` special form. This keyword is followed by a function name, a string containing a cron expression and a function body.

##### Valkyrie Example:
```clojure
(startd daemon-func
    "* * * ? * *"		;; cron expression
    (println "in-daemon-func"))	;; function body
```
This Valkyrie code snippet will setup a daemon function called ```daemon-func``` and run the function body every second on the server. This will result in ```"in-daemon-func"``` being printed on the console every second.
___


Team: @noapi

Team Members: @abdulbahajaj @natalianzp

Github: https://github.com/abdulbahajaj/valkyrie

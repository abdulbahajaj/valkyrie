# Valkyrie
Valkyrie is a programming language designed for writing client-server applications without having to coordinate the client and the server.

In every client/server application you have to build an API for the client and the server to communicate with each other. This is becoming more true in reactive applications. 

In the good old days, if you wanted to have an HTML page with some stuff from your database, you could just write an HTML page and open PHP brackets <? and write code to retrieve whatever you want and render it with the HTML page ?>. Since then the difficulty has increase substantially. To do that now you have to 
1. Write an HTML page
2. Choose a way for the client to communicate to the server ( GraphQL? REST? )
3. Design an API 
4. Implement it 
5. Test it
6. Debug it until it works
7. Hope that stuff don't change too much

Valkyrie allows you to describe the connection between the client and server via language constructs. For example, you can define "server functions" and invoke them from the client. The compiler will generate a server and a client that know how to talk to each other.

## Demo
Source repl: https://repl.it/@noapi/valkyrie#.replit

Description:

## Documentation
### Server Functions
defs

defchan

___

### Daemon Functions
Daemon functions can be setup and defined using the ```startd``` keyword. This keyword is followed by a function name, a string containing a cron expression and a function body.

##### Valkyrie Example:
```
(startd daemon-func
    "* * * ? * *"		;; cron expression
    (println "in-daemon-func"))	;; function body
```
This Valkyrie code snippet will setup a daemon function called ```daemon-func``` and run the function body every second. This will result in ```"in-daemon-func"``` being printed on the server console every second.
___


Team: @noapi

Team Members: @abdulbahajaj @natalianzp

Github: https://github.com/abdulbahajaj/valkyrie

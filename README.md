# Valkyrie
Valkyrie is a programming language designed for writing client-server applications without the redundant API code. The language is built on top of Clojure on the server side and ClojureScript on the client side.

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
#!/bin/sh

# Set to true if running in repl.it
replit=true

# Compiler entry point
rm -rf out
git clone https://github.com/abdulbahajaj/valk-runtime.git out
clj valkyrie.clj $@

if [ "$replit" = true ] ; then
  install-pkg leiningen
  rm /home/runner/.apt/usr/bin/lein
  cp /home/runner/valkyrie/lein /home/runner/.apt/usr/bin/lein
  chmod a+x /home/runner/.apt/usr/bin/lein
fi

cd out
make build_server 
make run_server

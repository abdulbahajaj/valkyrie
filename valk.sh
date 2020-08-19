#!/bin/sh

# Compiler entry point
rm -rf out
git clone https://github.com/abdulbahajaj/valk-runtime.git out
clj valkyrie.clj $@
cd out && make build_server

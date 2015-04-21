#!/usr/bin/bash


rm -rf /tmp/iosr/frontend
cp -r frontend /tmp/iosr/
forever start /tmp/iosr/frontend/app.js

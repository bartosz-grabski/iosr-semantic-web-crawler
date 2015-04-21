#!/usr/bin/bash


rm -rf /tmp/iosr/frontend
cp -r frontend /tmp/iosr/frontend
forever start /tmp/iosr/frontend/app.js

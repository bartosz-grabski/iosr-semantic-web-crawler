#!/usr/bin/bash


rm -rf /var/iosr/frontend
cp -r frontend /var/iosr/
forever start /var/iosr/frontend/app.js

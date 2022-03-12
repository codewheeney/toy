#!/bin/bash

java -cp $(ls target/*.jar | tr '\n' ':')  org.example.App $1 $2 

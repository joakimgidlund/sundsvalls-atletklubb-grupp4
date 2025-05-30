#!/bin/bash

echo "==== Sundsvalls Atletklubb - Instructions ==="
echo " "
echo "1. Download the project from GitHub: https://github.com/joakimgidlund/sundsvalls-atletklubb-grupp4"
echo "2. Extract the zip-folder"
echo "3. Navigate to the project root"
echo "4. If you want to run this script and the project you need to have Maven installed"
echo "5. Run this script by typing ./group4-script.sh"
echo "Building the project with Maven..."
mvn clean javafx:run

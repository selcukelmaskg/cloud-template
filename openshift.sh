#!/usr/bin/env bash

printf "Welcome to openshift deploy wizard... \n\n"

# Check for oc login
oc status > /dev/null 2>&1
if [[ $? -eq 0 ]]; then
		PS3='Please enter your choice: '
		options=(\
			"First Deploy"\
			"Re-Deploy"\
			"Quit"
			)
		select opt in "${options[@]}"
		do
			case ${opt} in
						"First Deploy")
            printf "\n\nYou choose First Deploy 😊 \n\n"

						# Get project info from user
            printf "Please enter project name: "
            read projectName
						printf "name is: ${projectName}\n\n"

						printf "Please enter project description: "
						read projectDescription
						printf "description is: ${projectDescription}\n\n"

						printf "Please enter project display name: "
						read projectDisplayName
						printf "display name is: ${projectDisplayName}\n\n\n"

						printf "******************************\n"
						printf "Creating project with this configuration: \n"
						printf "Project Name: ${projectName}\n"
						printf "Project Description:${projectDescription} \n"
						printf "Project Display Name:${projectDisplayName} \n"

						# Create new openshift project with above configuration
						oc new-project ${projectName} --description=${projectDescription} --display-name=${projectDisplayName} > /dev/null 2>&1
						if [[ $? -eq 0 ]]; then
								printf "Project created successfuly 🎉🎉🎉🎉\n\n"
								oc project ${projectName} > /dev/null 2>&1
						else
								echo Project creation failed! Aborting...
								break;
						fi
            ;;
						"Re-Deploy")
								printf "You choose Re-Deploy"
								;;
						"Quit")
								break
								;;
        *) printf "invalid option $REPLY";;
			esac
		done
else
		printf "Firstly you have to login openshift via cli.\n"
		echo "Please open your openshift dashboard, copy login command and paste in terminal 😇"
		exit 1 # send exit signal
fi
